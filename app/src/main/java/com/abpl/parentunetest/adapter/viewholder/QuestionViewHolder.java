package com.abpl.parentunetest.adapter.viewholder;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.abpl.parentunetest.databinding.ViewQuestionItemBinding;
import com.abpl.parentunetest.interfaces.AdapterCallbacks;
import com.abpl.parentunetest.models.BlogModel;
import com.abpl.parentunetest.models.QuestionModel;
import com.bumptech.glide.Glide;


public class QuestionViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    ViewQuestionItemBinding itemBinding;
    public QuestionViewHolder(ViewQuestionItemBinding itemBinding) {
        super(itemBinding.getRoot());

        context = itemBinding.getRoot().getContext();
        this.itemBinding = itemBinding;
    }

    public void bind(Object model, final AdapterCallbacks adapterCallbacks,int position) {
        QuestionModel data = (QuestionModel) model;
        Glide.with(context).load(data.getExpertavatar()).into(itemBinding.imageViewUser);
        itemBinding.textViewUserName.setText(data.getExpertname());
        itemBinding.textViewDesignation.setText(data.getExpertdesignation());
        itemBinding.textViewQuestion.setText(data.getQuestion());
        itemBinding.textViewAnswer.setText(data.getAnswer());
    }
}