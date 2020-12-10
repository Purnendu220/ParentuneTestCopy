package com.abpl.parentunetest.adapter.viewholder;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;




public class EmptyViewHolder extends RecyclerView.ViewHolder {

    private final Context context;

    public EmptyViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();

    }

    public void bind() {
        itemView.setVisibility(View.GONE);
    }
}
