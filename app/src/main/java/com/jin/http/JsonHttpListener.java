package com.jin.http;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonHttpListener<T> implements IHttpListener {

    private Class<T> response = null;
    private IDataListener iDataListener;
    private Handler handler = new Handler(Looper.getMainLooper());

    public JsonHttpListener(Class<T> response, IDataListener iDataListener) {
        this.response = response;
        this.iDataListener = iDataListener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        // had data
        String content = getContent(inputStream);
        try {
//            final T responseObject = JSON.parseObject(content, response);
            final T responseObject = null;
            handler.post(() -> iDataListener.onSuccess(responseObject));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onFailure() {

    }

    private String getContent(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line = null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
