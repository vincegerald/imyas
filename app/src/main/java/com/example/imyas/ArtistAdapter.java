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
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    private List<ArtistModel> mUploads;
    private Context mContext;

    public ArtistAdapter(List<ArtistModel> mUploads, Context mContext) {
        this.mUploads = mUploads;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artistlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ArtistModel uploadCurrent = mUploads.get(position);

        holder.artistName.setText(uploadCurrent.getFirstname() + " " + uploadCurrent.getLastname());
        holder.artistRating.setRating(Float.parseFloat(uploadCurrent.getRating().toString()));
        Picasso.get().load(uploadCurrent.getImage()).fit().centerCrop().into(holder.artistImage);

        if(uploadCurrent.getHair().equals("true")){
            holder.hair.setVisibility(View.VISIBLE);
        }

        if(uploadCurrent.getMakeUp().equals("true")){
            holder.makeup.setVisibility(View.VISIBLE);
        }

        holder.finalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ClickActivity.class);
                intent.putExtra("artistName", uploadCurrent.getFirstname() + uploadCurrent.getLastname());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView artistImage, hair, makeup;
        TextView artistName;
        RatingBar artistRating;
        CardView finalCard;

        public ViewHolder(View itemView) {
            super(itemView);
            artistImage = itemView.findViewById(R.id.artistImage);
            artistName = itemView.findViewById(R.id.artistName);
            artistRating = itemView.findViewById(R.id.artistRating);
            finalCard = itemView.findViewById(R.id.finalCard);
            hair = itemView.findViewById(R.id.hair);
            makeup = itemView.findViewById(R.id.makeup);
        }
    }
}
