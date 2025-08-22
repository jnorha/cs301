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

    private double distance;
    private double elevHigh;
    private double elevLow;
    private double elevChange;

    // Constructor
    public Bike(Hero playerHero, LocalDate inDate, String doubleype, double inDuration,
                double heartRate, int inDist, double elevationHigh, double elevationLow) {
        super(playerHero, inDate, doubleype, inDuration, heartRate);
        setDistance(inDist);
        setElevHigh(elevationHigh);
        setElevLow(elevationLow);
        setElevChange(elevationLow, elevationHigh);
    }
    
    public Bike(Hero playerHero, LocalDate inDate, String doubleype, double inDuration,
            double heartRate, double inDist, double elevationHigh, double elevationLow) {
    super(playerHero, inDate, doubleype, inDuration, heartRate);
    setDistance(inDist);
    setElevHigh(elevationHigh);
    setElevLow(elevationLow);
    setElevChange(elevationLow, elevationHigh);
}

    // Getters and Setters
    public double getDistance() {
        return distance;
    }

    public void setDistance(double inDist) {
        this.distance = inDist;
    }
    
    public void setDistance(int inDist) {
		this.distance = (double) Math.round(inDist);
	}

    public double getElevHigh() {
        return elevHigh;
    }

    public void setElevHigh(double elevationHigh) {
        this.elevHigh = elevationHigh;
    }

    public double getElevLow() {
        return elevLow;
    }

    public void setElevLow(double elevationLow) {
        this.elevLow = elevationLow;
    }

    public double getElevChange() {
        return elevChange;
    }

    public void setElevChange(double elevationLow, double elevationHigh) {
        this.elevChange = elevationHigh - elevationLow;
    }
}