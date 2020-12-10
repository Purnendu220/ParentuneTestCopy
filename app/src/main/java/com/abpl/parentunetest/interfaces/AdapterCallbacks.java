package com.abpl.parentunetest.interfaces;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;



public interface AdapterCallbacks<Object> {
    void onAdapterItemClick(RecyclerView.ViewHolder viewHolder, View view, Object model, int position);

    void onAdapterItemLongClick(RecyclerView.ViewHolder viewHolder, View view, Object model, int position);

    void onShowLastItem();
}
