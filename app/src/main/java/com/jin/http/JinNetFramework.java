package com.jin.http;

public class JinNetFramework {
    public static <T, M> void sendJsonRequest(String url, T requestParams,
                                              Class<T> response, IDataListener iDataListener) {
        IHttpRequest iHttpRequest = new JsonHttpRequest();
        IHttpListener iHttpListener = new JsonHttpListener<>(response, iDataListener);
        HttpTask httpTask = new HttpTask(url, requestParams, iHttpRequest, iHttpListener);
        ThreadManager.getInstance().addTask(httpTask);
    }

}
