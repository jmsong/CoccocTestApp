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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MovieFragment extends Fragment {
  private static final String ARG_USER_ID = "userId";
  @BindView(R.id.user_name) TextView userNameView;
  @BindView(R.id.login) TextView loginView;
  @BindView(R.id.statistics) TextView statsView;
  private String userId;

  private Unbinder unbinder;

  public MovieFragment() {
    // Required empty public constructor
  }

  public static MovieFragment newInstance(String userId) {
    MovieFragment fragment = new MovieFragment();
    Bundle args = new Bundle();
    args.putString(ARG_USER_ID, userId);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null) {
      userId = getArguments().getString(ARG_USER_ID);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_movie, container, false);

    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Movie user = MoviesStore.get(CoccocTestApp.get(getActivity()).getRxFlux().getDispatcher()).getMovie(userId);
    userNameView.setText(user.getName());
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
//    unbinder.unbind();
  }
}
