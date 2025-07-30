

/**
 * Class: Hero
 * Function: holds information about a users character 
 * Author: Josh Daniels (jndaniels@wisc.edu)
 * Date: 7/26/25
 * Version: 1.0
 */

import java.util.*;

public class Hero {
	private String name;
	private int level;
    private int health;
    private int strength;
    private int speed;
    private int runSkill;
    private int bikeSkill;
    private int swimSkill;
    private int weightSkill;
    private double exp;
    private int floor;
	
	public Hero(String inName) {
		setName(inName);
		//rest of them will be base level 1 and other starting values for different stats
		this.level       = 1;
        this.health     = 15; //starting with 15 for health
        this.strength    = 0;
        this.speed       = 0;
        this.runSkill    = 0;
        this.bikeSkill   = 0;
        this.swimSkill   = 0;
        this.weightSkill = 0;
        this.exp         = 0;
        this.floor       = 0;
	
	}
	
//--// Methods - Getters and Setters //--//		
	public String getName() {
		return name;
	}
	
	public void setName(String inName) {
		this.name = inName;
	}
	
	public int getLevel() {
        return level;
    }
    
    public void setLevel(int inLevel) {
        this.level = inLevel;
    }

    public int getHealth() {
        return health;
    }
    
    public void setHealth(int inStamina) {
        this.health = inStamina;
    }

    public int getStrength() {
        return strength;
    }
    
    public void setStrength(int inStrength) {
        this.strength = inStrength;
    }
    
    public int getSpeed() {
        return strength;
    }
    
    public void setSpeed(int inSpeed) {
        this.strength = inSpeed;
    }

    public int getRunSkill() {
        return runSkill;
    }
    
    public void setRunSkill(int inRunSkill) {
        this.runSkill = inRunSkill;
    }

    public int getBikeSkill() {
        return bikeSkill;
    }
    
    public void setBikeSkill(int inBikeSkill) {
        this.bikeSkill = inBikeSkill;
    }

    public int getSwimSkill() {
        return swimSkill;
    }
    
    public void setSwimSkill(int inSwimSkill) {
        this.swimSkill = inSwimSkill;
    }

    public int getWeightSkill() {
        return weightSkill;
    }
    
    public void setWeightSkill(int inWeightSkill) {
        this.weightSkill = inWeightSkill;
    }
    
    public double getExp() {
        return exp;
    }
    
    public void setExp(double inExp) {
        this.exp = inExp;
    }
    
    public int getFloor() {
        return floor;
    }
    
    public void setFloor(int inFloor) {
        this.floor = inFloor;
    }
    
    
    
    //Other Methods
    /**
     * Method: gainExp()
     * Function: take an activity and the history of activities for a hero to then gain experience value
     * @param heroActivities, activity
     */
    
    public void gainExp(ArrayList<Activity> heroActivities, Activity act) {
    	double expGained = calcExp(heroActivities, act);
    	System.out.println(this.getName() + " has earned "+ expGained + " experience from this "+ act.getType()+ ". Way to go!!");
    	
    	//set new exp value
    	this.exp = exp + expGained;
    	
    	//increase the specific skills (just a count of total activities for them for now)
    	if (act instanceof Run) {
    		runSkill++;
    	}
        else if (act instanceof Bike) {
        	bikeSkill++;
        }
        else if (act instanceof Swim) {
        	swimSkill++;
        }
        else if (act instanceof Weights) {
        	weightSkill++;
        }

    	expToNext();
    }
    
    
    
    
    /**
     * Method: calcExp()
     * Function: calculates the amount of exp gained based on new activity and the last 3 activities from history
     * @param heroActivities
     * @returns double of exp gained
     */
    
