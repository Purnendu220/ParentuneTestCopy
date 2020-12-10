package com.abpl.parentunetest.adapter.viewholder;

import android.content.Context;
import android.view.View;


import androidx.recyclerview.widget.RecyclerView;

import com.abpl.parentunetest.databinding.ViewListProgressBinding;
import com.abpl.parentunetest.interfaces.AdapterCallbacks;
import com.abpl.parentunetest.models.ListLoader;


public class LoaderViewHolder extends RecyclerView.ViewHolder {


    private Context context;
    ViewListProgressBinding itemBinding;
    public LoaderViewHolder(ViewListProgressBinding itemBinding) {
        super(itemBinding.getRoot());

        context = itemBinding.getRoot().getContext();
        this.itemBinding = itemBinding;
    }

    public void bind(ListLoader model, final AdapterCallbacks adapterCallbacks) {

        if (model.isFinish()) {
            itemBinding.progressBar.setVisibility(View.GONE);
        } else {
            itemBinding.progressBar.setVisibility(View.VISIBLE);
        }

        if (model.isShowText()) {

            itemBinding.text.setVisibility(View.VISIBLE);

            if (model.isFinish())
                itemBinding.text.setText(model.getFinishText());
            else
                itemBinding.text.setText(model.getLoadingText());

        } else {
            itemBinding.text.setVisibility(View.GONE);
            itemBinding.text.setText("");
        }
    }
}
