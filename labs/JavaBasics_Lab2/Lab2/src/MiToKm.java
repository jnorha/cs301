/* 
  		This program converts miles to kilometers based on input
 		The class name is MiToKm
 */
// very first we will import the necessary utilities
import java.util.*;

public class MiToKm {

	public static void main(String[] args) {
		// Again, good habbit to set the variable son initialization
		double mi=0;
		double km=0;
		final double MILES_TO_KM_CONVERSION=1.609344;
		Scanner scanner;
		scanner = new Scanner(System.in);
		
		// now we take some input from the console
		System.out.print("Enter a distance in miles:");
		mi=scanner.nextDouble();
		//convert it to liters
		km = mi*MILES_TO_KM_CONVERSION; //converts the gallons into liters
		System.out.println(mi + " miles is equal to " + km +" kilometers.");

	}

}
