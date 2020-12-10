package com.abpl.parentunetest.interfaces;

public interface RequestListener<T> {

        void onApiSuccess(T response, int requestCode);

        void onApiFailure(String errorMessage, int requestCode);
    }