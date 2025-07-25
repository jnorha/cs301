/**
 * Course: CS300 - Summer 2025
 * Program: Coach.java 
 * 	Function: a calss for an NFL Coach subclass of NFLEmployee
 * Name: Josh Daniels
 * Wisc Email: jndaniels@wisc.edu
 * Date:	6/15/25
 */
public class Coach extends NFLEmployee{

	private TeamRecord record; //active/out/questionable
	private String style; //player statistics for the past season
	private int experience;
	
	//Constructor	
	public Coach(String employeeName, NFLTeam employeeTeam, String employeeJobCategory, String employeeTitle, 
			int employeeSalary, int employeeEndYear, TeamRecord teamRecord, String coachStyle, int coachExperience) {
		super(employeeName, employeeTeam, employeeJobCategory, employeeTitle, 
				employeeSalary, employeeEndYear);
		setRecord(teamRecord);
		setStyle(coachStyle);
		setExperience(coachExperience);
	}
	
	//Setters and getters
	public TeamRecord getRecord() {
		return record;
	}

	public void setRecord(TeamRecord teamRecord) {
		this.record = teamRecord;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String coachStyle) {
		this.style = coachStyle;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int coachExperience) {
		this.experience = coachExperience;
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
	public double calcWinPercent() {
		//Parse the fields in the stat String
		String statsArray[] = this.record.teamRecord().split("-");
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
	 * display style method - presents the type of coaching style the coach has. Only displays for Head Coach, Offensive Coordinator, and Defensive coordinator
	 * @param: none. Just use attributes
	 * @return: none. just display their coaching style
	 */
	
	public void displayStyle() {
		if(this.style.equals("HC") || this.style.equals("OC") || this.style.equals("DC")) {
			System.out.println(this.getName() + "' coaching style is: " + this.style);
		}
		else {
			System.out.println("This coach does not have an applied style.");
		}
	}
	
	
	/**
	 * bonus method - calculate a coaches bonus based on their stats
	 * @param: none. Just use attributes
	 * @return: the bonus value
	 */
	public double bonus() {

		double bonusValue = 0;
		
	
		double winPercent = calcWinPercent();
		
		//only qualified if one played more than half of the games
		if(winPercent >= 0.75) {
			bonusValue = this.getSalary()*0.2;
		}
		else if (winPercent >= 0.5 && winPercent < 0.75) {  //snapRtae >0.6 and snapRtae <0.8
			bonusValue = this.getSalary()*0.1;
		}
		else {
				//bonusValue = 0;
		}
		//else case, bonusValue = 0, no need of handling it
		
		return bonusValue;		
		
	}
	
	/**
	 * Method: printContract
	 * overrides the other print contract to include coach info
	 */
	
	public void printContract() {
		//Print out name, and contract information
		String employeeName = this.getName();
		String employeeTeam = this.getTeam().getTeamName();
		String employeeCategory = this.getJobCategory();
		String employeeTitle = this.getTitle();
		int employeeExpires = this.getEndYear();
		double employeeSalary = this.calcIncome();
		String style = this.style;
		System.out.printf("%24s %20s %20s %20s %20s %20s \n", "Employee Name", "Team",
		"Job Category", "Title", "Salary", "Contract Expiring", "Coaching Style");
		System.out.printf("%24s %20s %20s %20s %20.2f %20d %20s \n", employeeName, employeeTeam,
				employeeCategory, employeeTitle, employeeSalary,employeeExpires,style);
		
	}		

}
