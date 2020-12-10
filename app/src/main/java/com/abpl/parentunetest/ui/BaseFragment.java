package com.abpl.parentunetest.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public class BaseFragment<B extends ViewBinding> extends Fragment {
    protected B dataBinding;
    public Context mContext;
    private ProgressDialog mProgressDialog;

    protected B bindView(B dataBinding) {
        this.dataBinding = dataBinding;
        return this.dataBinding;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext =getActivity();


    }
    public void showLoading() {
        hideLoading();
        mProgressDialog = showLoadingDialog();
    }
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }
    public  ProgressDialog showLoadingDialog() {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        //   progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

}
