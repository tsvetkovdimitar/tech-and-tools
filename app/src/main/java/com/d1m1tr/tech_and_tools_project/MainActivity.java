package com.d1m1tr.tech_and_tools_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private String userType = " ";
    private ProgressBar progressBar;
    private static final String TAG = "DocSnippets";

    private final String CARER = "carer";
    private final String PARENT = "parent";

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private EditText email;
    private EditText password;
    private Button signUp;
    private Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.edt_email);
        password = findViewById(R.id.edt_password);
        logIn = findViewById(R.id.btn_login);
        signUp = findViewById(R.id.btn_signup);

        progressBar = findViewById(R.id.main_activity_progress_bar);

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String loginEmail = email.getText().toString().trim();
                String loginPassword = password.getText().toString().trim();

                if (!(TextUtils.isEmpty(loginEmail) && TextUtils.isEmpty(loginPassword))){

                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())

                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {

                                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                            final String uid = currentUser.getUid();

                                            db.collection("users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                                    if (task.isSuccessful()) {

                                                        DocumentSnapshot document = task.getResult();


                                                        if (document.exists()) {

                                                            userType = document.getString("userType");
                                                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                                            if(userType.equals(CARER)){


                                                            Intent signInCarerIntent = new Intent(MainActivity.this, CarerProfileActivity.class);
                                                            signInCarerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                            startActivity(signInCarerIntent);
                                                            finish();

                                                        }
                                                        else if(userType.equals(PARENT)){

                                                            Intent signInParentIntent = new Intent(MainActivity.this, ParentProfileActivity.class);
                                                            signInParentIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                            startActivity(signInParentIntent);
                                                            finish();


                                                    }

                                                        } else {

                                                            Log.d(TAG, "No such document");
                                                        }

                                                    } else {

                                                        Log.d(TAG, "get failed with ", task.getException());
                                                    }
                                                }
                                            });



                                        } else {

                                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.INVISIBLE);

                                        }

                                        progressBar.setVisibility(View.INVISIBLE);

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
