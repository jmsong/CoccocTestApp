package com.coccoc.coccoctestapp.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.coccoc.coccoctestapp.CoccocTestApp;
import com.coccoc.coccoctestapp.R;
import com.coccoc.coccoctestapp.actions.Actions;
import com.coccoc.coccoctestapp.stores.MoviesStore;
import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.dispatcher.RxViewDispatch;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RxViewDispatch {

    private MoviesStore moviesStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        refresh();
    }

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        switch (change.getStoreId()) {
            case MoviesStore.ID:
                switch (change.getRxAction().getType()) {
                    case Actions.GET_MOVIES:
                        // TODO: set data source for adapter again
                        break;

                    case Actions.GET_MOVIE:
                        break;
                }
                break;
        }
    }

    @Override
    public void onRxError(@NonNull RxError error) {
        Throwable throwable = error.getThrowable();
        if (throwable != null) {
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
        moviesStore = MoviesStore.get(CoccocTestApp.get(this).getRxFlux().getDispatcher());
        moviesStore.register();
        return Arrays.asList(moviesStore);
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToUnRegister() {
        moviesStore.unregister();
        return Arrays.asList(moviesStore);
    }

    private void refresh() {
//        CoccocTestApp.get(this).getRestfulActionCreator().getMovies();
        CoccocTestApp.get(this).getRestfulActionCreator().getMovie("1901");
    }
}
