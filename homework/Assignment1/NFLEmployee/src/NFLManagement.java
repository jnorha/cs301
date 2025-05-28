/**
 * Course: CS300 - Summer 2025
 * Program: NFLManagement.java for Lab 1
 * 		gathers all the necessary information for an employee and generates the new object
 * Name: Josh Daniels
 * Wisc Email: jndaniels@wisc.edu
 * Date:	05/28/2025
 * Version:	1.0
 * @see NFLEmployee class 
 * 
 */

import java.util.*;

public class NFLManagement {
	public static void main(String args[]) {
		//Ask user to input the employee's name, team, job category, job title, salary, and contract end year 
		//(1) Create a scanner object to take input
		Scanner myScanner = new Scanner(System.in);
		//(2) Define variable to take the input
		System.out.println("What is the employee's name?");
		String name = myScanner.nextLine();
		System.out.println("What team is the employee on?");
		String team = myScanner.nextLine();
		System.out.println("What is the employee's job category?");
		String jobCategory = myScanner.nextLine();
		System.out.println("What is the employee's job title?");
		String title = myScanner.nextLine();
		System.out.println("What is the employee's salary?");
		int salary = myScanner.nextInt();
		System.out.println("What year does the employee's contract end?");
		int endYear = myScanner.nextInt();
		
		//Create an employee object using the input values and the set constructor
		NFLEmployee newEmployee = new NFLEmployee(name, team,jobCategory, title, salary, endYear);
		
		// check the contract status for this new employee
		
		if(newEmployee.getEndYear() <= 2024) {
			System.out.println("This employee's contract has expired. Their contract record should be remvoed from the database.");
			System.out.println("Expired Employee Contract Info:");
			System.out.println("Name="+newEmployee.getName()+", Team="+newEmployee.getTeam()+", Job Category="+newEmployee.getJobCategory()+", Title="+newEmployee.getTitle()+", Salary="+newEmployee.getSalary()+", Contract End Year="+newEmployee.getEndYear());
		}
		else if(newEmployee.getEndYear() == 2025) {
			Scanner newScanner = new Scanner(System.in);
			System.out.println("This employee's contract needs to be updated. Please provide the updated information: ");
			System.out.println("What is their updated job title? ");
			String newJobTitle = newScanner.nextLine();
			System.out.println("What is their updated annual salary?");
			int newSalary = newScanner.nextInt();
			System.out.println("What year does their new contract end?");
			int newEndYear = newScanner.nextInt();
			
			newEmployee.newContract(newJobTitle, newSalary, newEndYear);
			System.out.println("Updated Employee Contract Info:");
			System.out.println("Name="+newEmployee.getName()+", Team="+newEmployee.getTeam()+", Job Category="+newEmployee.getJobCategory()+", Title="+newEmployee.getTitle()+", Salary="+newEmployee.getSalary()+", Contract End Year="+newEmployee.getEndYear());
		}
		else {
			System.out.println("Employee Contract Info:");
			System.out.println("Name="+newEmployee.getName()+", Team="+newEmployee.getTeam()+", Job Category="+newEmployee.getJobCategory()+", Title="+newEmployee.getTitle()+", Salary="+newEmployee.getSalary()+", Contract End Year="+newEmployee.getEndYear());
		}
		
		//Call the printPayStub method of the NFLEmployee object to generate their monthly pay stub
		System.out.println("Employee Monthly Pay Stub:");
		newEmployee.printPayStub();
	
		}
}
