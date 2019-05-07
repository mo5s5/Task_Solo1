package com.example.task_solo.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class FeedResponse extends RealmObject {

    @SerializedName("status")
    private String status;

    @SerializedName("userTier")
    private String userTier;

    @SerializedName("total")
    private int total;

    @SerializedName("startIndex")
    private int startIndex;

    @SerializedName("pageSize")
    private int pageSize;

    @SerializedName("currentPage")
    private int currentPage;

    @SerializedName("pages")
    private int pages;

    @SerializedName("orderBy")
    private String orderBy;

    @SerializedName("results")
    private RealmList<Result> results;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserTier() {
        return userTier;
    }

    public void setUserTier(String userTier) {
        this.userTier = userTier;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public RealmList<Result> getResults() {
        return results;
    }

    public void setResults(RealmList<Result> results) {
        this.results = results;
    }
}
