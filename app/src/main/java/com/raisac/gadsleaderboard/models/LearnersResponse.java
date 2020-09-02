package com.raisac.gadsleaderboard.models;


import com.google.gson.annotations.SerializedName;

public class LearnersResponse {
    @SerializedName("name")
    private final String mName;

    @SerializedName("hours")
    private final int mHours;
    @SerializedName("score")
    private final int mScore;
    @SerializedName("country")
    private final String mCountry;
    @SerializedName("badgeUrl")
    private final String mBadgeUrl;

    public LearnersResponse(String name, int hours, int score, String country, String badgeUrl) {
        this.mName = name;
        this.mHours = hours;
        this.mScore = score;
        this.mCountry = country;
        this.mBadgeUrl = badgeUrl;
    }

    public int getScore() {
        return mScore;
    }

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

