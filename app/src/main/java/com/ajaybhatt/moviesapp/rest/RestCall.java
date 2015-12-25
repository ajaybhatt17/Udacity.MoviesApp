package com.ajaybhatt.moviesapp.rest;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public abstract class RestCall {

    public <S> S createService(String baseUrl, Class<S> serviceClass) {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                final com.squareup.okhttp.Response response = chain.proceed(chain.request());

                // Do anything with response here
                //System.out.println(response.toString());
                //System.out.println(response.request().toString());
                return response;
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(serviceClass);
    }

}
