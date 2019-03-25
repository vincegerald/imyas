package com.example.imyas;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView signupUser, signupArtist;
    Button login;
    EditText username, password;

    //firebase

    FirebaseAuth mAuth;
    FirebaseDatabase mRegister, mDatabase;

    //progressbar
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textview
        signupUser = findViewById(R.id.signupUser);
        signupArtist = findViewById(R.id.signupArtist);
        //button
        login = findViewById(R.id.login);
        login.setBackgroundResource(R.drawable.disable_button);

        //edittext
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        //edit text textwatchers
        username.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);

        //firebase
        mAuth = FirebaseAuth.getInstance();
        mRegister = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        //progressbar
        progressbar = findViewById(R.id.progressbar);


        //listeners

        //textview
        signupUser.setOnClickListener(this);
        signupArtist.setOnClickListener(this);
        //end of textview
        //button
        login.setOnClickListener(this);
        //end of button

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
                break;

            case R.id.login:
                login();

        }

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
    protected void onStart() {
        super.onStart();

        if(!networkConnection()){
            Snackbar.make(null, "No internet connection...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        else{
            final FirebaseUser firebaseUser = mAuth.getCurrentUser();

            if(firebaseUser != null){
                mDatabase.getReference("Artist").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Intent intent = new Intent(MainActivity.this, ArtistHome.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Welcome Artist", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intent = new Intent(MainActivity.this, UserHome.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Welcome User", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
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
                                Intent intent = new Intent(MainActivity.this, UserHome.class);
                                startActivity(intent);
                            }
                            else{
                                progressbar.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }


    }

    //check if there is internet connection

    private boolean networkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
