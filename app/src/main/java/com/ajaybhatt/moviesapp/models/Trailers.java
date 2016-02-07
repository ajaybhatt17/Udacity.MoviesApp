package com.ajaybhatt.moviesapp.models;

import java.util.ArrayList;
import java.util.List;

public class Trailers {

    private List<TrailerModel> quicktime = new ArrayList<>();
    private List<TrailerModel> youtube = new ArrayList<>();

    public List<TrailerModel> getQuicktime() {
        return quicktime;
    }

    public void setQuicktime(List<TrailerModel> quicktime) {
        this.quicktime = quicktime;
    }

    public List<TrailerModel> getYoutube() {
        return youtube;
    }

    public void setYoutube(List<TrailerModel> youtube) {
        this.youtube = youtube;
    }

    public List<TrailerModel> getAllTrailers() {
        List<TrailerModel> trailerModels = new ArrayList<>();
        for (TrailerModel trailerModel : quicktime) {
            trailerModel.setOrigin("quicktime");
            trailerModels.add(trailerModel);
        }
        for (TrailerModel trailerModel : youtube) {
            trailerModel.setOrigin("youtube");
            trailerModels.add(trailerModel);
        }
        return trailerModels;
    }
}