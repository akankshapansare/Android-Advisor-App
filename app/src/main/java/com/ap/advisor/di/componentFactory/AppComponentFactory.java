package com.ap.advisor.di.componentFactory;

import android.app.Application;

import com.ap.advisor.di.component.AppComponent;
import com.ap.advisor.di.component.DaggerAppComponent;
import com.ap.advisor.di.module.AppModule;

public final class AppComponentFactory {
    private AppComponentFactory() {

    }

    public static AppComponent create(Application app) {
        return DaggerAppComponent.builder().appModule(new AppModule(app)).build();
    }
}
