package com.ap.advisor.model;


import com.ap.advisor.data.User;

public class AppStateModel {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
