package com.coccoc.coccoctestapp.core;

import com.coccoc.coccoctestapp.model.Movie;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tungtm on 1/24/17.
 */

public interface RestfulApi {
    String ENDPOINT = "https://www.cgv.vn/api/movie/";

    @GET("list")
    Observable<MovieListResponse> getMovies();

    @GET("movie/id/{movieId}")
    Observable<MovieResponse> getMovie(@Path("movieId") String movieId);

    class Factory {
        private static RestfulApi instance;

        private static void create() {
            OkHttpClient client = new OkHttpClient();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestfulApi.ENDPOINT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
            instance = retrofit.create(RestfulApi.class);
        }

        public static synchronized RestfulApi getApi() {
            if (instance == null) {
                create();
            }
            return instance;
        }
    }
}
