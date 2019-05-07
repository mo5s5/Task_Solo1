package com.example.task_solo.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.task_solo.R;
import com.example.task_solo.databinding.FragmentItemBinding;
import com.example.task_solo.response.Result;
import com.example.task_solo.viewmodel.HomeViewModel;

import java.util.List;

public class ItemFragment extends Fragment {

    FragmentItemBinding binding;
    Result result;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        result = getArguments().getParcelable("result");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item, container, false);
        binding.setResult(result);
        Glide.with(binding.itemImage).
                load(result.getFields().
                        getThumbnail()).
                into(binding.itemImage);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setFragment(this);
    }
}
