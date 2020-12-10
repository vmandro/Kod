package com.example.luk.multiplayer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinActivity extends ListActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    ListAdapter la;
    List<GameState> gameStates;
    List<Map<String, String>> games;
    FirebaseDb fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        fb = new FirebaseDb();
        fb.joinActivity = this;
        lv = getListView();
        lv.setOnItemClickListener(this);
    }

    public void loadGames() {
        games = new ArrayList<>();
        gameStates = new ArrayList<>();
        String[] from = {"key", "name"};
        int[] to = { android.R.id.text1, android.R.id.text2};
        for (DataSnapshot child: fb.data.getChildren()) {
            GameState gs = child.getValue(GameState.class);
            if (gs.gameOver)
                continue;
            HashMap<String, String> game = new HashMap<>();
            game.put("key", gs.key);
            game.put("name", gs.name);
            games.add(game);
            gameStates.add(gs);
        }
        la = new SimpleAdapter(this, games, android.R.layout.simple_list_item_2, from, to);
        lv.setAdapter(la);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("MultiplayerLog", "ListClicked: " + games.get(position));
        GameActivity.gs = gameStates.get(position);

        if (GameActivity.gs.player1ID.equals(GameActivity.playerID)) {
            Intent in = new Intent("GameActivity");
            startActivity(in);
        } else if (GameActivity.gs.player2ID.equals("")) {
            GameActivity.gs.player2ID = GameActivity.playerID;
            fb.saveGame();
            Toast.makeText(this, "Game Joined!", Toast.LENGTH_LONG).show();
            Intent in = new Intent("GameActivity");
            startActivity(in);
        } else {
            Toast.makeText(this, "This Game is Full!", Toast.LENGTH_LONG).show();
            GameActivity.gs = null;
        }
    }
}
