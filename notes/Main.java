/**
 * Course: CS300 - Summer 2025
 * Program: Main.java for Main computer science project
 * 		Runs the Marathon Game methods and produces output
 * Author: Josh Daniels
 * Date:	7/23/25
 * Version:	1.0
 */

import java.util.*;	
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;




public class Main {
	public static void main(String args[]) {
		
		
		
		
		
		//Testing Methods
		String activityFileName = "activityHistory.csv";
		ArrayList<Activity> activities = new ArrayList<Activity>();
		DateTimeFormatter ISO = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		//test data
		/*
		LocalDate testDate = LocalDate.parse("2025/07/27", ISO);
		Hero Learoy = new Hero("Learoy");
		Activity test = new Run(Learoy,testDate, "Run", 58, 120, 6, 1200, 1000);
		*/
		
		//activities.add(test);
				
		mainMenu(activities);
		
		printActivity(activities.get(0));
		
	}
	
	
//--//METHODS
	
	
	/**
	 * Method: mainMenu();
	 * Function: initial login option to take users to their next choice
	 * 
	 */
	
	public static void mainMenu(ArrayList<Activity> activities) {
			
	
			//Print out the menu, and ask the user to make the choice
			System.out.println("\n ========== Ascend! Main Menu ==============");
			System.out.println(" 1 - Upload New Workout");
			System.out.println(" 2 - Write current activities to CSV");
			System.out.println(" 3 - exit");
			System.out.println(" Your choice:");
			Scanner input = new Scanner(System.in);
			int choice = input.nextInt();
			//dummy next line
			input.nextLine();
			
			//starting with case 1 of uploading a new activity
			switch(choice) {
				case 1:
					uploadActivity(input, activities);
					mainMenu(activities);
					
					
					break;
					
				case 2:
					String activityFileName = "activityHistory.csv";
					System.out.println("Writing activities file...");
					writeActivityCSV(activityFileName, activities);
					mainMenu(activities);
                
                    return;
                    
				case 3:
					System.out.println("Exiting");
                    input.close();
                    return;

					
				default:
					System.out.println("Invalid input, try again ...");
							
			}
			
		}
	
	
	
