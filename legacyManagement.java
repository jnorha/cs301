/**
 * Course: CS300 - Summer 2025
 * Program: NFLManagement.java for 5/29 class (File processing version)
 * 		Read in the personnel file and process the employee information in a batch
 * Author: Josh Daniels
 * Date:	6/9/2025
 * Version:	1.3
 */

import java.util.*;	
import java.io.*;

public class NFLManagement {
	public static void main(String args[]) {
		//Create a menu for personnel management that can manage all employees whose information
		// is stored in a file
		
		System.out.println("Test for inheritance:");
		Scanner scannerAlpha = new Scanner(System.in);
		
		String teamFileName = "NFLTeams.csv";
		String personnelFileName = "NFLPersonne2.csv";
		ArrayList<NFLTeam> teamFileArray = teamReadIn(teamFileName);
		ArrayList<NFLEmployee> employeeArray = readEmployeeToArray(personnelFileName, teamFileArray);
		//some print checks
		/*
		for(int i=0;i<teamFileArray.size();i++) {
			System.out.println(teamFileArray.get(i).getTeamName());
		}
		*/
		
		personelManagement(personnelFileName, teamFileArray, employeeArray);
	}
	
	
	
	/**
	 * Method: managing personnel, this is the main menu
	 * 			in a personnel file
	 * @param employee personnel file name
	 * @retun none
	 */
	public static void personelManagement(String fileName, ArrayList<NFLTeam> teamArray, ArrayList<NFLEmployee> employeeArray) {
		

		//Print out the menu, and ask the user to make the choice
		System.out.println(" ========== Personnel Management Menu ==============");
		System.out.println(" 1 - Manage Contracts");
		System.out.println(" 2 - Manage Paystubs");
		System.out.println(" 3 - Manage Player Teams");
		System.out.println(" 4 - Generate Team Tax Files");
		System.out.println(" Any other input - exit");
		System.out.println(" Your choice:");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		//Do the detailed management operations based on the choice
		switch(choice) {
			case 1:
				manageContract(fileName, teamArray, employeeArray);
				//System.out.println("To be done");
				
				break;
				
			case 2:
				managePayStub(fileName, teamArray, employeeArray);
				break;
				
			case 3:
				System.out.println("Employee name?");
				Scanner inputEmployeeName = new Scanner(System.in);
				String employeeNameChoice = inputEmployeeName.nextLine();
				//employeeNameChoice.printPayStub();
				if (employeeNameChoice != null) {
					employeeArray = transferProcessing(fileName, employeeNameChoice, employeeArray, teamArray);
					personelManagement(fileName, teamArray, employeeArray);
				}
				else {
					System.out.println("Please make sure you type first and last name of employee with a space in between.");
				}
				break;
				
			case 4:
				createTaxFile(fileName, teamArray, employeeArray);
				break;
			default:
				System.out.println("Alternate input, exiting ...");
				return;
						
		}
		
	}
	/**
	 * Method: Managing the pay stub options with designated menu
	 * @param employee personnel file name
	 * @return none
	 */
	public static void managePayStub(String fileName, ArrayList<NFLTeam> teamArray, ArrayList<NFLEmployee> employeeArray) {
		
		// add a new men
		System.out.println(" ========== Personnel PayStub Management Menu ==============");
		System.out.println(" 1 - Print Paystub for Specific Employee");
		System.out.println(" 2 - Print Paystubs for the Highest and Lowest Salary Employees");
		System.out.println(" 3 - Print Paystubs a Specific Team on screen and also generate text files for ALL employee contracts, nested within team directory");
		System.out.println(" 4 - Return to main menu");
		System.out.println(" Any other input - exit the program");
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
				targetPrintPayStub(employeeNameChoice, teamArray, employeeArray);
			}
			else {
				System.out.println("Please make sure you type first and last name of employee with a space in between.");
			}
		
			
			break;
			
		case 2:
			printHighLow(teamArray, employeeArray);
			break;
			
		case 3:
			//manageContract(fileName);
			System.out.println("Team name?");
			Scanner inputTeamName = new Scanner(System.in);
			String teamNameChoice = inputTeamName.nextLine();
			if (teamNameChoice != null) {
				teamPayStubs(fileName, teamNameChoice, teamArray, employeeArray);
			}
			else {
				System.out.println("Please make sure you type the name of the team as it exists in the contract.");
			}
			
