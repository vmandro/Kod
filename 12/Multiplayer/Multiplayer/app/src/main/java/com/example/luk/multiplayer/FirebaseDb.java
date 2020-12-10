package com.example.luk.multiplayer;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FirebaseDb {
    private DatabaseReference databaseReference;
    DataSnapshot data;
    JoinActivity joinActivity;
    GameActivity gameActivity;

    public FirebaseDb() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Games");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data = dataSnapshot;
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    GameState gs = child.getValue(GameState.class);
                    Log.d("MultiplayerLog", "GameState: " + gs);
                }
                if (joinActivity != null) {
                    joinActivity.loadGames();
                }
                if (gameActivity != null) {
                    gameActivity.refresh();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("MultiplayerLog", "ReadFailed: " + databaseError);
            }
        });

    }

   public void saveGame() {
       databaseReference.child(GameActivity.gs.key).setValue(GameActivity.gs);
   }

   public void deleteGame() {
       databaseReference.child(GameActivity.gs.key).removeValue();
   }

    public void insertGame(String name) {
        String key = databaseReference.push().getKey();
        GameActivity.gs = new GameState(key, name, GameActivity.playerID, "");
        databaseReference.child(key).setValue(GameActivity.gs);
    }

}
