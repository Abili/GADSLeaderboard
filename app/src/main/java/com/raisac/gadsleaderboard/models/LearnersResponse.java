package com.raisac.gadsleaderboard.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "learners_table")
public class LearnersResponse {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private final String mName;

    @ColumnInfo(name = "hours")
    @SerializedName("hours")
    private final int mHours;

    @ColumnInfo(name = "score")
    @SerializedName("score")
    private final int mScore;

    @ColumnInfo(name = "country")
    @SerializedName("country")
    private final String mCountry;

    @ColumnInfo(name = "badgeUrl")
    @SerializedName("badgeUrl")
    private final String mBadgeUrl;

    public LearnersResponse(String name, int hours, Integer score, String country, String badgeUrl) {
        this.mName = name;
        this.mHours = hours;
        this.mScore = score;
        this.mCountry = country;
        this.mBadgeUrl = badgeUrl;
    }

    public int getScore() {
        return mScore;
    }

    @NotNull
    public String getName() {
        return mName;
    }

    public int getHours() {
        return mHours;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getBadgeUrl() {
        return mBadgeUrl;
    }

    @NotNull
    @Override
    public String toString() {
        return "LearnersResponse{" +
                "mName='" + mName + '\'' +
                ", mHours=" + mHours +
                ", mScore=" + mScore +
                ", mCountry='" + mCountry + '\'' +
                ", mBadgeUrl='" + mBadgeUrl + '\'' +
                '}';
    }
}

