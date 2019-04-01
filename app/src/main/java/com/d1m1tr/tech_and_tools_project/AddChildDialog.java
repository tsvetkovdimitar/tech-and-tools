package com.d1m1tr.tech_and_tools_project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddChildDialog extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "AddChildDialog";

    private EditText edtChildName, edtChildAge;
    private TextView btnAddChild, btnCancelDialog;

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_parent_add_child:{

                // insert the new note

                String name = edtChildName.getText().toString();
                String age = edtChildAge.getText().toString();

                if(!name.equals("")){

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

}
