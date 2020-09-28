package com.raisac.gadsleaderboard.models;


import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.raisac.gadsleaderboard.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "learners_table")
public class LearnersResponse implements Parcelable {

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

    protected LearnersResponse(Parcel in) {
        mName = in.readString();
        mHours = in.readInt();
        mScore = in.readInt();
        mCountry = in.readString();
        mBadgeUrl = in.readString();
    }

    public static final Creator<LearnersResponse> CREATOR = new Creator<LearnersResponse>() {
        @Override
        public LearnersResponse createFromParcel(Parcel in) {
            return new LearnersResponse(in);
        }

        @Override
        public LearnersResponse[] newArray(int size) {
            return new LearnersResponse[size];
        }
    };

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



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mHours);
        dest.writeInt(mScore);
        dest.writeString(mCountry);
        dest.writeString(mBadgeUrl);
    }
    @BindingAdapter("android:imageUrl")
    public static void loadImage(ImageView view,  String imageUrl) {
        if (imageUrl!=null) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.place_holder)
                    .into(view);
        } else {
            view.setBackgroundResource(R.drawable.place_holder);
        }
    }
}

