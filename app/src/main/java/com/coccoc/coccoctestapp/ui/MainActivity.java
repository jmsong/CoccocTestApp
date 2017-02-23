package com.coccoc.coccoctestapp.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.coccoc.coccoctestapp.CoccocTestApp;
import com.coccoc.coccoctestapp.R;
import com.coccoc.coccoctestapp.actions.Actions;
import com.coccoc.coccoctestapp.actions.Keys;
import com.coccoc.coccoctestapp.dagger.DaggerManager;
import com.coccoc.coccoctestapp.model.Movie;
import com.coccoc.coccoctestapp.stores.MoviesStore;
import com.coccoc.coccoctestapp.ui.navigation.NavigationManager;
import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.dispatcher.RxViewDispatch;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RxViewDispatch, MovieAdapter.OnMovieClick, NavigationManager.NavigationListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_loading)
    ProgressBar progress_loading;
    @BindView(R.id.root_coordinator)
    CoordinatorLayout coordinatorLayout;
    private MovieAdapter adapter;

    @Inject
    public NavigationManager mNavigationManager;

    @Inject
    public CoccocTestApp app;

    @Inject
    public MoviesStore moviesStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inject members
        DaggerManager.component().inject(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // Initialize the NavigationManager with this activity's FragmentManager
        mNavigationManager.init(getSupportFragmentManager());
        mNavigationManager.setNavigationListener(this);

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
            Snackbar.make(coordinatorLayout, "An error occurred" + throwable.getMessage(), Snackbar.LENGTH_INDEFINITE).setAction("Retry", v -> refresh()).show();
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
        return Arrays.asList(moviesStore);
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToUnRegister() {
        return Arrays.asList(moviesStore);
    }

    private void showMovieFragment(String id) {
        MovieFragment movieFragment = MovieFragment.newInstance(id);
        getSupportFragmentManager().beginTransaction().replace(R.id.root, movieFragment).addToBackStack(null).commit();
    }

    private void setLoadingFrame(boolean show) {
        progress_loading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override public void onClicked(Movie movie) {
        setLoadingFrame(true);
        app.getRestfulActionCreator().getMovie(movie.getId());
    }

    @Override public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            // we have only one fragment left so we would close the application with this back
            showExitDialog();
        } else {
            mNavigationManager.navigateBack(this);
        }
    }

    /**
     * Shows the logout dialog. Stops the service and finishes the application.
     */
    protected void showExitDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.exit_message).setCancelable(false).setPositiveButton(android.R.string.yes,
                (dialog, id) -> finish()).setNegativeButton(android.R.string.cancel, null);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void refresh() {
        setLoadingFrame(true);
        app.getRestfulActionCreator().getMovies();
    }

    @Override
    public void onBackstackChanged() {

    }
}
