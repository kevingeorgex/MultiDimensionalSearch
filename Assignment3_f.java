//package DAY1;
//Vaishakhi Kulkarni
//vpk140230

import java.util.Arrays;
import java.util.Scanner;



public class Assignment3_f {

	// Function for preorder traversal
	public static void printLevelOrder(BSTnode<Integer> tree) {

		int h = height(tree); // Calculate the height
		int i = 1;
		while (i <= h) { // while it reach the height
			System.out.println("Nodes of level "+i+" are:");
			printLevelOrder(tree, i);
			System.out.println();
			i++;
		}

	}

	// Tree to build BST
	public static <E extends Comparable<? super E>> BSTnode<E> buildBST(
			E[] arr, int p, int r) {

		if (p > r) // to check whether p > r
			return null;

		int mid = (p + r) >>> 1; // find the mid of array
		BSTnode<E> node = new BSTnode<E>(arr[mid]); // create a node
		node.left = buildBST(arr, p, mid - 1); // move left side
		node.right = buildBST(arr, mid + 1, r); // move right side
		return node;
	}

	// Recursively call the question to display the elements every level
	public static void printLevelOrder(BSTnode<Integer> tree, int level) {
		if (tree == null)
			return;
		if (level == 1)
			System.out.print(tree.data + " ");
		else {
			printLevelOrder(tree.left, level - 1);
			printLevelOrder(tree.right, level - 1);
		}
	}

	// Calculate the tree height
	public static int height(BSTnode<Integer> tree) {
		if (tree == null)
			return 0;
		else
			return (1 + Math.max(height(tree.left), height(tree.right)));

	}

	public static void main(String args[]) {
		System.out.println("*****Level Order Traversal*****");
		System.out.println(" ");

		Integer arr[];
		// Enter the size of the LinkedList
		System.out.println("Enter the size of the Array");
		System.out.println(" ");

		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();

		arr = new Integer[size];
		// Enter the elements in any order
		System.out.println("Enter the elements");
		System.out.println(" ");

		int i = 0;
		while (i != size) {
			arr[i] = scan.nextInt();
			i++;
		}
		scan.close();

		//Sort the elements
		Arrays.sort(arr);

		// Build tree
		BSTnode<Integer> root = buildBST(arr, 0, size - 1);

		// Call the function for level order traversal
		printLevelOrder(root);

	}
}
