package com.abpl.parentunetest.ui.question;

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
import com.abpl.parentunetest.adapter.QuestionAdapter;
import com.abpl.parentunetest.databinding.FragmentDashboardBinding;
import com.abpl.parentunetest.databinding.FragmentHomeBinding;
import com.abpl.parentunetest.interfaces.AdapterCallbacks;
import com.abpl.parentunetest.models.QuestionModel;
import com.abpl.parentunetest.ui.BaseFragment;

import java.util.List;

public class QuestionFragment extends BaseFragment<FragmentDashboardBinding> implements AdapterCallbacks<QuestionModel> {

    private QuestionViewModel questionViewModel;
    private QuestionAdapter adapter;
    private int page = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        questionViewModel =
                ViewModelProviders.of(this).get(QuestionViewModel.class);
        questionViewModel.setmContext(mContext);
        questionViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if(isLoading){
                showLoading();
            }else{
                hideLoading();
            }
        });
        return bindView(FragmentDashboardBinding.inflate(inflater,container,false)).getRoot();    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
        questionViewModel.getQuestions().observe(getViewLifecycleOwner(), questionModels -> {
            if(questionModels.size()>0){
                adapter.addAllData(questionModels);
                adapter.notifyDataSetChanged();
            }else{
                adapter.loaderDone();
            }
            checkEmpty();

        });
        questionViewModel.getQuestionsFromRepo(page);

    }

    private void initRecyclerView(){
        dataBinding.questionList.setLayoutManager(new LinearLayoutManager(mContext));
        dataBinding.questionList.setHasFixedSize(false);
        try {
            ((SimpleItemAnimator) dataBinding.questionList.getItemAnimator()).setSupportsChangeAnimations(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter = new QuestionAdapter(mContext,true,this);
        dataBinding.questionList.setAdapter(adapter);
        checkEmpty();
    }

    @Override
    public void onAdapterItemClick(RecyclerView.ViewHolder viewHolder, View view, QuestionModel model, int position) {

    }

    @Override
    public void onAdapterItemLongClick(RecyclerView.ViewHolder viewHolder, View view, QuestionModel model, int position) {

    }

    @Override
    public void onShowLastItem() {
        page++;
        questionViewModel.getQuestionsFromRepo(page);

    }
    void checkEmpty() {
        dataBinding.textEmpty.setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }
}