package com.example.imyas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    //Edittext
    EditText firstname, lastname, contact, email, password;
    //Button
    Button register, cancel;

    //string
    private String image;

    //progressbar
    ProgressBar progressbar;

    //firebase
    FirebaseAuth mAuth;
    FirebaseDatabase mRegister, mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //firebase
        mAuth = FirebaseAuth.getInstance();
        mRegister = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        //edittext
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        //progressbar
        progressbar = findViewById(R.id.progressbar);

        //edit text watchers

        firstname.addTextChangedListener(registerTextWatcher);
        lastname.addTextChangedListener(registerTextWatcher);
        contact.addTextChangedListener(registerTextWatcher);
        email.addTextChangedListener(registerTextWatcher);
        password.addTextChangedListener(registerTextWatcher);

        //button
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);
        register.setBackgroundResource(R.drawable.disable_button);

        //button listeners
        register.setOnClickListener(this);
        cancel.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.register:
                registerUser();
                break;
            case R.id.cancel:
                Intent intent = new Intent(RegisterUser.this, MainActivity.class);
                startActivity(intent);
        }
    }
    //register user
    private void registerUser() {

        final String firstnameInput = firstname.getText().toString().trim();
        final String lastnameInput = lastname.getText().toString().trim();
        final String contactInput = contact.getText().toString().trim();
        final String emailInput = email.getText().toString().trim();
        final String passwordeInput = password.getText().toString().trim();

        image = "https://firebasestorage.googleapis.com/v0/b/imyas-d9db6.appspot.com/o/male.png?alt=media&token=af3b743c-ad0d-465c-9bcc-e8568648de29";
        mAuth.createUserWithEmailAndPassword(emailInput, passwordeInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.VISIBLE);
                register.setBackgroundResource(R.drawable.disable_button);
                if(task.isSuccessful()){
                    UserModel userModel = new UserModel(
                          mAuth.getCurrentUser().getUid(),
                          firstnameInput,
                          lastnameInput,
                          contactInput,
                          emailInput,
                          passwordeInput,
                          image
                    );
                    FirebaseDatabase.getInstance().getReference("User")
                            .child(mAuth.getCurrentUser().getUid()).setValue(userModel)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        progressbar.setVisibility(View.INVISIBLE);
                                        register.setBackgroundResource(R.drawable.round_button);
                                        Toast.makeText(RegisterUser.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterUser.this, UserHome.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(RegisterUser.this, "error", Toast.LENGTH_SHORT).show();
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
    //end of register user

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
                register.setEnabled(true);
                register.setBackgroundResource(R.drawable.round_button);
            }

            else{
                register.setEnabled(false);
                register.setBackgroundResource(R.drawable.disable_button);

            }


        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    //end of text wathcer method
}
