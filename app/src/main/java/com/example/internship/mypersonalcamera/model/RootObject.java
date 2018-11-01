package com.example.internship.mypersonalcamera.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RootObject {
    @SerializedName("info")
    public Info info;

    @SerializedName("above")
    public List<Above> above;

    public RootObject(Info info, List<Above> above) {
        this.info = info;
        this.above = above;
    }

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


}
