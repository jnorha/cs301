
public class Clock {
	private int hours;
	private int mins;
	private String amPM;

	
	// define instance variables hour, minute, and aOrP (am or pm)
	// use the exact same variable names

	public Clock (int hours, int mins, char aOrP) {
	    // create a clock, 12-hour display
		setHours(hours);
		setMins(mins);
		setAmPm(aOrP);
	}
	
	public void setHours(int hours){
		this.hours = hours;
	}
	public int getHours(){
		return this.hours;
	}	
	
	public void setMins(int mins) {
		this.mins = mins;
	}
	public int getMins(){
		return this.mins;
	}
	
	public void setAmPm(char amOrPm) {
		if(amOrPm == 'a'){
			this.amPM = "am";
		}
		else if(amOrPm == 'p'){
			this.amPM = "pm";
		}
	}
	public String getAmPm(){
		return this.amPM;
	}
	

	public Clock() {
	    // calls the other constructor to set time to 12:00 am 
		this.setHours(12);
		this.setMins(00);
		this.setAmPm('a');
	}
	

	public void aheadOneHour() {
	    // set the clock ahead one hour
		if(this.hours == 12 && this.amPM.equals("am")) {
			this.setHours(1);
			this.setAmPm('p');
		}
		else if(this.hours == 12 && this.amPM.equals("pm")) {
			this.setHours(1);
			this.setAmPm('a');
		}
		else {
			this.setHours(hours+1);
		}
		
	    // Hint: come up with corner cases and handle them all
	}

	public void backOneHour() {
	    // set the clock back one hour
		if(this.hours == 1 && this.amPM.equals("am")) {
			this.setHours(12);
			this.setAmPm('p');
		}
		else if(this.hours == 1 && this.amPM.equals("pm")) {
			this.setHours(12);
			this.setAmPm('a');
		}
		else {
			this.setHours(hours-1);
		}
		
	    // Hint: come up with corner cases and handle them all
	}

	
	public void addTime(int hours, int mins) {
		if(hours >= 0 && mins >= 0) {
			int totalMins = this.mins+mins;
			int totalHours = hours;
			if(totalMins>=60) {
				int addHours = totalMins/60;
				totalHours = totalHours+addHours;
				this.mins=(totalMins/addHours)-60;
				for(int i=0;i<totalHours;i++) {
					System.out.println(this.hours +":" + this.padMinute() + this.amPM);
					this.aheadOneHour();
				}
			}
			else {
				this.mins=totalMins;
				for(int i=0;i<totalHours;i++) {
					//System.out.println(this.hours +":" + this.padMinute() + this.amPM);
					this.aheadOneHour();
				}
			}
			
		}
		else {
			throw new IllegalArgumentException("hours or minutes cannot be negative");
		}
	}

	private String padMinute() {
	    // pads the minute with a 0 for < 10 minutes
	    String minuteStr = "";
	    if (this.mins < 10) {
	        minuteStr = "0" + String.valueOf(this.mins);
	    } else {
	        minuteStr = String.valueOf(this.mins);
	    }
	    return minuteStr;
	}

	//If you print any object, Java compiler internally invokes the toString() method on the object. 
	public String toString() {
		String clocks = this.getHours() + ":"+this.padMinute()+" "+this.getAmPm();
		return clocks;
	    // show H:MM am or H:MM pm
	    // make sure to pad minutes for < 10 minutes
	    // example: 7:03 am, 11:05 pm
	}
}
