/**
 * Program to test the correctness of your implementation on the methods in 
 * 		the dynamic array and linked list classes 
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
//import java.util.LinkedList;
import java.util.HashMap;

public class TestCorrectness {
	public static void main(String[] args) throws Exception {
		testLinkedListCorrectness();
	}

	private static void testLinkedListCorrectness() {
		System.out.println("****************** Test Linked List Correctness ****************** \n");

		LinkedList list = new LinkedList();
		
		System.out.println("****************** Test 1 ****************** \n");
		for (int i = 0; i < 18; i++) {
			list.insertAtEnd(i * 5);
			list.printList();	
		}
		System.out.println(" Number of elements: " + list.getSize() );
		System.out.println();
		
		System.out.println("****************** Test 2 ****************** \n");		
		System.out.println("Inserting 28 after position 5. Current list:");
		list.insertAfter(5, 28);
		list.printList();
		System.out.println("Deleting a member after position 5. Current list:");
		list.deleteAfter(5);
		list.printList();
		System.out.println("Deleting the last number. Current list:");
		list.deleteLast();
		list.printList();
		System.out.println();
		System.out.println(" Number of elements: " + list.getSize() );
		
		System.out.println("****************** Test 3 ****************** \n");
		System.out.println("Inserting three elements with value 80. Current list:");
		list.insertAfter(5, 80);
		list.insertAfter(8, 80);
		list.insertAfter(10, 80);
		list.printList();
		System.out.println(" Number of elements: " + list.getSize() );
		

		System.out.println("****************** Test 4 ****************** \n");
		System.out.println("Replacing all elements with value 80 by value 70. Current list:");
		list.replaceAll(80,70);
		list.printList();
		System.out.println("Deleting all elements with value 70. ");
		int deletedNumbers = list.deleteAll(70);
		if(deletedNumbers >0) {
			System.out.println("Have deleted "+deletedNumbers + " elements. Current list:");
		}
		list.printList();
		System.out.println(" Number of elements: " + list.getSize() );
		
	}
}