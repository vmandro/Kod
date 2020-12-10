package com.example.luk.multiplayer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

public class IntroActivity extends AppCompatActivity {
    FirebaseLogin fa;
    InputMethodManager imm;
    EditText emailText;
    EditText passText;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        fa = new FirebaseLogin(this);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        status = findViewById(R.id.statusText);
        emailText = findViewById(R.id.emailText);
        passText = findViewById(R.id.passwordText);
        updateStatus();
    }

    public void updateStatus() {
        FirebaseUser user = fa.mAuth.getCurrentUser();
        if (user != null) {
            GameActivity.playerID = user.getUid();
            String name = user.getEmail().split("@")[0];
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            status.setText("Logged in as: " + name);
        } else {
            GameActivity.playerID = "";
            status.setText("You are not logged in!");
        }
    }

    public void newGameButton(View view) {
        if(fa.checkLogin()) {
            Intent in = new Intent("NewActivity");
            startActivity(in);
        } else {
            Toast.makeText(this, "You must login first!", Toast.LENGTH_LONG).show();
        }
    }

    public void joinGameButton(View view) {
        if(fa.checkLogin()) {
            Intent in = new Intent("JoinActivity");
            startActivity(in);
        } else {
            Toast t = Toast.makeText(this, "You must login first!", Toast.LENGTH_LONG);
            t.show();
        }
    }

    public void loginButton(View view) {
        String email = emailText.getText().toString();
        String pass = passText.getText().toString();
        fa.login(email, pass);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        updateStatus();
    }

    public void howToButton(View view) {
        String url = "https://en.m.wikipedia.org/wiki/Dots_and_Boxes";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}
