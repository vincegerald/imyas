package com.example.imyas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ArtistProfileActivity extends AppCompatActivity {



    RecyclerView portfolioList;

    List<PortfolioModel> portfolioLst;
    ProfilePortfolioAdapter profilePortfolioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_profile);

        portfolioList = findViewById(R.id.portfolioList);

        Date currentTime = Calendar.getInstance().getTime();

        portfolioLst = new ArrayList<>();
        portfolioLst.add(new PortfolioModel("1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/miracle.png?alt=media&token=a936eae3-affc-46c4-8020-11aa2de9c203",
                "1", "Sample", currentTime));
        portfolioLst.add(new PortfolioModel("1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/MATU.png?alt=media&token=51906e34-1b6d-4f6f-b31d-cec38c7df4ea",
                "1", "Sample", currentTime));
        portfolioLst.add(new PortfolioModel("1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/miracle.png?alt=media&token=a936eae3-affc-46c4-8020-11aa2de9c203",
                "1", "Sample", currentTime));
        portfolioLst.add(new PortfolioModel("1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/MATU.png?alt=media&token=51906e34-1b6d-4f6f-b31d-cec38c7df4ea",
                "1", "Sample", currentTime));
        portfolioLst.add(new PortfolioModel("1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/miracle.png?alt=media&token=a936eae3-affc-46c4-8020-11aa2de9c203",
                "1", "Sample", currentTime));
        portfolioLst.add(new PortfolioModel("1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/MATU.png?alt=media&token=51906e34-1b6d-4f6f-b31d-cec38c7df4ea",
                "1", "Sample", currentTime));

        portfolioLst.add(new PortfolioModel("1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/miracle.png?alt=media&token=a936eae3-affc-46c4-8020-11aa2de9c203",
                "1", "Sample", currentTime));
        portfolioLst.add(new PortfolioModel("1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/MATU.png?alt=media&token=51906e34-1b6d-4f6f-b31d-cec38c7df4ea",
                "1", "Sample", currentTime));
        portfolioLst.add(new PortfolioModel("1", "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/miracle.png?alt=media&token=a936eae3-affc-46c4-8020-11aa2de9c203",
                "1", "Sample", currentTime));



        profilePortfolioAdapter = new ProfilePortfolioAdapter(this, portfolioLst);
        int numberOfColumns = 2;
        portfolioList.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        portfolioList.setAdapter(profilePortfolioAdapter);

    }
}
