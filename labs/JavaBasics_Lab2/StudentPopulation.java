/*
 * Compute the ratio of poputillation at two different schools
 * Written: 	Jiazhen Zhou
 * Created on:	XXX
 */
import java.util.*;
public class StudentPopulation {
	public static void main(String args[])
	{
		//Declare variables for student population at UWO and UWW
		int uwo = 14000; //Student population in UW-Oshkosh
		int uww = 12000; //Student population in UW-Whitewater
		
		//Compute the ratio of UWO vs UWW
		double ratio = 0;
		ratio = (double)(uwo)/uww;
		
		//Print out the ratio value
		System.out.println("The ratio of student population at UW-Oshkosh is "
				+ ratio +" times of UW-Whitewater.");
		
	}
	
	
}
