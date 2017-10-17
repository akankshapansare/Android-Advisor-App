package com.ap.advisor;


import android.app.Application;

import com.ap.advisor.di.component.AppComponent;
import com.ap.advisor.di.componentFactory.AppComponentFactory;

public class AdvisorApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponentGraph();
    }

    private void buildComponentGraph() {
        appComponent = AppComponentFactory.create(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
