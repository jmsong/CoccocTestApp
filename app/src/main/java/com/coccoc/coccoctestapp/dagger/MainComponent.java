package com.coccoc.coccoctestapp.dagger;

import android.app.Application;

import com.coccoc.coccoctestapp.CoccocTestApp;
import com.coccoc.coccoctestapp.dagger.modules.AppModule;
import com.coccoc.coccoctestapp.dagger.modules.NavigationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger interface which connects all the modules
 * Created by becze on 9/17/2015.
 */
@Singleton
@Component(modules = { AppModule.class, NavigationModule.class})
public interface MainComponent extends DaggerComponentGraph {

    final class Initializer {

        public static MainComponent init(CoccocTestApp app) {

            //@formatter:off
            return DaggerMainComponent.builder()
                            .appModule(new AppModule(app))
                            .navigationModule(new NavigationModule())
                            .build();
            //@formatter:on
        }

    }
}
