/**
 * Class: Activity
 * Function: holds information about a users workout 
 * Author: Josh Daniels (jndaniels@wisc.edu)
 * Date: 7/26/25
 * Version: 1.0
 * 
 */


import java.time.*;
import java.util.*;

public class Activity {
	
	//Attributes
	private Hero character;
	private LocalDate date;
	private String type;
	private double duration;
	private double avgHeartRate;

	
//--//Constructor
	public Activity (Hero playerHero, LocalDate inDate, String inType, double inDuration, double heartRate) {
		setCharacter(playerHero);
		setDate(inDate);
		setType(inType);
		setDuration(inDuration);
		setHR(heartRate);
	}


//--//Methods - setters and getters
	// ---------------------------------------
	//Strings first
	//Setters and getters
	public Hero getCharacter() {
		return character;
	}

	public void setCharacter(Hero playerHero) {
		this.character = playerHero;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate inDate) {
		this.date = inDate;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String inType) {
		this.type = inType;
	}
	
	public double getDuration() {
		return duration;
	}
	
	public void setDuration(double inDuration) {
		this.duration = inDuration;
	}
	
	public double getHR() {
		return avgHeartRate;
	}
	
	public void setHR(double heartRate) {
		this.avgHeartRate = heartRate;
	}
	

}