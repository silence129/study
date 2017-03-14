package com.example.hello.study.util;

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}