package com.coccoc.coccoctestapp.actions;

import com.hardsoftstudio.rxflux.*;
import com.hardsoftstudio.rxflux.action.RxAction;

/**
 * Created by tungtm on 1/24/17.
 */

public interface Actions {
    String GET_MOVIES = "get_movies";
    String GET_MOVIE = "get_movie";

    void getMovies();
    void getMovie(String movieId);

    boolean retry(RxAction action);
}
