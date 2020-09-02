package com.raisac.gadsleaderboard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raisac.gadsleaderboard.R;
import com.raisac.gadsleaderboard.StringUtil;
import com.raisac.gadsleaderboard.models.LearnersResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SkillIQBoardAdapter extends RecyclerView.Adapter<SkillIQBoardAdapter.ViewHolder> {
    private List<LearnersResponse> mLearnersResponses = new ArrayList<>();
    private OnDetailsListener mOnDetailsListener;

    public SkillIQBoardAdapter(OnDetailsListener onDetailsListener) {
        mOnDetailsListener = onDetailsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leaner_model, parent, false);

        return new ViewHolder(itemView, mOnDetailsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LearnersResponse learnersResponse = mLearnersResponses.get(position);

        holder.leaner_name.setText(learnersResponse.getName());
        holder.learner_score.setText(
                StringUtil.skillIQToString(learnersResponse.getScore()));
        holder.learner_country.setText(learnersResponse.getCountry());
        //image

        if (learnersResponse.getBadgeUrl() != null) {
            String imageUrl = learnersResponse.getBadgeUrl();
//                    .replace("http://", "https://");
            Picasso.get()
                    .load(imageUrl)
                    .into(holder.learnerImage);
        }

    }

    @Override
    public int getItemCount() {
        return mLearnersResponses.size();
    }

    public void setIQResponses(List<LearnersResponse> learnersResponses) {
        this.mLearnersResponses = learnersResponses;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.learnerName)
        TextView leaner_name;
        @BindView(R.id.learnerHours)
        TextView learner_score;
        @BindView(R.id.learnerCountry)
        TextView learner_country;
        @BindView(R.id.learner_Image)
        ImageView learnerImage;

        OnDetailsListener mOnDetailsListener;

        public ViewHolder(@NonNull View itemView, OnDetailsListener onDetailsListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //mBinding = DataBindingUtil.bind(itemView);
            this.mOnDetailsListener = onDetailsListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            mOnDetailsListener.onCardClick(getAdapterPosition());
        }
    }

    public interface OnDetailsListener {
        void onCardClick(int position);

    }
}
