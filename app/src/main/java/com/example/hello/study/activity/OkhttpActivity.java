package com.example.hello.study.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hello.study.R;
import com.example.hello.study.model.VgomcPostData;
import com.example.hello.study.model.VgomcPostDataValue;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "OkhttpActivity";

    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static void startActivity(Context context) {

        Intent intent = new Intent(context, OkhttpActivity.class);
        context.startActivity(intent);
    }

    public static final int UPDATE_TEXT = 1;
    public static final int TOAST = 2;

    Button button_vgomc;
    Button button_vgomc_raw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        button_vgomc = (Button) findViewById(R.id.button_okhttp_vgomc);
        button_vgomc_raw = (Button)findViewById(R.id.button_okhttp_vgomc_raw);

        button_vgomc.setOnClickListener(this);
        button_vgomc_raw.setOnClickListener(this);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {

            String text = message.obj.toString();

            switch (message.what) {
                case UPDATE_TEXT:
                    break;
                case TOAST:
                    Toast.makeText(OkhttpActivity.this, text, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_okhttp_vgomc:
                vgomcPost("http://192.168.16.40:8081/api/v1/vgomc-data");
                break;

            case R.id.button_okhttp_vgomc_raw:
                vgomcPost("http://192.168.16.40:8081/api/v1/raw");
                break;
        }
    }

    protected void vgomcPost(final String url) {

        final String url2 = "http://192.168.16.40:8081/api/v1/vgomc-data";

        VgomcPostDataValue v = new VgomcPostDataValue();
        v.setChannelId("channlId");
        v.setValue("23.22");
        v.setFormatValue(v.getValue());
        v.setVarDate("2011-11-22 22:11:01");
        v.setVarId("varId");
        v.setVarName("varName");
        v.setVarUnit("varUnit");

        VgomcPostData data = new VgomcPostData();
        data.setCompanyName("test company");
        data.setDeviceId("deviceId");
        data.setDeviceName("deviceName");
        data.setDevicePointx("pointx");
        data.setDevicePointy("pointy");

        List<VgomcPostDataValue> values = new ArrayList<>();
        values.add(v);
        data.setDataValues(values);

        Gson gson = new Gson();
        final String jsonString = gson.toJson(data);

        Log.i(TAG, jsonString);

        new Thread(new Runnable() {
            @Override
            public void run() {

                RequestBody body = RequestBody.create(JSON, jsonString);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();

                Message message = new Message();
                message.what = TOAST;
                String log = jsonString;

                try {
                    //message.obj = log;
                    //handler.sendMessage(message);

                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseValue = null;
                        responseValue = response.body().string();
                        log = "response :" + responseValue;
                        Log.i(TAG, log);
                    } else {
                        log = "error";
                        Log.e(TAG, log);
                    }
                } catch (IOException e) {
                    log = e.toString();
                }

                message.obj = log;
                handler.sendMessage(message);
            }
        }).start();
    }
}
