/**
 * Course: CS300 - Summer 2025
 * Program: NFLEmployee.java for Lab 1
 * 		A class that defines the attributes and operations about an NFL employee
 * Name: Josh Daniels
 * Wisc Email: jndaniels@wisc.edu
 * Date:	06/10/2025
 * Revision Date: 06/16/2025
 * Major revisions: add calcIncome() method; call the calcIncome method in calcTax() and printPayStub()
 * Version:	1.3
 * @see NFLEmployee class 
 * 
 */

import java.util.*;

public class NFLEmployee {
	//Attributes
	private String name;
	private NFLTeam team;
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
	
	public void setTeam(NFLTeam teamName) {
		this.team = teamName;
	}
	public NFLTeam getTeam(){
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
	public void setSalary(int valSalary) {
		if (valSalary < 30000) {
				System.out.println("Salary too low. Defaulting to $30,000.");
				this.salary = 30000;
			}
		else{
				this.salary = valSalary;
			}
		
	}
	
	public int getSalary() {
		return this.salary;
	}

	public void setEndYear(int contractEndYear){
		//Make sure the contract end year is realistic
		if(contractEndYear > 2125 || contractEndYear < 1925){
			System.out.println("The contract end year must be after 1925 and before 2125. Defaulting to 2026.");
			this.endYear = 2026;
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
	public NFLEmployee (String employeeName, NFLTeam employeeTeam, String employeeJobCategory, String employeeTitle, int employeeSalary, int employeeEndYear) {
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
	* @param String newTitle, int newSalary, int newEndYear
	* @return NFLEmployee with updated values
	*/
	
	public void newContract(Scanner scanner) {
		System.out.println("New job category: ");
		String updatedJob = scanner.nextLine();
		setJobCategory(updatedJob);
		System.out.println("New title: ");
		String updatedTitle = scanner.nextLine();
		setTitle(updatedTitle);
		System.out.println("New annual salary: ");
		int updatedSalary = scanner.nextInt();
		setSalary(updatedSalary);
		System.out.println("New end year: ");
		int updatedEndYear = scanner.nextInt();
		setEndYear(updatedEndYear);
		scanner.nextLine();
		
	}
	
	/**
	* Calculate one's income. This method could be overridden by subclasses as the income source
	* might differ based on job categories
	* @param none. Just use attributes
	* @return: income
	*/
	public double calcIncome() {
		return this.salary;
	}
	
	/**
	* Method: calcTax
	* Function:calculates an employee's annual federal tax
	* @param employeeSalary
	* @return annualFedTax
	*/
	
	public double calcTax() {
		double tax = 0.0;
		double[] brackets = {11925,48475,103350,197300,250525,626350};
		double[] rates = {0.10, 0.12, 0.22, 0.24, 0.32, 0.35, 0.37};
		double lowerBound = 0.0;
		double income = calcIncome();
		
		// loop through each income bracket.
		for (int i = 0; i < brackets.length; i++) {
			// quits loop if salary is less than the current bracket
			if (income <= brackets[i]) {
				tax += (income - lowerBound) * rates[i];
				return tax;
			}
		// sets the current bracket as the lower bound before looping to the next bracket
		else {
			tax += (brackets[i] - lowerBound) * rates[i];
			lowerBound = brackets[i];
		}
		}
		// if we get to here, then salary is higher than the highest bracket
			tax += (income - lowerBound) * rates[rates.length - 1];
			return tax;
		}
	
	/**
	* Method: printPayStub
	* Function:prints a pay stub for each month for an employee which includes their  (i) Employee name; (ii) Monthly salary; (iii) monthly Federal tax; (iv) Monthly net pay in one row.
	* @param none. Just return the attributes and call any of the methods necessary for calculating
	* @return none. Just print the pay stub
	*/
	
	public void printPayStub() {
		//Print out name, and salary information
		double monthlySalary = this.salary / 12;
		double annualfedTax = calcTax();
		double monthlyTax = annualfedTax / 12;
		double netMonthPay = monthlySalary - monthlyTax;
		System.out.printf("%24s %20s %20s %20s \n", "Employee Name", "Monthly salary",
		"Monthly Federal tax", "Monthly net pay");
		System.out.printf("%24s %20.2f %20.2f %20.2f \n", name, monthlySalary,
				monthlyTax, netMonthPay);
		
	}
	
	/**
	* Method: printPayStub
	* Function:prints a pay stub for each month for an employee which includes their  (i) Employee name; (ii) Monthly salary; (iii) monthly Federal tax; (iv) Monthly net pay in one row.
	* @param none. Just return the attributes and call any of the methods necessary for calculating
	* @return none. Just print the pay stub
	*/
	
	public void printContract() {
		//Print out name, and contract information
		String employeeName = this.getName();
		String employeeTeam = this.getTeam().getTeamName();
		String employeeCategory = this.getJobCategory();
		String employeeTitle = this.getTitle();
		int employeeExpires = this.getEndYear();
		double employeeSalary = this.salary;
		System.out.printf("%24s %20s %20s %20s %20s %20s \n", "Employee Name", "Team",
		"Job Category", "Title", "Salary", "Contract Expiring");
		System.out.printf("%24s %20s %20s %20s %20.2f %20d \n", employeeName, employeeTeam,
				employeeCategory, employeeTitle, employeeSalary,employeeExpires);
		
	}

}
