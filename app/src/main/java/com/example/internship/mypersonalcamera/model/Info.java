package com.example.internship.mypersonalcamera.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Info {
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("transactionscount")
    @Expose
    private Integer transactionscount;
    @SerializedName("satcount")
    @Expose
    private Integer satcount;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getTransactionscount() {
        return transactionscount;
    }

    public void setTransactionscount(Integer transactionscount) {
        this.transactionscount = transactionscount;
    }

    public Integer getSatcount() {
        return satcount;
    }

    public void setSatcount(Integer satcount) {
        this.satcount = satcount;
    }

}
