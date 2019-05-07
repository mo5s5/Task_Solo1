package com.example.task_solo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.task_solo.App;
import com.example.task_solo.response.BaseResponse;
import com.example.task_solo.response.Result;
import com.example.task_solo.utils.Utils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Result>> resultResponseMutableLiveData;
    private Realm database;

    public HomeViewModel() {
        database = Realm.getDefaultInstance();
        this.resultResponseMutableLiveData = new MutableLiveData<>();
    }

    public void getResults(int page) {
        if (Utils.isConnected()) {
            App.instance().getApiService().postFeed("9c316183-b6ac-4839-943d-b1f41b22591c", "json",page,25).enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                    if (response.body() != null && response.body().getResponse().getStatus().equals("ok")) {
                       App.instance().saveFirstId(response.body().getResponse().getResults().get(1).getId());
                        resultResponseMutableLiveData.setValue(response.body().getResponse().getResults());
                        database.beginTransaction();
                        database.insertOrUpdate(response.body().getResponse().getResults());
                        database.commitTransaction();
                    } else
                        resultResponseMutableLiveData.setValue(null);
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    resultResponseMutableLiveData.setValue(null);
                }
            });
        } else if (page == 1) {
            RealmResults<Result> realmResults = database.where(Result.class).findAll();
            resultResponseMutableLiveData.setValue(realmResults);
        }
    }

    public LiveData<List<Result>> getResultListMutableLiveData() {
        return resultResponseMutableLiveData;
    }


}
