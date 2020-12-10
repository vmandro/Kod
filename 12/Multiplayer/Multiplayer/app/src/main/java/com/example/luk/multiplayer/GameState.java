package com.example.luk.multiplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameState {
    int DIM = 4;
    int COUNT = 16;
    String key;
    String name;
    String player1ID;
    String player2ID;
    int playerOnTurn = 1;
    boolean gameOver = false;
    List<List<Integer>> edges = new ArrayList<>();
    List<Integer> squares = new ArrayList<>();

    public GameState() { }

    public GameState(String key, String name) {
        this.key = key;
        this.name = name;
        generateBoard();
    }

    public Set<Integer> neighbors(int n1) {
        Set<Integer> res = new HashSet<>();
        for (int n2 = 0; n2 < COUNT ; n2++) {
            if (edges.get(n1).get(n2) != 0) {
                res.add(n2);
            }
        }
        return res;
    }

    public GameState(String key, String name, String player1ID, String player2ID) {
        this.key = key;
        this.name = name;
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        generateBoard();
    }

    private void generateBoard() {
        for (int i = 0; i < COUNT; i++) {
            edges.add(new ArrayList<Integer>());
            for (int j = 0; j < COUNT; j++) {
                edges.get(i).add(0);
            }
        }

        for (int i = 0; i < (DIM-1)*(DIM-1); i++) {
            squares.add(0);
        }
    }

    public void checkGameOver() {
        gameOver = true;
        for (int i = 0; i < (DIM-1)*(DIM-1); i++) {
            if (squares.get(i) == 0) {
                gameOver = false;
            }
        }
    }

    public int checkWinner() {
        if (!gameOver)
            return -1;

        if (player1ID.equals("LEFT")) {
            return 2;
        } else if (player2ID.equals("LEFT")) {
            return 1;
        }

        int p1 = 0;
        for (int i = 0; i < (DIM-1)*(DIM-1); i++) {
            if (squares.get(i) == 1) {
                p1 += 1;
            }
        }
        if (p1 >= 5) return 1;
        return 2;
    }

    @Override
    public String toString() {
        return name;
    }
}
