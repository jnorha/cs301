/**
 * Course: CS300 - Summer 2025
 * Program: NFLEmployee.java for Lab 1
 * 		A class that defines the attributes and operations about an NFL employee
 * Name: Josh Daniels
 * Wisc Email: jndaniels@wisc.edu
 * Date:	finish date
 * Version:	1.0
 * @see NFLEmployee class 
 * 
 */

public class NFLEmployee {
	//Attributes
	private String name;
	private String team;
	private String jobCategory;
	private String title;
	private int salary;
	private int endYear;//contract end year


//--//Methods - setters and getters
	// ---------------------------------------
	//Strings first
	public void setName(String emploeeName){
		this.name = emploeeName;
	}
	public String getName(){
		return this.name;
	}	
	
	public void setTeam(String teamName) {
		this.team = teamName;
	}
	public String getTeam(){
		return this.team;
	}
	
	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}
	
	public String getJobCategory(){
		return this.jobCategory;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}
	
	// Now for the Integers with check logic
	public void setSalary(int salary) {
		if(salary < 15080) {
			System.out.println("Less than minimum wage in 2023?? Are they working for tips?? This can't be right.");
		}
		else if(salary > 1000000000) {
			System.out.println("More than a billion dollars?? Can I get a raise?? This can't be right.");
		}
		else {
			this.salary = salary;
		}
	}
	
	public int getSalary() {
		return this.salary;
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
	public NFLEmployee (String employeeName, String employeeTeam, String employeeJobCategory, String employeeTitle, int employeeSalary, int employeeEndYear) {
		setName(employeeName);
		setTeam(employeeTeam);
		setJobCategory(employeeJobCategory);
		setTitle(employeeTitle);
		setSalary(employeeSalary);
		setEndYear(employeeEndYear);
	}
	

	
//--//Methods
	
	/**
	* Method: newContract
	* Function:update an employee's information based on new contract terms. Updates the job title, salary, and contract end time attributes.
	* @param newTitle, newSalary, newEndYear
	* @return NFLEmployee with updated values
	*/
	
	<placeholder>
	
	/**
	* Method: calcTax
	* Function:calculates an employee's annual federal tax
	* @param employeeSalary
	* @return annualFedTax
	*/
	
	<placeholder>
	
	/**
	* Method: printPayStub
	* Function:prints a pay stub for each month for an employee which includes their  (i) Employee name; (ii) Monthly salary; (iii) monthly Federal tax; (iv) Monthly net pay in one row.
	* @param employeeName
	* @return payStub
	*/
}
