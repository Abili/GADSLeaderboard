package com.raisac.gadsleaderboard.apis;

import com.raisac.gadsleaderboard.models.LearnersResponse;
import com.raisac.gadsleaderboard.models.SubmitProject;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LeaderBoardService {
    @GET("api/hours")
    Observable<List<LearnersResponse>> getLeaners();
    @GET("api/skilliq")
    Observable<List<LearnersResponse>> getSkillIQ();

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<SubmitProject>submitProject(
            @Field("entry.1824927963") String emailAddress,
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String lastName,
            @Field("entry.284483984") String linkToProject

    );
}