			break;
			
		case 4:
			String personnelFileName = "NFLPersonnel.csv";
			personelManagement(personnelFileName, teamArray, employeeArray);
			break;
		default:
			System.out.println("Alternative input, exiting ...");
			return;
					
		}
	
		
	}
	
//--// METHODS FOR MANAGE PAYSTUB _______--------------------------------------___________________________
	
	/** 
	 * Method: Print the paystub for the specific employee
	 * @param employeeName of the person you want printed
	 * @return none - prints out the paystub for that person
	 * NOTE: I did this one first, before realizing I would need a list/array/sorting function for other questions. Could revisit and try to re write but I think it works.
	 */
	
	public static void targetPrintPayStub(String givenEmployeeName, ArrayList<NFLTeam> teamArray, ArrayList<NFLEmployee> employeeArray) {
		
		for(int i=0;i<employeeArray.size();i++) {
			if (employeeArray.get(i).getName().equals(givenEmployeeName)){
				//Call the old print paystub method to print this persons stuff
				employeeArray.get(i).printPayStub();
			}
			else {
				//pass other employees
				;
			}
		}

		System.out.println("Choose what to do next:");
		System.out.println(" 1 - Return to Main Menu");
		System.out.println(" Any Other Key - Exit the Program");
		System.out.println(" Your choice:");
		
		// new scanner
		Scanner input2 = new Scanner(System.in);
		int navChoice = input2.nextInt();
		
		switch(navChoice) {
		case 1:
			String personnelFileName = "NFLPersonnel.csv";
			personelManagement(personnelFileName, teamArray, employeeArray);
			break;
			
		default:
			System.out.println("Alternative input, exiting ...");
			return;
					
		}
		
	}
	
	/**
	 * Method: Print the highest and lowest paid employees
	 * @param string fileName
	 * @return none - just prints the high and low
	 */
	
	public static void printHighLow(ArrayList<NFLTeam> teamArray, ArrayList<NFLEmployee> employeeArray) {
		
		
		//Check our array
		//System.out.println("This is the last employee in the object array " + allEmployees[175].getName());
		
		// try to sort (did this plenty in python) It goes low to high by default
		employeeArray.sort(Comparator.comparingInt(NFLEmployee -> NFLEmployee.getSalary()));
		System.out.println("Employee with the highest salary: ");
		int numEmployee = employeeArray.size();
		employeeArray.get(numEmployee-1).printPayStub();
		System.out.println("Employee with the lowest salary: ");
		employeeArray.get(0).printPayStub();
		//System.out.println("This is the lowest paid employee in the object array " + allEmployees[0].getName());

		System.out.println("Choose what to do next:");
		System.out.println(" 1 - Return to Main Menu");
		System.out.println(" Any Other Key - Exit the Program");
		System.out.println(" Your choice:");
		
		// new scanner
		Scanner input2 = new Scanner(System.in);
		int navChoice = input2.nextInt();
		
		switch(navChoice) {
		case 1:
			String personnelFileName = "NFLPersonnel.csv";
			personelManagement(personnelFileName,teamArray, employeeArray);
			break;
			
		default:
			System.out.println("Alternative input, exiting ...");
			return;
					
		}
		
		
	}
	
	
	/**
	 * Method: Print all the paystubs for a given team
	 * @param string fileName, string teamName
	 * @return none - prints all the pay stubs of a given team and also writes to a new folder/file about the players
	 */
	
	public static void teamPayStubs(String fileName, String targetTeamName, ArrayList<NFLTeam> teamArray, ArrayList<NFLEmployee> employeeArray) {
		
		int fileLength = employeeArray.size();
		//Actually, i think java will just build the folder's and nest accordingly.
		for(int i=0;i<fileLength;i++) {
			String theTeamName = employeeArray.get(i).getTeam().getTeamName();
			String thePlayerName[] = employeeArray.get(i).getName().split(" ");
			String thePlayerFullName = thePlayerName[0] + thePlayerName[1];
			if(employeeArray.get(i).getTeam().getTeamName().contains(targetTeamName)) {
				employeeArray.get(i).printPayStub();
			}
			else {
				;
			}
			String newFileName ="C:\\temp\\" +  theTeamName+"\\Stub" + thePlayerFullName + ".txt";
			//Test it out before writing files like a madman
			//System.out.println(newFileName);
			try {
				FileWriter fw = new FileWriter(newFileName,true);
				double playerSalary = employeeArray.get(i).getSalary();
				double monthlySalary = employeeArray.get(i).getSalary() / 12;
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
				String filePayStubContents = String.format("%24s %20.2f %20.2f %20.2f \n", employeeArray.get(i).getName(), monthlySalary,
						monthlyTax, netMonthPay);
				fw.write(fileHeader);
				fw.write(filePayStubContents);
				fw.close();
			}
			catch(IOException ioe) {
				System.out.println("Something went wrong either generating the file or running the method. Check the following errors:");
				System.err.println("IOException: " + ioe.getMessage());
			}
		
		}

		System.out.println("Choose what to do next:");
		System.out.println(" 1 - Return to Main Menu");
		System.out.println(" Any Other Key - Exit the Program");
		System.out.println(" Your choice:");
		
		// new scanner
		Scanner input2 = new Scanner(System.in);
		int navChoice = input2.nextInt();
		
		switch(navChoice) {
		case 1:
			String personnelFileName = "NFLPersonnel.csv";
			personelManagement(personnelFileName, teamArray, employeeArray);
			break;
			
		default:
			System.out.println("Alternative input, exiting ...");
			return;
					
		}
	}

	
