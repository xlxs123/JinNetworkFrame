package com.jin.http;

public interface IHttpRequest {
    void setUrl(String url);

    void setParams(byte[] params);

    void execute();

    void setListener(IHttpListener listener);
}
