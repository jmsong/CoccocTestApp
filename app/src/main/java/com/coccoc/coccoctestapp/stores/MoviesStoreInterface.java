package com.coccoc.coccoctestapp.stores;

import com.coccoc.coccoctestapp.model.Movie;

import java.util.ArrayList;

/**
 * Created by tungtm on 1/24/17.
 */

public interface MoviesStoreInterface {
    ArrayList<Movie> getMovies();
    Movie getMovie(String movieId);
}
