package com.coccoc.coccoctestapp.core;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by tungtm on 3/1/17.
 */

public final class RealmService {

    static Void writeToRealm(MovieListResponse movieListResponse) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(movieListResponse.getMovies());
        realm.commitTransaction();

        return null;
    }

    static String writeToRealm(MovieResponse movieResponse) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(movieResponse.getMovie());
        realm.commitTransaction();

        return movieResponse.getMovie().getId();
    }

    static RealmResults<Movie> readAllMovies(Void param) {
        return getAllMovies();
    }

    static Movie getMovieById(String movieId) {
        return getMovie(movieId);
    }

    private static RealmResults<Movie> getAllMovies() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Movie.class).findAll();
    }

    private static Movie getMovie(String movieId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Movie.class).equalTo("id", movieId).findFirst();
    }
}
