package com.example.luk.multiplayer;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseLogin {
    private IntroActivity introActivity;
    FirebaseAuth mAuth;

    public FirebaseLogin(IntroActivity activity) {
        introActivity = activity;
        mAuth = FirebaseAuth.getInstance();
    }

    public boolean checkLogin() {
        return mAuth.getCurrentUser() != null;
    }

    public void register(final String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(introActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("MultiplayerLog", "Register");
                            introActivity.updateStatus();
                            Toast.makeText(introActivity, "Registration complete", Toast.LENGTH_LONG).show();
                        } else {
                            Log.e("MultiplayerLog", "RegistrationFailure" + task.getException());
                            Toast.makeText(introActivity, "Login Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void login(final String email, final String pass) {
        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(introActivity, "Empty credentials!", Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(introActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("MultiplayerLog", "Login");
                            introActivity.updateStatus();
                            Toast.makeText(introActivity, "Login successful", Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("MultiplayerLog", "LoginFailure" + task.getException());
                            Log.d("MultiplayerLog", "RegisterTry" + task.getException());
                            register(email, pass);
                        }
                    }
                });

    }
}
