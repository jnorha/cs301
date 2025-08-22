/**
 * Course: CS300 - Summer 2025
 * Program: MyQueue.java
 * 	Function: subclass of LinkedList that contains Queue type methods
 * Name: Josh Daniels
 * Wisc Email: jndaniels@wisc.edu
 * Date:	7/22/25
 * @param <E>
 */
import java.util.*;


public class MyQueue<T> extends LinkedList {
	
	/*
	private T first;
	*/
	
	
	//methods
	/**
	 * Method: add(E element)
	 * function: adds element at the back of the queue
	 * implements the insertAtEnd() inherited method
	 */ 
	public void add(T element) {
		insertAtEnd(element);	
	}
	
	/**
	 * Method: remove()
	 * function: removes the element from the front of the queue and returns it
	 * @returns element at front of queue
	 * implements the deleteHead() inherited method
	 */
	
	public T remove() {
		//typing might be wrong here. 
		T removed = (T) deleteHead().value;
		return removed;
	}
	
	/**
	 * Method: peek()
	 * function: returns the front element. Throws an exception if the queue is empty
	 * @returns element at front of queue
	 * implements the getNodeAt() inherited method
	 */
	
	public T peek() {
			if(isEmpty()) {
				throw new NoSuchElementException("Queue is Empty");
			}
			else{
				T front = (T) getNodeAt(0).value;
				return front;
			}
		
	}
	
	/**
	 * Method: isEmpty()
	 * function: returns the boolean true if the queue is empty
	 * @returns boolean true or false
	 * 
	 */
	
	public boolean isEmpty() {
		return (size<=0);
	}
	
	/**
	 * Method: size()
	 * function: returns the number of elements in the queue
	 * @returns int size
	 * 
	 */
	
	public int size() {
		return size;
	}
	

}
