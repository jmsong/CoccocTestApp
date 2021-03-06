package com.coccoc.coccoctestapp.actions;

import com.coccoc.coccoctestapp.core.DataServiceManager;
import com.coccoc.coccoctestapp.core.RestfulApi;
import com.coccoc.coccoctestapp.dagger.DaggerManager;
import com.hardsoftstudio.rxflux.action.RxAction;
import com.hardsoftstudio.rxflux.action.RxActionCreator;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;
import com.hardsoftstudio.rxflux.util.SubscriptionManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tungtm on 1/24/17.
 */

public class RestfulActionCreator extends RxActionCreator implements Actions {

    public RestfulActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        super(dispatcher, manager);
        DaggerManager.component().inject(this);
    }

    @Override
    public void getMovies() {
        final RxAction action = newRxAction(GET_MOVIES);
        if (hasRxAction(action)) return;

        addRxAction(action, DataServiceManager.getMovies()
                .subscribe(movies -> postRxAction(newRxAction(GET_MOVIES, Keys.MOVIES, movies)),
                        throwable -> postError(action, throwable)));
    }

    @Override
    public void getMovie(String movieId) {
        final RxAction action = newRxAction(GET_MOVIE, Keys.ID, movieId);
        if (hasRxAction(action)) return;

        addRxAction(action, DataServiceManager.getMovie(movieId)
                .subscribe(movie -> postRxAction(newRxAction(GET_MOVIE, Keys.MOVIE, movie)),
                        throwable -> postError(action, throwable)));
    }

    @Override
    public boolean retry(RxAction action) {
        if (hasRxAction(action)) return true;

        switch (action.getType()) {
            case GET_MOVIE:
                getMovie(action.get(Keys.ID));
                return true;
            case GET_MOVIES:
                getMovies();
                return true;
        }
        return false;
    }
}
