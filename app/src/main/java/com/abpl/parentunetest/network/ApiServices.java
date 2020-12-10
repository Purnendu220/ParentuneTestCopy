package com.abpl.parentunetest.network;

import com.abpl.parentunetest.models.BlogModel;
import com.abpl.parentunetest.models.QuestionModel;
import com.abpl.parentunetest.utils.AppConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServices {

    @GET(AppConstants.ApiParamKey.BLOG_URL + "/{" + AppConstants.ApiParamKey.PAGE + "}")
    Call<ResponseModel<List<BlogModel>>> fetchBlogs(@Path(AppConstants.ApiParamKey.PAGE) int page);

    @GET(AppConstants.ApiParamKey.QUESTIONS_URL + "/{" + AppConstants.ApiParamKey.PAGE + "}")
    Call<ResponseModel<List<QuestionModel>>> fetchQuestions(@Path(AppConstants.ApiParamKey.PAGE) int page);



}
