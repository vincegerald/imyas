
package com.example.imyas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ArtistLogin extends AppCompatActivity implements View.OnClickListener {

    TextView signupUser;

    //Button
    Button login;
    //edit text
    EditText username, password;
    //progress bar
    ProgressBar progressbar;

    //firebase

    FirebaseAuth mAuth;
    FirebaseDatabase mRegister, mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_login);
        signupUser = findViewById(R.id.signupUser);

        //button
        login = findViewById(R.id.login);
        login.setBackgroundResource(R.drawable.disable_button);
        //edittext
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        //progressbar
        progressbar = findViewById(R.id.progressbar);

        //edit text watchers
        username.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);
        //listeners

        //textview
        signupUser.setOnClickListener(this);

        //button
        login.setOnClickListener(this);

        //end of listeners

        //firebase
        mAuth = FirebaseAuth.getInstance();
        mRegister = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
    }

    //text watcher method

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String usernameInput = username.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();


            if(!usernameInput.isEmpty() && !passwordInput.isEmpty()){
                login.setEnabled(true);
                login.setBackgroundResource(R.drawable.round_button);

            }

            else{
                login.setEnabled(false);
                login.setBackgroundResource(R.drawable.disable_button);
            }


        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){

            case R.id.signupUser:
                Intent intent = new Intent(ArtistLogin.this, ArtistRegistration.class);
                startActivity(intent);
                break;

            case R.id.login:
                login();

        }
    }

    private void login(){
        String finalUsername = username.getText().toString().trim();
        String finalPassword = password.getText().toString().trim();

        if(finalUsername.equals("") || finalPassword.equals("")){
            Toast.makeText(this, "Input all fields", Toast.LENGTH_SHORT).show();
        }
        else{
            progressbar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(finalUsername, finalPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressbar.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(ArtistLogin.this, ArtistHome.class);
                                startActivity(intent);
                            }
                            else{
                                progressbar.setVisibility(View.INVISIBLE);
                                Toast.makeText(ArtistLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }


    }
}