//--// METHODS FOR MANAGE CONTRACT _______--------------------------------------___________________________

	
	/**
	 * Method: primary manage contract method. Prints options
	 * @param string fileName
	 * @return none - presents all of the next options for managing contracts
	 */
	public static void manageContract(String fileName, ArrayList<NFLTeam> teamArray, ArrayList<NFLEmployee> employeeArray) {
		
		// add a new men
		System.out.println(" ========== Personnel Contract Management Menu ==============");
		System.out.println(" 1 - Print Contract for Specific Employee");
		System.out.println(" 2 - Print Total Salary of players on a given team and adjust based on Salary Cap");
		System.out.println(" 3 - Edit an Employee's contract");
		System.out.println(" 4 - Remove contracts ending in 2024 or earlier from the file");
		System.out.println(" 5 - Return to main menu");
		System.out.println(" Others - exit");
		System.out.println(" Your choice:");
		
		
		
		// new scanner
		Scanner inputContracts = new Scanner(System.in);
		int contractChoice = inputContracts.nextInt();
		
		switch(contractChoice) {
		case 1:
			//manageContract(fileName);
			System.out.println("Employee name? If not matches are found, presents options for next steps.");
			Scanner inputEmployeeName = new Scanner(System.in);
			String employeeNameChoice = inputEmployeeName.nextLine();
			//employeeNameChoice.printPayStub();
			if (employeeNameChoice != null) {
				targetPrintContract(employeeNameChoice, fileName, teamArray, employeeArray);
			}
			else {
				System.out.println("Something Went Wrong");
			}
		
			
			break;
			
		case 2:
			//get the input for what team and year
			System.out.println("Team name?");
			Scanner inputTeamNameAndYear = new Scanner(System.in);
			String teamName = inputTeamNameAndYear.nextLine();
			System.out.println("Target season year Salary Cap consideration?");
			int targetYear = inputTeamNameAndYear.nextInt();
			System.out.println("Target season Salary Cap amount?");
			int targetCap = inputTeamNameAndYear.nextInt();
			printTeamSalary(fileName, teamName, targetYear, targetCap, teamArray, employeeArray);
			break;
			
		case 3:
			//manageContract(fileName);
			System.out.println("Employee name? If not matches are found, presents options for next steps.");
			Scanner inEmployeeName = new Scanner(System.in);
			String employeeChoice = inEmployeeName.nextLine();
			//employeeNameChoice.printPayStub();
			if (employeeChoice != null) {
				targetEditContract(employeeChoice, fileName, teamArray, employeeArray);
			}
			else {
				System.out.println("Something Went Wrong");
			}
			break;
			
		case 4:
			remove2024(fileName, teamArray, employeeArray);
			break;
		
		case 5:
			String personnelFileName = "NFLPersonnel.csv";
			personelManagement(personnelFileName, teamArray, employeeArray);
			break;

		default:
			System.out.println("Invalid choice, exiting ...");
			return;
					
		}
	
		
	}
		
	/** 
	 * Method: Print the contract for the specific employee
	 * @param employeeName of the person you want printed
	 * @return none - prints out the contract for that person
	 * NOTE: I did this one by copying the other so thats why it doesnt use object array, before realizing I would need a list/array/sorting function for other questions. Could revisit and try to re write but I think it works.
	 */
		
	public static void targetPrintContract(String givenEmployeeName, String fileName, ArrayList<NFLTeam> teamArray, ArrayList<NFLEmployee> employeeArray) {
		int fileLength = employeeArray.size();
		for(int i=0;i<fileLength;i++) {
			if (employeeArray.get(i).getName().equals(givenEmployeeName)){
				//Call the old print paystub method to print this persons stuff
				employeeArray.get(i).printContract();
			}
		}
			
		System.out.println("Choose what to do next:");
		System.out.println(" 1 - Return to Main Menu");
		System.out.println(" Any Other Key - Exit the Program");
		System.out.println(" Your choice:");
		
		// new scanner
		Scanner input2 = new Scanner(System.in);
		int navChoice = input2.nextInt();
		
		switch(navChoice) {
		case 1:
			String personnelFileName = "NFLPersonnel.csv";
			personelManagement(personnelFileName, teamArray, employeeArray);
			break;
			
		default:
			System.out.println("Alternative input, exiting ...");
			return;
					
		}
	}
	
	/** 
	 * Method: Edit the contract for the specific employee
	 * @param employeeName of the person you want to edit
	 * @return changes the file with the updated employee contract
	 * 
	 */
		
	public static void targetEditContract(String givenEmployeeName, String fileName, ArrayList<NFLTeam> teamArray, ArrayList<NFLEmployee> employeeArray) {	
			try {
				int fileLength = employeeArray.size();
				ArrayList<NFLEmployee> updatedList = new ArrayList<>();
				
				for(int i=0;i<fileLength;i++) {
					if(employeeArray.get(i).getName().equals(givenEmployeeName) && employeeArray.get(i).getEndYear() > 2024) {
						System.out.println("Editing Contract Information for " + employeeArray.get(i).getName());
						Scanner editEmp = new Scanner(System.in);
						System.out.println("Employee's new name?");
						String playerName = editEmp.nextLine();
						System.out.println("Employee's new Team?");
						String teamName = editEmp.nextLine();
						System.out.println("Employee's new Job Category?");
						String category = editEmp.nextLine();
						System.out.println("Employee's new Title?");
						String title = editEmp.nextLine();
						System.out.println("Employee's new Salary?");
						int salary = editEmp.nextInt();
						System.out.println("Employee's new End Year?");
						int expirationYear = editEmp.nextInt();
						int teamLength = teamArray.size();
						for(int t=0;t<teamLength;t++) {
							if(teamArray.get(t).getTeamName().contains(teamName)) {
								NFLEmployee employee = new NFLEmployee(playerName, teamArray.get(t), category, title, salary, expirationYear);
								updatedList.add(employee);
							}
						}
					}
					else if(employeeArray.get(i).getName().equals(givenEmployeeName) && employeeArray.get(i).getEndYear() <= 2024) {
						System.out.println("Target Employee "+employeeArray.get(i).getName()+"'s contract expires in 2024 or earlier, cannot edit.");
						updatedList.add(employeeArray.get(i));
					}
					
					else {
						// just pass all other employees to updated list
						updatedList.add(employeeArray.get(i));
					}
				}

				if(updatedList.size()==0) {
					System.out.println("It looks like we have no record for that player. Please create a new contract and add it to the database.");
					//System.out.println(updatedList.size());
					System.out.println("Writing updated employee file...");
					File fileNameNew = new File("UPDATED_"+fileName);
					FileOutputStream fileStream = new FileOutputStream(fileNameNew);
					PrintWriter printWriter = new PrintWriter(fileStream);
					//String inStr;
					printWriter.println("Name,Team,Job Category,Title,Salary,Contract Expires");
					for (int i=0;i<fileLength;i++) {
						printWriter.println(employeeArray.get(i).getName()+","+employeeArray.get(i).getTeam().getTeamName()+","+employeeArray.get(i).getJobCategory()+","+employeeArray.get(i).getTitle()+","+employeeArray.get(i).getSalary()+","+employeeArray.get(i).getEndYear());
					}
					printWriter.close();
				}
				else {
					//System.out.println(updatedList.size());
					System.out.println("Writing updated employee file...");
					File fileNameNew = new File("UPDATED_"+fileName);
					FileOutputStream fileStream = new FileOutputStream(fileNameNew);
					PrintWriter printWriter = new PrintWriter(fileStream);
					//String inStr;
					printWriter.println("Name,Team,Job Category,Title,Salary,Contract Expires");
					for (int i=0;i<fileLength;i++) {
						printWriter.println(updatedList.get(i).getName()+","+updatedList.get(i).getTeam().getTeamName()+","+updatedList.get(i).getJobCategory()+","+updatedList.get(i).getTitle()+","+updatedList.get(i).getSalary()+","+updatedList.get(i).getEndYear());
					}
					printWriter.close();
				}

			} 
			catch(IOException ioe) {
				System.out.println("Something went wrong either generating the file or running the method. Check the following errors:");
				System.err.println("IOException: " + ioe.getMessage());
			}
		System.out.println("Choose what to do next:");
		System.out.println(" 1 - Return to Main Menu");
		System.out.println(" Any Other Key - Exit the Program");
		System.out.println(" Your choice:");
		
		// new scanner
		Scanner input2 = new Scanner(System.in);
		int navChoice = input2.nextInt();
		
		switch(navChoice) {
		case 1:
			String personnelFileName = "NFLPersonnel.csv";
			personelManagement(personnelFileName, teamArray, employeeArray);
			break;
			
		default:
			System.out.println("Alternative input, exiting ...");
			return;
					
		}
	}
	
	/** 
	 * Method: Remove contracts if they ended in 2024 or earlier
	 * @param employeeName of the person you want to edit
	 * @return changes the file with the updated employee contract
	 * 
	 */
		
	public static void remove2024(String fileName, ArrayList<NFLTeam> teamArray, ArrayList<NFLEmployee> employeeArray) {
			try {				
				int fileLength = employeeArray.size();					
				ArrayList<NFLEmployee> updatedList = new ArrayList<>();
				
				for(int i=0;i<fileLength;i++) {
					if(employeeArray.get(i).getEndYear()>= 2025) {
						updatedList.add(employeeArray.get(i));
					}
					else {
						// just pass all other employees
					}
				}
				
				System.out.println("Writing updated employee file...");
				File fileNameNew = new File(fileName);
				FileOutputStream fileStream = new FileOutputStream(fileNameNew);
				PrintWriter printWriter = new PrintWriter(fileStream);
				printWriter.println("Name,Team,Job Category,Title,Salary,Contract Expires");
				int updatedLength = updatedList.size();
				for (int i=0;i<updatedLength;i++) {
					printWriter.println(updatedList.get(i).getName()+","+updatedList.get(i).getTeam().getTeamName()+","+updatedList.get(i).getJobCategory()+","+updatedList.get(i).getTitle()+","+updatedList.get(i).getSalary()+","+updatedList.get(i).getEndYear());
				}
				printWriter.close();

			} 
			catch(IOException ioe) {
				System.out.println("Something went wrong either generating the file or running the method. Check the following errors:");
				System.err.println("IOException: " + ioe.getMessage());
			}
		System.out.println("Choose what to do next:");
		System.out.println(" 1 - Return to Main Menu");
		System.out.println(" Any Other Key - Exit the Program");
		System.out.println(" Your choice:");
		
		// new scanner
		Scanner input2 = new Scanner(System.in);
		int navChoice = input2.nextInt();
		
		switch(navChoice) {
		case 1:
			String personnelFileName = "NFLPersonnel.csv";
			personelManagement(personnelFileName, teamArray, employeeArray);
			break;
			
		default:
			System.out.println("Alternative input, exiting ...");
			return;
					
		}
	}
	
		/**
		 * Method: Salary Cap management of team contracts. Records input of max salary from user keyboard input and compares the team summary.
		 * @param string fileName, string teamName, int salaryCap
		 * @return revises the roster,and contract file based which person the user decides to remove from the lsit of team members.
		 */
		
	public static void printTeamSalary(String fileName, String targetTeamName, int targetYear, int targetCap, ArrayList<NFLTeam> teamArray, ArrayList<NFLEmployee> employeeArray) {			
		//Grab the employees for the given team with contracts that expire AFTER the given year and start adding them up
		int teamTotal = 0;
		int fileLength = employeeArray.size();
		for(int i=0;i<fileLength;i++) {
			if(employeeArray.get(i).getTeam().getTeamName().equals(targetTeamName) && employeeArray.get(i).getEndYear() > targetYear) {
				teamTotal = teamTotal+employeeArray.get(i).getSalary();
			}
			else {
				;
			}
		//for loop end - now we have the team total salary for the target year and target team
		}
		ArrayList<NFLEmployee> updatedList = new ArrayList<>();
		
		if (teamTotal>targetCap) {
			System.out.println("The current team total salary of $"+teamTotal+" is above the provided salary cap of $"+targetCap+" for "+targetYear);
			System.out.println("The team roster will need to be edited to be below the total cap");
			System.out.println("Please select a player from the below list to remove from the roster:");
			for(int i=0;i<fileLength;i++) {
				if(employeeArray.get(i).getTeam().getTeamName().equals(targetTeamName) && employeeArray.get(i).getEndYear() >= targetYear) {
					System.out.println("Name: "+employeeArray.get(i).getName()+" and their Salary: $"+employeeArray.get(i).getSalary());
				}
				else {
					//do nothing
					;
				}
			
			}
			System.out.println("Player to remove: ");
			Scanner input2 = new Scanner(System.in);
			String removePlayer = input2.nextLine();
		
			
			//for now, removes any players from earlier than the target year too
			for(int i=0;i<fileLength;i++) {
				if(employeeArray.get(i).getName().equals(removePlayer)) {
					//do nothing for target employee
					;
				}
				else {
					updatedList.add(employeeArray.get(i));
				}
			
			}
		try {
			//create the new file
			System.out.println("Writing updated employee file...");
			File fileNameNew = new File(fileName);
			FileOutputStream fileStream = new FileOutputStream(fileNameNew);
			PrintWriter printWriter = new PrintWriter(fileStream);
			printWriter.println("Name,Team,Job Category,Title,Salary,Contract Expires");
			int updatedLength = updatedList.size();
			for (int i=0;i<updatedLength;i++) {
				printWriter.println(updatedList.get(i).getName()+","+updatedList.get(i).getTeam().getTeamName()+","+updatedList.get(i).getJobCategory()+","+updatedList.get(i).getTitle()+","+updatedList.get(i).getSalary()+","+updatedList.get(i).getEndYear());
			}
			printWriter.close();
			
			//end of contract salary cap summary conditional
		}
		catch(IOException ioe) {
			System.out.println("Something went wrong either generating the file or running the method. Check the following errors:");
			System.err.println("IOException: " + ioe.getMessage());
		}
		}
		else if (teamTotal<=targetCap){
			System.out.println("The total Team Salary for contracts extending beyond "+targetYear+" is $"+teamTotal);
			int salaryDifference = targetCap-teamTotal;
			System.out.println("The difference between the total and the salary cap is: $"+ salaryDifference);
		}
		else {
			System.out.println("There are no employee records for that team. Please make sure your target team name lines up with what is in the contract.");
		}
			
		System.out.println("Choose what to do next:");
		System.out.println(" 1 - Return to Main Menu");
		System.out.println(" Any Other Key - Exit the Program");
		System.out.println(" Your choice:");
		
		// new scanner
		Scanner input2 = new Scanner(System.in);
		int navChoice = input2.nextInt();
		
		switch(navChoice) {
		case 1:
			String personnelFileName = "NFLPersonnel.csv";
			personelManagement(personnelFileName, teamArray, employeeArray);
			break;
			
		default:
			System.out.println("Alternative input, exiting ...");
			return;
					
		}
	}
	
	
