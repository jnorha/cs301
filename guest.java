/**
 * Class: Guest
 * Function: new object for Happy Farms guest 
 * Name: Josh Daniels
 * Wisc Email: jndaniels@wisc.edu
 */


import java.util.*;
import java.time.*;

public class Guest {
		//Attributes
		private String name;
		private LocalTime arrival;
		private String group;

		
		//constructor
		
		public Guest (String guestName, LocalTime arrivalTime, String groupName) {
			setName(guestName);
			setArrival(arrivalTime);
			setGroup(groupName);
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
		
		public void setArrival(LocalTime arrivalTime){
			this.arrival = arrivalTime;
		}
		public LocalTime getArrival(){
			return this.arrival;
		}
		
		public void setGroup(String groupName){
			this.group = groupName;
		}
		public String getGroup(){
			return this.group;
		}
		
		
}
