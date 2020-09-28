package com.raisac.gadsleaderboard.repository;

import com.raisac.gadsleaderboard.apis.LeaderBoardService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitRepo {

    public static class Submit {
        private LeaderBoardService mLeaderBoardService;
        private static final String SUBMISION_URL = "https://docs.google.com/forms/d/e/";
        public static final Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(SUBMISION_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        public static final LeaderBoardService sLeaderBoardService =
                retrofit.create(LeaderBoardService.class);

        public static Call<Void> submitProject(String fName, String lName, String email, String link) {
            return sLeaderBoardService.submitProject(fName, lName, email, link);
        }

    }


}
