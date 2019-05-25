package com.example.imyas;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfilePortfolioAdapter extends RecyclerView.Adapter<ProfilePortfolioAdapter.ViewHolder> {

    private Context mContext;
    private List<PortfolioModel> mUploads;

    public ProfilePortfolioAdapter(Context mContext, List<PortfolioModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profilelayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final PortfolioModel currentUpload = mUploads.get(position);
        Picasso.get().load(currentUpload.getPortfolioImage()).fit().centerCrop().into(holder.portfolioImage);
        holder.portfolioName.setText(currentUpload.getPortfolioDescription());
        holder.portfolioCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ViewAlbumActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView portfolioImage;
        CardView portfolioCard;
        TextView portfolioName;

        public ViewHolder(View itemView){
            super(itemView);

            portfolioImage = itemView.findViewById(R.id.portfolioProfileImage);
            portfolioCard = itemView.findViewById(R.id.portfolioProfileCard);
            portfolioName = itemView.findViewById(R.id.portfolioName);

        }


    }


}
