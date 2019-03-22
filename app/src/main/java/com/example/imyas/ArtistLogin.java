
package com.example.imyas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ArtistLogin extends AppCompatActivity implements View.OnClickListener {

    TextView signupUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_login);
        signupUser = findViewById(R.id.signupUser);

        //listeners

        //textview
        signupUser.setOnClickListener(this);

        //end of listeners
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){

            case R.id.signupUser:
                Intent intent = new Intent(ArtistLogin.this, ArtistRegistration.class);
                startActivity(intent    );

        }
    }
}
