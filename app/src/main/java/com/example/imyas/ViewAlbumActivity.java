package com.example.imyas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewAlbumActivity extends AppCompatActivity {

    RecyclerView albumList;
    AlbumAdapter albumAdapter;
    List<AlbumImagesModel> imagesList;
    Date currentTime = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_album_activity);
        albumList = findViewById(R.id.albumList);

        albumList.setHasFixedSize(true);
        albumList.setLayoutManager(new LinearLayoutManager(this));

        imagesList = new ArrayList<>();
        imagesList.add(new AlbumImagesModel("1", "1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/MATU.png?alt=media&token=51906e34-1b6d-4f6f-b31d-cec38c7df4ea", "Sample Description", currentTime ));
        imagesList.add(new AlbumImagesModel("1", "1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/miracle.png?alt=media&token=a936eae3-affc-46c4-8020-11aa2de9c203", "Sample Description", currentTime ));
        imagesList.add(new AlbumImagesModel("1", "1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/MATU.png?alt=media&token=51906e34-1b6d-4f6f-b31d-cec38c7df4ea", "Sample Description", currentTime ));
        imagesList.add(new AlbumImagesModel("1", "1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/miracle.png?alt=media&token=a936eae3-affc-46c4-8020-11aa2de9c203", "Sample Description", currentTime ));


        albumAdapter = new AlbumAdapter(this, imagesList);
        albumList.setAdapter(albumAdapter);





    }
}
