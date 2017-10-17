package com.ap.advisor.di.component;

import com.ap.advisor.di.module.AppModule;
import com.ap.advisor.ui.activity.AddAdvisorActivity;
import com.ap.advisor.ui.activity.ContactListActivity;
import com.ap.advisor.ui.activity.CreateAccountActivity;
import com.ap.advisor.ui.activity.LoginActivity;
import com.ap.advisor.ui.activity.MakeAdvisorActivity;
import com.ap.advisor.ui.activity.RegisterActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,})
public interface AppComponent {

    void inject(RegisterActivity registerActivity);

    void inject(LoginActivity loginActivity);

    void inject(CreateAccountActivity createAccountActivity);

    void inject(MakeAdvisorActivity makeAdvisorActivity);

    void inject(AddAdvisorActivity addAdvisorActivity);

    void inject(ContactListActivity contactListActivity);
}
