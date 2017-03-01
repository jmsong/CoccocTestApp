package com.coccoc.coccoctestapp.dagger;

import com.coccoc.coccoctestapp.CoccocTestApp;

/**
 * Base class for initializing and accessing the Dagger Component Graph
 * This is the only singleton implementation in the project. The rest of the singleton business logic managers
 * are created and handled/scoped by dagger.
 * Created by tungtm on 2/16/17.
 */
public class DaggerManager {

    /**
     * Dagger component graph
     */
    private DaggerComponentGraph graph;

    // Just make it private
    private DaggerManager() {
    }

    /**
     * @return the Dagger generate graph
     */
    public static DaggerComponentGraph component() {
        return getInstance().graph;
    }

    public static DaggerManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Rebuilds the dagger generated object graph
     */
    public void buildComponentAndInject(CoccocTestApp app) {
        graph = DaggerMainComponent.Initializer.init(app);
        graph.inject(app);
    }

    private static class Holder {
        static final DaggerManager INSTANCE = new DaggerManager();
    }
}
