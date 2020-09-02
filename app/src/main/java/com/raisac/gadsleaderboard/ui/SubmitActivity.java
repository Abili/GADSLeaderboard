package com.raisac.gadsleaderboard.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;

import com.raisac.gadsleaderboard.R;
import com.raisac.gadsleaderboard.apis.LeaderBoardService;
import com.raisac.gadsleaderboard.apis.ServiceBuilder;
import com.raisac.gadsleaderboard.models.LearnersResponse;
import com.raisac.gadsleaderboard.models.SubmitProject;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity implements View.OnClickListener {


    private LeaderBoardService mLeaderBoardService;
    private MutableLiveData<List<LearnersResponse>> mListMutableLiveData;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    String keyword;
    private AlertDialog.Builder mDialog;
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

            dialogsSuccess(R.layout.areyou_sure_dialog);
            mMyviews.setOnClickListener(this);

        });
    }

    private void projectSumit(EditText firstName, EditText lastName, EditText emaildress, EditText gitHubLink) {
        SubmitProject projectSubmit = new SubmitProject();
        String firs_name = firstName.getText().toString();
        String last_name = lastName.getText().toString();
        String email = emaildress.getText().toString();
        String github = gitHubLink.getText().toString();

        if (TextUtils.isEmpty(firs_name)) {
            firstName.setError("Required");
        } else if (TextUtils.isEmpty(last_name)) {
            lastName.setError("Required");
        } else if (TextUtils.isEmpty(email)) {
            emaildress.setError("Required");
        } else if (TextUtils.isEmpty(github)) {
            gitHubLink.setError("Required");
        } else {
            projectSubmit.setFirstName(firs_name);
            projectSubmit.setLastName(last_name);
            projectSubmit.setEmailAddress(email);
            projectSubmit.setLintToProject(github);
        }

        LeaderBoardService ideaService = ServiceBuilder.buildService(LeaderBoardService.class);
        Call<SubmitProject> creatRequest = ideaService.submitProject(
                projectSubmit.getEmailAddress(),
                projectSubmit.getFirstName(),
                projectSubmit.getLastName(),
                projectSubmit.getLintToProject());
        creatRequest.enqueue(new Callback<SubmitProject>() {
            @Override
            public void onResponse(Call<SubmitProject> call, Response<SubmitProject> response) {
                dialogsSuccess(R.layout.sucess_dialog);

            }

            @Override
            public void onFailure(Call<SubmitProject> call, Throwable t) {
                dialogsSuccess(R.layout.failure_dialog);
            }
        });
    }

    public void dialogsSuccess(int dialog_layout) {
        mDialog = new AlertDialog.Builder(SubmitActivity.this);
        mMAlertDialog = mDialog.create();
        LayoutInflater inflater = getLayoutInflater();
        mMyviews = inflater.inflate(dialog_layout, null);
        mDialog.setView(mMyviews);
        mDialog.show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_submission:
                mMAlertDialog.dismiss();
                break;
            case R.id.sure_btn:
                projectSumit(mFirstName, mLastName, mEmaildress, mGitHubLink);
                break;
        }
    }
}


