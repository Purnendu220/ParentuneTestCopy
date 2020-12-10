package com.abpl.parentunetest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.abpl.parentunetest.adapter.viewholder.BlogViewHolder;
import com.abpl.parentunetest.adapter.viewholder.EmptyViewHolder;
import com.abpl.parentunetest.adapter.viewholder.LoaderViewHolder;
import com.abpl.parentunetest.adapter.viewholder.QuestionViewHolder;
import com.abpl.parentunetest.databinding.ViewBlogItemBinding;
import com.abpl.parentunetest.databinding.ViewListProgressBinding;
import com.abpl.parentunetest.databinding.ViewQuestionItemBinding;
import com.abpl.parentunetest.interfaces.AdapterCallbacks;
import com.abpl.parentunetest.models.BlogModel;
import com.abpl.parentunetest.models.ListLoader;
import com.abpl.parentunetest.models.QuestionModel;

import java.util.ArrayList;
import java.util.List;





public class BlogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_UNKNOWN = -1;
    private static final int VIEW_TYPE_BLOG= 1;
    private static final int VIEW_TYPE_LOADER = 2;

    private final AdapterCallbacks<BlogModel> adapterCallbacks;

    private List<Object> list;
    private Context context;


    private boolean showLoader;
    private ListLoader listLoader;

    public BlogAdapter(Context context, boolean showLoader, AdapterCallbacks<BlogModel> adapterCallbacks) {
        this.adapterCallbacks = adapterCallbacks;
        this.context = context;
        list = new ArrayList<>();
        this.showLoader = showLoader;
        listLoader = new ListLoader(true, "No more blogs","Loading blogs");
    }

    public List<Object> getList() {
        return list;
    }

    public void addData(BlogModel model) {
        list.add(model);
        addLoader();
    }

    public void addAllData(List<BlogModel> models) {
        list.addAll(models);
        addLoader();
    }



    public void clearAll() {
        list.clear();
    }

    public void loaderDone() {
        listLoader.setFinish(true);
        try {
            notifyItemChanged(list.indexOf(listLoader));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loaderReset() {
        listLoader.setFinish(false);
    }

    private void addLoader() {
        if (showLoader) {
            list.remove(listLoader);
            list.add(listLoader);
        }
    }

    @Override
    public int getItemViewType(int position) {

        int itemViewType = VIEW_TYPE_UNKNOWN;

        Object item = getItem(position);
        if (item instanceof BlogModel) {
            itemViewType = VIEW_TYPE_BLOG;
        } else if (item instanceof ListLoader) {
            itemViewType = VIEW_TYPE_LOADER;
        }

        return itemViewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_BLOG) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            ViewBlogItemBinding itemBinding = ViewBlogItemBinding.inflate(layoutInflater,parent,false);

            return new BlogViewHolder(itemBinding);


        }  else if (viewType == VIEW_TYPE_LOADER) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            ViewListProgressBinding itemBinding = ViewListProgressBinding.inflate(layoutInflater,parent,false);

            return new LoaderViewHolder(itemBinding);
        }
        return new EmptyViewHolder(new LinearLayout(context));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BlogViewHolder) {
            if (getItem(position) != null)
                ((BlogViewHolder) holder).bind(getItem(position), adapterCallbacks, position);

        }  else if (holder instanceof LoaderViewHolder) {
            ((LoaderViewHolder) holder).bind(listLoader, adapterCallbacks);
            if (position == getItemCount() - 1 && !listLoader.isFinish()) {
                adapterCallbacks.onShowLastItem();
            }
        } else if (holder instanceof EmptyViewHolder) {
            ((EmptyViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Object getItem(int position) {
        if (list != null && list.size() > 0)
            return list.get(position);
        else
            return null;
    }


}