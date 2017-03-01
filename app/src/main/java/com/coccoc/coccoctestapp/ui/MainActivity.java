package com.coccoc.coccoctestapp.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.coccoc.coccoctestapp.CoccocTestApp;
import com.coccoc.coccoctestapp.R;
import com.coccoc.coccoctestapp.actions.Actions;
import com.coccoc.coccoctestapp.dagger.DaggerManager;
import com.coccoc.coccoctestapp.stores.MoviesStore;
import com.coccoc.coccoctestapp.ui.custom.MovieListView;
import com.coccoc.coccoctestapp.ui.custom.ViewContainer;
import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.dispatcher.RxViewDispatch;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RxViewDispatch {

    @BindView(R.id.root) RelativeLayout rootLayout;
    @BindView(R.id.progress_loading) ProgressBar progressBar;
    @BindView(R.id.container) ViewContainer container;
    @BindView(R.id.list_movies) MovieListView movieListView;

    @Inject public CoccocTestApp app;

    @Inject public MoviesStore moviesStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inject members
        DaggerManager.component().inject(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
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
        showLoading(false);
        switch (change.getStoreId()) {
            case MoviesStore.ID:
                switch (change.getRxAction().getType()) {
                    case Actions.GET_MOVIES:
                        movieListView.setMovies(moviesStore.getMovies());
                        break;

                    case Actions.GET_MOVIE:
                        container.showItem(moviesStore.getMovie());
                        break;
                }
                break;
        }
    }

    @Override
    public void onRxError(@NonNull RxError error) {
        showLoading(false);
        Throwable throwable = error.getThrowable();
        if (throwable != null) {
            Snackbar.make(rootLayout, "An error occurred" + throwable.getMessage(), Snackbar.LENGTH_INDEFINITE).setAction("Retry", v -> refresh()).show();
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

    private void showLoading(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override public void onBackPressed() {
        boolean handled = container.onBackPressed();
        if (!handled) {
            showExitDialog();
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
        showLoading(true);
        app.getRestfulActionCreator().getMovies();
    }

    public void getMovie(String movieId) {
        showLoading(true);
        app.getRestfulActionCreator().getMovie(movieId);
    }

    public ViewContainer getContainer() {
        return container;
    }
}
