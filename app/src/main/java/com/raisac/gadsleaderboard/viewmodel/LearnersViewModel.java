package com.raisac.gadsleaderboard.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.raisac.gadsleaderboard.models.LearnersResponse;
import com.raisac.gadsleaderboard.repository.LeasderBoardRepository;

import java.util.List;

public class LearnersViewModel extends AndroidViewModel {
    private LiveData<List<LearnersResponse>> mLearningHours;
    private LiveData<List<LearnersResponse>> mSkillIQ;

    public LearnersViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        if (mLearningHours != null) {
            return;
        }
        LeasderBoardRepository leasderBoardRepository = new LeasderBoardRepository();
        mLearningHours = leasderBoardRepository.getLeanersMutableLiveData();

        if(mSkillIQ!=null){
            return;
        }
        LeasderBoardRepository skillIqBoard = new LeasderBoardRepository();
        mSkillIQ = skillIqBoard.getSkilligMutableLiveData();
    }

    public LiveData<List<LearnersResponse>>getLearnersData() {
        return mLearningHours;
    }
    public LiveData<List<LearnersResponse>>getSkillIQData() {
        return mSkillIQ;
    }
}

