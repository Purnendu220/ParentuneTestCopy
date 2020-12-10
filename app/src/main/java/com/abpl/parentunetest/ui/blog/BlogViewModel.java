package com.abpl.parentunetest.ui.blog;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.abpl.parentunetest.interfaces.RequestListener;
import com.abpl.parentunetest.models.BlogModel;
import com.abpl.parentunetest.repository.BlogsRepository;
import com.abpl.parentunetest.utils.AppConstants;

import java.util.List;

public class BlogViewModel extends ViewModel implements  RequestListener {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<BlogModel>> mBlogs = new MutableLiveData<>();
    private Context mContext;

    public BlogViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<List<BlogModel>> getBlogs() {
        return mBlogs;
    }

    public void getBlogsFromRepo(int page){
        BlogsRepository.getInstance(mContext).getBlogs(page,this);
    }



    @Override
    public void onApiSuccess(Object response, int requestCode) {
        switch (requestCode){
            case AppConstants.REQUEST_CODE_BLOGS:
                List<BlogModel> data = (List<BlogModel>) response;
                    mBlogs.setValue(data);
                    break;

        }
    }

    @Override
    public void onApiFailure(String errorMessage, int requestCode) {
        switch (requestCode){
            case AppConstants.REQUEST_CODE_BLOGS:

                break;

        }
    }
}