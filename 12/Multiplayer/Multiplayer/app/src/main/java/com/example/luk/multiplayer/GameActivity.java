package com.example.luk.multiplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;

public class GameActivity extends AppCompatActivity {
    public static GameState gs;
    public static String playerID;
    GameView gameView;
    FirebaseDb fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        fb = new FirebaseDb();
        fb.gameActivity = this;
        gameView = findViewById(R.id.gameView);
    }

    public void saveGame() {
        fb.saveGame();
    }

    public void refresh() {
        for (DataSnapshot child: fb.data.getChildren()) {
            GameState gameState = child.getValue(GameState.class);
            if (gameState.key.equals(GameActivity.gs.key)) {
                GameActivity.gs = gameState;
                break;
            }
        }
        gameView.invalidate();
    }

    public void leaveGameButton(View view) {
        if (GameActivity.gs.player1ID.equals(GameActivity.playerID)) {
            GameActivity.gs.player1ID = "LEFT";
        } else if (GameActivity.gs.player2ID.equals(GameActivity.playerID)) {
            GameActivity.gs.player2ID = "LEFT";
        }
        GameActivity.gs.gameOver = true;
        fb.saveGame();
        finish();
    }
}
