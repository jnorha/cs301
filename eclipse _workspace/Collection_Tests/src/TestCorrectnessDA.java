/**
 * Program to test the correctness of your implementation on the methods in 
 * 		the dynamic array and linked list classes 
 */
import java.util.Arrays;

public class TestCorrectnessDA {

	private static void testDynamicArrayCorrectness() {
		DynamicArray<Integer> da = new DynamicArray();
		System.out.println("****************** Test Dynamic Array Correctness ****************** \n");
		System.out.println("****************** Test 1 ****************** \n");
		for (int i = 0; i < 18; i++) {
			da.insertAtEnd(i * 5);
			System.out.print(da.toString() + "\n");

		}
		System.out.println(" Number of elements: " + da.getSize() + ", Capacity: " + da.getCapacity());
		System.out.println();
		
		System.out.println("****************** Test 2 ****************** \n");		
		System.out.println("Inserting 28 after position 5. Current dynamic array:");
		da.insertAfter(5, 28);
		System.out.print(da.toString());
		System.out.println("Deleting a member after position 5. Current dynamic array:");
		da.deleteAfter(5);
		System.out.print(da.toString());
		System.out.println("Deleting the last number. Current dynamic array:");
		da.deleteLast();
		System.out.print(da.toString());
		System.out.println();
		System.out.println(" Number of elements: " + da.getSize() + ", Capacity: " + da.getCapacity());
		
		System.out.println("****************** Test 3 ****************** \n");
		System.out.println("Inserting three elements with value 80. Current dynamic array:");
		da.insertAfter(5, 80);
		da.insertAfter(8, 80);
		da.insertAfter(10, 80);
		System.out.print(da.toString());
		System.out.println(" Number of elements: " + da.getSize() + ", Capacity: " + da.getCapacity());
		

		System.out.println("****************** Test 4 ****************** \n");
		System.out.println("Replacing all elements with value 80 by value 70. Current dynamic array:");
		da.replaceAll(80,70);
		System.out.print(da.toString());
		System.out.println("Deleting all elements with value 70. ");
		int deletedNumbers = da.deleteAll(70);
		if(deletedNumbers >0) {
			System.out.println("Have deleted "+deletedNumbers + " elements. Current dynamic array:");
		}
		System.out.print(da.toString());
		System.out.println(" Number of elements: " + da.getSize() + ", Capacity: " + da.getCapacity());
		
	}

	public static void main(String[] args) throws Exception {
		System.out.println();
		testDynamicArrayCorrectness();
	}
}
