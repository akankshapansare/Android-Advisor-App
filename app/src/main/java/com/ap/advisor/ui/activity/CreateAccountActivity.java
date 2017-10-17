package com.ap.advisor.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ap.advisor.AdvisorApplication;
import com.ap.advisor.R;
import com.ap.advisor.networking.FirebaseService;

import javax.inject.Inject;

public class CreateAccountActivity extends AppCompatActivity {

    @Inject
    FirebaseService firebaseService;

    public static void start(Context context) {
        Intent intent = new Intent(context, CreateAccountActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AdvisorApplication) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_create_account);
    }
}
