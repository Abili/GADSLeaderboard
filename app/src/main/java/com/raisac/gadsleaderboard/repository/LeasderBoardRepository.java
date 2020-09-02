package com.raisac.gadsleaderboard.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.raisac.gadsleaderboard.apis.LeaderBoardService;
import com.raisac.gadsleaderboard.models.LearnersResponse;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.net.ConnectivityManager.EXTRA_NO_CONNECTIVITY;

public class LeasderBoardRepository {
    private static final String LEADERBOARD_URL = "https://gadsapi.herokuapp.com/";

    private LeaderBoardService mLeaderBoardService;
    private MutableLiveData<List<LearnersResponse>> mListMutableLiveData;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    String keyword;
    Context mContext;

    public LeasderBoardRepository() {
        mListMutableLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
        mLeaderBoardService = new retrofit2.Retrofit.Builder()
                .baseUrl(LEADERBOARD_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(LeaderBoardService.class);

    }


    public void getLeaners() {
        mLeaderBoardService.getLeaners()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<LearnersResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<LearnersResponse> learnersResponses) {
                        mListMutableLiveData.postValue(learnersResponses);

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListMutableLiveData.postValue(null);
                    }


                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getSkillIQ() {
        mLeaderBoardService.getSkillIQ()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<LearnersResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<LearnersResponse> learnersResponses) {
                        mListMutableLiveData.postValue(learnersResponses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mListMutableLiveData.postValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<LearnersResponse>> getLeanersMutableLiveData() {
        getLeaners();
        return mListMutableLiveData;
    }

    public LiveData<List<LearnersResponse>> getSkilligMutableLiveData() {
        getSkillIQ();
        return mListMutableLiveData;
    }

}
