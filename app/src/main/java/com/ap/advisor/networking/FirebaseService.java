package com.ap.advisor.networking;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ap.advisor.data.Advisor;
import com.ap.advisor.data.User;
import com.ap.advisor.model.AppStateModel;
import com.ap.advisor.networking.api.IAdvisorListCallBack;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FirebaseService {

    private final AppStateModel appStateModel;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    public FirebaseService(FirebaseDatabase firebaseDatabase,
                           FirebaseAuth firebaseAuth,
                           AppStateModel appStateModel) {
        this.firebaseDatabase = firebaseDatabase;
        this.firebaseAuth = firebaseAuth;
        this.appStateModel = appStateModel;
    }

    // Create Account
    public void signUp(final String firstName, final String lastName, final String email, final String password, final CallBackI callBackI) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    User u = new User(user.getUid(), firstName, lastName, user.getEmail(), user.getPhotoUrl(), user.getProviderId());
                    storeUserProfileInFirebase(u);
                    callBackI.onCallBackComplete(user.getUid());
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    callBackI.onCallBackFailed();
                }
            }
        });
    }

    // Login
    public void signIn(String email, String password, final CallBackI callBackI) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            final FirebaseUser user = firebaseAuth.getCurrentUser();
                            firebaseDatabase.getReference("users/" + user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    User myUser = dataSnapshot.getValue(User.class);
                                    appStateModel.setCurrentUser(myUser);
                                    callBackI.onCallBackComplete(user.getUid());
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", databaseError.toException());
                                    callBackI.onCallBackFailed();
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            callBackI.onCallBackFailed();
                        }
                    }
                });
    }

    private void storeUserProfileInFirebase(User user) {
        DatabaseReference databaseReference = firebaseDatabase.getReference("users").child(user.getUid());
        databaseReference.setValue(user);
    }

    //Make Advisor

    public void makeAdvisor(final String advisorType, final String advisorname, final String mobilenumber, final CallBackI callBackI) {
        Advisor advisor = new Advisor(advisorType, advisorname, mobilenumber);
        firebaseDatabase.getReference("advisor").push().setValue(advisor, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    callBackI.onCallBackComplete("");
                } else {
                    callBackI.onCallBackFailed();
                }
            }
        });
    }

    //Show all Advisors

    public void getAllAdvisors(final IAdvisorListCallBack iAdvisorListCallBack) {
        firebaseDatabase.getReference("advisor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Advisor> advisorList = new ArrayList<Advisor>();
                for (DataSnapshot childSnapShot : dataSnapshot.getChildren()) {
                    Advisor advisor = childSnapShot.getValue(Advisor.class);
                    advisorList.add(advisor);
                }
                iAdvisorListCallBack.onCallBackComplete(advisorList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadAdvisor:onCancelled", databaseError.toException());
                iAdvisorListCallBack.onCallBackFailed();
            }
        });
    }

    public interface CallBackI {

        public void onCallBackComplete(String userID);

        public void onCallBackFailed();

    }
}
