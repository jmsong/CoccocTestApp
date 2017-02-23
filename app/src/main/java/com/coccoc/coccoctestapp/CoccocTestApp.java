package com.coccoc.coccoctestapp;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.coccoc.coccoctestapp.actions.RestfulActionCreator;
import com.coccoc.coccoctestapp.dagger.DaggerManager;
import com.coccoc.coccoctestapp.stores.MoviesStore;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.hardsoftstudio.rxflux.RxFlux;
import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;

import javax.inject.Inject;

/**
 * Created by tungtm on 1/24/17.
 */

public class CoccocTestApp extends Application {

    @Inject
    public RestfulActionCreator restfulActionCreator;

    @Override
    public void onCreate() {
        super.onCreate();

        initLibs();

    }

    private void initLibs() {
        Fresco.initialize(this);

        // Initialize dagger
        DaggerManager.getInstance().buildComponentAndInject(this);
        DaggerManager.component().inject(this);

        //@formatter:off
        // Initialize typeface helper
        TypefaceCollection typeface = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf"))
                .set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf"))
                .create();
        TypefaceHelper.init(typeface);
        //@formatter:on
    }

    public RestfulActionCreator getRestfulActionCreator() {
        return restfulActionCreator;
    }
}
