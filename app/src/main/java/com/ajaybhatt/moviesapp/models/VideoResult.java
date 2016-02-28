package com.ajaybhatt.moviesapp.models;

import com.google.gson.annotations.SerializedName;

public class VideoResult {

    private String id;

    @SerializedName("iso_639_1")
    private String isoNumber;

    private String key;

    private String name;

    private String site;

    private int size;

    private String type;

    private static final String TYPE_YOUTUBE = "YouTube";
    private static final String TYPE_QUICKTIME = "QuickTime";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsoNumber() {
        return isoNumber;
    }

    public void setIsoNumber(String isoNumber) {
        this.isoNumber = isoNumber;
    }

    public String getKey() {
        return key;
    }

    public String getLink() {
        if (type.equals(TYPE_QUICKTIME)) {
            return String.format("http://www.quicktime.com/watch?v=%1$s", key);
        } else {
            return String.format("http://www.youtube.com/watch?v=%1$s", key);
        }
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
