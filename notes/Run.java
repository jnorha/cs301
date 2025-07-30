/**
 * Class: Run extends Activity
 * Function: holds information about a users workout 
 * Author: Josh Daniels (jndaniels@wisc.edu)
 * Date: 7/26/25
 * Version: 1.0
 * @see Activity
 */

import java.time.*;
import java.util.*;

public class Run extends Activity{


	private int distance; //active/out/questionable
	private int elevHigh; //player statistics for the past season
	private int elevLow;
	private int elevChange;
	
//--//Constructor	
	public Run(Hero playerHero, LocalDate inDate, String inType, int inDuration, int heartRate, int inDist, int elevationHigh, int elevationLow) {
		super(playerHero, inDate, inType, inDuration, 
				heartRate);
		setDistance(inDist);
		setElevHigh(elevationHigh);
		setElevLow(elevationLow);
		setElevChange(elevationLow, elevationHigh);
	}
	
	//second constructor with double as input
	
	public Run(Hero playerHero, LocalDate inDate, String inType, int inDuration, int heartRate, double inDist, int elevationHigh, int elevationLow) {
		super(playerHero, inDate, inType, inDuration, 
				heartRate);
		setDistance(inDist);
		setElevHigh(elevationHigh);
		setElevLow(elevationLow);
		setElevChange(elevationLow, elevationHigh);
	}
	
//--//Methods: Getters and Setters
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int inDist) {
		this.distance = inDist;
	}
	
	//check for if they provide a double, which many do their distance
	public void setDistance(double inDist) {
		this.distance = (int) Math.round(inDist);
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
