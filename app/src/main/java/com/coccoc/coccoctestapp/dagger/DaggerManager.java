package com.coccoc.coccoctestapp.dagger;

import com.coccoc.coccoctestapp.CoccocTestApp;

/**
 * Base class for initializing and accessing the Dagger Component Graph
 * This is the only singleton implementation in the project. The res of the singleton business logic managers
 * are created and handled/scoped by dagger.
 * Created by tungtm on 2/16/17.
 */
public class DaggerManager {

    // ------------------------------------------------------------------------
    // TYPES
    // ------------------------------------------------------------------------

    private static class Holder {
        static final DaggerManager INSTANCE = new DaggerManager();
    }

    // ------------------------------------------------------------------------
    // STATIC FIELDS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // STATIC METHODS
    // ------------------------------------------------------------------------

    /**
     * @return the Gagger generate graph
     */
    public static DaggerComponentGraph component() {
        return getInstance().graph;
    }

    public static DaggerManager getInstance() {
        return Holder.INSTANCE;
    }

    // ------------------------------------------------------------------------
    // FIELDS
    // ------------------------------------------------------------------------

    /**
     * Dagger component graph
     */
    private DaggerComponentGraph graph;

    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    // Just make it private
    private DaggerManager() {
    }

    /**
     * Rebuilds the dagger generated object graph
     */
    public void buildComponentAndInject(CoccocTestApp app) {
        graph = DaggerMainComponent.Initializer.init(app);
        graph.inject(app);
    }

    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // GETTERS / SETTTERS
    // ------------------------------------------------------------------------
}
