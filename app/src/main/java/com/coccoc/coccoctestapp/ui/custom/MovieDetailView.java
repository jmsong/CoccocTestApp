package com.coccoc.coccoctestapp.ui.custom;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import com.coccoc.coccoctestapp.R;
import com.coccoc.coccoctestapp.core.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tungtm on 2/24/17.
 */

public class MovieDetailView extends CoordinatorLayout {

    @BindView(R.id.thumbnail)
    SimpleDraweeView thumbnail;
    @BindView(R.id.name)
    TextView nameText;
    @BindView(R.id.director)
    TextView directorText;
    @BindView(R.id.actress)
    TextView actressText;
    @BindView(R.id.genre)
    TextView genreText;
    @BindView(R.id.release_date)
    TextView releaseDateText;
    @BindView(R.id.end_time)
    TextView endTimeText;
    @BindView(R.id.language)
    TextView languageText;

    public MovieDetailView(Context context) {
        super(context);
    }

    public MovieDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        ButterKnife.bind(this);
    }

    public void setMovie(Movie movie) {
        thumbnail.setImageURI(movie.getThumbnail());
        nameText.setText(movie.getName());
        directorText.setText(movie.getMovieDirector());
        actressText.setText(movie.getMovieActress());
        genreText.setText(movie.getGenre());
        releaseDateText.setText(movie.getReleaseDate());
        endTimeText.setText(movie.getMovieEndtime());
        languageText.setText(movie.getMovieLanguage());
    }
}
