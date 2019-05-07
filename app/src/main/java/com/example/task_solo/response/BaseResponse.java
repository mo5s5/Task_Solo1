package com.example.task_solo.response;

import com.google.gson.annotations.SerializedName;

import okhttp3.Response;

public class BaseResponse {

    public FeedResponse getResponse() {
        return response;
    }

    public void setResponse(FeedResponse response) {
        this.response = response;
    }

    @SerializedName("response")
    private FeedResponse response;
}
