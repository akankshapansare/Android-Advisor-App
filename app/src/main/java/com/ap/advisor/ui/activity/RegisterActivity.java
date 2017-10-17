package com.ap.advisor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ap.advisor.AdvisorApplication;
import com.ap.advisor.R;
import com.ap.advisor.networking.FirebaseService;

import javax.inject.Inject;

public class RegisterActivity extends AppCompatActivity {

    @Inject
    FirebaseService firebaseService;

    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AdvisorApplication) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_register);

        Button loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.start(RegisterActivity.this);
            }
        });

        Button createAccountButton = (Button) findViewById(R.id.button_create_account);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccountActivity.start(RegisterActivity.this);
            }
        });

    }
}
