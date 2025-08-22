/**
 *  Test the use of methods in BST and measure the performance
 *  @author:
 *  @Created: 8/6/2025
 *  @Revised:
 */
import java.util.Random;
import java.util.Arrays;

public class TestBST {
	public static void main(String [] arrgs) {
		//Task 3.1: test the correctness of your codes 
		Integer iArray[] = {45,65,43,72,38,32,28};
		//TO BE FILLED
		//Call the method to create a BST out of the given array
		
		
		//BST testTree = new BST(7);
		/*
		testTree.insert(4);
		testTree.insert(8);
		testTree.insert(12);
		*/
		//testTree.inOrdTrav();
		
		BST theSmallTREE = toBSTit(iArray);
		
		
		//Call the in-order traversal method to print out the sorted array
		
		theSmallTREE.inOrdTrav();
		System.out.println();
		//Manually verify the correctness of your code by checking the printout
		// is sorted or not
		
		
		//*****Task 3.2: test the "BST Sorting" time***** 
		long start = System.currentTimeMillis();
		double longIntToBST = 0, longIntTimSort=0;
		//Create a long integer array using the initializeLongIntegerArray method
		
		Integer lArray[] = initializeLongIntegerArray();
		
		//Call the method to create a BST out of the given array and -- This one is ITERATIVE
		// measure the time spent. No need to call the inOrder method
		
		start = System.currentTimeMillis();
		toBSTit(lArray);			
		longIntToBST += (System.currentTimeMillis() - start);
		
		//Call Arrays.sort() and test the sorting time using TimSort
		
		start = System.currentTimeMillis();
		Arrays.sort(lArray);			
		longIntTimSort += (System.currentTimeMillis() - start);
		
		//Print out the sorting time using "BST sort" and TimSort
		
		System.out.println("----------------------------------------------------------------");
		System.out.println("		Test 3.2: Sorting time on Long integer arrays          ");
		System.out.println("----------------------------------------------------------------");
		System.out.printf("Running time of Iterative to BST Integer array= %.2fms \n", longIntToBST);
		System.out.printf("Running time of TimSort Sort on Integer array= %.2fms \n", longIntTimSort);
		
		
		
		
		//*****Task 3.3: test the limit of using recursion***** 
		double longIntToBSTREC = 0, longInt2TimSort=0;
		//Create a long integer array using the method provided
		
		Integer lArray2[] = initializeLongIntegerArray();
		
		//Call Arrays.sort() to sort the array
		
		start = System.currentTimeMillis();
		Arrays.sort(lArray2);			
		longInt2TimSort += (System.currentTimeMillis() - start);
		
		//Call the method to create a BST out of the given array. Make sure
		//your method uses the RECURSIVE version of the insert method
			
		//start = System.currentTimeMillis();
		//toBSTrc(lArray2);			
		//longIntToBSTREC += (System.currentTimeMillis() - start);
		
		//You were supposed to observe stack overflow problem. -- I did first time!
		// If not, try to increase SIZE in initializeLongIntegerArray
		
		/*
		System.out.println("----------------------------------------------------------------");
		System.out.println("		Test 3.3: Sorting time on long integer arrays RECURSIVE ");
		System.out.println("----------------------------------------------------------------");
		//System.out.printf("Running time of Recursive to BST Integer array 2= %.2fms \n", longIntToBSTREC);
		//System.out.printf("Running time of TimSort Sort on Integer array 2= %.2fms \n", longInt2TimSort);
		 * */
		
		
		//*****Task 3.4: test the effect of unbalanced BST****** 
		double searcBSTit = 0, searchTimSort = 0, randArrayTime = 0;
		//Create a long integer array using the method provided
		Integer lArray3[] = initializeLongIntegerArray();
		
		//Call Arrays.sort() to sort the array
		
		//start = System.currentTimeMillis();
		Arrays.sort(lArray3);			
		//searchTimSort += (System.currentTimeMillis() - start);
		
		//Call the method to create a BST out of the given array. Make sure
		//your method uses the ITERATIVE version of the insert method
		
		//start = System.currentTimeMillis();
		BST sortedBST = toBSTit(lArray3);			
		//searcBSTit += (System.currentTimeMillis() - start);
		
		//Create an array that stores 10 numbers from the long integer array.
		//Do this by finding 10 random index values that are less than the length
		//of the array, then read out the array members based on the indexes
		//Implement this as a method and then call it here.
		start = System.currentTimeMillis();
		Integer randArray[] = randomIDX(lArray3);
		randArrayTime += (System.currentTimeMillis() - start);
		System.out.println(randArrayTime + "ms");
		//System.out.println(randArray[1]);
		
		//Search the 10 numbers in the BST and measure the time
		
		start = System.currentTimeMillis();
		for(int x = 0; x<randArray.length;x++) {
			sortedBST.search(randArray[x]);
			//searcBSTit += (System.currentTimeMillis() - start);
		}
		searcBSTit += (System.currentTimeMillis() - start);
		
		//Search the 10 numbers in the sorted array using the 
		// java.util.Arrays.binarySearch() method and measure the time
		
		start = System.currentTimeMillis();
		for(int x = 0; x<randArray.length;x++) {
			Arrays.binarySearch(lArray3, randArray[x]);
			//searchTimSort += (System.currentTimeMillis() - start);
		}
		searchTimSort += (System.currentTimeMillis() - start);
		// Print out the search time for the two cases
		
		System.out.println("----------------------------------------------------------------");
		System.out.println("		Test 3.4: Search time on long integer arrays Iterative  ");
		System.out.println("----------------------------------------------------------------");
		System.out.printf("Running time of BST Search of 10 random Ints= %.2fms \n", searcBSTit);
		System.out.printf("Running time of TimSort Search of 10 random Ints= %.2fms \n", searchTimSort);

		
		
	}
	
