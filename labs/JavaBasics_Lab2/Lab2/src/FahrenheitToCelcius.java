// very first we will import the necessary utilities
import java.util.*;

public class FahrenheitToCelcius {
	public static void main(String[] args) {
		// Initialize while declaring a variable
		double c=0;
		double f=0;
		Scanner scanner;
		scanner = new Scanner(System.in);
		
		// now we take some input from the console
		System.out.print("Enter a temperature in Fahrenheit:");
		f=scanner.nextDouble();
		//convert it to liters
		c = ((f-32)*5)/9; //converts the gallons into liters
		System.out.println(f + " degrees Fahrenheit is equal to " + c +" degrees Celcius.");
	}
}
