/**
 *  The BST class
 *  @author:
 *  @Created: 8/6/2025
 *  @Revised:
 */

import java.util.Random;

//import java.util.*;
public class  BST {
	private TreeNode root;
	private int size;

	/**
	 * Constructor, create a BST with only one root node using the given element
	 */
	public BST(Comparable element) {
		TreeNode newNode = new TreeNode(element);
		this.root = newNode;
	}

	/*
	 * Implement the search, insert, and in-order traverse methods below
	 * */

//--// METHODS
	
	/**
	 * Method: search
	 * Function: searches through a binary search tree for value and returns true or false
	 * @param E element
	 */
	/*
	 * 
	 // this one doesn't work without helper
	public <E extends Comparable> Boolean  search(E element) {
		TreeNode current = this.root;
		//base case
		if (current==null) {
			return false;
		}
		if (element.compareTo(current.value) < 0) {
			search((current.left).value);
		}
		else if(element.compareTo(current.value) > 0) {
			search((current.right).value);
		}
		//didn't like my else statement here, so leaving it as true if it gets to this point but it SHOULDNT hit this one....
		System.out.println("Node didnt match anything and it just kept going...");
		return true;
	}
	*/
	
	public <E extends Comparable> Boolean  search(E element) {
		Boolean result = searchHelper(element, this.root);
		return result;
	}
	
	//now build out search helper which takes the element and applies it to this search tree
	private <E extends Comparable> Boolean searchHelper(E value, TreeNode subroot) {
		///base case
		if(subroot == null){
			return false;
		}

		int result = value.compareTo(subroot.value);
		if(result < 0)
			return searchHelper(value, subroot.left);
		else if (result > 0)
			return searchHelper(value, subroot.right);
		else
			return true;

	}
	
	
	/**
	 * Method: insert()
	 * Function: inserts an element where it should go in a binary tree
	 * @param E element
	 */
	
	public <E extends Comparable> Boolean insert(E element) {
		//outside case that the current tree is empty (i dont think that this one applies since its a BST method so it should already have at least a node from setter. Maybe it was deleted though idk so leaving in conditional
		if(this.root == null) {
			root = new TreeNode(element);
			return true;
		}
		
		else {
			//search through nodes for one without a child in the appropriate spot
			TreeNode current = this.root;
			//set null tree node that will serve as parent as we move through branches
			TreeNode parent = new TreeNode(null);
			//go through everything and find the right spot
			while(current!=null) {
				int result = element.compareTo(current.value);
				if (result < 0) {
					parent = current;
					current = current.left;
				}
				else if (result > 0) {
					parent = current;
					current = current.right;
				}
				// if it makes it past both of those it should be equal to the node meaning it's a duplicated value
				else {
					return false; 
				}

			}
			//while loop breaks when either left or right is null
			//create the new node and attach it to the parent
			if (element.compareTo(current.value) < 0 ) {
				parent.left = new TreeNode(element);				
			}
			else {
				parent.right = new TreeNode(element);
			}
			//if we get this far, element has been inserted
			System.out.println("Element" + element + " successfully inserted into tree right below "+ parent);
			return true;
		}
	}
}

	 
	



