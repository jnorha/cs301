/**
 * Class: Bike extends Activity
 * Function: holds information about a users workout 
 * Author: Josh Daniels (jndaniels@wisc.edu)
 * Date: 7/26/25
 * Version: 1.0
 * @see Activity
 */


import java.time.*;

public class Bike extends Activity {

    private int distance;
    private int elevHigh;
    private int elevLow;
    private int elevChange;

    // Constructor
    public Bike(Hero playerHero, LocalDate inDate, String inType, int inDuration,
                int heartRate, int inDist, int elevationHigh, int elevationLow) {
        super(playerHero, inDate, inType, inDuration, heartRate);
        setDistance(inDist);
        setElevHigh(elevationHigh);
        setElevLow(elevationLow);
        setElevChange(elevationLow, elevationHigh);
    }

    // Getters and Setters
    public int getDistance() {
        return distance;
    }

    public void setDistance(int inDist) {
        this.distance = inDist;
    }

    public int getElevHigh() {
        return elevHigh;
    }

    public void setElevHigh(int elevationHigh) {
        this.elevHigh = elevationHigh;
    }

    public int getElevLow() {
        return elevLow;
    }

    public void setElevLow(int elevationLow) {
        this.elevLow = elevationLow;
    }

    public int getElevChange() {
        return elevChange;
    }

    public void setElevChange(int elevationLow, int elevationHigh) {
        this.elevChange = elevationHigh - elevationLow;
    }
}
