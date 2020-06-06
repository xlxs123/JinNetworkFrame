package com.jin.http;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

public class HttpTask<T> implements Runnable {
    private IHttpRequest iHttpRequest;
    private IHttpListener iHttpListener;

    public HttpTask(String url, T requestData, IHttpRequest iHttpRequest, IHttpListener iHttpListener) {
        this.iHttpRequest = iHttpRequest;
        this.iHttpListener = iHttpListener;
        this.iHttpRequest.setUrl(url);
        this.iHttpRequest.setListener(iHttpListener);

        if (requestData != null) {
            String dataStr = JSON.toJSONString(requestData);
            try {
                this.iHttpRequest.setParams(dataStr.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        this.iHttpRequest.execute();
    }
}
