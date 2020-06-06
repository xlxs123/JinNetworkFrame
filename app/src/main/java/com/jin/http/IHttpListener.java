package com.jin.http;

import java.io.InputStream;

public interface IHttpListener {
    void onSuccess(InputStream inputStream);

    void onFailure();
}
