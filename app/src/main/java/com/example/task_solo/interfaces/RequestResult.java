package com.example.task_solo.interfaces;

import android.support.annotation.NonNull;

import com.example.task_solo.response.BaseResponse;

import retrofit2.Response;

public interface RequestResult {
void onSucces(Response<BaseResponse> baseREsponse, @NonNull Integer resultId);
void error(Throwable e,Integer resultId);
}
