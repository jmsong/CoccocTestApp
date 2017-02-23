package com.coccoc.coccoctestapp.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coccoc.coccoctestapp.R;
import com.coccoc.coccoctestapp.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListFragment extends Fragment implements MovieAdapter.OnMovieClick {

    public interface OnMovieSelectedListener {
        public void onMovieSelected(String id);
    }

    OnMovieSelectedListener mCallback;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private MovieAdapter adapter;

    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnMovieSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnMovieSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MovieAdapter();
        adapter.setCallback(this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override public void onClicked(Movie movie) {
        mCallback.onMovieSelected(movie.getId());
    }

    public void setMovies(ArrayList<Movie> movies) {
        adapter.setMovies(movies);
    }
}
