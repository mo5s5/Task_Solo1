package com.example.task_solo.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    public static ApiService getApiService() {
        return createRetrofit("https://content.guardianapis.com/").create(ApiService.class);
    }

    private static Retrofit createRetrofit(String serverURL) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().
                connectTimeout(20, TimeUnit.SECONDS).
                addInterceptor(interceptor).
                readTimeout(20, TimeUnit.SECONDS);

        return new Retrofit.Builder().
                baseUrl(serverURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
    }
}
