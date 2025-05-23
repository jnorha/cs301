
/**

 * Course: CS300 - Summer 2025
 * Program: BMI calculator
 * Name: Josh Daniels
 * Email: jndaniels@wisc.edu
* @see any classes that this class uses, to show dependencies
 */


// first grab your stuff

import java.util.*;

/*
public class person {
	private string name 
	Private double weight
	private int height feet
	private int height inches
}

height inches and BMI calculation as a method
health status is then a different method
print statement as final method
*/ 

public class CalculateBMI {
	public static void main(String[] args) {
		// First start with initializing variables
		String name;
		int height_feet=0;
		int height_inches=0;
		int height_just_inches=0;
		double weight=0;
		double bmi=0;
		final int CONVERT_FT_TO_IN=12;
		
		Person person1 = new Person(name, weight, height ..args.)
		person1.printHealthReport() 
		
		Scanner pullstuff;
		pullstuff = new Scanner(System.in);

		
		// gather our inputs
		System.out.println("Input your name? "); //, weight, hight in Feet & Inches, spaces in between: ");
		name=pullstuff.nextLine();
		System.out.println("Weight ");
		weight=pullstuff.nextDouble();
		System.out.println("What is your height? ");
		System.out.println("Feet: ");
		height_feet=pullstuff.nextInt();
		System.out.println("Inches: ");
		height_inches=pullstuff.nextInt();
		// convert to just inches
		
		/* height_just_inches=(height_feet * CONVERT_FT_TO_IN) + height_inches;
		 */
		// Bug Check
		// System.out.print("Check inches: " + height_just_inches);
		
		bmi=weight*703 / Math.pow(height_just_inches,2);
		// Bug Check
		// System.out.print(name + "'s BMI value:" + bmi);
		
		// Judge ones BMI value
		String healthStatus = "";
		if (bmi < 18.5) {
			healthStatus = "underweight";
		}
		else if (bmi > 25) {
			healthStatus = "overweight";
		}
		else {
			healthStatus = "normal";
		}
		
		// check
		//System.out.print(name + "'s bmi is a " + healthStatus + " value of " + bmi);
		
		// build new variables
		String heightFull=height_feet+"'" + height_inches + "\"";
		//Print out name, weight, height, BMI value, and health status
		System.out.printf("%24s %10s %10s %12s %15s \n", "Name", "Weight", "Height", "BMI Value", "Health Status");
		// System.out.println(); This can be replaced by \n
		System.out.printf("%24s %10.1f %10s %12.2f %15s \n", name, weight, heightFull, bmi, healthStatus);
	}
	

}