    //gains hero experience based on the type of activity done for that hero
    public double calcExp(ArrayList<Activity> heroActivities, Activity act) {
    	//updates a hero's stats and overall level based on the activity added and the previous activities they've done
    	//looks back 3(?) activities in this heros history and returns a "consistency" or "crossfit" multiplier
    	
    	
    	//special modifiers
    	//check the type of most recent 3 activities
    	heroActivities.sort(Comparator.comparing(Activity -> Activity.getDate()));
    	//this puts most recent days at end by default
    	//ArrayList<Activity> last3 = new ArrayList<Activity>();
    	double crossMultiplier = 1;
    	
    	//set each type count
    	int runCount = 0;
		int bikeCount = 0;
		int swimCount = 0;
		int weightCount = 0;
    	
		//if they have less than 3 activities, just skip it with base multiplier
		if(heroActivities.size()>3) {
			for(int g=(heroActivities.size()-1);g>=heroActivities.size()-3;g--) {   		
	    		if(heroActivities.get(g) instanceof Run) {
	    			runCount++;
	    		}
	    		else if(heroActivities.get(g) instanceof Bike) {
	    			bikeCount++;
	    		}
	    		else if(heroActivities.get(g) instanceof Swim) {
	    			swimCount++;
	    		}
	    		else if(heroActivities.get(g) instanceof Weights) {
	    			weightCount++;
	    		}
	    	}	    	
	    	//first see if all three were the same (no multiplier)
	    	if(runCount==3 || bikeCount==3 || swimCount==3 || weightCount==3) {
	    		crossMultiplier=1;
	    	}
	    	else if(runCount==2 || bikeCount==2 || swimCount==2 || weightCount==2) {
	    		crossMultiplier=1.25;
	    	}
	    	//in all other scenarios they should each only have count of 1
	    	else {
	    		crossMultiplier=1.5;
	    	}
		}
		
		//not enough workouts for multiplier
		else {
			crossMultiplier = 1;
		}
		
    	
    	
    	//set a base exp gain which shrinks with the amount of relative effort. Someone high level will have to work out longer to get more exp   	
    	double base = crossMultiplier * act.getDuration() * (act.getHR()/10) / level;
    	   	
    	if (act instanceof Run) {
    		double runExp = base + 10 * ((Run) act).getDistance();
            return runExp;
        } else if (act instanceof Bike) {
        	double bikeExp =  3 * ((Bike) act).getDistance();
        	return bikeExp;
        } else if (act instanceof Swim) {
        	double swimExp = base + 8 * ((Swim) act).getDistance();
            return swimExp;
        } else if (act instanceof Weights) {
        	double weightExp = ((Weights) act).getReps() * ((Weights) act).getWeightInLbs() / 100;
            return weightExp;
        }
        return base;
    }
    
    /**
     * Method: expToNext()
     * function: reports exp needed to get to next level
     * 
     */
    
    public void expToNext() {
    	double expNeeded = level * 100 * 1.25;
    	if (exp >= expNeeded) {
    		//partially reset exp so they gotta grind again! >:)
    		exp -= expNeeded;
    		level ++;
    		System.out.printf("%s leveled up to %d!!", name, level);
    		//recursively check if exp is still higher than the next level needed
    		expToNext();
    	}
    	else {
    		double expDiff = expNeeded - exp;
    		int nextLevel = this.getLevel()+1;
    		//add some goal metric based on magnitude of exp needed
    		/*
    		if(500>expDiff>100) {
    			
    		}
    		*/
    		System.out.println(this.getName() + " needs "+expDiff+" more experience points to reach "+ nextLevel +" ... can we get there by the end of the month?");
    	}
    }
    
    /**
     * Method: printHero()
     * @Overrides the base Object toString() method
     * 
     */
    
    public void printHero() {
    	String heroName = this.getName();
		int heroLevel = this.getLevel();
		int heroHealth = this.getHealth();
		int heroStrength = this.getStrength();
		int heroSpeed = this.getSpeed();
		int runSkill = this.getRunSkill();
		int bikeSkill = this.getBikeSkill();
		int swimSkill = this.getSwimSkill();
		int weightSkill = this.getWeightSkill();
		int currentFloor = this.getFloor();
		
		String heroDescription = String.format("%s (Lvl %d) Current Floor: %d - HP:%d STR:%d SPD:%d (Skills) - RUN:%d BIKE:%d SWIM:%d WT:%d", heroName, heroLevel, currentFloor,
				heroHealth, heroStrength, heroSpeed,runSkill,bikeSkill,swimSkill,weightSkill);
		
		System.out.println(heroDescription);
    }

}
