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
    private int stamina;
    private int strength;
    private int health;
    private int runSkill;
    private int bikeSkill;
    private int swimSkill;
    private int weightSkill;
	
	public Hero(String inName) {
		setName(inName);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String inName) {
		this.name = inName;
	}

}
