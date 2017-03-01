package com.coccoc.coccoctestapp.ui.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.coccoc.coccoctestapp.core.Movie;
import com.coccoc.coccoctestapp.ui.MainActivity;
import com.coccoc.coccoctestapp.ui.MovieAdapter;

import java.util.ArrayList;

/**
 * Created by tungtm on 2/24/17.
 */

public class MovieListView extends RecyclerView {
    final MovieAdapter adapter = new MovieAdapter();

    public MovieListView(Context context) {
        super(context);
    }

    public MovieListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        MainActivity activity = (MainActivity) getContext();

        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(activity));
        setAdapter(adapter);

        adapter.setCallback(movie -> activity.getMovie(movie.getId()));
    }

    public void setMovies(ArrayList<Movie> movies) {
        adapter.setMovies(movies);
    }
}
