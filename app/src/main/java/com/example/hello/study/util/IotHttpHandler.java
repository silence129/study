package com.example.hello.study.util;

import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.example.hello.study.activity.JsonActivity;
import com.example.hello.study.model.ApiResponseModel;
import com.example.hello.study.model.Expert;
import com.example.hello.study.model.Factor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 54353 on 2017/2/6.
 */

public class IotHttpHandler {

    private static final String TAG = "IotHttpHandler";

    public static final String BASE_URL = "http://123.127.160.69:10006/iot2/easyapi/";

    public static List<Factor> getFactores(){

        final String url = BASE_URL + "factor/readAll";
        final List<Factor> list = new ArrayList<>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String response = HttpRequestUtil.get(url);
                if(null != response){

                    Gson gson = new Gson();
                    List<Factor> model = gson.fromJson(response,
                            new TypeToken<List<Factor>>(){}.getType());
                    if(model != null){
                        Log.d(TAG, "get factor size:" + model.size());
                        list.addAll(model);
                    }
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "return factor size:" + list.size());
        return list;
    }
}
