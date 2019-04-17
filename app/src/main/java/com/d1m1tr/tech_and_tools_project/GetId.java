package com.d1m1tr.tech_and_tools_project;

import android.support.annotation.NonNull;

public class GetId {

    public String id;

    public <T extends GetId> T withId(@NonNull final String id){

        this.id = id;
        return (T) this;

    }

}
