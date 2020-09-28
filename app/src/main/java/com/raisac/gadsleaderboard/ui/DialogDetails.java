package com.raisac.gadsleaderboard.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.raisac.gadsleaderboard.R;
import com.raisac.gadsleaderboard.databinding.DetailsDialogBinding;
import com.raisac.gadsleaderboard.models.LearnersResponse;

public class DialogDetails extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        LearnersResponse response = intent.getParcelableExtra("response");
//        if (response != null) {
//            Picasso.get()
//                    .load(response.getBadgeUrl())
//                .into(cardDetail)
//        }


        DetailsDialogBinding card_details = DataBindingUtil.setContentView(getActivity(), R.layout.details_dialog);
        card_details.setLearnerResponse(response);

        AlertDialog.Builder details = new AlertDialog.Builder(getContext());
        AlertDialog mDetail = details.create();
        LayoutInflater inflater = getLayoutInflater();
        View mMyviews = inflater.inflate(R.layout.details_dialog, null);
        mDetail.setView(mMyviews);
        mDetail.show();
        return details.create();
    }
}