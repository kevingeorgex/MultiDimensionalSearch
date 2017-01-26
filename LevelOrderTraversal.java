/*Author Kevin A George
Program to print the nodes of a binary search tree in level order */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


public class LevelOrderTraversal
 {

/* This function prints the nodes in tree in level order using a queue. Queue is implemented using a LinkedList
 * 	@param = root node of tree
 */
	public static void printLevelOrder(BST<Integer> p) 
	{
		int height,level = 0;      		
		int prevheight=0;
		LinkedList<BST> list = new LinkedList<BST>();		// Linkedlist using which queue is implemented
		if(p==null)
			System.out.println("Empty tree");
		
		list.add(p);							// Each  root element is added to queue first
		while(!list.isEmpty())
		{
			p=list.pop();						// pulling elements from queue to print it.
			height = height(p);
												/* Elements are pulled out via queue in level order. In order to print in level order,height is 
												 * calculated and compared with previous height to check whether a level is jumped
												 */
			if(height!=prevheight)				
			{
				level++;
				System.out.println();
				System.out.println();
				System.out.println(" Nodes of level "+level+" are : ");
				System.out.print(" "+p.data);
				prevheight=height;
				
			}
			else
			{
				System.out.print(" "+p.data);
			}
			if(p.left!=null)					// Adding left and right child of node
				list.add(p.left);
			if(p.right!=null)
				list.add(p.right);
			
		}
		
    }
	
	/*This function calculates the 
	 * height of a node in tree recursively
	 * @param: Node of which height need to be calculated */
	
	public static int height(BST<Integer> node)
	{
		if (node == null)
			return 0;
		else
			return (1 + Math.max(height(node.left), height(node.right)));

	}
	
	
	/* This function builds the binary search tree from an array recursively 
	 * @param :array of elements from which tree is created,start index of array,end index of array
	 */
	public static <E extends Comparable<? super E>> BST<E> buildBinarySearchTree(E[] a,int p,int r)
	{
		
		int mid = (p+r)/2;
		
		if(p>r)                 // Base case 
			return null;
		else
		{
			BST<E> node = new BST<E>(a[mid]);      					 // Middle node of the array will be the root
			node.left= buildBinarySearchTree(a, p, mid-1);
			node.right= buildBinarySearchTree(a, mid+1, r);
			return node;
		}
		
	}
	
	public static void main(String args[])
	{
		Integer array[] = {4,6,2,8,3,1,20,14,17,5,11,10,9,7,12};		// Initial array which contains unsorted elements
		
		Arrays.sort(array);												// Sorting the array to build BST later
		
		BST<Integer> root= buildBinarySearchTree(array,0,array.length-1);		/* This function creates the binary search tree 
																			which will be traversed later in level order  */
		printLevelOrder(root);           
	
	}

}

/* Output:
> javac LevelOrderTraversal.java 
> java LevelOrderTraversal
 
 Nodes of level 1 are : 
 8

 Nodes of level 2 are : 
 4 12

 Nodes of level 3 are : 
 2 6 10 17

 Nodes of level 4 are : 
 1 3 5 7 9 11 14 20
*/


