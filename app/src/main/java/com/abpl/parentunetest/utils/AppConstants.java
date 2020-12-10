package com.abpl.parentunetest.utils;

public interface AppConstants {
    int API_CONNECTION_TIMEOUT = 45;
    int API_READ_TIMEOUT = 45;
    int REQUEST_CODE_BLOGS = 1;
    int REQUEST_CODE_QUESTION = 2;

    String BASE_URL="http://35.200.248.239/testapi/";
     interface ApiParamKey{
        String PAGE="page_count";
        String BLOG_URL ="blogs";
         String QUESTIONS_URL="questions";


     }
}
