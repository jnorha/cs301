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
import java.time.format.*;




public class Main {
	public static void main(String args[]) {
		
		//setting up memory data sources
		ArrayList<Activity> activities = new ArrayList<Activity>();
		ArrayList<Hero> heroes = new ArrayList<Hero>();
		
		//initial scanner
		Scanner scanAlpha = new Scanner(System.in);
		
		//try setting up from file readers
		ArrayList<Hero> heroes2 = readHeroFile("heroes.csv",scanAlpha);
		ArrayList<Activity> activities2 = readActivityFile("activityHistory.csv", heroes2);
		
		//heroes2.get(0).printHero();
		
		
		//Testing Methods
		String activityFileName = "activityHistory.csv";
		String heroFileName = "heroes.csv";
		DateTimeFormatter ISO = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		//test data
		/*
		LocalDate testDate = LocalDate.parse("2025/07/27", ISO);
		Hero Learoy = new Hero("Learoy");
		Activity test = new Run(Learoy,testDate, "Run", 58, 120, 6, 1200, 1000);
		*/
		
		//activities.add(test);
		
		//printActivity(activities2.get(0));
		
		//heroes2.get(0).printHero();
		Scanner input = new Scanner(System.in);	
		
		//rock and roll
		mainMenu(activities2, heroes2, input);
		
		
		
	}
	
	
//--//METHODS
	
//--// Menu Methods //--//	
	/**
	 * Method: mainMenu();
	 * Function: initial login option to take users to their next choice
	 * 
	 */
	
	public static void mainMenu(ArrayList<Activity> activities, ArrayList<Hero> heroes, Scanner input) {
			
	
			//Print out the menu, and ask the user to make the choice
			System.out.println("\n ========== Ascend! Main Menu ==============");
			System.out.println(" 1 - Create New Hero");
			System.out.println(" 2 - Select Hero");
			/*
			 * Going to make these automated parts of save and exit program
			//System.out.println(" 3 - Write current activities to CSV");
			//System.out.println(" 4 - Write hero changes to CSV");
			*/
			
			System.out.println(" 3 - Save & Exit"); //saves current hero list to the file. This should always be most recent version since changes will pass the new array to the mainMenu method
			System.out.println(" Your choice:");
			
			int choice = input.nextInt();
			//dummy next line
			input.nextLine();
			String heroFileName = "heroes.csv";
			//starting with case 1 of uploading a new activity
			while(true) {
				
				switch(choice) {
					case 1:
						//MAKE SURE TO CHECK FOR DUPLICATE HERO NAMES
						
						//part of the createNewHero method checks the current arraylist in memory for duplicates
						Hero newHero = createNewHero(input, heroes);
						boolean duplicateCheck = false;
						for(Hero h : heroes) {
							if(newHero.getName().equals(h.getName())) {
								//there was a match! change boolean value
								duplicateCheck = true;
							}
						}
						//if there is a duplicate, duplicateCheck becomes true and the hero will NOT be added since it's already there
						if(duplicateCheck==false) {
							heroes.add(newHero);
						}
						
						
						//heroes.add(newHero);
						/*
						 * dont need this anymore since we will clean this up at the end of session with hero
						//add it to the heroes file as well before we forget
						//appendHero(heroFileName,newHero);
						 */
						//take new hero as hero and go to action menu
						actionMenu(newHero, heroes, activities , input);						
						break;
						
					case 2:
						//find hero from list of current heroes
						System.out.println("Hero name?");
						String heroChoice = input.nextLine();
						Hero selectedHero = new Hero("Temp");
						for(Hero h : heroes) {
							if(h.getName().toLowerCase().equals(heroChoice.toLowerCase())) {
								selectedHero = h;
							}
						}
						
						if(selectedHero.getName().equals("Temp")) {
							System.out.println("Hero "+heroChoice + " not found - Please create new hero named "+heroChoice);
							mainMenu(activities,heroes, input);
							
						}
						//launch action menu
						actionMenu(selectedHero, heroes, activities, input);												
						break;
						
						//Save & Exit
					case 3:
						/* going to do this in each action step actually. 
						 * so each time a new activity is uploaded the activity file is just appended
						 * We will need to update the hero row in the hero file when exiting the action menu
						 */
						//fixHeroCSV(heroFileName, heroes);
						System.out.println("All changes saved. Thanks for playing!");
						//input.close(); - exiting system and terminating window instead.	                
	                    System.exit(0);
	                    
	
						
					default:
						System.out.println("Invalid input, try again ...");
						mainMenu(activities,heroes, input);
				}
							
			}
			
		}
	
	
	
