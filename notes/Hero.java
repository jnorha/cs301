/**
 * Class: Hero
 * Function: holds information about a users character 
 * Author: Josh Daniels (jndaniels@wisc.edu)
 * Date: 7/26/25
 * Version: 1.0
 */



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
	
	public Hero(String inName) {
		setName(inName);
		//rest of them will be base level 1 and other starting values for different stats
		this.level       = 1;
        this.health     = 15; //starting with 15 for health
        this.strength    = 10;
        this.speed       = 3;
        this.runSkill    = 0;
        this.bikeSkill   = 0;
        this.swimSkill   = 0;
        this.weightSkill = 0;
	
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
    
    
    
    //Other Methods
    
    //gains hero experience based on the type of activity done for that hero
    public void expGain(Activity act) {
    	
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
		
		String heroDescription = String.format("%s (Lvl %d) - HP:%d STR:%d SPD:%d (Skills) - RUN:%d BIKE:%d SWIM:%d WT:%d", heroName, heroLevel,
				heroHealth, heroStrength, heroSpeed,runSkill,bikeSkill,swimSkill,weightSkill);
		
		System.out.println(heroDescription);
    }

}
