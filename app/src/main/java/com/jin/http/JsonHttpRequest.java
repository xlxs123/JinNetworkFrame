package com.jin.http;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonHttpRequest implements IHttpRequest {
    private String url;
    private byte[] params;
    private IHttpListener iHttpListener;

    @Override

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setParams(byte[] params) {
        this.params = params;
    }


    @Override
    public void setListener(IHttpListener listener) {
        this.iHttpListener = listener;
    }


    @Override
    public void execute() {
        OkHttpClient okHttpClient = new OkHttpClient();
        try {

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    InputStream in = response.body().byteStream();
                    iHttpListener.onSuccess(in);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
