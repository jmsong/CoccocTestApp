package com.coccoc.coccoctestapp.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.coccoc.coccoctestapp.R;
import com.coccoc.coccoctestapp.core.Movie;

/**
 * Created by tungtm on 2/24/17.
 */

public class SinglePaneContainer extends LinearLayout implements ViewContainer<Movie> {

    private MovieListView listView;

    public SinglePaneContainer(Context context) {
        super(context);
    }

    public SinglePaneContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SinglePaneContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        listView = (MovieListView) getChildAt(0);
    }

    @Override
    public void showItem(Movie movie) {
        if (listViewAttached()) {
            removeViewAt(0);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            inflater.inflate(R.layout.movie_detail_2, this);
        }

        MovieDetailView detailView = (MovieDetailView) getChildAt(0);
        detailView.setMovie(movie);
    }

    @Override
    public boolean onBackPressed() {
        if (!listViewAttached()) {
            removeViewAt(0);
            addView(listView);
            return true;
        }
        return false;
    }

    private boolean listViewAttached() {
        return listView.getParent() != null;
    }
}
