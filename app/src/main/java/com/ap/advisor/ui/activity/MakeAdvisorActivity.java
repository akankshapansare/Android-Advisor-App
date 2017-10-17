package com.ap.advisor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ap.advisor.AdvisorApplication;
import com.ap.advisor.R;
import com.ap.advisor.networking.FirebaseService;

import javax.inject.Inject;

public class MakeAdvisorActivity extends AppCompatActivity {

    @Inject
    FirebaseService firebaseService;

    public static void start(Context context) {
        Intent intent = new Intent(context, MakeAdvisorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AdvisorApplication) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_making_advisor);

        final EditText advisorType = (EditText) findViewById(R.id.text_advisor_type);
        final EditText advisorName = (EditText) findViewById(R.id.text_advisor_name);
        final EditText mobileNumber = (EditText) findViewById(R.id.number_advisor_mobile);

        Button saveButton = (Button) findViewById(R.id.button_save);
        saveButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseService.makeAdvisor(advisorType.getText().toString(), advisorName.getText().toString(), mobileNumber.getText().toString(), new FirebaseService.CallBackI() {
                    @Override
                    public void onCallBackComplete(String userID) {
                        Toast.makeText(MakeAdvisorActivity.this, "Advisor Added", Toast.LENGTH_SHORT).show();
                        AddAdvisorActivity.start(MakeAdvisorActivity.this);
                        finish();
                    }

                    @Override
                    public void onCallBackFailed() {
                        Toast.makeText(MakeAdvisorActivity.this, "Create Advisor Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        ImageButton goToContactsButton = (ImageButton) findViewById(R.id.button_go_to_contacts);
        goToContactsButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactListActivity.start(MakeAdvisorActivity.this);
            }
        });
    }


}
