/**
 * Class: Swim extends Activity
 * Function: holds information about a users workout 
 * Author: Josh Daniels (jndaniels@wisc.edu)
 * Date: 7/26/25
 * Version: 1.0
 * @see Activity
 */

import java.time.*;
import java.util.*;

public class Swim extends Activity {

    private int distance;
    //private String strokeType;

    // Constructor
    public Swim(Hero playerHero, LocalDate inDate, String inType, int inDuration,
                int heartRate, int inDist) {
        super(playerHero, inDate, inType, inDuration, heartRate);
        setDistance(inDist);
        //setStroke(inStroke);
    }

    // Getter and Setter
    public int getDistance() {
        return distance;
    }

    public void setDistance(int inDist) {
        this.distance = inDist;
    }
    
    /*
    public String getStroke() {
        return strokeType;
    }

    public void setStroke(String inStroke) {
        this.strokeType = inStroke;
    }
    */
}
