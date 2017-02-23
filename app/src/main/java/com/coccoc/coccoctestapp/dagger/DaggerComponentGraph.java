package com.coccoc.coccoctestapp.dagger;

import com.coccoc.coccoctestapp.CoccocTestApp;
import com.coccoc.coccoctestapp.actions.RestfulActionCreator;
import com.coccoc.coccoctestapp.stores.MoviesStore;
import com.coccoc.coccoctestapp.ui.BaseActivity;
import com.coccoc.coccoctestapp.ui.MainActivity;
import com.coccoc.coccoctestapp.ui.MovieFragment;
import com.coccoc.coccoctestapp.ui.MovieListFragment;
import com.hardsoftstudio.rxflux.RxFlux;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;

/**
 * Created by tungtm on 2/16/17.
 */

public interface DaggerComponentGraph {

    void inject(CoccocTestApp app);

    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(MovieListFragment movieListFragment);

    void inject(MovieFragment movieFragment);

//    void inject(RxFlux rxFlux);
//
//    void inject(RestfulActionCreator restfulActionCreator);
//
//    void inject(Dispatcher dispatcher);
//
//    void inject(MoviesStore moviesStore);
}
