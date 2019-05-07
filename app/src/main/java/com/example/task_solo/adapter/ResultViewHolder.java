package com.example.task_solo.adapter;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.task_solo.R;
import com.example.task_solo.databinding.ResultsItemBinding;
import com.example.task_solo.interfaces.ClickListener;
import com.example.task_solo.response.Result;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ResultViewHolder extends BaseViewHolder<Result> {

    private ResultsItemBinding binding;
    private Result result;

    public ResultViewHolder(final ViewDataBinding binding, ClickListener clickListener) {
        super(binding, clickListener);
        this.binding = (ResultsItemBinding) binding;
    }

    @Override
    public void bind(Result object) {
        result = object;
        binding.setResult(result);
        binding.setHolder(this);
        Glide.with(binding.resultImage).
                load(result.getFields().getThumbnail()).
                into(binding.resultImage);
    }

    public void onClick() {
        NavController navController = Navigation.findNavController(binding.getRoot());
        Bundle bundle = new Bundle();
        bundle.putParcelable("result", result);
        navController.navigate(R.id.action_homeFragment_to_itemFragment, bundle);
    }
}
