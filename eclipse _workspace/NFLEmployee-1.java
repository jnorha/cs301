/**
 * Course: CS300 - Summer 2025
 * Program: NFLEmployee.java for Lab 3
 * 		A class that defines the attributes and operations about an NFL employee
 * Name: Nick Izu
 * Wisc Email: izu@wisc.edu
 *  * Date:	6/9/25
 *  Revised by: Jiazhen Zhou
 *  Revision Date: 6/11/25
 *  Major revisions: (1) Add a calcIncome() method; (2) Call the calcIncome() method in the 
 *  		calcTax() and printPayStub() method
 * Version:	4.0
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
	
	// Constructor
	public NFLEmployee(String employeeName, NFLTeam employeeTeam, String employeeJobCategory, String employeeTitle, int employeeSalary, int employeeEndYear) {
		setName(employeeName);
		setTeam(employeeTeam);
		setJobCategory(employeeJobCategory);
		setTitle(employeeTitle);
		setSalary(employeeSalary);
		setEndYear(employeeEndYear);
	}

	//Methods - setters and getters
	public void setName(String employeeName){
		this.name = employeeName;
	}
	public String getName(){
		return this.name;
	}	

	
	public void setTeam(NFLTeam employeeTeam){
		this.team = employeeTeam;
	}
	public NFLTeam getTeam(){
		return this.team;
	}	
	
	
	public void setJobCategory(String employeeJobCategory){
		this.jobCategory = employeeJobCategory;
	}
	public String getJobCategory(){
		return this.jobCategory;
	}	
	
	
	public void setTitle(String employeeTitle){
		this.title = employeeTitle;
	}
	public String getTitle(){
		return this.title;
	}	
	
	
	public void setSalary(int employeeSalary){
		if (employeeSalary < 30000) {
			System.out.println("Salary too low. Defaulting to $30,000.");
			this.salary = 30000;	
		}
		else{
			this.salary = employeeSalary;
		}
	}
	public int getSalary(){
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


	/**
	 * Creates a new contract for the employee that sets the  job category, title,
  annual salary and contract ending year.
	 * @param	scanner: scanner to read user input 
	 * @return:	void. 
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
	 * 		might differ based on job categories
	 * @param none. Just use attributes
	 * @return: income
	 */
	public double calcIncome() {
		return this.salary;
	}
	
	/**
	 *  Calculates the annual federal tax based on the federal tax rate of 2025
	 * @param:	none. 
	 * @return:tax	The annual federal tax owed for the employee's salary based on the 2025 rates. 
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
	 * Prints the pay stub for each month with name, monthly salary, monthly federal tax, and monthly net pay. 
	 * @param:	none. 
	 * @return:	none. 
	*/
	public void printPayStub() {
		System.out.println("=============================");
		System.out.println("Monthly pay");
		System.out.println("Name: \t" + this.name);
		System.out.println("Team: \t" + this.team.getName());
		System.out.println("Gross pay: \t" + (calcIncome() / 12));
		System.out.printf("Taxes: %.2f\t\n", (calcTax() / 12));
		System.out.printf("Net pay: %.2f\t\n", ((calcIncome() / 12) - (calcTax() / 12)));
		System.out.println("=============================");
	}
	
	
	/**
	 * Print the basic information of the employee
	 * @param:	none. Using the attributes
	 * @return:	none. Just print out the attribute values
	*/
	public void printBasicInfo(){
		System.out.println("=============================");
		System.out.println("Basic information of the employee");
		System.out.println("Name: \t" + this.name);
		System.out.println("Team: \t" + this.team.getName());
		System.out.println("Category: \t" + this.jobCategory);
		System.out.println("Title/position: \t"+this.title);
		System.out.println("Salary: \t" + this.salary);
		System.out.println("=============================");
	}

}
