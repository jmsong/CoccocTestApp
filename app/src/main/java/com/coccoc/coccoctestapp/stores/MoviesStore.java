package com.coccoc.coccoctestapp.stores;

import com.coccoc.coccoctestapp.actions.Actions;
import com.coccoc.coccoctestapp.actions.Keys;
import com.coccoc.coccoctestapp.core.MovieListResponse;
import com.coccoc.coccoctestapp.core.MovieResponse;
import com.coccoc.coccoctestapp.core.Movie;
import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;

import java.util.ArrayList;

/**
 * Created by tungtm on 1/24/17.
 */

public class MoviesStore extends RxStore implements MoviesStoreInterface {
    public static final String ID = "MoviesStore";
    private static MoviesStore instance;
    private ArrayList<Movie> movies;
    private Movie movie;

    public MoviesStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public ArrayList<Movie> getMovies() {
        return movies == null ? new ArrayList<>() : movies;
    }

    @Override
    public Movie getMovie() {
        return movie;
    }

    @Override
    public void onRxAction(RxAction action) {
        switch (action.getType()) {
            case Actions.GET_MOVIES:
                this.movies = action.get(Keys.MOVIES);
                break;

            case Actions.GET_MOVIE:
                this.movie = action.get(Keys.MOVIE);
                break;

            default:
                break;
        }

        postChange(new RxStoreChange(ID, action));
    }
}
