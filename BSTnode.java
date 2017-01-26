//package DAY1;
//Vaishakhi Kulkarni
//vpk140230

public class BSTnode<E extends Comparable<? super E>> {
	E data;
	BSTnode<E> left, right;


	// parameterized constructor
	BSTnode(E currentNode) {
		data = currentNode;
		left = null;
		right = null;
	}

	// default constructor
	BSTnode() {
		data = null;
		left = null;
		right = null;
	}

}
