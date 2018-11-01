package com.example.internship.mypersonalcamera.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {


    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("info")
    @Expose
    private Info info;

    @SerializedName("above")
    @Expose
    private List<Above> above = null;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Above> getAbove() {
        return above;
    }

    public void setAbove(List<Above> above) {
        this.above = above;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {

        return error;
    }

}
