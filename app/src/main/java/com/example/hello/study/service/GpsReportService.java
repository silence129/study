package com.example.hello.study.service;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.hello.study.R;
import com.example.hello.study.activity.GpsActivity;
import com.example.hello.study.util.HttpRequestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GpsReportService extends Service {

    private static final String TAG = "GpsReportService";

    public GpsReportService() {
    }

    private GpsReportBinder reportBinder = new GpsReportBinder();

    public class GpsReportBinder extends Binder {

        private static final int PERMISSIONS_GPS = 100;
        private LocationManager locationManager;
        private String provider;
        private Location lastLocation;

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
                lastLocation = location;
                publicLocation();
            }
        };

        public void getLastKnownLocation() {

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            List<String> providerList = locationManager.getProviders(true);

            if (providerList.contains(LocationManager.GPS_PROVIDER)) {
                provider = LocationManager.GPS_PROVIDER;
            } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
                provider = LocationManager.NETWORK_PROVIDER;
            } else {
                Log.d(TAG, "No location provider to use");
                return;
            }

            if (ActivityCompat.checkSelfPermission(GpsReportService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GpsReportService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                lastLocation = location;
                publicLocation();
            }
            locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
        }

        public void startReport(){
            getLastKnownLocation();
        }

        public Location publicLocation() {

            if(lastLocation != null){

                final String url = "http://123.127.160.69:10006/delivery/api/gpsdata/report";

                final Map<String ,Object> parameters = new HashMap<>();
                parameters.put("lat",lastLocation.getLatitude());
                parameters.put("lng",lastLocation.getLongitude());
                parameters.put("gpsCode","101");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String response = HttpRequestUtil.post(url,parameters);
                        Log.d(TAG, "reponse :" + response);
                    }
                }).start();

                return lastLocation;
            }
            return null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return reportBinder;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        Log.d(TAG, "onCreate");

        Intent intent = new Intent(this, GpsActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("GPS Reporter")
                .setContentTitle("GPS Report")
                .setContentText("GPS now is reporting...")
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("GPS");
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand");
        reportBinder.getLastKnownLocation();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
