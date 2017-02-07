package com.coccoc.coccoctestapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.coccoc.coccoctestapp.R;
import com.coccoc.coccoctestapp.actions.Actions;
import com.coccoc.coccoctestapp.actions.Keys;
import com.coccoc.coccoctestapp.model.Movie;
import com.coccoc.coccoctestapp.stores.MoviesStore;
import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.dispatcher.RxViewDispatch;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.coccoc.coccoctestapp.CoccocTestApp.get;

public class MainActivity extends AppCompatActivity implements RxViewDispatch, MovieAdapter.OnMovieClick {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_loading)
    ProgressBar progress_loading;
    @BindView(R.id.root_coordinator)
    CoordinatorLayout coordinatorLayout;
    private MovieAdapter adapter;
    private MoviesStore moviesStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter();
        adapter.setCallback(this);
        recyclerView.setAdapter(adapter);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        refresh();
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        setLoadingFrame(false);
        switch (change.getStoreId()) {
            case MoviesStore.ID:
                switch (change.getRxAction().getType()) {
                    case Actions.GET_MOVIES:
                        adapter.setMovies(moviesStore.getMovies());
                        break;

                    case Actions.GET_MOVIE:
                        showMovieFragment((String) change.getRxAction().getData().get(Keys.ID));
                        break;
                }
                break;
        }
    }

    @Override
    public void onRxError(@NonNull RxError error) {
        setLoadingFrame(false);
        Throwable throwable = error.getThrowable();
        if (throwable != null) {
            Snackbar.make(coordinatorLayout, "An error occurred", Snackbar.LENGTH_INDEFINITE).setAction("Retry", v -> get(this).getRestfulActionCreator().retry(error.getAction())).show();
            throwable.printStackTrace();
        } else {
            Toast.makeText(this, "Unknown error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRxViewRegistered() {

    }

    @Override
    public void onRxViewUnRegistered() {

    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        moviesStore = MoviesStore.get(get(this).getRxFlux().getDispatcher());
        return Arrays.asList(moviesStore);
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToUnRegister() {
        moviesStore = MoviesStore.get(get(this).getRxFlux().getDispatcher());
        return Arrays.asList(moviesStore);
    }

    private void showMovieFragment(String id) {

        MovieFragment movieFragment = MovieFragment.newInstance(id);
        getFragmentManager().beginTransaction().replace(R.id.root, movieFragment).addToBackStack(null).commit();
    }

    private void setLoadingFrame(boolean show) {
        progress_loading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override public void onClicked(Movie movie) {
        setLoadingFrame(true);
        get(this).getRestfulActionCreator().getMovie(movie.getId());
    }

    @Override public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    private void refresh() {
        setLoadingFrame(true);
        get(this).getRestfulActionCreator().getMovies();
    }
}
