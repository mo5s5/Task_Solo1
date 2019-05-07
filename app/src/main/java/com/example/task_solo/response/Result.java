package com.example.task_solo.response;

import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.task_solo.R;
import com.example.task_solo.interfaces.BaseModel;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Result extends RealmObject implements BaseModel, Parcelable {

    public Result() {
    }

    @SerializedName("id")
    @PrimaryKey
    private String id;

    @SerializedName("sectionId")
    private String sectionId;

    @SerializedName("sectionName")
    private String sectionName;

    @SerializedName("webPublicationDate")
    private String webPublicationDate;

    @SerializedName("webTitle")
    private String webTitle;

    @SerializedName("webUrl")
    private String webUrl;

    @SerializedName("apiUrl")
    private String apiUrl;

    @SerializedName("fields")
    private Field fields;

    protected Result(Parcel in) {
        id = in.readString();
        sectionId = in.readString();
        webPublicationDate = in.readString();
        webTitle = in.readString();
        webUrl = in.readString();
        apiUrl = in.readString();
    }

    @Bindable
    private boolean pinned;

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        if (pinned) {
            pinned = false;
        } else {
            pinned = true;
        }
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public Field getFields() {
        return fields;
    }

    public void setFields(Field fields) {
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public int getViewType() {
        return R.layout.results_item;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(sectionId);
        parcel.writeString(webPublicationDate);
        parcel.writeString(webTitle);
        parcel.writeString(webUrl);
        parcel.writeString(apiUrl);
    }
}
