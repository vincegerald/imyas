package com.example.imyas;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ClickActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        profile = findViewById(R.id.profile);

        String name = getIntent().getStringExtra("artistName");

        profile.setText(name);

        bottomNavigationView = findViewById(R.id.nav_bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.screen, new ProfileFragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectFragment = null;

                switch (item.getItemId()){

                    case R.id.profile:
                        selectFragment = new ProfileFragment();
                        break;
                    case R.id.portfolio:
                        selectFragment = new PortfolioFragment();
                        break;

                    case R.id.review:
                        selectFragment = new CustomerReviewFragment();
                        break;

                    case R.id.book:
                        selectFragment = new BookFragment();
                        break;
                }
                 getSupportFragmentManager().beginTransaction().replace(R.id.screen, selectFragment).commit();

                return true;
            }
        });
    }
}
