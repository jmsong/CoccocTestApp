package com.coccoc.coccoctestapp;

import android.app.Application;
import android.graphics.Typeface;

import com.coccoc.coccoctestapp.actions.RestfulActionCreator;
import com.coccoc.coccoctestapp.dagger.DaggerManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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

        // Initialize Realm
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfig);

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
