/**
 * Self-defined LinkedList class
 * @author Josh Daniels
 * @Created: 7/14/2022 
 * @Revised: 7/14/2025
 */
public class LinkedList <T> {

	public ListNode head, tail;
	public int size;

	public LinkedList() {
		head = tail = null;
		size = 0;
	}
	
	/**	 Retrieve the node at the specific position of the linked list  
	 * @param index
	 * @return the node at the specific position
	 */	 
	public ListNode getNodeAt(int index) { //complete this
		if(index<0 || index>=size) {
			return null;
		}
		else if(index==0) {
			return head;
		}
		else {
			/*
			//I think the .get() function should work for linked lists.
			LinkedList.get(index);  - list might not be defined though?
			*/
			
			//Move through the list to locate node at index value and add after
			ListNode<T> current = head;
			for(int i = 1;i<=index;i++) {
				//extra check, it should pop out the node here and exit the program
				if(i==index) {
					return current;
				}
				current = current.next;
				//insert node at position after index (i==index)
			}
			//if it gets to here, no node at index value
			return null;
		}
			
		
	}

	
	
	/**
	 * Replace all elements in the array that equal origin with "replacement"
	 * @param origin - the original element in the array
	 * @param replacement - the new element to replace the original element
	 * @return number of elements that have been replaced
	 */
	public int replaceAll(T origin, T replacement) { // complete this method
		//use getNodeAt() to check value of node 
		//first check if index is within bounds
		int replaceCounter = 0;
		if(size==0) {
			return 0;
		}
		else {
			//start at head and move through all nodes
		
			ListNode<T> current = head;
			
			for (int i = 1;i<size;i++) {
				//move through all nodes
				if(current.value==origin) {
					insertAfter(i-1,replacement);
					deleteAfter(i-1);
					replaceCounter++;
					/*//I think this will work? may need to set current.next equal to the new 				 
					current = new ListNode<>(replacement);
					System.out.println(current);
					replaceCounter++;
					System.out.println(replaceCounter);
					*/
				}
				current = current.next;
			}
			if(tail.value==origin) {
				tail.value=replacement;
				replaceCounter++;
			}
			//doesnt actually need to change size at all since we are just replacing nodes
			return replaceCounter;
		}
	}

	/**
	 * Delete all elements in the array that equal to the specified element
	 * @param toDelete - the element to be deleted
	 * @return number of elements that have been deleted
	 */
	public int deleteAll(T toDelete) { // complete this method
		int deleteCount = 0;
		if(size==0) {
			return 0;
		}
		else {
			
			if(head.value==toDelete) {
				deleteHead();
				deleteCount++;
			}
			if(tail.value==toDelete) {
				deleteLast();
				size--;
				deleteCount++;
			}
			
			ListNode<T> current = head;
			//while(current != null && current.next!=null) {
			for (int i = 1;i<size;i++) {
				//move through all nodes
				if(current.next.value==toDelete) {
					//I think this will work? may need to set current.next equal to the new node
					//ListNode<T> willRemove = current;
					//current.next = willRemove.next;
					//current.next = current.next.next;
					//deleteAfter(i-1);
					deleteCount++;
					current.next = current.next.next;
					size--;
					//current.next = current.next.next;
				
				}
				else {
					current = current.next;
				}
				
			}
			/*
			 *  */
			
			return deleteCount;
		}
			
			//set node after deleted node to the one after current
			
	}
	/**
	 * Insert a node with the specified value after position index
	 * @param index
	 * @param value - the value to be added as a node into the linked list
	 */
	public void insertAfter(int index, T value) { // complete this method
		if(index == 0) {
			insertAtFront(value);
		}
		else if(index>=size) {
			insertAtEnd(value);
		}
		else {
			//Move through the list to locate node at index value and add after
			ListNode<T> current = head;
			for(int i = 1;i<=index;i++) {
				//insert node at position after index (i==index)
				if(i==index) {
					//set displaced node to node following one just inserted
					ListNode<T> temp = current.next;
					current.next = new ListNode<>(value);
					(current.next).next = temp;
					size++;
				}
				else {
					current = current.next;
				}	
				
			}
			
		}
	}

	/**
	 * Delete the node after the node at position indicated by index, note that
	 * 	head node is at  position 0.
	 * @param index
	 * @return the deleted node
	 */
	public ListNode deleteAfter(int index) { // complete this method
		//first check if index is within bounds
		if(index<0 || index>=size) {
			return null;
		}
		else if(index==0) {
			return deleteHead();
		}
		else if(index==size-1) {
			return deleteLast();
		}
		else {
			ListNode<T> current = head;
			for (int i = 1;i<=index;i++) {
				//move through all nodes
				current = current.next;
			}
			ListNode<T> toDelete = current.next;
			//set node after deleted node to the one after current
			current.next = toDelete.next;
			size--;
			return toDelete;
		}
	}


	/**
	 * Insert a node with the specified value as the new head node 
	 * @param value - the value to be added as a node into the linked list
	 */
	public ListNode insertAtFront(T value) {
		ListNode <T> newNode = new ListNode<> (value);
		if (size == 0) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		size++;
		return newNode;
	}
	/**
	 * Insert a node with the specified value as the new tail node 
	 * @param value - the value to be added as a node into the linked list
	 */
	public ListNode insertAtEnd(T value) {
		ListNode <T> newNode = new ListNode<> (value);
		if (size == 0) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		return newNode;
	}

	/**
	 * Delete the head node if there is one 
	 * @return the node at the head
	 */
	public ListNode deleteHead() { //Complete this
		if(size==0) {
			return null;
		}
		ListNode removedNode = head;
		head = head.next;
		if(head==null) {
			tail = head;
		}
		size--;
		return removedNode;

	}

	/**
	 * Delete the last (tail) node if there is one 
	 * @return the node at the tail
	 */
	public ListNode deleteLast() { //Complete this
		if(size == 0) {
			return null;
		}
		
		ListNode removedNode = tail;
		
		if(size == 1) {
			head = null;
			tail = null;
			size--;
		}
		ListNode<T> current = head;
		for(int i=1;i<size-1;i++) {
			current = current.next;
		}
		tail = current;
		size--;
		return removedNode;

	}


	/**
	 * Print out all members in the list
	 */
	void printList() {
		if (size == 0)
			System.out.println("[]");
		else {
			ListNode tmp = head;
			String output = "[";
			for (int i = 0; i < size - 1; i++) {
				output += tmp.value + " -> ";
				tmp = tmp.next;
			}
			output += tail.value + "]";
			System.out.println(output);
		}
	}

	/**
	 *  Retrieve the size of the linked list
	 * @return the total number of elements 
	 */
	public int getSize() {
		return size;
	}
}