	/**
	 * Method: writeActivityCSV()
	 * Function: Overwrites all activity data to the fileName based on a list of activities
	 * @param fileName,List<Activity> activities
	 * 
	 */
	
	
	public static void writeActivityCSV(String fileName, ArrayList<Activity> activities) {
		//throw exception if can't find file for some reason (should just write to local "activities" dir for now)5
		// Superset header matching your 11 columns
        String header = "hero,date,type,duration,heartRate,distance,elevHigh,elevLow,elevChange,reps,weightInLbs";
        Path filePath = Path.of("C:\\temp\\"+fileName);
                      

        // Use try-with-resources to auto-close & overwrite the file each run
        
        // Found examples of BufferedWriter online which seem to work better with paths and read/write
        try (BufferedWriter fw = Files.newBufferedWriter(filePath,StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

            // 1) Write header
            fw.write(header);
            fw.newLine();

            // 2) Write each activity
            for (Activity a : activities) {
                fw.write(activityToRow(a));
                fw.newLine();
            }

            System.out.println("Wrote " + activities.size()
                             + " activities to " + filePath.toString());
        }
        catch (IOException ioe) {
            System.err.println("Failed writing CSV: " + ioe.getMessage());
        }
		
        
        
        //using older FileWriter
		
		/*
		try {
			String newFileName ="C:\\temp\\" +  fileName;
			FileWriter fw = new FileWriter(newFileName,false);
			
			//set up header column with all activity attributes
			String actHeader = "hero,time,type,duration,heartRate,distance,elevHigh,elevLow,elevChange,reps,weightInLbs";
			
			fw.write(actHeader+"\n");

			
			for(Activity a : activities) {
				fw.write(activityToRow(a)+"\n");
			}
		}
		catch(IOException ioe) {
			System.out.println("Something went wrong either generating the file or running the method. Check the following errors:");
			System.err.println("IOException: " + ioe.getMessage());
		}
		*/
	}
	
	
	/**
	 * Method: appendActivity()
	 * Function: appends a single activity to the activity file
	 * @param fileName,List<Activity> activities
	 * 
	 */

	public static void appendActivityFile(String fileName, Activity activity) { 
	//throw exception if can't find file for some reason (should just write to local "temp" dir for now)
	
		Path filePath = Path.of("C:\\temp\\"+fileName);
		//also updating this to use BufferedWriter:
		try (BufferedWriter fw = Files.newBufferedWriter(filePath,StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            
            
            fw.write(activityToRow(activity));
            fw.newLine();
           

            System.out.println("Wrote new activity to " + filePath.toString());
        }
        catch (IOException ioe) {
            System.err.println("Failed writing CSV: " + ioe.getMessage());
        }
		
	
		//old way with fileWriter
		/*
		try {
			String newFileName ="C:\\temp\\" +  fileName;
			FileWriter fw = new FileWriter(newFileName,true);
			
			fw.write(activityToRow(activity)+"\n");
			
		}
		catch(IOException ioe) {
			System.out.println("Something went wrong either generating the file or running the method. Check the following errors:");
			System.err.println("IOException: " + ioe.getMessage());
		}
		*/
	
	
	}
	
	
	/**
	 * Method: activityToRow()
	 * Function: converts an activity to a csv row
	 * @param activity
	 * 
	 */
	private static String activityToRow(Activity act) {
		// create a list that will hold all attributes
	    String[] vals = new String[11];
	    DateTimeFormatter ISO = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	    // common fields
	    vals[0] = act.getCharacter().getName();
	    vals[1] = act.getDate().format(ISO);
	    vals[2] = act.getType();
	    vals[3] = String.valueOf(act.getDuration());
	    vals[4] = String.valueOf(act.getHR());
	
	    // subclass‐specific
	    if (act instanceof Run) {
	        Run r = (Run) act;
	        vals[5] = String.valueOf(r.getDistance());
	        vals[6] = String.valueOf(r.getElevHigh());
	        vals[7] = String.valueOf(r.getElevLow());
	        vals[8] = String.valueOf(r.getElevChange());
	    }
	    else if (act instanceof Bike) {
	        Bike b = (Bike) act;
	        // ensure derived fields are up to date

	        vals[5]  = String.valueOf(b.getDistance());
	        vals[6]  = String.valueOf(b.getElevHigh());
	        vals[7]  = String.valueOf(b.getElevLow());
	        vals[8]  = String.valueOf(b.getElevChange());
	    }
	    else if (act instanceof Swim) {
	        Swim s = (Swim) act;
	        vals[5]  = String.valueOf(s.getDistance());
	    }
	    else if (act instanceof Weights) {
	        Weights w = (Weights) act;
	        vals[9] = String.valueOf(w.getReps());
	        vals[10] = String.valueOf(w.getWeightInLbs());
	    }
	
	    // join, replacing any nulls with empty strings
	    for (int i = 0; i < vals.length; i++) {
	        if (vals[i] == null) vals[i] = "";
	    }
	    return String.join(",", vals);
	}
	
	/**
	 * Method: uploadActivity()
	 * Function: add a new workout to the running memory array list of workouts
	 * @param ArrayList<Activity> activities, scanner
	 * 
	 */
	
	private static ArrayList<Activity> uploadActivity(Scanner input, ArrayList<Activity> activities) {
		//set up try/catch to handle IO exceptions
		
        try {
        	DateTimeFormatter ISO = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        	
            // Gather the base Activity fields
            System.out.println("Enter hero name: ");
            String heroName = input.nextLine();
            Hero hero = new Hero(heroName);

            System.out.println("Enter Date (YYYY/MM/DD): ");
            String dateStr = input.nextLine().trim();
            LocalDate date = LocalDate.parse(dateStr, ISO);

            System.out.println("Enter activity type (Run, Bike, Swim, Weights): ");
            String type = input.nextLine().trim();

            System.out.println("Enter duration (minutes): ");
            int duration = Integer.parseInt(input.nextLine().trim());

            System.out.println("Enter average heart rate: ");
            int hr = Integer.parseInt(input.nextLine().trim());

            Activity activity = null;

            // follow up prompts based on type of activity
            switch (type.toLowerCase()) {
                case "run":
                    System.out.println("Enter distance (miles): ");
                    int runDist = Integer.parseInt(input.nextLine().trim());
                    System.out.println("Enter elevation high (ft): ");
                    int runHigh = Integer.parseInt(input.nextLine().trim());
                    System.out.println("Enter elevation low (ft): ");
                    int runLow = Integer.parseInt(input.nextLine().trim());
                    activity = new Run(hero, date, "Run", duration, hr, runDist, runHigh, runLow);
                    break;

                case "bike":
                    System.out.println("Enter distance (miles): ");
                    int bikeDist = Integer.parseInt(input.nextLine().trim());
                    System.out.println("Enter elevation high (ft): ");
                    int bikeHigh = Integer.parseInt(input.nextLine().trim());
                    System.out.println("Enter elevation low (ft): ");
                    int bikeLow = Integer.parseInt(input.nextLine().trim());         
                    Bike bike = new Bike(hero, date, "Bike", duration, hr, bikeDist, bikeHigh, bikeLow);
                    activity = bike;
                    break;

                case "swim":
                    System.out.println("Enter distance (yards): ");
                    int swimDist = Integer.parseInt(input.nextLine().trim());
                    Swim swim = new Swim(hero, date, "Swim", duration, hr, swimDist);
                    activity = swim;
                    break;

                case "weights":
                    System.out.println("Enter reps: ");
                    int reps = Integer.parseInt(input.nextLine().trim());
                    System.out.println("Enter weight (lbs): ");
                    int weight = Integer.parseInt(input.nextLine().trim());
                    Weights weights = new Weights(hero, date, "Weights", duration, hr, reps, weight);
                    weights.setTotalVol(weight, reps);
                    activity = weights;
                    break;

                default:
                    System.out.println("Unknown activity type. Hopefully will add in the future!");
                    return activities;
            }

            // write the new activity to the arraylist in memory and append to the file
            activities.add(activity);
            appendActivityFile("activityHistory.csv", activity);
            System.out.println(type + " workout saved successfully and added to file!");
            return activities;

        } catch (Exception e) {
            System.out.println("Error recording workout: " + e.getMessage());
            return activities;
        }
        
        
    }
	
	/**
	 * Method printActivity()
	 * @param <Activity> activity
	 */
	
	
	public static void printActivity(Activity act) {
        // Common fields
        System.out.println("Hero: " + act.getCharacter().getName());
        System.out.println("Date: " + act.getDate());
        System.out.println("Type: " + act.getType());
        System.out.println("Duration: " + act.getDuration() + " min");
        System.out.println("Heart Rate: " + act.getHR() + " bpm");

        // Subclass‐specific
        if (act instanceof Run) {
            Run r = (Run) act;
            System.out.println("Distance: " + r.getDistance() + " mi");
            System.out.println("Elev High: " + r.getElevHigh() + " ft");
            System.out.println("Elev Low: " + r.getElevLow() + " ft");
            System.out.println("Elev Change: " + r.getElevChange() + " ft");
        }
        else if (act instanceof Bike) {
            Bike b = (Bike) act;
            System.out.println("Distance: " + b.getDistance() + " mi");
            System.out.println("Elev High: " + b.getElevHigh() + " ft");
            System.out.println("Elev Low: " + b.getElevLow() + " ft");
            System.out.println("Elev Change: " + b.getElevChange() + " ft");

        }
        else if (act instanceof Swim) {
            Swim s = (Swim) act;
            System.out.println("Distance: " + s.getDistance() + " yd");

        }
        else if (act instanceof Weights) {
            Weights w = (Weights) act;
            System.out.println("Reps: " + w.getReps());
            System.out.println("Weight: " + w.getWeightInLbs() + " lbs");
            System.out.println("Total Vol: " + w.getTotalVol());
        }
        System.out.println("-----------------------------------");
    }

}
	
