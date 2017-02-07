package com.coccoc.coccoctestapp;

import android.app.Application;
import android.content.Context;

import com.coccoc.coccoctestapp.actions.RestfulActionCreator;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.hardsoftstudio.rxflux.RxFlux;

/**
 * Created by tungtm on 1/24/17.
 */

public class CoccocTestApp extends Application {
    private RxFlux rxFlux;

    private RestfulActionCreator restfulActionCreator;

    public static CoccocTestApp get(Context context) {
        return ((CoccocTestApp) context.getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
        rxFlux = RxFlux.init(this);
        restfulActionCreator = new RestfulActionCreator(rxFlux.getDispatcher(), rxFlux.getSubscriptionManager());
    }

    public RxFlux getRxFlux() {
        return rxFlux;
    }

    public RestfulActionCreator getRestfulActionCreator() {
        return restfulActionCreator;
    }
}
