package com.abpl.parentunetest.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.abpl.parentunetest.interfaces.RequestListener;
import com.abpl.parentunetest.models.BlogModel;
import com.abpl.parentunetest.network.ApiServices;
import com.abpl.parentunetest.network.ResponseModel;
import com.abpl.parentunetest.network.RestClient;
import com.abpl.parentunetest.utils.AppConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogsRepository {
    private static BlogsRepository blogsRepository;
    private Context mContext;
    private ApiServices mApiService;

    private BlogsRepository(Context context) {
        this.mContext = context;
        this.mApiService = RestClient.getRetrofitClient(AppConstants.BASE_URL).create(ApiServices.class);

    }

    public static BlogsRepository getInstance(Context context) {
        if (blogsRepository == null) {
            blogsRepository = new BlogsRepository(context);
        }
        return blogsRepository;
    }
    public Call<ResponseModel<List<BlogModel>>> getBlogs(int page, RequestListener callback){
        Call<ResponseModel<List<BlogModel>>> call = mApiService.fetchBlogs(page);
        call.enqueue(new Callback<ResponseModel<List<BlogModel>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<BlogModel>>> call, Response<ResponseModel<List<BlogModel>>> response) {
                         callback.onApiSuccess(response.body().data,AppConstants.REQUEST_CODE_BLOGS);
            }

            @Override
            public void onFailure(Call<ResponseModel<List<BlogModel>>> call, Throwable t) {
                   callback.onApiFailure(t.getMessage(),AppConstants.REQUEST_CODE_BLOGS);
            }
        });

    return call;
    }

}
