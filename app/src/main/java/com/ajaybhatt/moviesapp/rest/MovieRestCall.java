package com.ajaybhatt.moviesapp.rest;

import com.ajaybhatt.moviesapp.models.DiscoverModel;
import com.ajaybhatt.moviesapp.models.ErrorModel;
import com.ajaybhatt.moviesapp.tools.Constants;
import com.squareup.otto.Bus;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

public class MovieRestCall extends RestCall {

    private Bus mBus;
    private MovieRestService apiService;

    public MovieRestCall(Bus bus) {
        this.mBus = bus;
        this.apiService = createService(Constants.API_BASE_URL, MovieRestService.class);
    }

    public void discover(String apiKey, String sortBy, String orderBy) {

        if(sortBy==null) {
            sortBy = "popularity";
        }

        if(orderBy==null) {
            orderBy = "desc";
        }

        Call<DiscoverModel> call = apiService.discover(apiKey, sortBy+"."+orderBy);
        call.enqueue(new Callback<DiscoverModel>() {
            @Override
            public void onResponse(Response<DiscoverModel> response, Retrofit retrofit) {
                DiscoverModel discoverModel = response.body();
                if (discoverModel != null) {
                    mBus.post(discoverModel);
                } else {
                    mBus.post(new ErrorModel("Error in fetching data"));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mBus.post(new ErrorModel("Error in fetching data"));
            }
        });

    }

    interface MovieRestService {

        @GET("discover/movie")
        Call<DiscoverModel> discover(@Query("api_key") String apiKey, @Query("sort_by") String sortBy);

    }

}
