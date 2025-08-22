/**
 * Course: CS300 - Summer 2025
 * Program: TeamRecord.java 
 * 	Function: a class for an NFL TeamRecord subclass of NFLTeam
 * Name: Josh Daniels
 * Wisc Email: jndaniels@wisc.edu
 * Date:	6/15/25
 */
public class TeamRecord extends NFLTeam{
	
	private int wins;
	private int losses;
	private int ties;

	
	//Constructor	
	public TeamRecord(String teamName, String teamLocation, String teamStadiumName, int teamStadiumCapacity, String teamConference, String teamDivision, int teamStartingYear, String teamCoach, int teamWins, int teamLosses, int teamTies) {
		super(teamName, teamLocation, teamStadiumName, teamStadiumCapacity, 
				teamConference, teamDivision,teamStartingYear,teamCoach);
		setWins(teamWins);
		setLosses(teamLosses);
		setTies(teamTies);
	}
	
	//Setters and getters
	public int getWins() {
		return wins;
	}

	public void setWins(int teamWins) {
		this.wins = teamWins;
	}
	
	public int getLosses() {
		return losses;
	}

	public void setLosses(int teamLosses) {
		this.losses = teamLosses;
	}
	
	public int getTies() {
		return ties;
	}

	public void setTies(int teamTies) {
		this.ties = teamTies;
	}
	
	public String teamRecord() {
		String teamRecordFormat = this.wins + "-" + this.losses + "-" + this.ties;
		return teamRecordFormat;
	}

	/**
	 * calcWinPercent method - calculate the ratio games won vs total games
	 * @param: none. Just use the stats attribute
	 * @return: the win percent as a double for the team.
	 */
	public Double calcWinPercent() {
		//Parse the fields in the stat String
		double winPercent = (this.wins * 2) + (this.ties)/(this.wins + this.losses + this.ties);
		
		return winPercent;
	}
			

}
