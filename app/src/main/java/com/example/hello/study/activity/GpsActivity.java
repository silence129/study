package com.example.hello.study.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello.study.R;
import com.example.hello.study.service.GpsReportService;

import java.util.List;

public class GpsActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int PERMISSIONS_GPS = 100;

    private TextView textView;
    private Button button_start_service;
    private Button button_stop_service;

    private LocationManager locationManager;
    private String provider;

    public static void startActivity(Context context) {

        Intent intent = new Intent(context, GpsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        textView = (TextView) findViewById(R.id.text_show);
        button_start_service = (Button) findViewById(R.id.button_start_service);
        button_stop_service = (Button) findViewById(R.id.button_stop_service);

        button_start_service.setOnClickListener(this);
        button_stop_service.setOnClickListener(this);

        getLastKnownLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == PERMISSIONS_GPS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastKnownLocation();
            } else {
                Toast.makeText(this, "No permission.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.removeUpdates(locationListener);
        }
    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle
                extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }
    };

    private void getLastKnownLocation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_GPS);
            return;
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providerList = locationManager.getProviders(true);

        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "No location provider to use", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 0);
            return;
        }

        Toast.makeText(this, "get gps ...", Toast.LENGTH_SHORT).show();
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            showLocation(location);
        }
        locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
    }

    private void showLocation(Location location) {
        String currentPosition = "latitude is " + location.getLatitude() + "\n"
                + "longitude is " + location.getLongitude();
        location.getSpeed();
        textView.setText(currentPosition);
    }

    /**
     * 返回查询条件
     *
     * @return
     */
    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        //设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //设置是否要求速度
        criteria.setSpeedRequired(false);
        // 设置是否允许运营商收费
        criteria.setCostAllowed(false);
        //设置是否需要方位信息
        criteria.setBearingRequired(false);
        //设置是否需要海拔信息
        criteria.setAltitudeRequired(false);
        // 设置对电源的需求
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return criteria;
    }
    private GpsReportService.GpsReportBinder gpsReportBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            gpsReportBinder = (GpsReportService.GpsReportBinder)service;
            gpsReportBinder.startReport();
            gpsReportBinder.getLastKnownLocation();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_start_service:
                Intent startService = new Intent(this,GpsReportService.class);
                startService(startService);
                break;
            case R.id.button_stop_service:
                Intent stopService = new Intent(this,GpsReportService.class);
                stopService(stopService);
                break;
        }

    }
}
