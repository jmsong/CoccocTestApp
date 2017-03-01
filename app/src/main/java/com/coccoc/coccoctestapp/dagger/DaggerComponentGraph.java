package com.coccoc.coccoctestapp.dagger;

import com.coccoc.coccoctestapp.CoccocTestApp;
import com.coccoc.coccoctestapp.actions.RestfulActionCreator;
import com.coccoc.coccoctestapp.ui.BaseActivity;
import com.coccoc.coccoctestapp.ui.MainActivity;

/**
 * Created by tungtm on 2/16/17.
 */

public interface DaggerComponentGraph {

    void inject(CoccocTestApp app);

    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(RestfulActionCreator restfulActionCreator);
}
