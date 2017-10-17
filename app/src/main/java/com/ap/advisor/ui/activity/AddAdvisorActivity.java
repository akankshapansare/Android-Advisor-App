package com.ap.advisor.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ap.advisor.AdvisorApplication;
import com.ap.advisor.R;
import com.ap.advisor.data.Advisor;
import com.ap.advisor.networking.FirebaseService;
import com.ap.advisor.networking.api.IAdvisorListCallBack;
import com.ap.advisor.ui.adapter.AdvisorListAdaptor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AddAdvisorActivity extends AppCompatActivity implements AdvisorListAdaptor.AdvisorListAdaptorListener {

    @Inject
    FirebaseService firebaseService;

    private AdvisorListAdaptor advisorListAdaptor;

    public static void start(Context context) {
        Intent intent = new Intent(context, AddAdvisorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AdvisorApplication) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_add_advisor);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_advisor_list);
        final List<Advisor> advisorList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        advisorListAdaptor = new AdvisorListAdaptor(this, advisorList, this);
        recyclerView.setAdapter(advisorListAdaptor);

        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.button_add_advisor);
        addButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakeAdvisorActivity.start(AddAdvisorActivity.this);
            }
        });

        firebaseService.getAllAdvisors(new IAdvisorListCallBack() {
            @Override
            public void onCallBackComplete(List<Advisor> advisorList) {
                advisorListAdaptor.setAdvisorList(advisorList);
                Toast.makeText(AddAdvisorActivity.this, "Advisors Displayed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCallBackFailed() {
                Toast.makeText(AddAdvisorActivity.this, "Advisors not displayed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAdvisorSelected(Advisor advisor) {

    }
}
