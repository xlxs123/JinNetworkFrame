package com.jin.http;

public interface IDataListener<T> {
    void onSuccess(T t);

    void onFailure();
}
