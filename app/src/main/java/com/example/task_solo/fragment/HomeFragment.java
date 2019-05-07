package com.example.task_solo.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task_solo.R;
import com.example.task_solo.adapter.BaseAdapter;
import com.example.task_solo.databinding.FragmentHomeBinding;
import com.example.task_solo.interfaces.BaseModel;
import com.example.task_solo.response.Result;
import com.example.task_solo.service.NewsService;
import com.example.task_solo.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    HomeViewModel homeViewModel;
    FragmentHomeBinding binding;
    BaseAdapter recyclerViewAdapter;
    LinearLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getResultListMutableLiveData().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> results) {

                if (results != null) {
                    recyclerViewAdapter.addData(new ArrayList<BaseModel>(results));
                    recyclerViewAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Offline Mode", Toast.LENGTH_LONG).show();
                }
            }
        });
        getActivity().startService(new Intent(getActivity(), NewsService.class));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setFragment(this);
        binding.resultsRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        binding.resultsRecyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new BaseAdapter();
        binding.resultsRecyclerView.setAdapter(recyclerViewAdapter);
        binding.resultsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int visibleItemCount;
            int pastVisibleItems;
            int totalItemCount;
            private boolean loading;
            int page = 1;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                loading = true;
                if (dy > 0) {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        if (loading) {
                            loading = false;
                            page++;
                            getResults(page);
                        }
                    }
                }
                if (dy < 0 && layoutManager.findFirstVisibleItemPosition() == 1) {
                    getResults(1);
                }
            }
        });
        getResults(1);
    }

    public void getResults(int page) {
        homeViewModel.getResults(page);
    }
}
