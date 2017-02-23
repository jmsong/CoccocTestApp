package com.coccoc.coccoctestapp.dagger.modules;

import com.coccoc.coccoctestapp.ui.navigation.NavigationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tungtm on 2/16/17.
 */

@Module
public class NavigationModule {

    @Provides
    @Singleton
    protected NavigationManager provideNavigationManager() {
        return new NavigationManager();
    }
}
