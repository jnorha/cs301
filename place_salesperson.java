/**
 * Course: CS300 - Summer 2025
 * Program: Salesperson.java for 6/18
 * 		A class that defines the attributes and operations about an NFL sales person
 * Name: Josh Daniels
 * Wisc Email: jndaniels@wisc.edu
 * Date:	6/16/25
 */
public class Salesperson extends NFLEmployee{

	private int sales; //active/out/questionable
	private double rate; //player statistics for the past season
	
	//Constructor	
	public Salesperson(String employeeName, NFLTeam employeeTeam, String employeeJobCategory, String employeeTitle, 
			int employeeSalary, int employeeEndYear, int salesAmount, double commissionRate) {
		super(employeeName, employeeTeam, employeeJobCategory, employeeTitle, 
				employeeSalary, employeeEndYear);
		setSalesAmount(salesAmount);
		setRate(commissionRate);
	}
	
	//Setters and getters
	public int getSalesAmount() {
		return sales;
	}

	public void setSalesAmount(int salesAmount) {
		this.sales = salesAmount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double commissionRate) {
		this.rate = commissionRate;
	}
	
	/**
	 * 
	 * @override calcIncome method
	 *  Calculate the income as the amount of sales times commission rate
	 * @param: none. Just use attributes
	 * @return: the total income
	 */
	public double calcIncome() {
		System.out.println("The SalesPerson class calcIncome method is called!");
		return this.getSalesAmount()*this.getRate();
	}
	
	
	/**
	* @override printPayStub
	* Function:prints out the total sales, commission rate information, monthly income, tax, and net income
	* @param none. Just return the attributes and call any of the methods necessary for calculating
	* @return none. Just print the pay stub
	*/
	
	public void printPayStub() {
		//Print out name, and salary information
		double monthlySalary = this.calcIncome() / 12;
		double annualfedTax = calcTax();
		double monthlyTax = annualfedTax / 12;
		double netMonthPay = monthlySalary - monthlyTax;
		System.out.printf("%24s %20s %20s %20s \n", "SalesPerson Name", "Total Sales", "Commission Rate", "Monthly salary",
		"Monthly Federal tax", "Monthly net pay");
		System.out.printf("%24s %20.2f %20.2f %20.2f \n", this.getName(), this.sales, this.rate, monthlySalary,
				monthlyTax, netMonthPay);
		
	}
			

}
