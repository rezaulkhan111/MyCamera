package com.example.internship.mypersonalcamera.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Above {
    @SerializedName("satid")
    @Expose
    private Integer satid;
    @SerializedName("satname")
    @Expose
    private String satname;
    @SerializedName("intDesignator")
    @Expose
    private String intDesignator;
    @SerializedName("launchDate")
    @Expose
    private String launchDate;
    @SerializedName("satlat")
    @Expose
    private Double satlat;
    @SerializedName("satlng")
    @Expose
    private Double satlng;
    @SerializedName("satalt")
    @Expose
    private Double satalt;

    public Integer getSatid() {
        return satid;
    }

    public void setSatid(Integer satid) {
        this.satid = satid;
    }

    public String getSatname() {
        return satname;
    }

    public void setSatname(String satname) {
        this.satname = satname;
    }

    public String getIntDesignator() {
        return intDesignator;
    }

    public void setIntDesignator(String intDesignator) {
        this.intDesignator = intDesignator;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public Double getSatlat() {
        return satlat;
    }

    public void setSatlat(Double satlat) {
        this.satlat = satlat;
    }

    public Double getSatlng() {
        return satlng;
    }

    public void setSatlng(Double satlng) {
        this.satlng = satlng;
    }

    public Double getSatalt() {
        return satalt;
    }

    public void setSatalt(Double satalt) {
        this.satalt = satalt;
    }

}
