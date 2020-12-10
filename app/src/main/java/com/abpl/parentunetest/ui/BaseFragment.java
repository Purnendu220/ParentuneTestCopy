package com.abpl.parentunetest.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public class BaseFragment<B extends ViewBinding> extends Fragment {
    protected B dataBinding;
    public Context mContext;
    protected B bindView(B dataBinding) {
        this.dataBinding = dataBinding;
        return this.dataBinding;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext =getActivity();


    }

}
