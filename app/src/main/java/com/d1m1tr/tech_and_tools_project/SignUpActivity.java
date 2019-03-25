package com.d1m1tr.tech_and_tools_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtUserFullName;
    private Button signUp;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        edtEmail = findViewById(R.id.edt_signup_email);
        edtPassword = findViewById(R.id.edt_signup_password);
        edtUserFullName = findViewById(R.id.user_name);
        signUp = findViewById(R.id.btn_register);

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

    private void signUpUser(String name, String email, String password){

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

//                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//                                String uid = currentUser.getUid();
//                                databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
//
//                                HashMap<String, String> userMap = new HashMap<>();

                                Intent signupIntent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(signupIntent);
                                finish();
                                Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                            }
                            else{

                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                FirebaseUser user = mAuth.getCurrentUser();

                            }


                        }
                    });

    }

}
