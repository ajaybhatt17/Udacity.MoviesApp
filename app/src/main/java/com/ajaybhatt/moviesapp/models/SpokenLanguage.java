package com.ajaybhatt.moviesapp.models;

import com.google.gson.annotations.SerializedName;

public class SpokenLanguage {

    @SerializedName("iso_639_1")
    private String isoNumber;
    private String name;


    public String getIsoNumber() {
        return isoNumber;
    }

    public void setIsoNumber(String isoNumber) {
        this.isoNumber = isoNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
