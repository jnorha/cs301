/**
 * Course: CS300 - Summer 2025
 * Program: NFLManagement.java for 5/29 class (File processing version)
 * 		Read in the personnel file and process the employee information in a batch
 * Author: Josh Daniels
 * Revised by: Jiazhen Zhou
 * Date:	5/29/2025
 * Version:	1.2
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
	 */
	public static void managePayStub(String fileName) {
		
		//Read in the NFLPersonnel file
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
				
				//Call the printPayStub method to print pay stub for each employee
				System.out.println("Employee Monthly Pay Stub:");
				employee.printPayStub();
				inputStr = bufferReader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
