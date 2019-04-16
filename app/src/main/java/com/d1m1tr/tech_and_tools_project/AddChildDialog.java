package com.d1m1tr.tech_and_tools_project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class AddChildDialog extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "AddChildDialog";

    private EditText edtChildName, edtChildAge;
    private TextView btnAddChild, btnCancelDialog;

    private String name;
    private String age;
    private @ServerTimestamp
    Date dateRegistered;
    private String userId;

    private Context context;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore db;
    private CollectionReference userRef;

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_parent_add_child:{

                // insert the new child data

                name = edtChildName.getText().toString();
                age = edtChildAge.getText().toString();

                if(!name.equals("")){

                    addNewChild();
                    getDialog().dismiss();
                }
                else{

                    Toast.makeText(getActivity(), "Enter a name", Toast.LENGTH_SHORT).show();
                }
                break;
            }

            case R.id.btn_cancel_add_child_dialog:{
                getDialog().dismiss();
                break;
            }
        }



    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int style = DialogFragment.STYLE_NORMAL;
        int theme = android.R.style.Theme_DeviceDefault_Light_Dialog;
        setStyle(style, theme);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        db = FirebaseFirestore.getInstance();
        userRef = db.collection("users");

        mUser = FirebaseAuth.getInstance().getCurrentUser();

        userId = mUser.getUid();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_child_dialog_layout, container, false);
        edtChildName = view.findViewById(R.id.child_name);
        edtChildAge = view.findViewById(R.id.child_age);
        btnAddChild = view.findViewById(R.id.btn_parent_add_child);
        btnCancelDialog = view.findViewById(R.id.btn_cancel_add_child_dialog);

        btnCancelDialog.setOnClickListener(this);

        btnAddChild.setOnClickListener(this);

        getDialog().setTitle("New Note");

        return view;
    }

    public void addNewChild(){

        if(mUser != null) {

            Child child = new Child(name, age, dateRegistered, userId);
            final String uid = mUser.getUid();
            userRef.document(uid).collection("children").add(child).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {



                }
            });
        }
    }

}
