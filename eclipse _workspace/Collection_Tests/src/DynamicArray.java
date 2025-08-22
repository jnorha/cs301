/**
 * Self-defined dynamic array (i.e. ArrayList) class
 * Updating 7/16 to fully delete deleted elements. not leave "null" in those index positions
 * @author Josh Daniels jndaniels@wisc.edu
 * @Created: 7/3/2025
 * @Revised: 7/16/2025
 */
import java.util.Arrays;

public class DynamicArray <T> {

	private T A[]; //array for storing the elements
	private int size; //actual number of elements in the dynamic array
	private int capacity; //A measure of capacity allocated for the array
	private final int DEFAULT_CAPACITY = 8;

	/**
	 * Default constructor, create an array of default capacity
	 */
	public DynamicArray() {
		size = 0;
		capacity = DEFAULT_CAPACITY;//at least the default capacity
		A = (T[]) (new Object[capacity]);
	}
	/**
	 * 	An overloaded constructor
	 * 	allow one to initialize the dynamic array to be longer than the default capacity
	 * @param initialSize
	 */
	public DynamicArray(int initialSize) {
		size = 0;
		capacity = initialSize>DEFAULT_CAPACITY?initialSize:DEFAULT_CAPACITY;//at least the default capacity
		A = (T[]) (new Object[capacity]);
	}
	

	/**
	 * Retrieve the array member at position index
	 * @param index
	 * @return A[index]
	 */
	public T access(int index) {
		if (index >= size || index <  0)
			throw new ArrayIndexOutOfBoundsException();
		return A[index];
	}

	/**
	 * Set the element at position index
	 * @param index - position in the array
	 * @param element - the element to be set
	 */
	public void update(int index, T element) {
		if (index >= size || index <  0)
			throw new ArrayIndexOutOfBoundsException();
		A[index] = element;
	}
	
	/**
	 * Replace all elements in the array that equal origin with "replacement"
	 * @param origin - the original element in the array
	 * @param replacement - the new element to replace the original element
	 * @return number of elements that have been replaced
	 */
	public int replaceAll(T origin, T replacement) {// complete this method
		//set up counter for number of things replaced
		int counter = 0;
		//for loop to run through generic array A
		for(int i=0;i<size;i++) {
			if(A[i]==origin) {
				A[i] = replacement;
				counter++;
			}
			else {
				//do nothing to non-matches
			}
		}
		//return number of things replaced - could make note here if nothing is replaced? Maybe add print statement to let them know this number is things replaced? 
		return counter;
	}

	/**
	 * Delete all elements in the array that equal to the specified element
	 * @param toDelete - the element to be deleted
	 * @return number of elements that have been deleted
	 */
	public int deleteAll(T toDelete) { // complete this method
		//set up counter for number of things replaced
		int counter = 0;
		T copy[] = (T[])(new Object[capacity]);;
		//for loop to run through generic array A
		for(int i=0;i<size;i++) {
			if(A[i]==toDelete) {
				//A[i] = replacement;
				deleteAfter(i-1);
				counter++;
			}
			else {
				//build new array
				A[i] = A[i];
			}
		}
		//re-establish A as elements without the removed value
		//A = Arrays.copyOf(copy, capacity);
		//return number of things replaced - could make note here if nothing is replaced? Maybe add print statement to let them know this number is things replaced? 
		return counter;
	
	}

	/**
	 * Insert a specified element after position index
	 * @param index - the position after which a element is inserted
	 * @param element
	 */
	public void insertAfter(int index, T element) { // complete this method
		//first make sure int index is within bounds
		//System.out.println("The length of A is: "+A.length);
		
		if (index < -1 || index >= capacity) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		//see if we need to double capacity
		//System.out.println("The capacity of A is: "+capacity);
		//System.out.println("The size of A is: "+size);
		if (size>=capacity) {
			reSize();
		}
		//System.out.println("The size of A after a resize is: "+size);
		
		//move everything up to but including index over
		for (int i = size; i > index + 1; i--) {
			
			//System.out.println("Index value is:"+i+" length of A is: "+A.length);
			A[i] = A[i-1];
			
		}
		A[index+1]=element;
		size++;


		//System.out.println("Current index size is:" + size);
		
	}
	
	/**
	 * Insert a specified element as the end of the array. Resize the array 
	 * 	if necessary 
	 * @param element - the element to be added as a node into the linked list
	 */
	public void insertAtEnd(T element) { // complete this method
		//System.out.println("A size is: "+size);	
		if (size>=capacity) {
			reSize();
		}
		
		A[size] = element;
		size++;
	}
	
	/**
	 * Delete a member that was after position index
	 * @param index - the position after which a member is deleted
	 */
	public void deleteAfter(int index) { // complete this method
		T copy[] = (T[])(new Object[capacity]);
		//for loop to run through generic array A
		for(int i=0;i<index;i++) {
			A[i]=A[i];
		}
		for(int j=index+1;j<size;j++) {
			A[j]=A[j+1];
		}
		size--;
		//re-establish A as elements without the removed value
		//A = Arrays.copyOf(copy, capacity);
	}

	/**
	 * Delete the last member of the array - if there is one
	 * @return the element deleted if successful; otherwise return null
	 */
	public T deleteLast() { 
		T copy[] = (T[])(new Object[capacity]);
		//for loop to run through generic array A
		T lastElement = A[size-1];
		size--;
		return lastElement;
		/*
		for (int i=0; i<size;i++) {
			copy[i] = A[i];
		}
		A = Arrays.copyOf(copy, capacity);
		if (lastElement!=null) {
			return lastElement;
		}
		else {
			return null;
		}
		*/
		
	}

	/**
	 * Retrieve the size of the array
	 * @return the number of elements in the array
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Retrieve the capacity allocated for the array
	 * @return the maximum number of elements that could be hosted in the array
	 */
	public int getCapacity() {
		return this.capacity;
	}
	/**
	 * Resize the array to twice the size when the currently allocated array is full
	 */
	public void reSize() {// complete this method
		capacity = capacity * 2;
		
		//keep generic array A as a copy of A but with new capacity
		A = Arrays.copyOf(A, capacity);
	}

	/**
	 * Return all members in the format of a string
	 */
	public String toString() {
		return Arrays.toString(Arrays.copyOfRange(A, 0, size));
	}
}
