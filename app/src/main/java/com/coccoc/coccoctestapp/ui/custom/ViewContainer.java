package com.coccoc.coccoctestapp.ui.custom;

/**
 * Created by tungtm on 2/24/17.
 */

public interface ViewContainer<T> {
    void showItem(T item);

    boolean onBackPressed();
}
