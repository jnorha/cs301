/**
 * Course: CS300 - Summer 2025
 * Program: NFLEmployee.java for Lab 1
 * 		A class that defines the attributes and operations about an NFL employee
 * Name: Josh Daniels
 * Wisc Email: jndaniels@wisc.edu
 * Date:	06/03/2025
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
	public void setSalary(int valSalary) {
		this.salary = 0;
		if(valSalary < 15080) {
			System.out.println("Less than minimum wage in 2023?? Are they working for tips?? This can't be right. Setting low end default salary value of 500000.");
			this.salary = 500000;
		}
		else if(valSalary > 1000000000) {
			System.out.println("More than a billion dollars?? Can I get a raise?? This can't be right. Setting high end default salary value of 8000000.");
			this.salary = 8000000;
		}
		else {
			this.salary = valSalary;
		}
	}
	
	public int getSalary() {
		return this.salary;
	}

	public void setEndYear(int contractEndYear){
		this.endYear = 0;
		//Make sure the contract end year is realistic
		// getting rid of this since its not real
		/*if(contractEndYear <= 2023){
			System.out.println("The contract end year is invalid! Must be 2024 or later. Please update the contract for " + this.name +".");
			this.endYear = contractEndYear;
		}
		else{
			this.endYear = contractEndYear;
		}
		*/
		this.endYear = contractEndYear;
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
	* @param String newTitle, int newSalary, int newEndYear
	* @return NFLEmployee with updated values
	*/
	
	public void newContract(String newTitle, int newSalary, int newEndYear) {
		this.title = newTitle;
		this.salary = newSalary;
		this.endYear = newEndYear;
		
	}
	
	/**
	* Method: calcTax
	* Function:calculates an employee's annual federal tax
	* @param employeeSalary
	* @return annualFedTax
	*/
	
	public double calcTax() {
		//calculate the annual federal tax based on their salary bracket
		double fedTax = 0;
		if(this.salary<=11925) {
			fedTax = this.salary * 0.10;
		}
		else if(11926<this.salary && this.salary<=48475) {
			fedTax = this.salary * 0.12;
		}
		else if(48475<this.salary && this.salary<=103350) {
			fedTax = this.salary * 0.22;
		}
		else if(103350<this.salary && this.salary<=197300) {
			fedTax = this.salary * 0.24;
		}
		else if(197300<this.salary && this.salary<=250525) {
			fedTax = this.salary * 0.32;
		}
		else if(250525<this.salary && this.salary<=626350) {
			fedTax = this.salary * 0.35;
		}
		else {
			fedTax = this.salary * 0.37;
		}
		
		return fedTax;
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
		String employeeTeam = this.getTeam();
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