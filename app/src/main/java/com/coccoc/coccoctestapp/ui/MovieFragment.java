package com.coccoc.coccoctestapp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coccoc.coccoctestapp.CoccocTestApp;
import com.coccoc.coccoctestapp.R;
import com.coccoc.coccoctestapp.model.Movie;
import com.coccoc.coccoctestapp.stores.MoviesStore;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MovieFragment extends Fragment {
    private static final String ARG_MOVIE_ID = "movieId";
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

    private String movieId;

    private Unbinder unbinder;

    public MovieFragment() {
        // Required empty public constructor
    }

    public static MovieFragment newInstance(String movieId) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            movieId = getArguments().getString(ARG_MOVIE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Movie movie = MoviesStore.get(CoccocTestApp.get(getActivity()).getRxFlux().getDispatcher()).getMovie(movieId);
        thumbnail.setImageURI(movie.getThumbnail());
        nameText.setText(movie.getName());
        directorText.setText(movie.getMovieDirector());
        actressText.setText(movie.getMovieActress());
        genreText.setText(movie.getGenre());
        releaseDateText.setText(movie.getReleaseDate());
        endTimeText.setText(movie.getMovieEndtime());
        languageText.setText(movie.getMovieLanguage());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//    unbinder.unbind();
    }
}
