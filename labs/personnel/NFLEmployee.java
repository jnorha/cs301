/**
 * Course: CS300 - Summer 2025
 * Program: NFLEmployee.java for Lab 1
 * 		A class that defines the attributes and operations about an NFL employee
 * Name: Your Name
 * Wisc Email: 
 * Date:	finish date
 * Version:	1.0
 * @see Person class 
 * 
 */

public class NFLEmployee {
	//Attributes
	private String name;
	private String team;
	private String jobCategoty;
	private String title;
	private int salary;
	private int endYear;//contract end year
	
	//Methods - setters and getters
	public void setName(String emploeeName){
		this.name = emploeeName;
	}
	public String getName(){
		return this.name;
	}	

	public void setEndYear(int contractEndYear){
		//Make sure the contract end year is realistic
		if(contractEndYear < 2023){
			System.out.println("The contract end year is invalid!");
			this.endYear = 2023;
		}
		else{
			this.endYear = contractEndYear;
		}
	}
	
	public int getEndYear(){
		return this.endYear;
	}


	/**
	 * Print the basic information of the employee
	 * @param:	none. Using the attributes
	 * @return:	none. Just print out the attribute values
	*/
	public void printBasicInfor(){
		System.out.println("=============================");
		System.out.println("Basic information of the employee:");
		System.out.println("Name: \t"+this.name");
		System.out.println("Team: \t"+this.team");
		System.out.println("Category: \t"+this.jobCategoty;");
		System.out.println("Title/position: \t"+this.title");
		System.out.println("Salary: \t"+this.salary");
		System.out.println("=============================");
	}

}
