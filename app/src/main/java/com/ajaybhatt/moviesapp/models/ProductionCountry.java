package com.ajaybhatt.moviesapp.models;

import com.google.gson.annotations.SerializedName;

public class ProductionCountry {

    @SerializedName("iso_3166_1")
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
