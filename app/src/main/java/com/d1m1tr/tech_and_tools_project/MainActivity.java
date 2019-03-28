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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private String userType = " ";
    private String currentUserId = " ";

    private final String CARER = "carer";
    private final String PARENT = "parent";

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;

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

                                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                            final String uid = currentUser.getUid();
                                            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");

                                            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    userType = dataSnapshot.child(uid).child("userType").getValue(String.class);

                                                    if(userType.equals(CARER)){


                                                        Intent signInCarerIntent = new Intent(MainActivity.this, ProfileActivity.class);
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


                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    Toast.makeText(MainActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();

                                                }
                                            });


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