	/**
	 * Method: actionMenu()
	 * Function: once a player either selects a hero from available heroes or creates a new hero they choose what to do with that hero
	 * @param hero, activityHistory
	 */
	
	public static void actionMenu(Hero hero, ArrayList<Hero> heroes, ArrayList<Activity> activityHistory, Scanner input) {
		//Print out the menu, and ask the user to make the choice
		System.out.println("\n ============ Action Menu (Selected Hero "+hero.getName()+") ===============");
		System.out.println(" 1 - Check Hero Stats!");
		System.out.println(" 2 - Upload New Workout");
		System.out.println(" 3 - Take on Floor Challenge!");
		System.out.println(" 4 - Save Hero & Exit to Main Menu");
		System.out.println(" Your choice:");
		
		int choice = input.nextInt();
		//dummy next line
		input.nextLine();
		
		//starting with case 1 of uploading a new activity
		while(true) {
			switch(choice) {
			case 1:
				//MAKE SURE TO CHECK FOR DUPLICATE HERO NAMES
				hero.printHero();
				actionMenu(hero,heroes,activityHistory,input);
				break;
				
			case 2:
				activityHistory = uploadActivity(input, activityHistory, hero);
				//first get only activities for this hero into a new arraylist of activities and pass the most recent one to the gain exp
				ArrayList<Activity> heroActivities = new ArrayList<Activity>();
				for (Activity a : activityHistory) {
					if(a.getCharacter().equals(hero)) {
						heroActivities.add(a);
					}
				}
				//printActivity(heroActivities.get(0));
				hero.gainExp(heroActivities, heroActivities.get(heroActivities.size()-1));
				actionMenu(hero,heroes,activityHistory,input);
				break;
			
			case 3:
				floorChallenge(hero, input);
				actionMenu(hero,heroes,activityHistory,input);
				break;
				
			case 4:
				/*
				 * need to save the hero info here as you exit the program. 
				 */
				System.out.println("Writing changes for " + hero.getName() + " to hero file...");
				String heroFileName = "heroes.csv";
				writeHeroCSV(heroFileName, heroes, hero);
				System.out.println("Returning to Main Menu");
                mainMenu(activityHistory, heroes, input);
                break;	
				
				
			default:
				System.out.println("Invalid input, try again ...");
				actionMenu(hero,heroes,activityHistory,input);
			
			}
		}
		
	}
	

	
//--// Gameplay Methods
	/**
	 * Method: floorChallenge()
	 * Function: player hero takes on a challenge and is tested against enemy, if they win they go up a floor, lose and they have a CHANCE to drop a floor
	 * @Param hero
	 */
	
	
	public static void floorChallenge(Hero hero, Scanner input) {
		//random number generator. we will use this for dice rolls and chance to drop floor
        Random rnd = new Random();

        // Generate opponent based on the floor they're on
        int oppLevel = (hero.getFloor() + rnd.nextInt(1,3) - 1); //this will set an opponent at the floor number + random int between 0-3
        int oppHealth = (oppLevel * 10) + rnd.nextInt(2,11);      // base 10×level plus 0–10 - ensures that their health won't start at 0
        int oppStrength = oppLevel * 2 + rnd.nextInt(5);
        int oppSpeed = (oppLevel * 2) + rnd.nextInt(10);// base 2×level plus 0–4 - opponent strength could be 0
        
        //create opponent as new hero for easier logic handling - we won't add this hero to the running memory array or file
        Hero opponent = new Hero("Floor Opponent");
        opponent.setLevel(oppLevel);
        opponent.setHealth(oppHealth);
        opponent.setStrength(oppStrength);
        opponent.setSpeed(oppSpeed);

        System.out.printf("A level %d enemy has appeared! (HP:%d STR:%d)%n",
                          oppLevel, oppHealth, oppStrength);

        // Combat loop - hero and enemy hero will do damage to eachother's health based on their (strength stat / 3) * rnd.nextInt(1,6) which simmulates rolling dice
        //check health in while loop
        int heroHealth = hero.getHealth();
        int enemyHealth = opponent.getHealth();
        
        while (heroHealth > 0 && enemyHealth > 0) {
        	//see who attacks first based on speed stat
        	if (hero.getSpeed() >= opponent.getSpeed()) {
        		// Hero attacks
                int heroDamage = (hero.getStrength() / 3) * rnd.nextInt(1,6);
                enemyHealth= enemyHealth - heroDamage;
                System.out.printf("%s hits for %d damage! Opponent HP now %d.%n", hero.getName(), heroDamage, enemyHealth);
                if (enemyHealth <= 0) {
                	break;
                }
                // Opponent attacks
                int oppDamage = (opponent.getStrength() / 3) * rnd.nextInt(1,6);
                heroHealth = heroHealth - oppDamage;
                System.out.printf("Opponent hits for %d damage! %s HP now %d.%n", oppDamage, hero.getName(), heroHealth);
                if(heroHealth <=0) {
                	break;
                }
        	}
        	else if (hero.getSpeed() < opponent.getSpeed()) {
        		// Opponent attacks
                int oppDamage = (opponent.getStrength() / 3) * rnd.nextInt(1,6);
                heroHealth = heroHealth - oppDamage;
                System.out.printf("Opponent hits for %d damage! %s HP now %d.%n", oppDamage, hero.getName(), heroHealth);
                if(heroHealth <=0) {
                	break;
                }
             // Hero attacks
                int heroDamage = (hero.getStrength() / 3) * rnd.nextInt(1,6);
                enemyHealth= enemyHealth - heroDamage;
                System.out.printf("%s hits for %d damage! Opponent HP now %d.%n", hero.getName(), heroDamage, enemyHealth);
                if (enemyHealth <= 0) {
                	break;
                }
        	}

            // prompt user to hit enter to repeat loop
        	System.out.print("\n Press Enter to attack again! ");
        	input.nextLine();
        	System.out.println();
        }

        if (heroHealth > 0) {
            System.out.println("\n Victory! You’ve cleared the floor.");
            hero.advanceFloor();

        } else {

        	int chanceDrop = rnd.nextInt(1,4);
        	if (chanceDrop == 1) {
        		hero.dropFloor();
        		System.out.println("\n Defeat - We'll get them next time. You retreated down a floor to recover...");
        	}
        	else {
        		System.out.println("\n Defeat - We'll get them next time. You retreat into the darkness to return soon ...");
        	}
        }
    }
	
	
	
//--// Methods for writing to existing files/overwriting files
	
