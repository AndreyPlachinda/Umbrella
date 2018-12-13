package com.example.mvpexample.Presenter;

public interface DataCallback<T> {
    void onResponse(T data);
    void onServerError();
    void onInternetError();
}
