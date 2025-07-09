/**
 * Course: CS300 - Summer 2025
 * Program: Player.java for 6/18 class
 * 		A class that defines the attributes and operations about an NFL player
 * Name: Josh Daniels
 * Wisc Email: jndaniels@wisc.edu
 * Date:	6/16/25
 */
public class Player extends NFLEmployee{

	private String status; //active/out/questionable
	private String stats; //player statistics for the past season
	
	//Constructor	
	public Player(String employeeName, NFLTeam employeeTeam, String employeeJobCategory, String employeeTitle, 
			int employeeSalary, int employeeEndYear, String plaerStaus, String playerStats) {
		super(employeeName, employeeTeam, employeeJobCategory, employeeTitle, 
				employeeSalary, employeeEndYear);
		setStatus(plaerStaus);
		setStats(playerStats);
	}
	
	//Setters and getters
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}
	
	/**
	 * 
	 * @override calcIncome method
	 *  Calculate the income as the sum of salary and bonus
	 * @param: none. Just use attributes
	 * @return: the total income
	 */
	public double calcIncome() {
		//System.out.println("The Player class calcIncome method is called!");
		return this.getSalary()+bonus();
	}

	/**
	 * calcGameRate method - calculate the ratio of games a player has played
	 * @param: none. Just use the stats attribute
	 * @return: the ratio of games one played
	 */
	public double calcGameRate() {
		//Parse the fields in the stat String
		String statsArray[] = this.stats.split("-");
		int gamesPlayed = Integer.parseInt(statsArray[0]);
		int gamesTotal = Integer.parseInt(statsArray[1]);
		
		if(gamesTotal <=0 ) {
			//System.out.println("Games total value is invalid");
			return 0;
		}		
		double gameAttendance = gamesPlayed*1.0/gamesTotal;

		return gameAttendance;
	}
	
	/**
	 * calcSnapRate method - calculate a player's snaps ratio
	 * @param: none. Just use the stats attribute
	 * @return: the ratio of snaps one played
	 */
	public double calcSnapRate() {
		//Parse the fields in the stat String
		String statsArray[] = this.stats.split("-");
		int snapsPlayed = Integer.parseInt(statsArray[2]);		
		int snapsTotal = Integer.parseInt(statsArray[3]);
			
		if(snapsTotal <=0) {
			//System.out.println("Snaps total value is invalid");
			return 0;
		}		
		
		double snapRate = snapsPlayed*1.0/snapsTotal;

		return snapRate;
	}
	
	/**
	 * bonus method - calculate a player's bonus based on his stats
	 * @param: none. Just use attributes
	 * @return: the bonus value
	 */
	public double bonus() {

		double bonusValue = 0;
	
		double gameAttendance = calcGameRate();
		double snapRate = calcSnapRate();
		
		//only qualified if one played more than half of the games
		if(gameAttendance > 0.5) {
			//they will get 10% of salary if the snap rate is over 60%, and 20% if the snap rate is over 80%
			if(snapRate >1.0 || snapRate <0) {
				System.out.println("The snap rate value is invalid");
				return 0;
			}
			
			if(snapRate >= 0.8) { //snapRtae >0.8
				bonusValue = this.getSalary()*0.2;
			}
			else if (snapRate >=0.6) {  //snapRtae >0.6 and snapRtae <0.8
				bonusValue = this.getSalary()*0.1;
			}
			else {
				//bonusValue = 0;
			}
		}
		//else case, bonusValue = 0, no need of handling it
		
		return bonusValue;		
		
	}
			

}
