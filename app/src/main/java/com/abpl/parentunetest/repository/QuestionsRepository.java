package com.abpl.parentunetest.repository;

import android.content.Context;

import com.abpl.parentunetest.interfaces.RequestListener;
import com.abpl.parentunetest.models.BlogModel;
import com.abpl.parentunetest.models.QuestionModel;
import com.abpl.parentunetest.network.ApiServices;
import com.abpl.parentunetest.network.ResponseModel;
import com.abpl.parentunetest.network.RestClient;
import com.abpl.parentunetest.utils.AppConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsRepository {
    private static QuestionsRepository questionsRepository;
    private Context mContext;
    private ApiServices mApiService;

    private QuestionsRepository(Context context) {
        this.mContext = context;
        this.mApiService = RestClient.getRetrofitClient(AppConstants.BASE_URL).create(ApiServices.class);

    }

    public static QuestionsRepository getInstance(Context context) {
        if (questionsRepository == null) {
            questionsRepository = new QuestionsRepository(context);
        }
        return questionsRepository;
    }
    public Call<ResponseModel<List<QuestionModel>>> getQuestions(int page,RequestListener callback){
        Call<ResponseModel<List<QuestionModel>>> call = mApiService.fetchQuestions(page);
        call.enqueue(new Callback<ResponseModel<List<QuestionModel>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<QuestionModel>>> call, Response<ResponseModel<List<QuestionModel>>> response) {
                callback.onApiSuccess(response.body().data,AppConstants.REQUEST_CODE_QUESTION);
            }

            @Override
            public void onFailure(Call<ResponseModel<List<QuestionModel>>> call, Throwable t) {
          callback.onApiFailure(t.getMessage(),AppConstants.REQUEST_CODE_QUESTION);
            }
        });

        return call;
    }

}
