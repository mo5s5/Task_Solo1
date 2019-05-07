package com.example.task_solo.adapter;

import android.databinding.ViewDataBinding;

import com.bumptech.glide.Glide;
import com.example.task_solo.databinding.PinnedItemBinding;
import com.example.task_solo.interfaces.ClickListener;
import com.example.task_solo.response.Result;

public class PinnedViewHolder extends BaseViewHolder<Result> {

    private PinnedItemBinding binding;
    private Result result;

    public PinnedViewHolder(ViewDataBinding binding, ClickListener clickListener) {
        super(binding, clickListener);
    }

    @Override
    public void bind(Result object) {
        result=object;
        binding.setResult(result);
        binding.setHolder(this);
        Glide.with(binding.pinnedItemImage).
                load(result.getFields().getThumbnail()).
                into(binding.pinnedItemImage);
    }
}
