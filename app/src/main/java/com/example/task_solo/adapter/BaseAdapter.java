package com.example.task_solo.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.task_solo.R;
import com.example.task_solo.interfaces.BaseModel;
import com.example.task_solo.interfaces.ClickListener;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ClickListener clickListener;
    private List<BaseModel> data;

    public BaseAdapter() {
        init();
    }

    private void init() {
        data = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getViewType();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        switch (viewType) {
            case R.layout.results_item:
                return new ResultViewHolder(viewDataBinding, clickListener);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public List<BaseModel> getData() {
        return data;
    }

    public void addData(List<BaseModel> data) {
        this.data.addAll(data);
    }
}