	//TO BE DONE
	
	/**
	 *  Method to create a BST out of a given array - Iterative
	 * @param array
	 * @return bst
	 */
	
	public static <E> BST toBSTit(Comparable<E> alist[]) {
		Random random = new Random();
		int randomIdx = random.nextInt(alist.length);
		BST bTree = new BST(alist[randomIdx]);
		
		//now fill in the tree with all the other index values
		for(int i = 0; i<alist.length;i++) {
			if(i==randomIdx) {
				//skip root, even though insert would know to skip it
			}
			else {
				//add to array
				
				//Iterative
				bTree.insert(alist[i]);
				
				//recursive
				//bTree.insertR(alist[i]);
			}
		}
		
		return bTree;
	}
	
	/**
	 *  Method to create a BST out of a given array - Recursive
	 * @param array
	 * @return bst
	 */
	
	public static <E> BST toBSTrc(Comparable<E> alist[]) {
		Random random = new Random();
		int randomIdx = random.nextInt(alist.length);
		BST bTree = new BST(alist[randomIdx]);
		
		//now fill in the tree with all the other index values
		for(int i = 0; i<alist.length;i++) {
			if(i==randomIdx) {
				//skip root, even though insert would know to skip it
			}
			else {
				//add to array
				
				//Iterative
				//bTree.insert(alist[i]);
				
				//recursive
				bTree.insertR(alist[i]);
			}
		}
		
		return bTree;
	}

	/**
	 * Method: randomIDX()
	 * function: finds 10 random index numbers from an array and returns an array of those 10 values
	 * @param array[]
	 */
	
	public static Integer[] randomIDX(Integer alist[]) {
		int n = alist.length;
		Random random = new Random();
		Integer retArray []= new Integer[10];
		for (int i = 0; i<10;i++) {
			int randomIdx = random.nextInt(n);
			retArray[i]=alist[randomIdx];
		}
		return retArray;
	}
	
	
	/**
	 * Initialize a long integer array and fill it with random integers less than MAXNUMBER
	 * @return the random integer array
	 * 
	 */	
	public static Integer [] initializeLongIntegerArray() {
		//1 Million, feel free to adjust this number if the test takes too long
		int SIZE = 500000; 
		int MAX_NUMBER = SIZE*100; //Make sure it is not larger than 2^32, about 4 Billion
		Integer integerLongArray [] = new Integer[SIZE];
		Random rand = new Random(System.currentTimeMillis());
		
		//Create an array of large size with each number being a random integer number
		for (int j = 0; j < SIZE; j++){
			integerLongArray[j] = rand.nextInt(MAX_NUMBER);
		}
		
		return integerLongArray;
		
	}

}
