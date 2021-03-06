package com.example.imyas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PortfolioFragment extends Fragment {

    RecyclerView portfolioList;
    List<PortfolioModel> portfolioLst;
    ProfilePortfolioAdapter profilePortfolioAdapter;
    Date currentTime = Calendar.getInstance().getTime();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_portfolio, container, false);
        portfolioList = v.findViewById(R.id.portfolioList);
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



        profilePortfolioAdapter = new ProfilePortfolioAdapter(getContext(), portfolioLst);
        int numberOfColumns = 2;
        portfolioList.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        portfolioList.setAdapter(profilePortfolioAdapter);
        return v;
    }


}
