package com.example.hello.study.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello.study.util.HttpRequestUtil;
import com.example.hello.study.R;
import com.example.hello.study.model.ApiResponseModel;
import com.example.hello.study.model.Expert;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class JsonActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int UPDATE_TEXT = 1;
    public static final int TOAST = 2;
    private static final String TAG = "JsonActivity";

    private TextView textView;
    private EditText editText;
    private Button button_get;
    private Button button_post;
    private Button button_test;

    public static void startActivity(Context context) {

        Intent intent = new Intent(context, JsonActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_json);

        editText = (EditText) findViewById(R.id.text_url2);
        textView = (TextView) findViewById(R.id.text_content);
        button_get = (Button) findViewById(R.id.button_browser2);
        button_post = (Button)findViewById(R.id.button_browser3);
        button_test = (Button)findViewById(R.id.button_browser4);

        button_get.setOnClickListener(this);
        button_post.setOnClickListener(this);
        button_test.setOnClickListener(this);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case UPDATE_TEXT:
                    String text = (String) message.obj;
                    textView.setText(text);
                    break;
                case TOAST:
                    Toast.makeText(JsonActivity.this,message.obj.toString(),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {

        final String text = editText.getText().toString();

        switch (v.getId()){
            case R.id.button_browser2:
                Toast.makeText(JsonActivity.this, text, Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String response = HttpRequestUtil.get(text);
                        if(null != response){

                            Gson gson = new Gson();
                            ApiResponseModel<Expert> model = gson.fromJson(text,
                                    new TypeToken<ApiResponseModel<Expert>>(){}.getType());
                            if(model != null){

                                Expert expert = model.getData();
                                Log.d(JsonActivity.class.getSimpleName(), expert.getName());
                                Log.d(JsonActivity.class.getSimpleName(), expert.getAddress());

                                Message message = new Message();
                                message.what = UPDATE_TEXT;
                                message.obj = response.toString();
                                handler.sendMessage(message);
                            }
                        }
                    }
                }).start();
                break;
            case R.id.button_browser3:
                Toast.makeText(JsonActivity.this, text, Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Map<String,Object> values = new HashMap<String, Object>();
                        values.put("username","admin");
                        values.put("password","admin12345");
                        String response = HttpRequestUtil.post(text,values);
                        if(null != response){
                            Message message = new Message();
                            message.what = UPDATE_TEXT;
                            message.obj = response;
                            handler.sendMessage(message);
                        }
                    }
                }).start();
                break;
            case R.id.button_browser4:
                testForm(this);
                break;
        }
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    protected  void testForm(final JsonActivity jsonActivity){
        //new Thread(networkTask).start();
        String url = "http://192.168.16.40:8080/tech-service/api/v1/account/updateSubscription";

        OkHttpClient client = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("accountId","73");
        builder.add("tags","test");
        RequestBody body = builder.build();

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            String content = responseBody.string();
            Log.e(TAG, "onResponse: " + content);

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: " + e.toString());
                    Message message = new Message();
                    message.what = TOAST;
                    message.obj = e.toString();
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    if(response == null){
                        Log.e(TAG, "response is null....");
                        return;
                    }

                    ResponseBody responseBody = response.body();
                    String content = responseBody.string();
                    Log.e(TAG, "onResponse: " + content);

                    Message message = new Message();
                    message.what = TOAST;
                    message.obj = content;
                    handler.sendMessage(message);
                }
            });
        }
        catch (Exception e){
            Log.e(TAG, "testForm: " + e.toString());
        }
    }


    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            String url = "http://192.168.16.40:8080/tech-service/api/v1/account/updateSubscription";

            OkHttpClient client = new OkHttpClient();

            FormBody.Builder builder = new FormBody.Builder();
            builder.add("accountId","73");
            builder.add("tags","test");
            RequestBody body = builder.build();

            final Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .header("User-Agent", "OkHttp Headers.java")
                    .addHeader("Accept", "application/json; q=0.5")
                    .addHeader("Accept", "application/vnd.github.v3+json")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                ResponseBody responseBody = response.body();
                String content = responseBody.string();
                Log.e(TAG, "onResponse: " + content);

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "onFailure: " + e.toString());
                        Message message = new Message();
                        message.what = TOAST;
                        message.obj = e.toString();
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if(response == null){
                            Log.e(TAG, "response is null....");
                            return;
                        }

                        ResponseBody responseBody = response.body();
                        String content = responseBody.string();
                        Log.e(TAG, "onResponse: " + content);

                        Message message = new Message();
                        message.what = TOAST;
                        message.obj = content;
                        handler.sendMessage(message);
                    }
                });
            }
            catch (Exception e){
                Log.e(TAG, "testForm: " + e.toString());
            }

        }
    };

}
