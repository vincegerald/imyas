package com.example.imyas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView signupUser, signupArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signupUser = findViewById(R.id.signupUser);
        signupArtist = findViewById(R.id.signupArtist);


        //listeners

        //textview
        signupUser.setOnClickListener(this);
        signupArtist.setOnClickListener(this);

        //end of listeners
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.signupUser:
                Intent intent = new Intent(MainActivity.this, RegisterUser.class);
                startActivity(intent);
                break;

            case R.id.signupArtist:
                Intent artistMain = new Intent(MainActivity.this, ArtistLogin.class);
                startActivity(artistMain);
        }

    }
}
