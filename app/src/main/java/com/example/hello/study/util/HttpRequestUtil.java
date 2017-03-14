package com.example.hello.study.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 54353 on 2017/1/18.
 */

public class HttpRequestUtil {

    private static final String TAG = "HttpRequestUtil";

    public static String get(String address) {

        HttpURLConnection connection = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);

            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            String text = response.toString();
            Log.d(TAG, text);

            return text;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static String post(String address, Map<String, Object> parameters) {

        HttpURLConnection connection = null;

        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();

            StringBuffer params = new StringBuffer();
            Iterator it = parameters.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry element = (Map.Entry) it.next();
                params.append(element.getKey());
                params.append("=");
                params.append(element.getValue());
                params.append("&");
            }
            if (params.length() > 0) {
                params.deleteCharAt(params.length() - 1);
                outputStream.write(params.toString().getBytes());
            }

            connection.connect();
            InputStream inputStream = null;
            BufferedReader reader = null;

            String result = null;
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                result = reader.readLine();
                Log.d(TAG, result);
            }
            reader.close();
            inputStream.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
