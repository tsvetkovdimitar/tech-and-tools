package com.d1m1tr.tech_and_tools_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
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
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties
public class AddDailyActivityDialog extends AppCompatDialogFragment implements View.OnClickListener {

    private static final String TAG = "AddDailyActivityDialog";

    private EditText edtActivityType;
    private EditText edtActivityNote;
    private TextView btnAddDailyActivity;
    private TextView btnCancelDialog;

    private String type;
    private String note;
    private @ServerTimestamp
    Date dateTimeAdded;
    private String userId;

    private String parentId;
    private String childId;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore db;
    private CollectionReference userRef;

//        View view = inflater.inflate(R.layout.add_daily_activity_dialog, null);
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

        if(!getArguments().isEmpty()){

            childId = getArguments().getString("childId");
            parentId = getArguments().getString("parentId");

        }

        View view = inflater.inflate(R.layout.add_daily_activity_dialog, container, false);
        edtActivityType = view.findViewById(R.id.edt_daily_activity_type);
        edtActivityNote = view.findViewById(R.id.edt_daily_activity_note);
        btnAddDailyActivity = view.findViewById(R.id.btn_add_daily_activity);
        btnCancelDialog = view.findViewById(R.id.btn_cancel_add_activity_dialog);

        btnCancelDialog.setOnClickListener(this);

        btnAddDailyActivity.setOnClickListener(this);

        getDialog().setTitle("Add daily activity");

        return view;
}

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_add_daily_activity:{

                // insert the new daily activity data

                type = edtActivityType.getText().toString();
                note = edtActivityNote.getText().toString();

                if(!type.equals("")){

                    addDailyActivity();
                    getDialog().dismiss();
                }
                else{

                    Toast.makeText(getActivity(), "Insert activity type", Toast.LENGTH_SHORT).show();
                }
                break;
            }

            case R.id.btn_cancel_add_activity_dialog:{
                getDialog().dismiss();
                break;
            }
        }

    }

    public void addDailyActivity(){

        if(mUser != null) {

            dateTimeAdded = new Date();

            DailyActivity dailyActivity = new DailyActivity(type, note, dateTimeAdded);
            userRef.document(parentId).collection("children").document(childId)
                    .collection("dailyActivities").add(dailyActivity).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {

                }
            });
        }
    }
}
