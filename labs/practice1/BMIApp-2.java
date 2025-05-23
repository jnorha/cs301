/**
 * Course: CS300 - Summer 2025
 * Program: BMI Calculator
 * 			Allow one to input their height, weight value, and then 
 * 			calculate BMI value and tell their health status
 * Name: Jiazhen Zhou
 * Wisc Email: <jzhou346@wisc.edu>
 * @see Person class 
 * 
 */

import java.util.*;

public class BMIApp {
	public static void main(String args[]) {
		//Ask user to input name, weight, and height
		//(1) Create a scanner object to take input
		Scanner myScanner = new Scanner(System.in);
		//(2) Define variable to take the input
		System.out.println("What is your name?");
		String name = myScanner.nextLine();
		System.out.println("Weight:");
		double weight = myScanner.nextDouble();
		System.out.println("Please tell me your height.");
		System.out.println("How many feet?");
		int heightFeet = myScanner.nextInt();
		System.out.println("How many Inches?");
		int heightInches = myScanner.nextInt();
		
		//Create a person object using the input values
		Person person1 = new Person(name, weight,heightFeet, heightInches);
		//Call the printHealthReport method of the Person object to generate the health report
		person1.printHealthReport();
		
	}

}
