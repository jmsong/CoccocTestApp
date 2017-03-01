package com.coccoc.coccoctestapp.core;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tungtm on 3/1/17.
 */

public final class DataServiceManager {

    public static Observable<List<Movie>> getMovies() {

        Observable<RealmResults<Movie>> observable =
                RestfulApi.Factory.getApi().getMovies()
                        .subscribeOn(Schedulers.io())
                        .map(RealmService::writeToRealm)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(RealmService::readAllMovies);

        RealmResults<Movie> cached = RealmService.readAllMovies(null);
        if (cached != null) {
            observable = observable.mergeWith(Observable.just(cached));
        }

        return observable.map(movies -> {
            List<Movie> movieList = new ArrayList<>(movies.size());
            movieList.addAll(movies.subList(0, movies.size()));
            return movieList;
        }).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Movie> getMovie(String movieId) {
        Observable<Movie> observable =
                RestfulApi.Factory.getApi()
                        .getMovie(movieId)
                        .subscribeOn(Schedulers.io())
                        .map(RealmService::writeToRealm)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(RealmService::getMovieById);
        Movie cached = RealmService.getMovieById(movieId);
        if (cached != null) {
            observable = observable.mergeWith(Observable.just(cached));
        }

        return observable.observeOn(AndroidSchedulers.mainThread());
    }
}
