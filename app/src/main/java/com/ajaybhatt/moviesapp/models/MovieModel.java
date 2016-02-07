package com.ajaybhatt.moviesapp.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ajaybhatt.moviesapp.BR;
import com.ajaybhatt.moviesapp.tools.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieModel extends BaseObservable {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("adult")
    private boolean isAdult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("id")
    private int id;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("video")
    private boolean isVideo;

    @SerializedName("vote_average")
    private double voteAverage;

    private List<GenereModel> geners;

    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies = new ArrayList<>();

    @SerializedName("production_countries")
    private List<ProductionCountry> productionCountries = new ArrayList<>();

    @SerializedName("spoken_languages")
    private List<SpokenLanguage> spokenLanguages = new ArrayList<>();

    private Reviews reviews = new Reviews();

    private Trailers trailers = new Trailers();

    private Videos videos = new Videos();

    private boolean isFavourite;
    private boolean isReviewed;
    private boolean isTrailerAvailable;
    private boolean isVideoAvailable;

    public String getPosterPath() {
        return posterPath;
    }

    public String getSmallPosterPath() {
        return Constants.IMAGE_BASE_URL + "w185/" + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setIsAdult(boolean isAdult) {
        this.isAdult = isAdult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getLargeBackdropPath() {
        return Constants.IMAGE_BASE_URL + "w780/" + backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setIsVideo(boolean isVideo) {
        this.isVideo = isVideo;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Bindable
    public boolean isFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
        notifyPropertyChanged(BR.favourite);
    }

    @Bindable
    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
        notifyPropertyChanged(BR.reviews);
        notifyPropertyChanged(BR.reviewed);
    }

    public Trailers getTrailers() {
        return trailers;
    }

    public void setTrailers(Trailers trailers) {
        this.trailers = trailers;
        notifyPropertyChanged(BR.trailerAvailable);
    }

    public List<GenereModel> getGeners() {
        return geners;
    }

    public void setGeners(List<GenereModel> geners) {
        this.geners = geners;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    @Bindable
    public boolean isReviewed() {
        if (reviews.getResults().size() > 0) return true;
        return false;
    }

    public void setIsReviewed(boolean isReviewed) {
        this.isReviewed = isReviewed;
        notifyPropertyChanged(BR.reviewed);
    }

    @Bindable
    public boolean isTrailerAvailable() {
        if (trailers.getAllTrailers().size() > 0) return true;
        return false;
    }

    public void setIsTrailerAvailable(boolean isTrailerAvailable) {
        this.isTrailerAvailable = isTrailerAvailable;
        notifyPropertyChanged(BR.trailerAvailable);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MovieModel && id == ((MovieModel) o).getId();
    }

    @Bindable
    public boolean isVideoAvailable() {
        if (videos == null) return false;
        if (videos.getResults().size() > 0) return true;
        return false;
    }

    public void setIsVideoAvailable(boolean isVideoAvailable) {
        this.isVideoAvailable = isVideoAvailable;
        notifyPropertyChanged(BR.videoAvailable);
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
        notifyPropertyChanged(BR.videoAvailable);
    }
}
