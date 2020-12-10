package com.abpl.parentunetest.ui.question;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abpl.parentunetest.interfaces.RequestListener;
import com.abpl.parentunetest.models.BlogModel;
import com.abpl.parentunetest.models.QuestionModel;
import com.abpl.parentunetest.network.ResponseModel;
import com.abpl.parentunetest.repository.BlogsRepository;
import com.abpl.parentunetest.repository.QuestionsRepository;
import com.abpl.parentunetest.utils.AppConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionViewModel extends ViewModel implements RequestListener {

    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<Boolean>();
    private Context mContext;
    private MutableLiveData<List<QuestionModel>> mQuestions = new MutableLiveData<>();;

    public QuestionViewModel() {

    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
    public LiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }
    public LiveData<List<QuestionModel>> getQuestions() {
        return mQuestions;
    }


    public void getQuestionsFromRepo(int page){
        if(page==1){
            mIsLoading.setValue(true);
        }
        QuestionsRepository.getInstance(mContext).getQuestions(page,this);
    }

    @Override
    public void onApiSuccess(Object response, int requestCode) {
        switch (requestCode){
            case AppConstants.REQUEST_CODE_QUESTION:
                List<QuestionModel> data = (List<QuestionModel>) response;
                    mQuestions.setValue(data);
                mIsLoading.setValue(false);



                break;

        }
    }

    @Override
    public void onApiFailure(String errorMessage, int requestCode) {
        switch (requestCode){
            case AppConstants.REQUEST_CODE_QUESTION:
                mIsLoading.setValue(false);


                break;

        }
    }
}