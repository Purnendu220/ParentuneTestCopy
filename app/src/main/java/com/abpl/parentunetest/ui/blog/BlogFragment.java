package com.abpl.parentunetest.ui.blog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.abpl.parentunetest.R;
import com.abpl.parentunetest.adapter.BlogAdapter;
import com.abpl.parentunetest.adapter.QuestionAdapter;
import com.abpl.parentunetest.databinding.FragmentDashboardBinding;
import com.abpl.parentunetest.databinding.FragmentHomeBinding;
import com.abpl.parentunetest.interfaces.AdapterCallbacks;
import com.abpl.parentunetest.models.BlogModel;
import com.abpl.parentunetest.models.QuestionModel;
import com.abpl.parentunetest.ui.BaseFragment;

import java.util.List;

public class BlogFragment extends BaseFragment<FragmentHomeBinding> implements AdapterCallbacks<BlogModel> {

    private BlogViewModel blogViewModel;
    private int page = 1;
    private BlogAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        blogViewModel = ViewModelProviders.of(this).get(BlogViewModel.class);
        blogViewModel.setmContext(mContext);
        blogViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return bindView(FragmentHomeBinding.inflate(inflater,container,false)).getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
        blogViewModel.getBlogs().observe(getViewLifecycleOwner(), blogModels -> {
            if(blogModels.size()>0){
                adapter.addAllData(blogModels);
                adapter.notifyDataSetChanged();
            }else{
                adapter.loaderDone();
            }

        });
        blogViewModel.getBlogsFromRepo(page);

    }

    private void initRecyclerView(){
        dataBinding.blogsList.setLayoutManager(new LinearLayoutManager(mContext));
        dataBinding.blogsList.setHasFixedSize(false);
        try {
            ((SimpleItemAnimator) dataBinding.blogsList.getItemAnimator()).setSupportsChangeAnimations(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter = new BlogAdapter(mContext,true,this);
        dataBinding.blogsList.setAdapter(adapter);

    }

    @Override
    public void onAdapterItemClick(RecyclerView.ViewHolder viewHolder, View view, BlogModel model, int position) {

    }

    @Override
    public void onAdapterItemLongClick(RecyclerView.ViewHolder viewHolder, View view, BlogModel model, int position) {

    }

    @Override
    public void onShowLastItem() {
        page++;
        blogViewModel.getBlogsFromRepo(page);

    }

    }
