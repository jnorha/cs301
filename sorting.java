/**
 * Program for testing the running time of different sorting algorithms
 * Tasks:
 * 	1. Write methods for Bubble Sort, Insertion Sort, Merge Sort, and Timsort.
 * 	2. Complete the testTime method in the places marked "TO BE FILLED" as instructed
 * @author your name
 * Created on: 
 * Revised on:
 */
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SortingTimeTest {
	
	public static void main(String[] args) throws Exception {
		testTime();
	}
	
	/**
	 * The method to test the running time of sorting algorithms
	 * 
	 */
	public static void testTime() {
		System.out.println("********* Time Test for Bubble Sort, Insertion Sort, Merge Sort, and TimSort ******** \n");
		double bubbleSortTimeString = 0, insertionSortTimeString=0, 
				mergeSortTimeString = 0, timSortTimeString=0;
		double bubbleSortTimeInteger = 0, insertionSortTimeInteger=0, 
				mergeSortTimeInteger = 0, timSortTimeInteger=0;
		double bubbleSortTimeIntegerLong = 0, insertionSortTimeIntegerLong=0, 
				mergeSortTimeIntegerLong = 0, timSortTimeIntegerLong=0;
		
		
		/*Time test for small size array, need to repeat multiple times
		*	to reduce the errors and obtain more accurate results. 
		* Test items:
		* (1) Bubble Sort, Insertion Sort, Merge Sort, and Timsort for short Integer array
		* (2) Bubble Sort, Insertion Sort, Merge Sort, and Timsort for short String array
		* (3) Bubble Sort, Insertion Sort, Merge Sort, and Timsort for long Integer array
		* 
		* 
		*/
		//String A[];
		String sArray[] = {}; //short string array
		Integer iArray[] = {}; //short Integer array
		Integer lArray[] = {}; //Long array with integers
		long start = System.currentTimeMillis();
		for (int num = 0; num <= 10000000; num ++) {
			/*
			 * 1. Test the sorting time for short Integer Array
			 * */
			//1.1 Test for bubble sort on Integer array			
			//Arrays.sort(iArray); //For your convenience of test and comparison			
			//TO BE FILLED:
			//Call the bubble sorting algorithm for the Integer array
			iArray = initializeShortIntegerArray();
			start = System.currentTimeMillis();
			bubbleSort(iArray);			
			bubbleSortTimeInteger += (System.currentTimeMillis() - start);
			
			//1.2 Test for insertion sort on Integer array
			//TO BE FILLED
			iArray = initializeShortIntegerArray();
			//start = System.currentTimeMillis();
			insertSort(iArray, 0, iArray.length-1);		
			insertionSortTimeInteger += (System.currentTimeMillis() - start);

			//1.3 Test for merge sort on Integer array
			iArray = initializeShortIntegerArray();
			//start = System.currentTimeMillis();
			mergeSort(iArray, 0, iArray.length-1);
			mergeSortTimeInteger += (System.currentTimeMillis() - start);
			//TO BE FILLED

			//1.4 Test for Timsort on Integer array
			iArray = initializeShortIntegerArray();
			//start = System.currentTimeMillis();
			timSort(iArray, 0, iArray.length-1);
			timSortTimeString += (System.currentTimeMillis() - start);
			//TO BE FILLED
			
			/*
			 * 2. Test the sorting time for short String Array
			 * */
			//2.1 Test for bubble sort on String array
			sArray = initializeShortStringArray();
			start = System.currentTimeMillis();
			bubbleSort(sArray);			
			bubbleSortTimeString += (System.currentTimeMillis() - start);
			//TO BE FILLED:

			//2.2 Test for insertion sort on the string array
			sArray = initializeShortStringArray();
			start = System.currentTimeMillis();
			insertSort(sArray, 0, sArray.length-1);			
			insertionSortTimeString += (System.currentTimeMillis() - start);

			//2.3 Test for merge sort on the string array
			sArray = initializeShortStringArray();
			start = System.currentTimeMillis();
			mergeSort(sArray, 0, sArray.length-1);			
			mergeSortTimeString += (System.currentTimeMillis() - start);
			//TO BE FILLED

			//2.4 Test for Timsort on the string array
			sArray = initializeShortStringArray();
			start = System.currentTimeMillis();
			timSort(sArray, 0, sArray.length-1);			
			timSortTimeString += (System.currentTimeMillis() - start);
			//TO BE FILLED

		}

		//System.out.println("Sorted String Array:   " + Arrays.toString(sArray));
		//System.out.println("Sorted Integer Array:   " + Arrays.toString(iArray));

		/*
		 * 3. Test the sorting time for long Integer Array
		 * */
		//3.1 Test for bubble sort on Integer array
		lArray = initializeLongIntegerArray();
		start = System.currentTimeMillis();
		bubbleSort(lArray);			
		bubbleSortTimeString += (System.currentTimeMillis() - start);
		//TO BE FILLED:
		//Call the bubble sorting algorithm for the long integer array
		
		//bubbleSortTimeIntegerLong += (System.currentTimeMillis() - start);
		
		//3.2 Test for insertion sort on Integer array
		lArray = initializeLongIntegerArray();
		start = System.currentTimeMillis();
		insertSort(lArray, 0, lArray.length-1);			
		insertionSortTimeString += (System.currentTimeMillis() - start);
		//TO BE FILLED

		//3.3 Test for merge sort on Integer array
		lArray = initializeLongIntegerArray();
		start = System.currentTimeMillis();
		mergeSort(lArray, 0, lArray.length-1);			
		mergeSortTimeString += (System.currentTimeMillis() - start);
		//TO BE FILLED

		//3.4 Test for Timsort on Integer array
		lArray = initializeLongIntegerArray();
		start = System.currentTimeMillis();
		timSort(lArray, 0, lArray.length-1);			
		timSortTimeString += (System.currentTimeMillis() - start);
		//TO BE FILLED
		
		
		//Print out the test results
		System.out.println("------------------------------------------------------------");
		System.out.println("		Test 1: Sorting time on short integer arrays ");
		System.out.println("------------------------------------------------------------");
		System.out.printf("Running time of Bubble Sort on Integer array= %.2fms \n", bubbleSortTimeInteger);
		System.out.printf("Running time of Insertion Sort on Integer array= %.2fms \n", insertionSortTimeInteger);
		System.out.printf("Running time of Merge Sort on Integer array= %.2fms \n", mergeSortTimeInteger);
		System.out.printf("Running time of TimSort on Integer array= %.2fms \n", timSortTimeInteger);
		System.out.println("------------------------------------------------------------");
		System.out.println("		Test 2: Sorting time on short string arrays ");
		System.out.println("------------------------------------------------------------");
		System.out.printf("Running time of Bubble Sort on String array= %.2fms \n", bubbleSortTimeString);
		System.out.printf("Running time of Insertion Sort on String array= %.2fms \n", insertionSortTimeString);
		System.out.printf("Running time of Merge Sort on String array= %.2fms \n", mergeSortTimeString);
		System.out.printf("Running time of TimSort on String array= %.2fms \n", timSortTimeString);
		System.out.println("------------------------------------------------------------");
		System.out.println("		Test 3: Sorting time on long arrays 		");
		System.out.println("------------------------------------------------------------");
		System.out.printf("Running time of Bubble Sort on Long Integer array= %.2fms \n", bubbleSortTimeIntegerLong);
		System.out.printf("Running time of Insertion Sort on Long Integer array= %.2fms \n", insertionSortTimeIntegerLong);
		System.out.printf("Running time of Merge Sort on Long Integer array= %.2fms \n", mergeSortTimeIntegerLong);
		System.out.printf("Running time of TimSort on Long Integer array= %.2fms \n", timSortTimeIntegerLong);
		
		System.out.println("------------------------------------------------------------");
		
	}
	
	public static Integer [] initializeShortIntegerArray() {
		Integer integerArray[] = { 98,23,75,42,85,28,55};
		return integerArray;
	}
	public static String [] initializeShortStringArray() {
		String stringArray[] = { "abcertabcertsw", "abcertdefmopaw", "abcertabcdertu", "abcertbceghopa", "abcertdertmoph", "fightabcertace", "frightnabcertb"};
		return stringArray;
	}
	
	/**
	 * Initialize a long integer array and fill it with random integers less than MAXNUMBER
	 * @return the random integer array
	 * 
	 */	
	public static Integer [] initializeLongIntegerArray() {
		//1 Million, feel free to adjust this number if the test takes too long
		int SIZE = 1000000; 
		int MAX_NUMBER = SIZE*100; //Make sure it is not larger than 2^32, about 4 Billion
		Integer integerLongArray [] = new Integer[SIZE];
		Random rand = new Random(System.currentTimeMillis());
		
		//Create an array of large size with each number being a random integer number
		for (int j = 0; j < SIZE; j++){
			integerLongArray[j] = rand.nextInt(MAX_NUMBER);
		}
		
		return integerLongArray;
		
	}

	
	/*
	 * Write your own methods for Bubble Sort, Insertion Sort, Merge Sort, and Timsort
	 * For your comparison, the way to use the sort method in the Arrays class is:
	 * Integer iArray[]; //need to be initalized
	 * Arrays.sort(iArray);
	 * 
	 */
	
	/**
	 * Method: bubbleSort()
	 * Function: uses bubble sorting algorithm to sort an array/list
	 * @param <E>
	 * @param <E>
	 * @param array
	 */
	
	public static void bubbleSort(Comparable[] alist) {
		int n = alist.length;
		Boolean swapFlag = false;
        for (int i = n-1; i >= 0; i--) {
        	//reset swap flag 
        	swapFlag = false;
            for (int j = 0; j < i ; j++) {
                if (alist[j].compareTo(alist[j + 1]) < 0) {
                    swap(alist, j, j+1);
                    swapFlag = true;
                }
            }
            if (swapFlag == false) {
            	return;
            }
        }
	}
	
	
	/**
	 * Method: searchSort()
	 * Function: sorts an array by finding largest value and putting it to the end. 
	 * @param list[] alist
	 */
	
	public static void searchSort(Comparable[] alist) {
		int maxIndex;
		int n = alist.length;
		Boolean swapFlag = false;
		for(int i = n - 1; i>=0;i++) {
			maxIndex = 0;
			for(int j = 1; j<i;j++) {
				if(alist[j].compareTo(alist[maxIndex]) > 0) {
					maxIndex = j;
				}
				//swap the value with the max index value. 
				swap(alist, maxIndex, i);
				swapFlag = true;
			}
			if (swapFlag == false) {
            	return;
            }
			
		}
		
	}
	
	/**
	 * Method: insertionSort()
	 * Function: sorts a list of elements by placing each element where it fits among known elements
	 * @param alist[]
	 */

	public static <E extends Comparable<E>> void insertSort(Comparable<E>[] alist, int p, int q) {
		//make sure temp variable is equal to the generic cpmparable we have - have to extend comparable when actually defining element E
		E temp;

		for(int i = p + 1; i <= q; i++) {
			//set the temp variable equal to thing at position i
			temp = (E) alist[i];
			//set j equal to index position i
			int j = i;
			// want to compare when j is greater than 0 AND when temp value is less than j - 1
			while(j > p && (((E) temp).compareTo((E) alist[j-1]) > 0)) {
				//swap elements when this happens
				int x = j - 1;
				//order of swap doesnt matter, just swapping positions
				swap(alist, j, x);
				//reduce j
				j--;
			}
			
		}
	}
	
	/**
	 * Method: mergeSort()
	 * Function: takes an array and splits it until it is empty or only has one element - this one is recursive
	 * @param alist[], int p which is initially 0, int q which is size of array
	 */
	
	public static <E extends Comparable<E>> void mergeSort(Comparable<E>[] alist, int p, int q) {
		//first set the base case where p (low end) is greater than or equal to q (high end). This would only happen when there is 0 or 1 element in array
		if (p>=q) {
			return;
		}
		
		//set new int m as the total size of the provided array split in half
		int m = (p+q)/2;
		
		mergeSort(alist, p, m);
		mergeSort(alist, m+1, q);
		
		//combine the 2 sorted arrays
		merge(alist, p, m, q);
		
	}
	
	
	
	
	
	
	/**
	 * Method: merge()
	 * Function: takes 2 sorted arrays and merges them into one
	 */
	
	public static void merge(Comparable [] alist, int p, int m, int q) {		
		//set up slices of array based on the provided array and overwritewrite to the clone array
		//temp array to clone left side
		Comparable[] clone = new Comparable[alist.length];
		
		
		System.arraycopy(alist, p, clone, p, q - p + 1);
		
		//set right variable position, p and q are left and right
		int rgt = m+1;
		// set left pointer
		int lft = p;
		//set anchor point
		int i = p;

		//while indexed LEFT position is less than the middle value AND middle + 1 is right of the RIGHT position (whole right side of array)
		while (lft <= m && rgt <= q) {
			if(clone[lft].compareTo(clone[rgt]) >= 0) {
				alist[i++] = clone[lft++];
			}
			else {
				alist[i++] = clone[rgt++];
			}
							
		}
		 
		//this is where we take the whole left side of the array and copy it over 
		while (lft <= m) {
			alist[i++] = clone[lft++];
		}
		
		// copy remaining right
        while (rgt < q) {
        	alist[i++] = clone[rgt++];
        }
        
	
		
	}
	
	
	
	/**
	 * Method: timSort()
	 * Function: uses a combination of merge and insertion sort. Uses Insertion Sort if array size is <8 (of course the array size will eventualy be <8 because of merge sort breaking them down)
	 */
	
	//just call insertSort() when alist <8 and use merge sort otherwise?
	
	public static <E extends Comparable<E>> void timSort(Comparable<E>[] alist, int p, int q) {
		if(q-p < 8) {
			insertSort(alist, p, q);
			return;
		}
		//set new int m as the total size of the provided array split in half
		int m = (p+q)/2;
		
		timSort(alist, p, m);
		timSort(alist, m+1, q);
		
		//combine the 2 sorted arrays
		merge(alist, p, m, q);
		
		
		
	}
	
	
	
	/**
	 * Method: swap()
	 * Function: takes a list with two index values and swaps those values. Used by multiple sorting algorithms.
	 * @param <E>
	 * @param list
	 * @param i
	 * @param j
	 */
	
	public static <E> void swap(E[] list, int i, int j) {
		E temp = list[j];
		list[j] = list[i];
		list[i] = temp; //this just swaps their spots
	}
	
}


