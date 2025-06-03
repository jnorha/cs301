/**
 * Course: CS300 - Summer 2025
 * Program: NFLManagement.java for 5/29 class (File processing version)
 * 		Read in the personnel file and process the employee information in a batch
 * Author: Josh Daniels
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
	 * Method: managing personnel, this is the main menu
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
		
		// add a new men
		System.out.println(" ========== Personnel PayStub Management Menu ==============");
		System.out.println(" 1 - Print Paystub for Specific Employee");
		System.out.println(" 2 - Print Paystubs for the Highest and Lowest Salary Employees");
		System.out.println(" 3 - Print Paystubs a Specific Team on screen and also generate text files for ALL employee contracts, nested within team directory");
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
			
		case 3:
			//manageContract(fileName);
			System.out.println("Team name?");
			Scanner inputTeamName = new Scanner(System.in);
			String teamNameChoice = inputTeamName.nextLine();
			teamPayStubs(fileName, teamNameChoice);
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
	 * NOTE: I did this one first, before realizing I would need a list/array/sorting function for other questions. Could revisit and try to re write but I think it works.
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
	 * @param string fileName
	 * @return none - just prints the high and low
	 */
	
	public static void printHighLow(String fileName) {
		
		// first, lets get data sources set up. List of string arrays first
		List<String[]> listArrayStrings = new ArrayList<>();
		
		try {
			File inFile = new File(fileName);

			FileReader  fileReader = new FileReader(inFile);
			BufferedReader bufferReader = new BufferedReader(fileReader);

			String inputStr = bufferReader.readLine();
			inputStr = bufferReader.readLine();

			while(inputStr != null ) {
				//Create an object for each record read in
				String lineStrings [] = inputStr.split(",");
				listArrayStrings.add(lineStrings); 
				inputStr = bufferReader.readLine();
			}
			
			// test the list of arrays
			//System.out.println("Length of file: " + listArrayStrings.size() + " And the First entry is: " + listArrayStrings.get(0)[1]);
			
			// now we should make an object array of employees from the list now that we actually KNOW HOW LONG THE FILE IS (java should really let you create arbitrary/flexible array sizes
			int fileLength = listArrayStrings.size();
			NFLEmployee allEmployees[] = new NFLEmployee[fileLength];

			for(int i=0;i<fileLength;i++) {
				
				String[] lineStrings = listArrayStrings.get(i);
				String playerName = lineStrings[0];
				String teamName = lineStrings[1];
				String category = lineStrings[2];
				String title = lineStrings[3];
				int salary = Integer.parseInt(lineStrings[4]);
				int expirationYear = Integer.parseInt(lineStrings[5]);
				
				allEmployees[i] = new NFLEmployee(playerName, teamName, category, title, salary, expirationYear);
			}
			
			
			//Check our array
			//System.out.println("This is the last employee in the object array " + allEmployees[175].getName());
			
			// try to sort (did this plenty in python) It goes low to high by default
			Arrays.sort(allEmployees, Comparator.comparingInt(NFLEmployee -> NFLEmployee.getSalary()));
			System.out.println("Employee with the highest salary: ");
			allEmployees[fileLength-1].printPayStub();
			System.out.println("Employee with the lowest salary: ");
			allEmployees[0].printPayStub();
			//System.out.println("This is the lowest paid employee in the object array " + allEmployees[0].getName());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * Method: Print all the paystubs for a given team
	 * @param string fileName, string teamName
	 * @return none - prints all the pay stubs of a given team and also writes to a new folder/file about the players
	 */
	
	public static void teamPayStubs(String fileName, String targetTeamName) {
		// borrowing our array creator from before
		//NOTE **This should be its own method, and if I could figure out how to return an object array from a method I would make it one

		List<String[]> listArrayStrings = new ArrayList<>();
		
		try {
			File inFile = new File(fileName);

			FileReader  fileReader = new FileReader(inFile);
			BufferedReader bufferReader = new BufferedReader(fileReader);

			String inputStr = bufferReader.readLine();
			inputStr = bufferReader.readLine();

			while(inputStr != null ) {
				//Create an object for each record read in
				String lineStrings [] = inputStr.split(",");
				listArrayStrings.add(lineStrings); 
				inputStr = bufferReader.readLine();
			}

			// test the list of arrays
			//System.out.println("Length of file: " + listArrayStrings.size() + " And the First entry is: " + listArrayStrings.get(0)[1]);
			
			// now we should make an object array of employees from the list now that we actually KNOW HOW LONG THE FILE IS (java should really let you create arbitrary/flexible array sizes
			int fileLength = listArrayStrings.size();
			NFLEmployee allEmployees[] = new NFLEmployee[fileLength];

			for(int i=0;i<fileLength;i++) {
				
				String[] lineStrings = listArrayStrings.get(i);
				String playerName = lineStrings[0];
				String teamName = lineStrings[1];
				String category = lineStrings[2];
				String title = lineStrings[3];
				int salary = Integer.parseInt(lineStrings[4]);
				int expirationYear = Integer.parseInt(lineStrings[5]);
				
				allEmployees[i] = new NFLEmployee(playerName, teamName, category, title, salary, expirationYear);
			}
			
			
			//Check our array
			//System.out.println("This is the last employee in the object array " + allEmployees[175].getName());
			
			/*
			//Build for loop to gather all available team names into a list
			List<String> teamList = new ArrayList<String>();
			
			for(int i=0;i<fileLength;i++) {
				String empTeamName = allEmployees[i].getTeam();
				if(!teamList.contains(empTeamName)) {
					teamList.add(empTeamName);
				}
			}
			*/ 
			
			//Actually, i think java will just build the folder's and nest accordingly.
			for(int i=0;i<fileLength;i++) {
				String theTeamName = allEmployees[i].getTeam();
				String thePlayerName[] = allEmployees[i].getName().split(" ");
				String thePlayerFullName = thePlayerName[0] + thePlayerName[1];
				if(allEmployees[i].getTeam().equals(targetTeamName)) {
					allEmployees[i].printPayStub();
				}
				else {
					;
				}
				String newFileName ="C:\\temp\\" +  theTeamName+"\\Stub" + thePlayerFullName + ".txt";
				//Test it out before writing files like a madman
				//System.out.println(newFileName);
				try {
					FileWriter fw = new FileWriter(newFileName,true);
					double playerSalary = allEmployees[i].getSalary();
					double monthlySalary = allEmployees[i].getSalary() / 12;
					double fedTax = 0.0;
					if(playerSalary<=11925) {
						fedTax = playerSalary * 0.10;
					}
					else if(11926<playerSalary && playerSalary<=48475) {
						fedTax = playerSalary * 0.12;
					}
					else if(48475<playerSalary && playerSalary<=103350) {
						fedTax = playerSalary * 0.22;
					}
					else if(103350<playerSalary && playerSalary<=197300) {
						fedTax = playerSalary * 0.24;
					}
					else if(197300<playerSalary && playerSalary<=250525) {
						fedTax = playerSalary * 0.32;
					}
					else if(250525<playerSalary && playerSalary<=626350) {
						fedTax = playerSalary * 0.35;
					}
					else {
						fedTax = playerSalary * 0.37;
					}
					double monthlyTax = fedTax / 12;
					double netMonthPay = monthlySalary - monthlyTax;
					String fileHeader = String.format("%24s %20s %20s %20s \n", "Employee Name", "Monthly salary",
							"Monthly Federal tax", "Monthly net pay");
					String filePayStubContents = String.format("%24s %20.2f %20.2f %20.2f \n", allEmployees[i].getName(), monthlySalary,
							monthlyTax, netMonthPay);
					fw.write(fileHeader);
					fw.write(filePayStubContents);
					fw.close();
				}
				catch(IOException ioe) {
					System.err.println("IOException: " + ioe.getMessage());
				}
			
			}
			
		} 
		catch(IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
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