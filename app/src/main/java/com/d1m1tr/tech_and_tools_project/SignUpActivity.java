package com.d1m1tr.tech_and_tools_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    private @ServerTimestamp
    Date time;
    private String userType;
    private final String ADMIN = "tsvetkovdimitar@gmail.com";
    private final String CARER = "carer";
    private final String PARENT = "parent";

    private ProgressBar progressBar;

    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtUserFullName;
    private Button signUp;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        edtUserFullName = findViewById(R.id.user_name);
        edtEmail = findViewById(R.id.edt_signup_email);
        edtPassword = findViewById(R.id.edt_signup_password);
        signUp = findViewById(R.id.btn_register);

        progressBar = findViewById(R.id.signup_activity_progress_bar);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String signUpUserserName = edtUserFullName.getText().toString().trim();
                final String signUpEmail = edtEmail.getText().toString().trim();
                final String signUpPassword = edtPassword.getText().toString().trim();

                if (!(TextUtils.isEmpty(signUpEmail) || TextUtils.isEmpty(signUpPassword) || TextUtils.isEmpty(signUpUserserName))) {

                    signUpUser(signUpUserserName, signUpEmail, signUpPassword);
                }
                else{

                    Toast.makeText(SignUpActivity.this, "Please, enter email and password.", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    private void signUpUser(final String name, final String email, String password){

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                progressBar.setVisibility(View.VISIBLE);

                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                final String uid = currentUser.getUid();

                                if(email.equals(ADMIN)){

                                    userType = CARER;

                                }
                                else{

                                    userType = PARENT;

                                }

                                //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                                User user = new User(uid, name, email, userType, time);

                                db.collection("users").document(uid).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){

                                            Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                            Intent signUpIntent = new Intent(SignUpActivity.this, MainActivity.class);
                                            signUpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                            startActivity(signUpIntent);
                                            finish();

                                        }else{

                                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                        }

                                    }
                                });



//                                DatabaseReference databaseReference = firebaseDatabase.getReference().child("users").child(uid);
//                                databaseReference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//
//                                        if(task.isSuccessful()){
//
//                                            Intent signUpIntent = new Intent(SignUpActivity.this, MainActivity.class);
//                                            signUpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                            Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
//                                            startActivity(signUpIntent);
//                                            finish();
//
//                                        }
//
//
//                                    }
//                                });


                            }
                            else{

                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                FirebaseUser user = mAuth.getCurrentUser();

                            }


                            progressBar.setVisibility(View.INVISIBLE);

                        }
                    });

    }

}
