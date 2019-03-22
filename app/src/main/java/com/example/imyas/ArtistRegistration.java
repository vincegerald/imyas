package com.example.imyas;

import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArtistRegistration extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    ArrayList<String> services = new ArrayList<String>();

    ImageView check1, check2;
    Button goStep2, register;
    ScrollView step1, step2;
    TextInputEditText firstname, lastname, contact, email, password;
    CheckBox hair, makeup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_registration);

        //checkbox
        hair = findViewById(R.id.hair);
        makeup = findViewById(R.id.makeup);
        //end of checkbox

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

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){

            case R.id.goStep2:
                step1.setVisibility(View.GONE);
                step2.setVisibility(View.VISIBLE);

        }
    }

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
                        Toast.makeText(this, services.size(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                    }
//                    register.setEnabled(true);
//                    register.setBackgroundResource(R.drawable.round_button);
//                    check2.setVisibility(View.VISIBLE);
                    break;
                }
                else{
                    Toast.makeText(this, "Removed Service for Hair", Toast.LENGTH_SHORT).show();
                    if(services.size() > 0){
                        Toast.makeText(this, services.size(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

            case R.id.makeup:
                if(b){
                    Toast.makeText(this, "Added Service for Makeup", Toast.LENGTH_SHORT).show();
                    services.add("Makeup");

                    if(services.size() > 0){
                        Toast.makeText(this, services.size(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                    }
//                    register.setEnabled(true);
//                    register.setBackgroundResource(R.drawable.round_button);
//                    check2.setVisibility(View.VISIBLE);
                    break;
                }
                else{
                    Toast.makeText(this, "Removed Service for Makeup", Toast.LENGTH_SHORT).show();
                    if(services.size() > 0){
                        Toast.makeText(this, services.size(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }


        }
    }

    //end of text watcher method
}
