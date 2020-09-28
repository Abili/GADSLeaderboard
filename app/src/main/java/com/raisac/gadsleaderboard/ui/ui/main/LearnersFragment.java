package com.raisac.gadsleaderboard.ui.ui.main;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raisac.gadsleaderboard.R;
import com.raisac.gadsleaderboard.adapters.LeaderBoardAdapter;
import com.raisac.gadsleaderboard.databinding.DetailsDialogBinding;
import com.raisac.gadsleaderboard.models.LearnersResponse;
import com.raisac.gadsleaderboard.viewmodel.LearnersViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class LearnersFragment extends Fragment implements LeaderBoardAdapter.OnDetailsListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private LearnersViewModel mLearnersViewModel;
    private LeaderBoardAdapter mLeaderBoardAdapter;
    private List<LearnersResponse> mCardList;
    ProgressBar mProgressBar;
    private AlertDialog.Builder mMDialog;
    private AlertDialog mNDialog;
    private View mMMyviews;

    public static LearnersFragment newInstance(int index) {
        LearnersFragment fragment = new LearnersFragment();
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
//      mCompositeDisposable = new CompositeDisposable();

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
        LearnersResponse response = mLearnersViewModel.getLearnersData().getValue().get(position);
        openDialog(R.layout.details_dialog);
        DetailsDialogBinding card_details = DataBindingUtil.setContentView(getActivity(), R.layout.details_dialog);
        card_details.setLearnerResponse(response);
    }

    public void openDialog(int dialog_layout) {
        mMDialog = new AlertDialog.Builder(getActivity());
        mNDialog = mMDialog.create();
        LayoutInflater inflater = getLayoutInflater();
        mMMyviews = inflater.inflate(dialog_layout, null);
        mNDialog.setView(mMMyviews);
        mNDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mNDialog.show();
    }
}