package com.example.imyas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArtistRegistration extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    ArrayList<String> services = new ArrayList<String>();
    ArrayList<String> servicesList = new ArrayList<>();

    ImageView check1, check2;
    Button goStep2, register;
    ScrollView step1, step2;
    TextInputEditText firstname, lastname, contact, email, password;
    CheckBox hair, makeup;
    RadioButton male, female;
    RadioGroup gender;

    //strings

    private String genderString="Male";
    private String image;
    private String hairString = "false";
    private String makeupString = "false";

    //firebase

    FirebaseAuth mAuth;
    FirebaseDatabase mRegister, mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_registration);

        //firebase
        mAuth = FirebaseAuth.getInstance();
        mRegister = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        //checkbox
        hair = findViewById(R.id.hair);
        makeup = findViewById(R.id.makeup);
        //end of checkbox

        //radio group
        gender = findViewById(R.id.gender);

        //end of radio group

        //radio group listener
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.male:
                        genderString = "Male";
                        break;
                    case R.id.female:
                        genderString = "Female";

                }
            }
        });
        //end of radio group listener

        //checkbox listener
        hair.setOnCheckedChangeListener(this);
        makeup.setOnCheckedChangeListener(this);
        //end of checkbox listener

        //edittext
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        //end of edittext

        //edittext textwatchers
        firstname.addTextChangedListener(registerTextWatcher);
        lastname.addTextChangedListener(registerTextWatcher);
        contact.addTextChangedListener(registerTextWatcher);
        email.addTextChangedListener(registerTextWatcher);
        password.addTextChangedListener(registerTextWatcher);
        //end of edittext textwatcher

        //scrollview
        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        //end of scrollview

        //imageview
        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        //end of imageview

        //button
        goStep2 = findViewById(R.id.goStep2);
        goStep2.setBackgroundResource(R.drawable.disable_button);
        register = findViewById(R.id.register);
        register.setBackgroundResource(R.drawable.disable_button);
        //end of button

        //button listner
        goStep2.setOnClickListener(this);
        register.setOnClickListener(this);
        //end of button listener

        //if array is not empty



        //end if

    }

    //onstart

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
                            Intent intent = new Intent(ArtistRegistration.this, ArtistHome.class);
                            startActivity(intent);
                            Toast.makeText(ArtistRegistration.this, "Welcome Artist", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intent = new Intent(ArtistRegistration.this, UserHome.class);
                            startActivity(intent);
                            Toast.makeText(ArtistRegistration.this, "Welcome User", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(ArtistRegistration.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    //end of onstart

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){

            case R.id.goStep2:
                step1.setVisibility(View.GONE);
                step2.setVisibility(View.VISIBLE);
                break;

            case R.id.register:
                artistRegister();

        }
    }

    //artist register
    private void artistRegister(){

        final String fname = firstname.getText().toString().trim();
        final String lname = lastname.getText().toString().trim();
        final String cont = contact.getText().toString().trim();
        final String em = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();



        if(genderString.equals("Male")){
            image = "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/male.png?alt=media&token=af3b743c-ad0d-465c-9bcc-e8568648de29";
        }
        else{
            image = "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/female.jpg?alt=media&token=a7245235-3310-419a-81cd-7715242ddbdf";
        }




        for(int i = 0; i < services.size(); i++){
            if(services.get(i).equals("Hair")){
                hairString = "true";
            }
            else if(services.get(i).equals("Makeup")){
                makeupString = "true";
            }
        }

        // String id, String firstname, String lastname, String contactNumber, String image, String email, String gender, String password, String hair, String makeUp, Double rating

        final Double rating = 5.0;

        mAuth.createUserWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    ArtistModel artistModel = new ArtistModel(
                            FirebaseAuth.getInstance().getCurrentUser().getUid(),
                            fname,
                            lname,
                            cont,
                            image,
                            em,
                            genderString,
                            pass,
                            hairString,
                            makeupString,
                            rating
                    );
                    Toast.makeText(ArtistRegistration.this, "asd", Toast.LENGTH_SHORT).show();
                    FirebaseDatabase.getInstance().getReference("Artist").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(artistModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ArtistRegistration.this, "Successful Registration", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ArtistRegistration.this, ArtistHome.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(ArtistRegistration.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
                }
                else{
                    Log.w("error", task.getException());
                }
            }
        });




    }
    //end of artist register

    //text watcher method

    private TextWatcher registerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String firstnameInput = firstname.getText().toString().trim();
            String lastnameInput = lastname.getText().toString().trim();
            String contactInput = contact.getText().toString().trim();
            String emailInput = email.getText().toString().trim();
            String passwordeInput = password.getText().toString().trim();


            if(!firstnameInput.isEmpty() && !lastnameInput.isEmpty() && !contactInput.isEmpty() && !emailInput.isEmpty() && !passwordeInput.isEmpty()){
                goStep2.setEnabled(true);
                goStep2.setBackgroundResource(R.drawable.round_button);
                check1.setVisibility(View.VISIBLE);
            }

            else{
                goStep2.setEnabled(false);
                goStep2.setBackgroundResource(R.drawable.disable_button);
                check1.setVisibility(View.GONE);
            }


        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        int id = compoundButton.getId();


        switch (id){
            case R.id.hair:
                if(b){
                    Toast.makeText(this, "Added Service for Hair", Toast.LENGTH_SHORT).show();
                    services.add("Hair");
                    if(services.size() > 0){
                        register.setEnabled(true);
                        register.setBackgroundResource(R.drawable.round_button);
                        check2.setVisibility(View.VISIBLE);
                        //Toast.makeText(this, "Not empty", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        register.setEnabled(false);
                        register.setBackgroundResource(R.drawable.disable_button);
                        check2.setVisibility(View.GONE);
                    }
//                    register.setEnabled(true);
//                    register.setBackgroundResource(R.drawable.round_button);
//                    check2.setVisibility(View.VISIBLE);
                    break;
                }
                else{
                    services.remove("Hair");
                    Toast.makeText(this, "Removed Service for Hair", Toast.LENGTH_SHORT).show();
                    if(services.size() > 0){
                        register.setEnabled(true);
                        register.setBackgroundResource(R.drawable.round_button);
                        check2.setVisibility(View.VISIBLE);
                        //Toast.makeText(this, "Not empty", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        register.setEnabled(false);
                        register.setBackgroundResource(R.drawable.disable_button);
                        check2.setVisibility(View.GONE);
                    }
                    break;
                }

            case R.id.makeup:
                if(b){

                    Toast.makeText(this, "Added Service for Makeup", Toast.LENGTH_SHORT).show();
                    services.add("Makeup");

                    if(services.size() > 0){
                        register.setEnabled(true);
                        register.setBackgroundResource(R.drawable.round_button);
                        check2.setVisibility(View.VISIBLE);
                    }
                    else{
                        register.setEnabled(false);
                        register.setBackgroundResource(R.drawable.disable_button);
                        check2.setVisibility(View.GONE);
                    }
//                    register.setEnabled(true);
//                    register.setBackgroundResource(R.drawable.round_button);
//                    check2.setVisibility(View.VISIBLE);
                    break;
                }
                else{
                    services.remove("Makeup");
                    Toast.makeText(this, "Removed Service for Makeup", Toast.LENGTH_SHORT).show();
                    if(services.size() > 0){
                        register.setEnabled(true);
                        register.setBackgroundResource(R.drawable.round_button);
                        check2.setVisibility(View.VISIBLE);
                    }
                    else{
                        register.setEnabled(false);
                        register.setBackgroundResource(R.drawable.disable_button);
                        check2.setVisibility(View.GONE);
                    }
                    break;
                }


        }
    }

    //end of text watcher method

    //check if there is internet connection

    private boolean networkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
