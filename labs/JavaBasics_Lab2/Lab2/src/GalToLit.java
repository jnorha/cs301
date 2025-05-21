/* 
  		This program converts gallons to liters
 		The class name is GalToLit
 */
public class GalToLit {

	public static void main(String[] args) {
		// Initialize while declaring a variable
		double gallons = 0;
		double liters = 0;
		final double GALLON_TO_LITER = 3.7854;
		
		// to be filled, set gallons as 5
		gallons = 5;
		
		//convert it to liters
		liters = gallons*GALLON_TO_LITER; //converts the gallons into liters
		System.out.println(gallons + " gallons is " + liters +" liters.");

	}

}
