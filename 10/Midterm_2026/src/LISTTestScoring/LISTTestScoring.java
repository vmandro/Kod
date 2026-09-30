package LISTTestScoring;

import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrej
 */
public class LISTTestScoring {
        
    private final HashMap<String, Score> scoringTable = new HashMap<String, Score>();
    
    public LISTTestScoring() {
    }
    
    public synchronized void updateScore(String scoreName, double scoreToAdd, double scoreMaximum) {
        if (scoringTable.containsKey(scoreName)) {
            scoringTable.get(scoreName).setMaximum(scoreMaximum);
            scoringTable.get(scoreName).addCurrent(scoreToAdd);
        } else {
            Score newScore = new Score(scoreToAdd, scoreMaximum);
            scoringTable.put(scoreName, newScore);
        }
    }

    public synchronized void updateScore(String scoreName, double scoreToAdd) {
        if (scoringTable.containsKey(scoreName)) {
            scoringTable.get(scoreName).addCurrent(scoreToAdd);
        } else {
            System.err.println("Scoring error, you are trying to modify non-existing score name: " + scoreName);
        }
    }
    
    public synchronized void setScore(String scoreName, double scoreToSet, double scoreMaximum) {
        if (scoringTable.containsKey(scoreName)) {
            scoringTable.remove(scoreName);
        }
        Score newScore = new Score(scoreToSet <= scoreMaximum ? (scoreToSet >= 0.0 ? scoreToSet : 0.0) : scoreMaximum, scoreMaximum);
        scoringTable.put(scoreName, newScore);
    }

    public synchronized void setScore(String scoreName, double scoreToSet) {
        if (scoringTable.containsKey(scoreName)) {
            scoringTable.get(scoreName).setCurrent(scoreToSet);
        } else {
            System.err.println("Scoring error, you are trying to modify non-existing score name: " + scoreName);
        }
    }
}

class Score {
    private double current = 0.0;
    private double maximum = 0.0;

    public Score(double attrCurrent, double attrMaximum) {
        current = attrCurrent;
        maximum = attrMaximum;
        if (maximum < 0) { maximum = 0; }
        if (current < 0) { current = 0; }
        if (current > maximum) { current = maximum; }
    }    

    public double getCurrent() {
        return current;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setCurrent(double current) {
        this.current = current;
        if (this.current < 0) { this.current = 0; }
        if (this.current > maximum) { this.current = maximum; }
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
        if (this.maximum < 0) { this.maximum = 0; }
        if (this.current > this.maximum) { this.current = this.maximum; }
    }
    
    public void addCurrent(double add) {
        setCurrent(getCurrent() + add);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(current);
        sb.append('/');
        sb.append(maximum);
        return sb.toString();
    }
}
