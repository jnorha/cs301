/**
 * Course: CS300 - Summer 2025
 * Program: NFLTeam.java for 5/29 class 
 * 		Read in the team file and process different aspects of an NFL Team object
 * Author: Josh Daniels
 * Date:	6/9/2025
 * Version:	1.3
 * @see NFLTeam class
 */


public class NFLTeam {
	private String team;
	private String location;
	private String stadiumName;
	private int stadiumCapacity;
	private String conference;
	private String division;
	private int startingYear;
	private String coach;
	
	//contract end year
	
//--//Methods - setters and getters
	// ---------------------------------------
	//Strings first
	public void setTeam(String teamName){
		this.team = teamName;
	}
	public String getTeam(){
		return this.team;
	}	
	
	public void setLocation(String teamLocation) {
		this.team = teamLocation;
	}
	public String getLocation(){
		return this.location;
	}
	
	public void setStadiumName(String teamStadiumName) {
		this.stadiumName = teamStadiumName;
	}
	
	public String getStadiumName(){
		return this.stadiumName;
	}
	
	public void setStadiumCapacity(int teamStadiumCapacity) {
		this.stadiumCapacity = 0;
		if(teamStadiumCapacity <= 0) {
			System.out.println("Stadium capacity cannot be less than or equal to 0. Setting default value of 1000.");
			this.stadiumCapacity = 1000;
		}
		else {
			this.stadiumCapacity = teamStadiumCapacity;
		}
	}
	public int getStadiumCapacity(){
		return this.stadiumCapacity;
	}
	
	

	public void setStartingYear(int teamStartingYear){
		this.startingYear = 0;
		//Make sure the starting year is realistic
		if (teamStartingYear > 2026) {
			System.out.println("No teams were created more recently than the future year of 2026. Setting default value of 1960.");
			this.startingYear = 1960;
		}
		else if(teamStartingYear < 1800) {
			System.out.println("No teams were created before the year 1800. Setting default value of 1960.");
			this.startingYear = 1960;
		}
		else {
			this.startingYear = teamStartingYear;
		}
	
	}
	
	public int getStartingYear(){
		return this.startingYear;
	}
	
	public void setConference(String teamConference) {
		this.conference = teamConference;
	}
	
	public String getConference() {
		return this.conference;
	}
	
	
	public void setDivision(String teamDivision) {
		this.division = teamDivision;
	}
	
	public String getDivision() {
		return this.division;
	}
	
	public void setCoach(String teamCoach) {
		this.coach = teamCoach;
	}
	
	public String getCoach() {
		return this.coach;
	}
	//Bug check Prints

	/**
	 * Print the basic information of the employee
	 * @param:	none. Using the attributes
	 * @return:	none. Just print out the attribute values
	*/
	
	/*
	public void printBasicInfor(){
		System.out.println("=============================");
		System.out.println("Basic information of the employee:");
		System.out.println("Name: \t"+this.name);
		System.out.println("Team: \t"+this.team);
		System.out.println("Category: \t"+this.jobCategory);
		System.out.println("Title/position: \t"+this.title);
		System.out.println("Salary: \t"+this.salary);
		System.out.println("Contract Expires: \t"+this.endYear);
		System.out.println("=============================");
	}
	*/
	
//--// now build the constructor an do initialization with setters
	public NFLTeam (String teamName, String teamLocation, String teamStadiumName, int teamStadiumCapacity, String teamConference, String teamDivision, int teamStartingYear, String teamCoach) {
		setTeam(teamName);
		setLocation(teamLocation);
		setStadiumName(teamStadiumName);
		setStadiumCapacity(teamStadiumCapacity);
		setConference(teamConference);
		setDivision(teamDivision);
		setStartingYear(teamStartingYear);
		setCoach(teamCoach);
	}
	

	
//--//Methods
	
	/**
	* Method: newLocaiton
	* Function:updates a teams stadium location, name, and capacity
	* @param String newLocation, String newStadiumName, int newStadiumCapacity
	* @return NFLTeam with updated values
	*/
	
	public void relocation(String newLocation, String newStadiumName, int newStadiumCapacity) {
		this.location = newLocation;
		this.stadiumName = newStadiumName;
		this.stadiumCapacity = newStadiumCapacity;
		
	}
	
}