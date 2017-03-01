package com.coccoc.coccoctestapp.dagger;

import com.coccoc.coccoctestapp.CoccocTestApp;
import com.coccoc.coccoctestapp.dagger.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger interface which connects all the modules
 * Created by tungtm on 2/16/17.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface MainComponent extends DaggerComponentGraph {

    final class Initializer {

        public static MainComponent init(CoccocTestApp app) {

            return DaggerMainComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }

    }
}
