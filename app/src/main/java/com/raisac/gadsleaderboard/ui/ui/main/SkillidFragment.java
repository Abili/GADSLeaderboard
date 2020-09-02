package com.raisac.gadsleaderboard.ui.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raisac.gadsleaderboard.R;
import com.raisac.gadsleaderboard.adapters.LeaderBoardAdapter;
import com.raisac.gadsleaderboard.adapters.SkillIQBoardAdapter;
import com.raisac.gadsleaderboard.models.LearnersResponse;
import com.raisac.gadsleaderboard.viewmodel.LearnersViewModel;

import java.util.ArrayList;
import java.util.List;

public class SkillidFragment extends Fragment implements LeaderBoardAdapter.OnDetailsListener, SkillIQBoardAdapter.OnDetailsListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private LearnersViewModel mLearnersViewModel;
    private SkillIQBoardAdapter mIQBoardAdapter;
    private List<LearnersResponse> mCardList;
    ProgressBar mProgressBar;

    public static SkillidFragment newInstance(int index) {
        SkillidFragment fragment = new SkillidFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCardList = new ArrayList<>();
        mIQBoardAdapter = new SkillIQBoardAdapter(this);

//        mCompositeDisposable = new CompositeDisposable();

        mLearnersViewModel = new ViewModelProvider(this).get(LearnersViewModel.class);
        mLearnersViewModel.init();
        mLearnersViewModel.getSkillIQData().observe(this, skillIQresponse -> {

            if (skillIQresponse != null) {
                mProgressBar.setVisibility(View.INVISIBLE);
                mIQBoardAdapter.setIQResponses(mLearnersViewModel.getSkillIQData().getValue());
                mIQBoardAdapter.notifyDataSetChanged();
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
        recyclerView.setAdapter(mIQBoardAdapter);
        return root;
    }

    @Override
    public void onCardClick(int position) {

    }
}
