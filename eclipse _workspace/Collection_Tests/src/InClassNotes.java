import java.util.*;

public class inClassWork {
	public void addFirst(E e) {
		Node<E> newNode = new Node<> (e);
		newNode.next = head;
		head = newNode;
		size++;
		if(tail == null) {
			tail = head;
		}
	}
	
	public void addLast(E e) {
		Node<E> newNode = new Node<> (e);
		if(tail==null) {
			addFirst(e);
		}
		else {
			tail.next = newNode;
			tail = newNode;
		}
	}
	
	public E removeFirst() {
		if(size==0) {
			return null;
		}
		E removedElement = head.Element;
		head = head.next;
		if(head==null) {
			tail = head;
		}
		size--;
		return removedElement;
	}
	
	public E removedLast() {
		if(size == 0) {
			return null;
		}
		
		E removedElement = tail.Element;
		
		if(size == 1) {
			head = null;
			tail = null;
			size--;
		}
		Node<E> current = head;
		for(int i=1;i<size-1;i++) {
			current = current.next;
		}
		tail = current;
		size--;
		return removedElement;
	}
	
	public static void main(String[] args) {
		
	}
}
