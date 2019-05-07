package com.example.task_solo.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;


import com.example.task_solo.interfaces.BaseModel;
import com.example.task_solo.interfaces.ClickListener;


public abstract class BaseViewHolder<T extends BaseModel> extends RecyclerView.ViewHolder {

    public BaseViewHolder(ViewDataBinding binding, ClickListener clickListener) {
        super(binding.getRoot());
    }

    public abstract void bind(T object);
}
