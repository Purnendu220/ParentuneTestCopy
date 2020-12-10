package com.abpl.parentunetest.adapter.viewholder;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.abpl.parentunetest.databinding.ViewBlogItemBinding;
import com.abpl.parentunetest.interfaces.AdapterCallbacks;
import com.abpl.parentunetest.models.BlogModel;
import com.bumptech.glide.Glide;


public class BlogViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    ViewBlogItemBinding itemBinding;
    public BlogViewHolder(ViewBlogItemBinding itemBinding) {
        super(itemBinding.getRoot());

        context = itemBinding.getRoot().getContext();
        this.itemBinding = itemBinding;
    }

    public void bind(Object model, final AdapterCallbacks adapterCallbacks,int position) {
        BlogModel data = (BlogModel) model;
        Glide.with(context).load(data.getImage()).into(itemBinding.imageViewBlog);
        Glide.with(context).load(data.getUseravatar()).into(itemBinding.imageViewUser);
        itemBinding.textViewUserName.setText(data.getUsername());
        itemBinding.textViewAgeGroup.setText(data.getAgegroup());
        itemBinding.textViewTitle.setText(data.getTitle());



    }
}