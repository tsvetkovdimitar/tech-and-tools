package com.d1m1tr.tech_and_tools_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signUp;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        email = findViewById(R.id.edt_signup_email);
        password = findViewById(R.id.edt_signup_password);
        signUp = findViewById(R.id.btn_register);


        if(mUser != null){

            startActivity(new Intent(SignUpActivity.this, ProfileActivity.class));

        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){

                                    Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                                }
                                else{

                                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    FirebaseUser user = mAuth.getCurrentUser();

                                }


                            }
                        });

            }
        });

    }

}
