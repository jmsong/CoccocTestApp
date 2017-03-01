package com.coccoc.coccoctestapp.dagger.modules;

import android.content.Context;

import com.coccoc.coccoctestapp.CoccocTestApp;
import com.coccoc.coccoctestapp.actions.RestfulActionCreator;
import com.coccoc.coccoctestapp.stores.MoviesStore;
import com.hardsoftstudio.rxflux.RxFlux;
import com.hardsoftstudio.rxflux.dispatcher.Dispatcher;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tungtm on 2/16/17.
 */

@Module
public class AppModule {

    private final CoccocTestApp app;

    public AppModule(CoccocTestApp application) {
        app = application;
    }

    @Provides
    @Singleton
    protected CoccocTestApp provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    protected Context provideApplicationContext()  {
        return app;
    }

    @Provides
    @Singleton
    protected RxFlux provideRxFlux() {
        return RxFlux.init(app);
    }

    @Provides
    @Singleton
    protected RestfulActionCreator provideRestfulActionCreator(RxFlux rxFlux) {
        return new RestfulActionCreator(rxFlux.getDispatcher(), rxFlux.getSubscriptionManager());
    }

    @Provides
    @Singleton
    protected Dispatcher provideDispatcher(RxFlux rxFlux) {
        return rxFlux.getDispatcher();
    }

    @Provides
    @Singleton
    protected MoviesStore provideMoviesStore(Dispatcher dispatcher) {
        return new MoviesStore(dispatcher);
    }

}
