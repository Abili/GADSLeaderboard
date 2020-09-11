package com.raisac.gadsleaderboard.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.raisac.gadsleaderboard.R;
import com.raisac.gadsleaderboard.apis.LeaderBoardService;
import com.raisac.gadsleaderboard.models.LearnersResponse;
import com.raisac.gadsleaderboard.models.SubmitProject;
import com.raisac.gadsleaderboard.repository.SubmitRepo;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {


    public static final String TAG = SubmitActivity.class.getSimpleName();
    private LeaderBoardService mLeaderBoardService;
    private MutableLiveData<List<LearnersResponse>> mListMutableLiveData;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    String keyword;
    private BottomSheetDialog mDialog;
    private View mMyviews;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmaildress;
    private EditText mGitHubLink;
    private AlertDialog mMAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_project);
        Toolbar toolbar = findViewById(R.id.submissionToolBar);
        setSupportActionBar(toolbar);
        final Context context = this;

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final Button submit = findViewById(R.id.submitBtn);
        mFirstName = findViewById(R.id.firstName);
        mLastName = findViewById(R.id.lastName);
        mEmaildress = findViewById(R.id.emailAddress);
        mGitHubLink = findViewById(R.id.githubLink);

        submit.setOnClickListener(view -> {
            projectSumit();

        });
    }

    private void projectSumit() {
        SubmitProject projectSubmit = new SubmitProject();
        String firs_name = mFirstName.getText().toString();
        String last_name = mLastName.getText().toString();
        String email = mEmaildress.getText().toString();
        String github = mGitHubLink.getText().toString();

        if (TextUtils.isEmpty(firs_name) && firs_name.length() < 2) {
            mFirstName.setError("Required");
        } else if (TextUtils.isEmpty(last_name)) {
            mLastName.setError("Required");
        } else if (TextUtils.isEmpty(email)) {
            mEmaildress.setError("Required");
        } else if (TextUtils.isEmpty(github)) {
            mGitHubLink.setError("Required");
        } else {
            openDialog(R.layout.areyou_sure_dialog);

            projectSubmit.setFirstName(firs_name);
            projectSubmit.setLastName(last_name);
            projectSubmit.setEmailAddress(email);
            projectSubmit.setLintToProject(github);

            Button sure = mDialog.findViewById(R.id.sure_btn);
            ImageView cancel = mDialog.findViewById(R.id.cancel_submission);

            assert sure != null;
            sure.setOnClickListener(v -> {
                mDialog.dismiss();
                SubmitRepo.Submit
                        .submitProject(email, firs_name, last_name, github)
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (!response.isSuccessful()) {
                                    openDialog(R.layout.failure_dialog);
                                    Log.d(TAG, response.message());
                                } else {
                                    openDialog(R.layout.sucess_dialog);
                                    mFirstName.setText("");
                                    mLastName.setText("");
                                    mEmaildress.setText("");
                                    mGitHubLink.setText("");
                                }

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                openDialog(R.layout.failure_dialog);
                                Log.d(TAG, t.getMessage(), t);
                            }

                        });
            });
            assert cancel != null;
            cancel.setOnClickListener(v -> mDialog.dismiss());

        }

    }

    public void openDialog(int dialog_layout) {
        mDialog = new BottomSheetDialog(SubmitActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        mMyviews = inflater.inflate(dialog_layout, null);
        mDialog.setContentView(mMyviews);
        mDialog.show();
    }

}