	/**
	 * Method: writeHeroCSV()
	 * Function: writes the updated hero information to the csv location. OVERWRITES THE HERO FILE
	 * @param filename, ArrayList<Hero> heroes, Hero hero
	 */
	public static void writeHeroCSV(String fileName, ArrayList<Hero> heroes, Hero hero) {
		//first find the selected hero in the ArrayList and make sure it's updated to the correct value
		/*
		for(Hero h : heroes) {
			if(h.getName().equals(hero.getName())) {
				h = hero;
			}
		}
		*/
		
		for (int i=0;i<heroes.size();i++) {
			if(heroes.get(i).getName().equals(hero.getName())) {
				//replace duplicate with updated values
				heroes.set(i, hero);
			}
		}
		
		//write the 9 columns ALL heroes will have
		String header = "hero,level,HP,strength,speed,runSkill,bikeSkill,swimSkill,weightSkill,exp,floor";
		//path to file
		Path filePath = Path.of("C:\\temp\\"+fileName);
		
		//use buffered writer to create new hero file
		try (BufferedWriter fw = Files.newBufferedWriter(filePath,StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

            // 1) Write header
            fw.write(header);
            fw.newLine();

            // 2) Write each activity
            for (Hero h : heroes) {
    			String megaHeroString = (h.getName()+","+h.getLevel()+","+h.getHealth()+","+h.getStrength()+","+h.getSpeed()+","+h.getRunSkill()+","+h.getBikeSkill()+","+h.getSwimSkill()+","+h.getWeightSkill()+","+h.getExp()+","+h.getFloor());
                fw.write(megaHeroString);
                fw.newLine();
            }
        }
        catch (IOException ioe) {
            System.err.println("Failed writing CSV: " + ioe.getMessage());
        }
	
		
	}
	
	/**
	 * Method: fixHeroCSV()
	 * @param fileName, heroes
	 * function: after reading a hero file, it will write the edited hero list (deduplicated) to the hero file that matches whats in memory
	 */
	public static void fixHeroCSV(String fileName, ArrayList<Hero> heroes) {
		
		//write the 9 columns ALL heroes will have
		String header = "hero,level,HP,strength,speed,runSkill,bikeSkill,swimSkill,weightSkill,exp,floor";
		//path to file
		Path filePath = Path.of("C:\\temp\\"+fileName);
		
		//use buffered writer to create new hero file
		try (BufferedWriter fw = Files.newBufferedWriter(filePath,StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

            // 1) Write header
            fw.write(header);
            fw.newLine();

            // 2) Write each activity
            for (Hero h : heroes) {
    			String megaHeroString = (h.getName()+","+h.getLevel()+","+h.getHealth()+","+h.getStrength()+","+h.getSpeed()+","+h.getRunSkill()+","+h.getBikeSkill()+","+h.getSwimSkill()+","+h.getWeightSkill()+","+h.getExp()+","+h.getFloor());
                fw.write(megaHeroString);
                fw.newLine();
            }
        }
        catch (IOException ioe) {
            System.err.println("Failed writing CSV: " + ioe.getMessage());
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
	 * Method: appendHero()
	 * Function: appends a single hero to the heroes file
	 * @param fileName,Hero newHero
	 * 
	 */

	public static void appendHero(String fileName, Hero newHero) {
		//first do path
		Path path = Path.of("C:\\temp\\"+ fileName);
		try (BufferedWriter fw = Files.newBufferedWriter(path,StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            
			String megaHeroString = (newHero.getName()+","+newHero.getLevel()+","+newHero.getHealth()+","+newHero.getStrength()+","+newHero.getSpeed()+","+newHero.getRunSkill()+","+newHero.getBikeSkill()+","+newHero.getSwimSkill()+","+newHero.getWeightSkill()+","+newHero.getExp()+","+newHero.getFloor());

            fw.write(megaHeroString);
            fw.newLine();
           

            System.out.println("Wrote new hero "+ newHero.getName() + " to " + path.toString());
        }
        catch (IOException ioe) {
            System.err.println("Failed writing CSV: " + ioe.getMessage());
        }
	}
	
	
	
	
	/**
	 * Method: activityToRow()
	 * Function: converts an activity to a csv row
	 * @param activity
	 * 
	 */
	private static String activityToRow(Activity act) {
		// create a list that will hold all attributes, list is good because it holds everything in order even if you're not recording values due to different subclass workout
	    String[] vals = new String[11];
	    //set standard date time
	    DateTimeFormatter ISO = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	    // base class fields
	    vals[0] = act.getCharacter().getName();
	    vals[1] = act.getDate().format(ISO);
	    vals[2] = act.getType();
	    vals[3] = String.valueOf(act.getDuration());
	    vals[4] = String.valueOf(act.getHR());
	
	    // subclass‐specific
	    if (act instanceof Run) {
	        vals[5] = String.valueOf(((Run) act).getDistance());
	        vals[6] = String.valueOf(((Run) act).getElevHigh());
	        vals[7] = String.valueOf(((Run) act).getElevLow());
	        vals[8] = String.valueOf(((Run) act).getElevChange());
	    }
	    
	    else if (act instanceof Bike) {
	        vals[5] = String.valueOf(((Bike) act).getDistance());
	        vals[6] = String.valueOf(((Bike) act).getElevHigh());
	        vals[7] = String.valueOf(((Bike) act).getElevLow());
	        vals[8] = String.valueOf(((Bike) act).getElevChange());
	    }
	    
	    else if (act instanceof Swim) {
	        vals[5] = String.valueOf(((Swim) act).getDistance());
	    }
	    
	    else if (act instanceof Weights) {
	        vals[9] = String.valueOf(((Weights) act).getReps());
	        vals[10] = String.valueOf(((Weights) act).getWeightInLbs());
	    }
	
	    // create one mega string with all the values
	    for (int i = 0; i < vals.length; i++) {
	    	//write any null values
	        if (vals[i] == null) { 
	        	vals[i] = "";
	        }
	    }
	    
	    String megaString = String.join(",", vals);
	    return megaString;
	}
	
	
	
//--// Methods for generating new player content //--//	
	/**
	 * Method: uploadActivity()
	 * Function: add a new workout to the running memory array list of workouts
	 * @param ArrayList<Activity> activities, scanner
	 * 
	 */
	
	private static ArrayList<Activity> uploadActivity(Scanner input, ArrayList<Activity> activities, Hero hero) {
		//set up try/catch to handle IO exceptions
		
        try {
        	DateTimeFormatter ISO = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        	
            // Gather the base Activity fields
        	/*
        	 * set working hero as the hero
            System.out.println("Enter hero name: ");
            String heroName = input.nextLine();
            Hero hero = new Hero(heroName);
            */
        	
        	//NEED TO SET SOME While loops or something to check for valid input
        	LocalDate date = null;
        	while (date == null) {
        		System.out.println("Enter Date (YYYY/MM/DD): ");               
                String dateStr = input.nextLine().trim();
                try {
                	date = LocalDate.parse(dateStr, ISO);
                }
                catch (DateTimeParseException e) {
                	System.out.println("Invalid date format. Please follow the format Year/Month/Date (YYYY/MM/DD).");
                }                        		
        	}
        	//making this a method instead of doing it 10 times for integers...
            System.out.println("Enter activity type (Run, Bike, Swim, Weights): ");
            String type = input.nextLine().trim().toLowerCase();

            //System.out.println("Enter duration (minutes): ");
            String durationPrompt = "Enter duration (minutes): ";
            int duration = checkIntInput(input,durationPrompt);
            //int duration = Integer.parseInt(input.nextLine().trim());
            
            String hrPrompt = "Enter average heart rate: ";
            int hr = checkIntInput(input,hrPrompt);

            //System.out.println("Enter average heart rate: ");
            //int hr = Integer.parseInt(input.nextLine().trim());

            Activity activity = null; 
            String distPrompt = "Enter distance (miles): ";
            String elevHighPrompt = "Enter max elevation (ft): ";
            String elevLowPrompt = "Enter lowest elevation (ft): ";

            // follow up prompts based on type of activity
            switch (type) {
                case "run":
                	
                	double runDist = checkDoubleInput(input, distPrompt);
                    //System.out.println("Enter distance (miles): ");
                    //int runDist = Integer.parseInt(input.nextLine().trim());
                    //System.out.println("Enter elevation high (ft): ");
                    int runHigh = checkIntInput(input,elevHighPrompt);
                    //System.out.println("Enter elevation low (ft): ");
                    int runLow = checkIntInput(input,elevLowPrompt);
                    activity = new Run(hero, date, "Run", duration, hr, runDist, runHigh, runLow);
                    break;

                case "bike":
                    //System.out.println("Enter distance (miles): ");
                    int bikeDist = checkIntInput(input, distPrompt);
                    //System.out.println("Enter elevation high (ft): ");
                    int bikeHigh = checkIntInput(input,elevHighPrompt);
                    //System.out.println("Enter elevation low (ft): ");
                    int bikeLow = checkIntInput(input,elevLowPrompt);        
                    Bike bike = new Bike(hero, date, "Bike", duration, hr, bikeDist, bikeHigh, bikeLow);
                    activity = bike;
                    break;

                case "swim":
                    String swimDistPrompt = "Enter distance (yards): ";
                    int swimDist = checkIntInput(input, swimDistPrompt);
                    Swim swim = new Swim(hero, date, "Swim", duration, hr, swimDist);
                    activity = swim;
                    break;

                case "weights":
                    String weightRepPrompt = "Enter reps: ";
                    int reps = checkIntInput(input,weightRepPrompt);
                    String weightLbsPrompt = "Enter weight (lbs): ";
                    int weight = checkIntInput(input, weightLbsPrompt);
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
	 * Method: uploadActivity()
	 * Function: add a new workout to the running memory array list of workouts
	 * @param ArrayList<Activity> activities, scanner
	 * 
	 */
	
	private static Hero createNewHero(Scanner input, ArrayList<Hero> heroes) {
		System.out.print("Enter hero name: ");
        String name = input.nextLine();
        Hero newHero = new Hero(name);
        for(Hero h : heroes) {
        	if(h.getName().equals(name)) {
    			System.out.println("Duplicate heroes found!! Please select which you would like to use: ");
    			System.out.println("A) "+h.getName()+" Lvl: "+h.getLevel());
    			System.out.println("B) New Hero: "+name+" Lvl: 1");
    			String choice = input.nextLine().toLowerCase();
    			
    			switch(choice) {
				case "a":
					newHero = h;
				case "b":
					//do nothing
					break;
    			}
					
    				
        	}
 
        }
      //creates a new hero with all default values
		//Hero newHero = new Hero(name);
        return newHero;
        
        
	}
	

//--//Methods for Reading Files //---------//
	/**
	 * Method: ingests activity history file and returns an object arraylist
	 * @params input filename
	 * @return arrayList of activity objects
	 */
		
	public static ArrayList<Activity> readActivityFile(String fileName, ArrayList<Hero> heroes) {
	
		ArrayList<Activity> fileArray = new ArrayList<>();
		String filePath = "C:\\temp\\"+fileName;
		
		try {
			//set up variables
			DateTimeFormatter ISO = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
			FileReader  fileReader = new FileReader(filePath); 
			BufferedReader bufferReader = new BufferedReader(fileReader);
	
			String inputStr = bufferReader.readLine();
			//skip header
			inputStr = bufferReader.readLine();
	
			while(inputStr != null ) {
				//Create an object for each record read in
				String lineStrings [] = inputStr.split(",");
				//check
				//System.out.println(lineStrings[0]+" "+lineStrings[1]+" "+lineStrings[2]+" "+lineStrings[3]+" "+lineStrings[4]+" "+lineStrings[5]+" "+lineStrings[6]+" "+lineStrings[7]+" ");
				String heroString = lineStrings[0]; //need to look through current heroes to add to right one
				LocalDate date = LocalDate.parse(lineStrings[1], ISO);
				String actTypeString = lineStrings[2];
				int duration = Integer.parseInt(lineStrings[3]);
				int heartRate = Integer.parseInt(lineStrings[4]);
				
				//Need to account for ALL attributes since each row will have all 11 spots
				// this didn't work here to parse null values, going to try to only parse the necessary spots per activity and skip parsing the null strings
				/*
				int distance = Integer.parseInt(lineStrings[5]);
				int elevHigh = Integer.parseInt(lineStrings[6]);
				int elevLow = Integer.parseInt(lineStrings[7]);
				//int elevChange = Integer.parseInt(lineStrings[8]); //nothing actually reads this in
				int reps = Integer.parseInt(lineStrings[9]);
				int weightInLbs = Integer.parseInt(lineStrings[10]);
				*/
				
				//should we create a new hero here? or throw an error...? 
				//I'd like this to just be an automated part of program start up so going to just roll with Placeholder for now
				
				Hero temp = new Hero("PLACEHOLDER");
				
				//first, find the hero to correlate
				for(Hero h : heroes) {
					if(h.getName().equals(heroString)) {
						temp = h;
					}
				}
				if(temp.getName().equals("PLACEHOLDER")) {
					System.out.println("NO HERO FOUND FOR ACTIVITY");
				}
				
				//Now we take each of these and put them into the correct activity based on Type
				if(actTypeString.equals("Run")) {
					int distance = Integer.parseInt(lineStrings[5]);
					int elevHigh = Integer.parseInt(lineStrings[6]);
					int elevLow = Integer.parseInt(lineStrings[7]);
					Run readAct = new Run(temp,date,actTypeString,duration,heartRate,distance,elevHigh,elevLow);
					fileArray.add(readAct); 
				}
				
				else if(actTypeString.equals("Bike")) {
					int distance = Integer.parseInt(lineStrings[5]);
					int elevHigh = Integer.parseInt(lineStrings[6]);
					int elevLow = Integer.parseInt(lineStrings[7]);
					Bike readAct = new Bike(temp,date,actTypeString,duration,heartRate,distance,elevHigh,elevLow);
					fileArray.add(readAct); 
				}
				
				else if(actTypeString.equals("Swim")) {
					double distance = Double.parseDouble(lineStrings[5]);
					Swim readAct = new Swim(temp,date,actTypeString,duration,heartRate,distance);
					fileArray.add(readAct); 
				}
				
				else if(actTypeString.equals("Weights")) {
					int reps = Integer.parseInt(lineStrings[9]);
					int weightInLbs = Integer.parseInt(lineStrings[10]);
					Weights readAct = new Weights(temp,date,actTypeString,duration,heartRate,reps, weightInLbs);
					fileArray.add(readAct); 
				}
				
				else {
					throw new IllegalArgumentException("Unknown activity type: " + actTypeString);
				}
				
				//System.out.println(teamForList.getTeamName());
				
				inputStr = bufferReader.readLine();
			}
			bufferReader.close();
			return fileArray;
			
		}
		catch (IOException ioe){
			System.out.println("something went wrong with reading the file. If it did not exist, the program will create the necessary file at "+filePath+" when it does it's first save.");
			return fileArray;
		}
		
	}
	
	/**
	 * Method: ingests hero file and returns an object arraylist
	 * @params input filename
	 * @return arrayList of hero objects
	 */
		
	public static ArrayList<Hero> readHeroFile(String fileName, Scanner input) {
	
		ArrayList<Hero> fileArray = new ArrayList<>();
		String filePath = "C:\\temp\\"+fileName;				
		
		try {
			FileReader  fileReader = new FileReader(filePath); 
			BufferedReader bufferReader = new BufferedReader(fileReader);
	
			String inputStr = bufferReader.readLine();
			//skip header
			inputStr = bufferReader.readLine();
			
			while(inputStr != null ) {
				//Create an object for each record read in
				String lineStrings [] = inputStr.split(",");
				//
				//System.out.println(lineStrings[0]+lineStrings[1]);
				//
				//we want to make sure there are no null values for heros. IF blank, they should be corrected to 0
				for(int x=0;x<lineStrings.length;x++) {
					if(lineStrings[x].equals("")) {
						lineStrings[x] = "0";
					}
				}
				//
				/*
				for(int y=0;y<lineStrings.length;y++) {
					System.out.print(lineStrings[y]);
				}
				*/
				//
				String heroString = lineStrings[0];
				//set all values first -- these should ALWAYS have an integer to parse since even at no level there should be 0
				int heroLevel = Integer.parseInt(lineStrings[1].trim());
				int health = Integer.parseInt(lineStrings[2]);
				int strength = Integer.parseInt(lineStrings[3]);
				int runSkill = Integer.parseInt(lineStrings[4]);
				int bikeSkill = Integer.parseInt(lineStrings[5]);
				int swimSkill = Integer.parseInt(lineStrings[6]);
				int weightSkill = Integer.parseInt(lineStrings[7]);
				double exp = Double.parseDouble(lineStrings[6]);
				int floor = Integer.parseInt(lineStrings[7]);
						
				//create hero object based on the name and then generate all of the statistics								
				//should also check to make sure there isn't already a hero with that name...?
				boolean duplicate = false;
				//in case of match, report on the level of each and ask which they would like to remove
				for (int h=0;h<fileArray.size();h++) {
					if(((Hero) fileArray.get(h)).getName().equals(heroString)) {
						System.out.println("Duplicate heroes found!! select which you would like to keep: ");
						System.out.println("A) "+((Hero) fileArray.get(h)).getName()+" Lvl: "+((Hero) fileArray.get(h)).getLevel());
						System.out.println("B) "+heroString+" Lvl: "+heroLevel);
						String choice = input.nextLine().toLowerCase();
						
						switch(choice) {
						case "a":
							//do nothing
							break;
						case "b":
							Hero newHero = new Hero(heroString);
							newHero.setLevel(heroLevel);
							newHero.setHealth(health);
							newHero.setStrength(strength);
							newHero.setRunSkill(runSkill);
							newHero.setBikeSkill(bikeSkill);
							newHero.setSwimSkill(swimSkill);
							newHero.setWeightSkill(weightSkill);
							newHero.setExp(exp);
							newHero.setFloor(floor);
							//finally put the new hero in the spot of the one we just chose to ignore
							fileArray.set(h, newHero);
							//need to set our replacement check so we don't create a new hero after the replacement
							duplicate = true;
						}
											
					}
				}
				
				//moving on from matching hero name check
				//if name did not match, we have to add our new hero to the array
				if(duplicate==false) {
					Hero newHero = new Hero(heroString);
					//set all of the stats
					newHero.setLevel(heroLevel);
					newHero.setHealth(health);
					newHero.setStrength(strength);
					newHero.setRunSkill(runSkill);
					newHero.setBikeSkill(bikeSkill);
					newHero.setSwimSkill(swimSkill);
					newHero.setWeightSkill(weightSkill);
					newHero.setExp(exp);
					newHero.setFloor(floor);
					
					//newHero.printHero();
					//add to array
					fileArray.add(newHero);
				}
				
				inputStr = bufferReader.readLine();
			}
			bufferReader.close();
			//System.out.println(fileArray.get(0).getName());
			return fileArray;
			
			
		}
		catch (IOException ioe){
			System.out.println("something went wrong with reading the file. If it did not exist, the program will create the necessary file at "+filePath+" when it does it's first save.");
			return fileArray;
		}
	}
	
	
	
//--// Methods for printing and Input
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
	
	
	/**
	 * Method: checkIntInput()
	 * Function: checks input to make sure it's an integer and reprompts if wrong
	 * NOTE: I'd like to be able to input decimal places
	 * @param Scanner input
	 */
	
	public static int checkIntInput(Scanner input, String prompt) {
		//set null value for integer
		Integer value = null;
	    while (value == null) {
	        System.out.print(prompt);
	        String line = input.nextLine().trim();
	        try {	        	
	            value = Integer.parseInt(line);
	        } catch (NumberFormatException e) {
	            System.out.println("Please enter a whole number - no decimal places");
	        }
	    }
	    return value;
	}
	
	/**
	 * Method: checkDoubleInput()
	 * Function: checks input to make sure it's a double and reprompts if wrong
	 * @param Scanner input
	 */
	
	public static double checkDoubleInput(Scanner input, String prompt) {
		//set null value for integer
		Double value = null;
	    while (value == null) {
	        System.out.print(prompt);
	        String line = input.nextLine().trim();
	        try {	        	
	            value = Double.parseDouble(line);
	        } catch (NumberFormatException e) {
	            System.out.println("Please enter a number.");
	        }
	    }
	    return value;
	}

}
	
