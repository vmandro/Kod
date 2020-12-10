package com.example.luk.multiplayer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;;

public class NewActivity extends AppCompatActivity {
    FirebaseDb fb;
    private TextView gameName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        fb = new FirebaseDb();
        gameName = findViewById(R.id.nameTextView);
    }

    public void createButton(View view) {
        String name = gameName.getText().toString();
        if (GameActivity.playerID.equals("")) {
            Toast.makeText(this, "Wrong User Id!", Toast.LENGTH_LONG).show();
        } else if (name.isEmpty()) {
            Toast.makeText(this, "Game name cannot be empty!", Toast.LENGTH_LONG).show();
        } else {
            fb.insertGame(gameName.getText().toString());
            AlertDialog dialog = new AlertDialog.Builder(this).create();
            dialog.setTitle("Game Created!");
            dialog.setButton(
                    DialogInterface.BUTTON_POSITIVE,
                    "Play!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent in = new Intent("GameActivity");
                            startActivity(in);
                        }
                    });
            dialog.setButton(
                    DialogInterface.BUTTON_NEGATIVE,
                    "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            fb.deleteGame();
                        }
                    });
            dialog.show();
        }
    }
}