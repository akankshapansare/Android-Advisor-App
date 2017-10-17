package com.ap.advisor.di.module;


import android.app.Application;
import android.content.Context;

import com.ap.advisor.di.annotation.ForApplicationContext;
import com.ap.advisor.model.AppStateModel;
import com.ap.advisor.networking.FirebaseService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public FirebaseDatabase provideFirbaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Singleton
    @Provides
    public FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Singleton
    @Provides
    public AppStateModel provideAppStateModel() {
        return new AppStateModel();
    }

    @Singleton
    @Provides
    public FirebaseService provideFirbaseService(FirebaseDatabase firebaseDatabase, FirebaseAuth firebaseAuth, AppStateModel appStateModel) {
        return new FirebaseService(firebaseDatabase, firebaseAuth, appStateModel);
    }
}
