/**
 * Class: Guest
 * Function: new object for Happy Farms guest 
 * Name: Josh Daniels
 * Wisc Email: jndaniels@wisc.edu
 */


import java.util.*;
import java.time.*;

public static class Group {
		//Attributes
		private String name;
		private LocalTime lastArrival;
		private LinkedList<Guest> members;
		int size;
		
		public Group (String groupName, LocalTime maxArrival, 
		
		

		
		//constructor
		
		public Guest (String guestName, String arrivalTime, String groupName) {
			setName(guestName);
			setArrival(arrivalTime);
			setGroup(groupName);
			setArrivalHour();
			setArrivalMin();
		}

	//--//Methods - setters and getters
		// ---------------------------------------
		//Strings first
		public void setName(String guestName){
			this.name = guestName;
		}
		public String getName(){
			return this.name;
		}	
		
		public void setArrival(String arrivalTime){
			this.arrival = arrivalTime;
		}
		public String getArrival(){
			return this.arrival;
		}
		
		public void setGroup(String groupName){
			this.group = groupName;
		}
		public String getGroup(){
			return this.group;
		}
		
		//ints
		public void setArrivalHour(){
			//take the first part of arrival string
			String arrivalStrings [] = arrival.split(":");
			int arrivalHourInt = Integer.parseInt(arrivalStrings[0]);
			this.arrivalHour = arrivalHourInt;
			
		}

		public int getArrivalHour(){
			return this.arrivalHour;
		}
		
		
		public void setArrivalMin(){
			//take the first part of arrival string
			String arrivalStrings [] = arrival.split(":");
			int arrivalMinInt = Integer.parseInt(arrivalStrings[1]);
			this.arrivalMin = arrivalMinInt;
			
		}

		public int getArrivalMin(){
			return this.arrivalMin;
		}
		
}