//--// METHODS FOR GENERATING TAX FILE _______--------------------------------------___________________________
	/**
	 * Method: Create files for team tax summaries
	 * @param string fileName
	 * @return none - generates txt files containing team player names and their annual tax
	 */
	
	public static void createTaxFile(String fileName, ArrayList<NFLTeam> teamArray, ArrayList<NFLEmployee> employeeArray) {
		// borrowing our array creator from before

		try {
			int fileLength = employeeArray.size();
			for(int i=0;i<fileLength;i++) {
				String theTeamName = employeeArray.get(i).getTeam().getTeamName();
				String newFileName ="C:\\temp\\" +  theTeamName+"Tax.txt";
				FileWriter fw = new FileWriter(newFileName,true);
				if(employeeArray.get(i).getEndYear()>2026) {
					//Test it out before writing files like a madman
					//System.out.println(newFileName);
					try {
						double playerSalary = employeeArray.get(i).getSalary();
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
						String fileTaxContents = String.format("%24s %20.2f \n", employeeArray.get(i).getName(), fedTax);
	
						fw.write(fileTaxContents);
						fw.close();
					}
					catch(IOException ioe) {
						System.out.println("Something went wrong either generating the file or running the method. Check the following errors:");
						System.err.println("IOException: " + ioe.getMessage());
					}
				}
				
				else {
					//dont want to do anything for players whos contract ends before 2026
					;
				}
				
			}
			System.out.println("Successfully generated team tax file");
		} 
		catch(IOException ioe) {
			System.out.println("Something went wrong either generating the file or running the method. Check the following errors:");
			System.err.println("IOException: " + ioe.getMessage());
		}
		System.out.println("Choose what to do next:");
		System.out.println(" 1 - Return to Main Menu");
		System.out.println(" Any Other Key - Exit the Program");
		System.out.println(" Your choice:");
		
		// new scanner
		Scanner input2 = new Scanner(System.in);
		int navChoice = input2.nextInt();
		
		switch(navChoice) {
		case 1:
			String personnelFileName = "NFLPersonnel.csv";
			personelManagement(personnelFileName, teamArray, employeeArray);
			break;
			
		default:
			System.out.println("Alternative input, exiting ...");
			return;
					
		}
	}

