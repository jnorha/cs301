/**
 * Course: CS300 - Summer 2025
 * Program: NFLManagement.java for 6/11 class
 * 
 * Author: Nick Izu
 * Date:	6/4/2025
 * Version:	1.2
 */

import java.util.*;
import java.io.*;

public class NFLManagement {
	public static void main(String args[]) {
		System.out.println("Test for inheritance:");
		Scanner scanner = new Scanner(System.in);
		//Create a menu for personnel management that can manage all employees whose information
		// is stored in a file
		ArrayList<NFLTeam> teams = teamsReadIn("NFLTeams.csv", scanner);
		ArrayList<NFLEmployee> employees = loadEmployees("NFLPersonnel2.csv", teams);
		assignCoaches(teams, employees);
		mainMenu(employees, teams, scanner);
		scanner.close();
	}
	
	
	/**
	 * main menu for user to select what to manage from the file, including contracts, paystubs, and taxes for all employees
	 * 			in a personnel file
	 * @param fileName: employee personnel file name
	 * @param scanner: Scanner to read user input
	 * @return none
	 */
	public static void mainMenu(ArrayList<NFLEmployee> employees, ArrayList<NFLTeam> teams, Scanner scanner) {
		while (true) {
			//Print out the menu, and ask the user to make the choice
			System.out.println(" ========== Personnel Management Menu ==============");
			System.out.println(" 1 - Manage contracts");
			System.out.println(" 2 - Manage paystubs");
			System.out.println(" 3 - Manage tax");
			System.out.println(" 4 - Transfer employee to new team");
			System.out.println(" 5 - Quit");
			System.out.println(" Enter your choice:");
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			
			// open menu based on user input
			switch(choice) {
				case 1:
					manageContract(employees, teams, scanner);
					break;
					
				case 2:
					managePayStub(employees, teams, scanner);
					break;
					
				case 3:
					manageTax(employees, teams, scanner);
					break;
					
				case 4:
					transferProcessing(employees, teams, scanner);
					break;
					
				default:
					System.out.println("Invalid choice, exiting ...");
					return;
							
			}
		}
	}
	
	/**
	 * Reads employee data from a comma delimited file, creates NFLEmployee objects for each line, and stores them in an array list
	 * @param fileName: employee personnel file name
	 * @param scanner: Scanner to read user input
	 * @return array list of employee objects created from the file
	 */
	
	public static ArrayList<NFLEmployee> loadEmployees(String fileName, ArrayList<NFLTeam> teams) {
		// Creates ArrayList object
		ArrayList<NFLEmployee> employees = new ArrayList<>();
		
		// open the file using BufferedReader
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = br.readLine();
			// loop until we hit an empty (null) line
			while ((line = br.readLine()) != null) {
				// split current line into parts at each comma
				String[] parts = line.split(",");

				// quit reading each line once we get to 13 parts separated by commas
				if (parts.length == 13) {//changed from 6 in the older NFLPersonnel file
					String name = parts[0];
					String teamName = parts[1];
					String category = parts[2];
					String title = parts[3];
					int salary = Integer.parseInt(parts[4]);
					int endYear = Integer.parseInt(parts[5]);
					//Revised 6/11 for reading NFLPersonnel2.csv file
					String record = parts[6];
					String style = parts[7];
					String experiences = parts[8];//need to convert into integer for a coach object
					String sales = parts[9];//Integer.parseInt(parts[9]);
					String commissionRate = parts[10];//Double.parseDouble(parts[10]);
					String playerStatus = parts[11];
					String playerStats = parts[12];
					
					// match teamName of employees to an NFLTeam object
					NFLTeam matchedTeam = null;
					for (NFLTeam t : teams) {
						if (t.getName().contains(teamName)) {
							matchedTeam = t;
							break;
						}
					}
					
					// if a matched team is found, create a new employee and add them to the ArrayList, otherwise thrown an error
					if (matchedTeam != null) {
						//Create a right type of object based on the job categoy
						if(category.equalsIgnoreCase("Player")) {
							//Create a player object
							Player newPlayer = new Player(name, matchedTeam, category, title, 
									salary, endYear,playerStatus, playerStats);
							employees.add(newPlayer);
						}
						else { //will be extended later to consider other subclasses
							employees.add(new NFLEmployee(name, matchedTeam, category, title, salary, endYear));
						}
					} else {
						System.out.println("Error: No team found for " + name);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return employees;
	}

}