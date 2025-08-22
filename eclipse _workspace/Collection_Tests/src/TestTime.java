/**
 * Program for testing the running time of different operations
 * Since it is fast to run several operations, the test is repeated for many times 
 * 	to reduce the errors and obtain more accurate results.
 * 
 * Special note about the test:
 * Please adjust the following two parameters if really necessary
 * 		final int NUM_RAND_ACCESS = 1000; //This number could be incremented if the total time is too short for your computer
		final int START_NUM = 1000000; //Change not recommended. But if really necessary, check with the instructor
		final int END_NUM = 50000000; //Change it to a different number if the test time is more than 1 hour or too short (like < 10 minutes)
 */
import java.util.Random;

public class TestTime {

	public static void testTime() {
		System.out.println("****************** Time Test Dynamic Array vs LinkedList ****************** \n");
		double insTimeDA = 0, insTimeLL = 0, delTimeDA = 0, delTimeLL = 0, 
				scanTimeDA = 0, scanTimeLL = 0, randInsertDelteteTimeDA =0,
				randInsertDelteteTimeLL = 0, randAccessTimeDA = 0, randAccessTimeLL = 0;
		long totalInsDelScan = 0, totalRandAccess = 0;
		
		Random rand = new Random(System.currentTimeMillis());
		int round = 0;
		final int NUM_RAND_ACCESS = 1000; //This number could be incremented if the total time is too short for your computer
		final int START_NUM = 100000;
		final int END_NUM = 600000;
		/*Note: you can change the ending number (50 million) to a small number for the trial test.
			Then increase the END_NUM to a value that lead to a total running time around 1 hour.
		 * 
		 */
		for (int num = START_NUM; num <= END_NUM; num *= 1.2) {
			DynamicArray da = new DynamicArray();
			LinkedList ll = new LinkedList();

			//Measure the time for insertion
			long start = System.currentTimeMillis();
			for (int i = 0; i < num; i++) {
				da.insertAtEnd(i);;
			}
			insTimeDA += (System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			for (int i = 0; i < num; i++) {
				ll.insertAtEnd(i);
			}
			insTimeLL += (System.currentTimeMillis() - start);

			//Measure the time for sequential scanning (traversing) the whole list
			start = System.currentTimeMillis();
			for (int i = 0; i < num; i++) {
				da.access(i);
			}
			scanTimeDA += (System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			ListNode tmp = ll.head;
			
			for (int i = 0; i < num; i++) {
				int y = (int)((ll.getNodeAt(i)).value);
			}
			scanTimeLL += (System.currentTimeMillis() - start);

			//Measure the time for random access on the lists 
			int[] randAccessIndexes = new int[NUM_RAND_ACCESS];
			//Create a random access sequence with the index < list size
			for (int j = 0; j < NUM_RAND_ACCESS; j++){
				randAccessIndexes[j] = rand.nextInt(da.getSize());
			}

			start = System.currentTimeMillis();
			for (int j = 0; j < NUM_RAND_ACCESS; j++){
				da.access(randAccessIndexes[j]);
			}
			randAccessTimeDA += (System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			for (int j = 0; j < NUM_RAND_ACCESS; j++) {
				ll.getNodeAt(randAccessIndexes[j]);
			}
			randAccessTimeLL += (System.currentTimeMillis() - start);

			//Measure the time for inserting and deleting at random locations 
			start = System.currentTimeMillis();
			for (int j = 0; j < NUM_RAND_ACCESS; j++){
				//Insert one member after a random position
				da.insertAfter(randAccessIndexes[j],j);
				//Delete the member that was just inserted
				da.deleteAfter(randAccessIndexes[j]);
			}
			randInsertDelteteTimeDA += (System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			for (int j = 0; j < NUM_RAND_ACCESS; j++){
				//Insert one member after a random position
				ll.insertAfter(randAccessIndexes[j],j);
				//Delete the member that was just inserted
				ll.deleteAfter(randAccessIndexes[j]);			
			}
			randInsertDelteteTimeLL += (System.currentTimeMillis() - start);

			
			//Measure the time for deleting at the end for both lists 
			start = System.currentTimeMillis();
			for (int i = 0; i < num; i++) {
				da.deleteLast();
			}
			delTimeDA += (System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			for (int i = 0; i < num; i++) {
				ll.deleteLast();
			}
			delTimeLL += (System.currentTimeMillis() - start);

			System.out.println("Round " + round + " Completed");
			round++;
			totalInsDelScan += num;
			totalRandAccess += NUM_RAND_ACCESS;
		}
		
		//Print out the test results
		System.out.println("\nTotal number of insertions, scans, and deletions (each) = " + totalInsDelScan);
		System.out.println("Total number of random accesses = " + totalRandAccess);
		System.out.printf("%nInsertion time of dynamic array = %.2fms%n", insTimeDA);
		System.out.printf("Insertion time of linked list = %.2fms%n", insTimeLL);
		System.out.println();
		System.out.printf("Scan time of dynamic array = %.2fms%n", scanTimeDA);
		System.out.printf("Scan time of linked list = %.2fms%n", scanTimeLL);
		System.out.println();
		System.out.printf("Deletion time of dynamic array = %.2fms%n", delTimeDA);
		System.out.printf("Deletion time of linked list = %.2fms%n", delTimeLL);
		System.out.println();
		System.out.printf("Random access time of dynamic array = %.2fms%n", randAccessTimeDA);
		System.out.printf("Random access time of linked list = %.2fms%n", randAccessTimeLL);
		System.out.println();
		System.out.printf("Random insertion and deletion time of dynamic array = %.2fms%n", randInsertDelteteTimeDA);
		System.out.printf("Random insertion and deletion time of linked list = %.2fms%n", randInsertDelteteTimeLL);

	}
	
	public static void main(String[] args) throws Exception {
		testTime();
	}
}
