package com.example.task_solo.response;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Field extends RealmObject {


    @SerializedName("thumbnail")
   private String thumbnail;

    @SerializedName("trailText")
    private String trailText;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTrailText() {
        return trailText;
    }

    public void setTrailText(String trailText) {
        this.trailText = trailText;
    }
}
