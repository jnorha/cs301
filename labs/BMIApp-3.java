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
		
		
		/*
		 * Test of passing by value vs. passing by reference
		 * Added on 5/28/2025
		 * */
		System.out.println("The object after the change:");
		changeObject(person1);
		person1.printHealthReport();
		System.out.println("The object after the second change:");
		changeObject2(person1);
		person1.printHealthReport();
		
		int number1 = 0, number2 = 0;
		changeValue(number1, number2);
		System.out.printf("After the change, number1 = %d, number 2 = %d \n", number1, number2);
		
		int aArray[] = new int[5];
		for(int i=0; i<aArray.length;i++) {
			aArray[i] = i+1;
		}
		System.out.println("The array before the change:");
		printArray(aArray);
		System.out.println("The array after the change:");
		changeArray(aArray);
		printArray(aArray);
		
	}
	
	public static void changeValue(int a, int b) {
		a = 5;
		b = 6;
	}
	
	public static void changeArray(int iArray[]) {
		for (int i=0; i<iArray.length; i++) {
			iArray[i] = 10;
		}
	}
	
	public static void printArray(int iArray[]) {
		for (int i=0; i<iArray.length-1; i++) {
			System.out.print(iArray[i]+",");
		}
		if(iArray.length >=1) {
			System.out.print(iArray[iArray.length-1]+"\n");
		}
	}
	
	public static void changeObject(Person person) {
		person.setName("Sam");
		person.setWeight(125);
		person.setHeightFeet(5);
		person.setHeightInches(9);
		
	}
	
	public static void changeObject2(Person person) {
		person = new Person("Sam", 128,5,9);
	}
}
