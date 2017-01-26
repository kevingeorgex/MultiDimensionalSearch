/** CS 4V95.001, 5V81.001.  Java example
    @author rbk
*/

import java.util.*;

public class Example implements Iterable<Item> {
    private static int twom = 2000000;
    private static int trials = 2000000;
    private static int ten = 10;
    boolean evens;
    Item[] arr;
    int size;

    /** Constructor
     *  @param x : int : value of element
     */
    Example(int s) {
	size = s;
	arr = new Item[size];
	evens = true;
    }


    public Iterator<Item> iterator() { return new ExampleIterator<>(); }

    /** Iterator that iterates over the even indexed elements or odd indexed elements
     *  of the array "arr", depending on the value of boolean "evens"
     *  Normally we would use "Item" instead of "T" in the following class
     */
    private class ExampleIterator<T> implements Iterator<T> {
	int index;  // stores the state of the iterator
	ExampleIterator() {
	    if(evens) {
		index = 0;
	    } else {
		index = 1;
	    }
	}

	public boolean hasNext() { return index < arr.length; }

	public T next() {
	    T rv = (T) arr[index];  // Casting needed due to type erasure
	    index += 2;
	    return rv;
	}
	public void remove() { throw new UnsupportedOperationException(); }
    }

    public static void main(String[] args) {
	Example x = new Example(twom);
	x.illustrateBinarySearch(twom);
	Example y = new Example(ten);
	y.illustrateIterator(ten);
    }

    private void illustrateBinarySearch(int size) {
	Random rand = new Random();
	for(int i=0; i<size; i++) {
	    arr[i] = new Item(rand.nextInt(10*size));
	}
	Arrays.sort(arr);

	Item[] searchKey = new Item[trials];
	for(int i=0; i<trials; i++) {
	    searchKey[i] = new Item(rand.nextInt(10*size));
	}

	int succ = 0;
	System.out.println("\nSearching for " + trials + " elements: binary search...");
	Search.timer();
	for(int i=0; i<trials; i++) {
	    Item x = searchKey[i];
	    int q = Search.binarySearch(arr, 0, size-1, x);
	    if(q != -1) { succ++; }
	}
	System.out.println("Successful searches: " + succ);
	Search.timer();
    }

    private void illustrateIterator(int size) {
	System.out.println("\nIterator illustration:");
	for(int i=0; i<size; i++) {
	    arr[i] = new Item(i);
	}

	System.out.println("Iterating with evens = true");
	for(Item x:this) { System.out.println(x); }

	System.out.println("\nIterating with evens = false");
	evens = false;
	for(Item x:this) { System.out.println(x); }
    }
}
