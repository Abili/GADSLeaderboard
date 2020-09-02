package com.raisac.gadsleaderboard.ui.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raisac.gadsleaderboard.R;
import com.raisac.gadsleaderboard.adapters.LeaderBoardAdapter;
import com.raisac.gadsleaderboard.models.LearnersResponse;
import com.raisac.gadsleaderboard.viewmodel.LearnersViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements LeaderBoardAdapter.OnDetailsListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private LearnersViewModel mLearnersViewModel;
    private LeaderBoardAdapter mLeaderBoardAdapter;
    private List<LearnersResponse> mCardList;
    ProgressBar mProgressBar;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCardList = new ArrayList<>();
        mLeaderBoardAdapter = new LeaderBoardAdapter(this);
//        mCompositeDisposable = new CompositeDisposable();

        mLearnersViewModel = new ViewModelProvider(this).get(LearnersViewModel.class);
        mLearnersViewModel.init();
        mLearnersViewModel.getLearnersData().observe(this, cardsResponse -> {

            if (cardsResponse != null) {
                mProgressBar.setVisibility(View.INVISIBLE);
                mLeaderBoardAdapter.setLearnersResponses(mLearnersViewModel.getLearnersData().getValue());
                mLeaderBoardAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_leader_board, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.section_label);
        mProgressBar = root.findViewById(R.id.progressBar);

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mLeaderBoardAdapter);
        return root;
    }

    @Override
    public void onCardClick(int position) {

    }
}