package com.example.luk.multiplayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class GameView extends View {

    GameActivity parentActivity;
    TextView playerText;
    TextView turnText;
    int minSize = -1;
    int cellSize = -1;
    int padding = -1;
    Paint p = new Paint();

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context) {
        super(context);
    }

    public void saveGame() {
        parentActivity.saveGame();
    }

    private int getNodeOfRowCol(int r, int s) {
        return r* GameActivity.gs.DIM + s;
    }

    private int[] getRowColOfNode(int node) {
        int row = node / GameActivity.gs.DIM;
        int col = node % GameActivity.gs.DIM;
        return new int[]{row, col};
    }

    private int[] getLogic(int x, int y) {
        int r = (int) (y / cellSize);
        int s = (int) (x / cellSize);
        return new int[]{r, s};
    }

    private int[] getPixel(int r, int s) {
        int horizontal = padding;
        int vertical = padding * 4;
        int x = (int) (s * cellSize) + horizontal;
        int y = (int) (r * cellSize) + vertical;
        return new int[]{x, y};
    }

    public boolean onTouchEvent(MotionEvent e) {
        int action = e.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            Log.d("GameLog", "x:" + e.getX() + " y:" + e.getY());
            if (!checkOnTurn()) {
                Toast.makeText(parentActivity, "You are not on turn!", Toast.LENGTH_LONG).show();
                return true;
            }
            if (GameActivity.gs.gameOver)
                return true;
            check((int) e.getX(), (int) e.getY());
            GameActivity.gs.checkGameOver();
            saveGame();
        }
        return true;
    }

    public boolean checkOnTurn() {
        if (GameActivity.playerID.equals(GameActivity.gs.player1ID)) {
            return GameActivity.gs.playerOnTurn == 1;
        } else {
            return GameActivity.gs.playerOnTurn == 2;
        }
    }

    public void check(int x, int y) {
        for (int i = 0; i < GameActivity.gs.COUNT-1; i++) {
            for (int j = i+1; j < GameActivity.gs.COUNT; j++) {
                if (i == j) continue;
                if (Math.abs(i%GameActivity.gs.DIM - j%GameActivity.gs.DIM) > 1 || Math.abs(i/GameActivity.gs.DIM - j/GameActivity.gs.DIM) > 1 || (Math.abs(i%GameActivity.gs.DIM - j%GameActivity.gs.DIM) == 1 && Math.abs(i/GameActivity.gs.DIM - j/GameActivity.gs.DIM) == 1))
                    continue;
                int[] rowCol1 = getRowColOfNode(i);
                int[] rowCol2 = getRowColOfNode(j);
                int[] pixel1 = getPixel(rowCol1[0], rowCol1[1]);
                int[] pixel2 = getPixel(rowCol2[0], rowCol2[1]);
                int nX = (pixel1[0] + pixel2[0]) / 2;
                int nY = (pixel1[1] + pixel2[1]) / 2;

                if (distance(x, nX, y, nY) < 50) {

                    GameActivity.gs.edges.get(i).set(j, GameActivity.gs.playerOnTurn);
                    GameActivity.gs.edges.get(j).set(i, GameActivity.gs.playerOnTurn);

                    if (!checkSquares()) {
                        GameActivity.gs.playerOnTurn = GameActivity.gs.playerOnTurn % 2 + 1;
                    }

                    invalidate();
                }
            }
        }
    }

    public int distance(int x1, int x2, int y2, int y1) {
        return (int) Math.sqrt((y2 - y1)*(y2 - y1) + (x2 - x1)*(x2 - x1));
    }

    public boolean checkSquares() {
        boolean flag = false;
        for (int n = 0; n < GameActivity.gs.squares.size(); n++) {
            int n1 = n + n/(GameActivity.gs.DIM-1);
            int n2 = n + 1 + n/(GameActivity.gs.DIM-1);
            int n3 = n + GameActivity.gs.DIM + n/(GameActivity.gs.DIM-1);
            int n4 = n + 1 + GameActivity.gs.DIM + n/(GameActivity.gs.DIM-1);
            if (GameActivity.gs.edges.get(n1).get(n2) != 0 && GameActivity.gs.edges.get(n1).get(n3) != 0 && GameActivity.gs.edges.get(n3).get(n4) != 0 && GameActivity.gs.edges.get(n2).get(n4) != 0) {
                if (GameActivity.gs.squares.get(n) == 0) {
                    flag = true;
                    GameActivity.gs.squares.set(n, GameActivity.gs.playerOnTurn);
                }

            }
        }
        return flag;
    }

    public void markSquares(Canvas canvas) {
        for (int i = 0; i < GameActivity.gs.squares.size(); i++) {
            if (GameActivity.gs.squares.get(i) != 0) {
                int n1 = i + i/(GameActivity.gs.DIM-1);
                int n2 = i+1 + i/(GameActivity.gs.DIM-1);
                int n3 = i+GameActivity.gs.DIM + i/(GameActivity.gs.DIM-1);
                int n4 = i+1+GameActivity.gs.DIM + i/(GameActivity.gs.DIM-1);
                Log.d("GameLog", "n1:" + n1 + " n2:" + n2 + " n3:" + n3 + " n4:" + n4);
                int[] rowCol1 = getRowColOfNode(n1);
                int[] rowCol2 = getRowColOfNode(n2);
                int[] rowCol3 = getRowColOfNode(n3);
                int[] rowCol4 = getRowColOfNode(n4);
                int[] pixel1 = getPixel(rowCol1[0], rowCol1[1]);
                int[] pixel2 = getPixel(rowCol2[0], rowCol2[1]);
                int[] pixel3 = getPixel(rowCol3[0], rowCol3[1]);
                int[] pixel4 = getPixel(rowCol4[0], rowCol4[1]);
                int nX = (pixel1[0] + pixel2[0] + pixel3[0] + pixel4[0]) / 4;
                int nY = (pixel1[1] + pixel2[1] + pixel3[1] + pixel4[1]) / 4;
                p.setStrokeWidth(2);
                p.setStyle(Paint.Style.FILL);
                p.setTextSize(60);
                p.setTextAlign(Paint.Align.CENTER);
                if (GameActivity.gs.squares.get(i) == 1) {
                    p.setColor(Color.BLUE);
                    canvas.drawText("P1", nX, nY+30, p);
                } else {
                    p.setColor(Color.RED);
                    canvas.drawText("P2", nX, nY+30, p);
                }
            }
        }

    }

    protected void onDraw(Canvas canvas) {
        if (parentActivity == null) {
            parentActivity = (GameActivity) getContext();
            playerText = parentActivity.findViewById(R.id.playerText);
            turnText = parentActivity.findViewById(R.id.turnText);
        }

        if(minSize == -1) {
            minSize = Math.min(getWidth(), getHeight()) - 2;
            cellSize = minSize / GameActivity.gs.DIM;
            padding = cellSize / 2;
        }

        if (GameActivity.gs.playerOnTurn == 1) {
            turnText.setText("On Turn: Player1");
        } else {
            turnText.setText("On Turn: Player2");
        }

        if (GameActivity.gs.player1ID.equals(GameActivity.playerID)) {
            playerText.setText("Playins as: Player1");
        } else {
            playerText.setText("Playins as: Player2");
        }

        canvas.drawColor(Color.WHITE);
        p.setStrokeWidth(8);

        for (int i = 0; i < GameActivity.gs.COUNT; i++) {
            for (int j = 0; j < GameActivity.gs.COUNT; j++) {
                if (i == j) continue;
                if (GameActivity.gs.edges.get(i).get(j) != 0) {
                    if (GameActivity.gs.edges.get(i).get(j)==1) p.setColor(Color.BLUE);
                    else p.setColor(Color.RED);
                    int[] rowCol1 = getRowColOfNode(i);
                    int[] rowCol2 = getRowColOfNode(j);
                    int[] pixel1 = getPixel(rowCol1[0], rowCol1[1]);
                    int[] pixel2 = getPixel(rowCol2[0], rowCol2[1]);
                    canvas.drawLine(pixel1[0], pixel1[1], pixel2[0], pixel2[1], p);
                }
            }
        }

        p.setColor(Color.BLACK);
        p.setStrokeWidth(10);
        for (int i = 0; i < GameActivity.gs.DIM; i++) {
            for (int j = 0; j < GameActivity.gs.DIM; j++) {
                int [] pixel = getPixel(i, j);
                canvas.drawCircle(pixel[0], pixel[1], cellSize/6, p);
            }
        }

        markSquares(canvas);

        if (GameActivity.gs.gameOver) {
            p.setStrokeWidth(2);
            p.setTextSize(70);
            p.setTextAlign(Paint.Align.CENTER);

            if (GameActivity.gs.checkWinner() == 1) {
                p.setColor(Color.BLUE);
                canvas.drawText("Player1 Wins!", getWidth()/2, 90, p);
            } else {
                p.setColor(Color.RED);
                canvas.drawText("Player2 Wins!", getWidth()/2, 90, p);
            }

            p.setColor(Color.BLACK);

            if (GameActivity.gs.player1ID.equals("LEFT")) {
                canvas.drawText("PLAYER1 LEFT", getWidth()/2, 190, p);
            } else if (GameActivity.gs.player2ID.equals("LEFT")) {
                canvas.drawText("PLAYER2 LEFT", getWidth()/2, 190, p);
            } else {
                canvas.drawText("GAME OVER", getWidth()/2, 190, p);
            }

        }
    }
}
