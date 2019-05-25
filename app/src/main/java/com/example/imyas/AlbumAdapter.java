package com.example.imyas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private Context mContext;
    private List<AlbumImagesModel> mUploads;

    public AlbumAdapter(Context mContext, List<AlbumImagesModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.albumlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final AlbumImagesModel currentUpload = mUploads.get(position);
        Picasso.get().load(currentUpload.getImageImage()).fit().centerCrop().into(holder.imageImage);
        holder.imageDescripntion.setText(currentUpload.getImageDescription());

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        ImageView imageImage;
        CardView imageCard;
        TextView imageDescripntion;

        public ViewHolder(View itemView){
            super(itemView);

            imageCard = itemView.findViewById(R.id.imageCard);
            imageImage = itemView.findViewById(R.id.imageImage);
            imageDescripntion = itemView.findViewById(R.id.imageDescription);

        }
    }
}
