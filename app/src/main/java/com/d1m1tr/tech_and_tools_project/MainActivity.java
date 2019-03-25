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

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    EditText email;
    EditText password;
    Button signUp;
    Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.edt_email);
        password = findViewById(R.id.edt_password);
        logIn = findViewById(R.id.btn_login);
        signUp = findViewById(R.id.btn_signup);

        mAuth = FirebaseAuth.getInstance();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String loginEmail = email.getText().toString().trim();
                String loginPassword = password.getText().toString().trim();

                if (!(TextUtils.isEmpty(loginEmail) && TextUtils.isEmpty(loginPassword))){


                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())

                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {

                                            Intent signInIntent = new Intent(MainActivity.this, ProfileActivity.class);
                                            //signInIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(signInIntent);
                                            finish();

                                        } else {

                                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                        }

                                    }
                            });

                }
                else{

                    Toast.makeText(MainActivity.this, "Please, enter email and password.", Toast.LENGTH_LONG).show();

                }


            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
    }
}
