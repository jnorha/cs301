/**
 * Class: Weights extends Activity
 * Function: holds information about a users workout 
 * Author: Josh Daniels (jndaniels@wisc.edu)
 * Date: 7/26/25
 * Version: 1.0
 * @see Activity
 */

import java.time.*;
import java.util.*;

public class Weights extends Activity {

    private int reps;
    private int weightInLbs;
    private int totalVolume;

    // Constructor
    public Weights(Hero playerHero, LocalDate inDate, String inType, int inDuration,
                   int heartRate, int inReps, int inWeightInLbs) {
        super(playerHero, inDate, inType, inDuration, heartRate);
        setReps(inReps);
        setWeightInLbs(inWeightInLbs);
    }

    // Getters and Setters
    public int getReps() {
        return reps;
    }

    public void setReps(int inReps) {
        this.reps = inReps;
    }

    public int getWeightInLbs() {
        return weightInLbs;
    }

    public void setWeightInLbs(int inWeightInLbs) {
        this.weightInLbs = inWeightInLbs;
    }
    
    public int getTotalVol() {
        return totalVolume;
    }

    public void setTotalVol(int inWeightInLbs, int inReps) {
        this.totalVolume = inWeightInLbs*inReps;
    }
}