//--// GLOBAL METHODS ___________________------------------------------__________________________
	/**
	 * Method: ingests the file and returns an object arraylist
	 * @params input filename
	 * @return arrayList of employee objects
	 */
		
	public static ArrayList<NFLEmployee> readEmployeeToArray(String fileName, ArrayList<NFLTeam> teamArray) {
	
		ArrayList<NFLEmployee> fileArray = new ArrayList<>();
		
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
				int numTeams = teamArray.size();
				for (int i=0;i<numTeams;i++) {
					if(teamArray.get(i).getTeamName().equals(teamName)) {
						NFLTeam playerTeam = teamArray.get(i);
						NFLEmployee employeeForList = new NFLEmployee(playerName, playerTeam, category, title, salary, expirationYear);
						fileArray.add(employeeForList);
					}
					else {
						//pass players who dont equal that team, check later
						;
					}
				}
				 
				inputStr = bufferReader.readLine();
			}
			
			return fileArray;
			
		}
		catch (IOException ioe){
			System.out.println("something went wrong with reading the file");
			return fileArray;
		}
	}
	
	
	/**
	 * Method: teamReadIn
	 * Function: reads in a team roster file
	 * @params String fileName
	 * @returns NFLTeam object arraylist 
	 */
	
	
	public static ArrayList<NFLTeam> teamReadIn(String fileName) {
		
		ArrayList<NFLTeam> teamFileArray = new ArrayList<>();
		
		try {
			File inFile = new File(fileName);
	
			FileReader  fileReader = new FileReader(inFile); 
			BufferedReader bufferReader = new BufferedReader(fileReader);
	
			String inputStr = bufferReader.readLine();
			inputStr = bufferReader.readLine();
	
			while(inputStr != null ) {
				//Create an object for each record read in
				String lineStrings [] = inputStr.split(",");
				//System.out.println(lineStrings[0]+" "+lineStrings[1]+" "+lineStrings[2]+" "+lineStrings[3]+" "+lineStrings[4]+" "+lineStrings[5]+" "+lineStrings[6]+" "+lineStrings[7]+" ");
				String teamName = lineStrings[0];
				String teamLocation = lineStrings[1];
				String teamStadiumName = lineStrings[2];
				int teamStadiumCapacity = Integer.parseInt(lineStrings[3]);
				String teamConference = lineStrings[4];
				String teamDivision = lineStrings[5];
				int teamStartingYear = Integer.parseInt(lineStrings[6]);
				String teamCoach = lineStrings[7];
				
				NFLTeam teamForList = new NFLTeam(teamName, teamLocation, teamStadiumName, teamStadiumCapacity, teamConference, teamDivision, teamStartingYear, teamCoach);
				//System.out.println(teamForList.getTeamName());
				teamFileArray.add(teamForList); 
				inputStr = bufferReader.readLine();
			}
			
			return teamFileArray;
			
		}
		catch (IOException ioe){
			System.out.println("something went wrong with reading the file");
			return teamFileArray;
		}
		
	}
	/**
	 * Method: transferProcessing
	 * Function: finds an employee and updates their team information
	 * @params String employeeName, String newTeamName, ArrayList employeeArrayList, ArrayList teamArrayList
	 * @returns ArrayList updatedArray
	 */
	
	public static ArrayList<NFLEmployee> transferProcessing(String fileName, String givenEmployeeName, ArrayList<NFLEmployee> employeeArray, ArrayList<NFLTeam> teamArray){
		ArrayList<NFLEmployee> updatedList = new ArrayList<>();
		try {
			int fileLength = employeeArray.size();
			
			
			for(int i=0;i<fileLength;i++) {
				if(employeeArray.get(i).getName().equals(givenEmployeeName)) {
					System.out.println("Editing Contract Information for " + employeeArray.get(i).getName());
					Scanner editEmp = new Scanner(System.in);
					System.out.println("Employee's new name?");
					String playerName = editEmp.nextLine();
					System.out.println("Employee's new Team?");
					String teamName = editEmp.nextLine();
					System.out.println("Employee's new Job Category?");
					String category = editEmp.nextLine();
					System.out.println("Employee's new Title?");
					String title = editEmp.nextLine();
					System.out.println("Employee's new Salary?");
					int salary = editEmp.nextInt();
					System.out.println("Employee's new End Year?");
					int expirationYear = editEmp.nextInt();
					int teamLength = teamArray.size();
					for(int t=0;t<teamLength;t++) {
						if(teamArray.get(t).getTeamName().contains(teamName)) {
							NFLEmployee employee = new NFLEmployee(playerName, teamArray.get(t), category, title, salary, expirationYear);
							updatedList.add(employee);
						}
					}
				}
				else {
					// just pass all other employees to updated list
					updatedList.add(employeeArray.get(i));
				}
			}
			//System.out.println("length of list ="+updatedList.size());
			if(updatedList.size()==0) {
				System.out.println("It looks like we have no record for that player. Please create a new contract and add it to the database.");
				return employeeArray;
			}
			else {
				//System.out.println(updatedList.size());
				System.out.println("Writing updated employee file...");
				File fileNameNew = new File("UPDATED_"+fileName);
				FileOutputStream fileStream = new FileOutputStream(fileNameNew);
				PrintWriter printWriter = new PrintWriter(fileStream);
				//String inStr;
				printWriter.println("Name,Team,Job Category,Title,Salary,Contract Expires");
				for (int i=0;i<fileLength;i++) {
					printWriter.println(updatedList.get(i).getName()+","+updatedList.get(i).getTeam().getTeamName()+","+updatedList.get(i).getJobCategory()+","+updatedList.get(i).getTitle()+","+updatedList.get(i).getSalary()+","+updatedList.get(i).getEndYear());
				}
				printWriter.close();
				return updatedList;
			}


		} 
		catch(IOException ioe) {
			System.out.println("Something went wrong either generating the file or running the method. Check the following errors:");
			System.err.println("IOException: " + ioe.getMessage());
			return updatedList;
		}
		
	}

// this one is end of main method
}
