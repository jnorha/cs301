/**
 * Course: CS300 - Summer 2025
 * Program: NFLManagement.java for 5/29 class (File processing version)
 * 		Read in the personnel file and process the employee information in a batch
 * Author: Josh Daniels
 * Revised by: Jiazhen Zhou on 05/28/2025
 * Date:	6/3/2025
 * Version:	1.3
 */

import java.util.*;	
import java.io.*;

public class NFLManagement {
	public static void main(String args[]) {
		//Create a menu for personnel management that can manage all employees whose information
		// is stored in a file
		String personnelFileName = "NFLPersonnel.csv";
		personelManagement(personnelFileName);
	}
	
	
	/**
	 * Method: managing personnel, including paystub and contract management for all employees
	 * 			in a personnel file
	 * @param employee personnel file name
	 * @retun none
	 */
	public static void personelManagement(String fileName) {
		//Print out the menu, and ask the user to make the choice
		System.out.println(" ========== Personnel Management Menu ==============");
		System.out.println(" 1 - Managing the contract");
		System.out.println(" 2 - Managing the paystub");
		System.out.println(" Others - exit");
		System.out.println(" Your choice:");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		
		//Do the detailed management operations based on the choice
		switch(choice) {
			case 1:
				//manageContract(fileName);
				System.out.println("To be done");
				
				break;
				
			case 2:
				managePayStub(fileName);
				break;
				
			default:
				System.out.println("Invalid choice, exiting ...");
				return;
						
		}
		
	}
	/**
	 * Method: Managing the pay stub for the specific employee
	 * @param employee personnel file name
	 * @return none
	 * 
* Add a menu to allow one to make choices on operations about pay stub and complete the related functionality and implement each functionality as a method: 

a. Print the paystub for a specific employee based on their name.

b. Print the paystubs for the employee with the highest salary and lowest salary.

c. Print the paystubs for all employees for a given team on screen. B
ut also print each paystub into a file named as TeamName/Stub+firstName+LastName.txt (i.e. each team will have a separate folder). 
An example of the file name is StubDevonteWyatt.txt inside the Packers folder.
	 * 
	 */
	public static void managePayStub(String fileName) {
		
		// add a new menu
		System.out.println(" ========== Personnel PayStub Management Menu ==============");
		System.out.println(" 1 - Print Paystub for Specific Employee");
		System.out.println(" 2 - Print Paystubs for the Highest and Lowest Salary Employees");
		System.out.println(" Others - exit");
		System.out.println(" Your choice:");
		
		
		
		// new scanner
		Scanner input2 = new Scanner(System.in);
		int payStubChoice = input2.nextInt();
		
		switch(payStubChoice) {
		case 1:
			//manageContract(fileName);
			System.out.println("Employee name?");
			Scanner inputEmployeeName = new Scanner(System.in);
			String employeeNameChoice = inputEmployeeName.nextLine();
			//employeeNameChoice.printPayStub();
			if (employeeNameChoice != null) {
				targetPrintPayStub(employeeNameChoice, fileName);
			}
			else {
				System.out.println("Something Went Wrong");
			}
		
			
			break;
			
		case 2:
			printHighLow(fileName);
			break;
			
		default:
			System.out.println("Invalid choice, exiting ...");
			return;
					
		}
	
		
	}
	
//--// METHODS FOR MANAGE PAYSTUB
	
	/** 
	 * Method: Print the paystub for the specific employee
	 * @param employeeName of the person you want printed
	 * @return none - prints out the paystub for that person
	 * 
	 */
	
	public static void targetPrintPayStub(String givenEmployeeName, String fileName) {
		try {
			File inFile = new File(fileName);

			FileReader  fileReader = new FileReader(inFile);
			BufferedReader bufferReader = new BufferedReader(fileReader);

			String inputStr = bufferReader.readLine();
			inputStr = bufferReader.readLine();

			while(inputStr != null ) {
				//Create an object for each record read in
				String lineStrings [] = inputStr.split(",");
			
				String playerName = lineStrings[0];
				String teamName = lineStrings[1];
				String category = lineStrings[2];
				String title = lineStrings[3];
				int salary = Integer.parseInt(lineStrings[4]);
				int expirationYear = Integer.parseInt(lineStrings[5]);
				
				NFLEmployee employee = new NFLEmployee(playerName, teamName, category, title, salary, expirationYear);
				if (employee.getName().equals(givenEmployeeName)){
					//Call the old print paystub method to print this persons stuff
					employee.printPayStub();
				}
				
				inputStr = bufferReader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method: Print the highest and lowest paid employees
	 * @param none
	 * @return none - just prints the high and low
	 */
	
	public static void printHighLow(String fileName) {
		// make a list of all salaries
		try {
			File inFile = new File(fileName);

			FileReader  fileReader = new FileReader(inFile);
			BufferedReader bufferReader = new BufferedReader(fileReader);

			String inputStr = bufferReader.readLine();
			inputStr = bufferReader.readLine();
			

			while(inputStr != null ) {
				//Create an object for each record read in
				String lineStrings [] = inputStr.split(",");

				String playerName = lineStrings[0];
				String teamName = lineStrings[1];
				String category = lineStrings[2];
				String title = lineStrings[3];
				int salary = Integer.parseInt(lineStrings[4]);
				int expirationYear = Integer.parseInt(lineStrings[5]);
				
				NFLEmployee employee = new NFLEmployee(playerName, teamName, category, title, salary, expirationYear);
				

				inputStr = bufferReader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



/**
 * 
 * Trying to get the object array to work....
 * 
 * // create object array
		NFLEmployee employees[] = new NFLEmployee[10];
		
		try {
			File inFile = new File(fileName);

			FileReader  fileReader = new FileReader(inFile);
			BufferedReader bufferReader = new BufferedReader(fileReader);

			String inputStr = bufferReader.readLine();
			inputStr = bufferReader.readLine();

			for (int i=0; i<employees.length; i++) {
				String lineStrings [] = inputStr.split(",");

				String playerName = lineStrings[0];
				String teamName = lineStrings[1];
				String category = lineStrings[2];
				String title = lineStrings[3];
				int salary = Integer.parseInt(lineStrings[4]);
				int expirationYear = Integer.parseInt(lineStrings[5]);
				
				employees[i] = new NFLEmployee(playerName, teamName, category, title, salary, expirationYear);
				
			}
			
			while(inputStr != null ) {
				//Create an object for each record read in
				inputStr = bufferReader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		employees[2].printPayStub();
		
 */