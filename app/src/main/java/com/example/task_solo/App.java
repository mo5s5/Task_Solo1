package com.example.task_solo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.task_solo.api.ApiService;
import com.example.task_solo.api.ServiceFactory;

import io.realm.Realm;

public class App extends Application {

    private static App instance;
    private ApiService apiService;
    private SharedPreferences sharedPref;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        Realm.init(this);
    }

    public static App instance() {
        return instance;
    }


    public SharedPreferences getSharedPref() {
        return sharedPref;
    }

    public void saveFirstId(String firstId){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("firstId", firstId);
        editor.apply();
    }
    public void changeFirstId(String newId){
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putString("newId",newId);
        editor.apply();

    }

    public ApiService getApiService() {
        if (apiService == null) {
            apiService = ServiceFactory.getApiService();
        }
        return apiService;
    }
}
