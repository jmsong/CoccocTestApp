package com.coccoc.coccoctestapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coccoc.coccoctestapp.R;
import com.coccoc.coccoctestapp.model.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    private OnMovieClick callback;

    public MovieAdapter() {
        super();
        movies = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_movie, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Movie movie = movies.get(i);
        viewHolder.setUp(movie);
        viewHolder.itemView.setOnClickListener(view -> {
            if (callback != null) callback.onClicked(movie);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void setCallback(OnMovieClick callback) {
        this.callback = callback;
    }

    public interface OnMovieClick {
        void onClicked(Movie repo);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date)
        public TextView dateView;
        @BindView(R.id.thumbnail)
        public SimpleDraweeView thumbnail;
        @BindView(R.id.name)
        TextView nameView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setUp(Movie movie) {
            thumbnail.setImageURI(movie.getThumbnail());
            nameView.setText(movie.getName());
            dateView.setText(movie.getReleaseDate());
        }
    }
}
