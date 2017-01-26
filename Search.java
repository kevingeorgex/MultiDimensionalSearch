/** Implementation of binary search
 *  @author rbk
 */

import java.util.Comparator;
import java.util.Random;
import java.util.Arrays;
// We could have replaced the above import lines with just "import java.util.*;"

public class Search {

    private static int size = 2000000;
    private static int trials = 2000000;
    private static int phase = 0;
    private static long startTime, endTime, elapsedTime;

    // Linear search; inefficient
    public static int linearSearch(int[] A, int p, int r, int x) {
	for(int i=p; i<=r; i++) {
	    if(A[i] == x) {
		return i;
	    }
	}
	return -1;
    }

    /** Procedure to run binary search on a sorted array of integers
     * Return q in [p..r] with A[q] = x, and -1 if no such q exists
     * Runs in time O(log n), where n = r-p+1
     *
     * @param A : int array.  Precondition: A is sorted.
     * If A is not sorted, behavior of procedure is arbitrary
     * @param p : int : left index of subarray of A
     * @param r : int : right index of subarray of A
     * @param x : int : element being searched
     * @return index q in [p..r] with A[q] = x, or -1 if no such index exists
     */
    public static int recursiveBinarySearch(int[] A, int p, int r, int x) {
	// Compare middle element of A[p..r] to x to decide which half of the array to search
	if(p <= r) {
	    int q = (p+r)/2;
	    if(A[q] < x) {  // A[q] is too small, x is not in left half
		return recursiveBinarySearch(A, q+1, r, x);
	    } else if (A[q] == x) {  // x found
		return q;
	    } else { // A[q] > x, so x is not in the right half
		return recursiveBinarySearch(A, p, q-1, x);
	    }
	} else { // empty array, return -1
	    return -1;
	}
    }

    // Iterative version of binary search
    public static int iterativeBinarySearch(int[] A, int p, int r, int x) {
	while(p <= r) {
	    int q = (p+r)/2;
	    if(A[q] < x) {  // A[q] is too small, x is not in left half
		p = q+1;
	    } else if (A[q] == x) {  // x found
		return q;
	    } else { // A[q] > x, so x is not in the right half
		r = q-1;
	    }
	}
	// x not found, return -1
	return -1;
    }


    /** Implementation of Binary Search algorithm using generics.  Use on any type
     *  when a comparator is supplied.
     */
    public static<T> int  binarySearch(T[] A, int p, int r, T x, Comparator<? super T> c) {
	while(p <= r) {
	    int q = (p+r) >>> 1;
            int cmp = c.compare(A[q], x);
            if (cmp < 0) {
		p = q+1;
	    } else if (cmp == 0) {  // x found
		return q;
	    } else { // A[q] > x, so x is not in the right half
		r = q-1;
	    }
	}
	// x not found, return -1
	return -1;
    }

    /** Implementation of Binary Search algorithm using generics.  Use on any type that
     *  implements Comparable
     */
    public static<T extends Comparable<? super T>> int  binarySearch(T[] A, int p, int r, T x) {
	while(p <= r) {
	    int q = (p+r) >>> 1;
            int cmp = A[q].compareTo(x);
            if (cmp < 0) {
		p = q+1;
	    } else if (cmp == 0) {  // x found
		return q;
	    } else { // A[q] > x, so x is not in the right half
		r = q-1;
	    }
	}
	// x not found, return -1
	return -1;
    }

    public static void main(String[] args) {
	int succ;
	int[] arr = new int[size];
	Integer[] iarr = new Integer[size];
	Random rand = new Random();
	for(int i=0; i<size; i++) {
	    arr[i] = rand.nextInt(10*size);
	}
	Arrays.sort(arr);

	for(int i=0; i<size; i++) {
	    iarr[i] = new Integer(arr[i]);
	}

	int[] searchKey = new int[trials];
	for(int i=0; i<trials; i++) {
	    searchKey[i] = rand.nextInt(10*size);
	}

	/* Search for many random elements to find time taken */

	succ = 0;
	System.out.println("\nSearching for " + trials/1000 + " elements: linear search...");
	timer();
	for(int i=0; i<trials/1000; i++) {
	    int x = searchKey[i];
	    int q = linearSearch(arr, 0, size-1, x);
	    if(q != -1) { succ++; }
	}
	System.out.println("Successful searches: " + succ);
	timer();

	succ = 0;
	System.out.println("\nSearching for " + trials + " elements: recursive binary search...");
	timer();
	for(int i=0; i<trials; i++) {
	    int x = searchKey[i];
	    int q = recursiveBinarySearch(arr, 0, size-1, x);
	    if(q != -1) { succ++; }
	}
	System.out.println("Successful searches: " + succ);
	timer();

	succ = 0;
	System.out.println("\nSearching for " + trials + " elements: iterative binary search...");
	timer();
	for(int i=0; i<trials; i++) {
	    int x = searchKey[i];
	    int q = iterativeBinarySearch(arr, 0, size-1, x);
	    if(q != -1) { succ++; }
	}
	System.out.println("Successful searches: " + succ);
	timer();

	succ = 0;
	System.out.println("\nSearching for " + trials + " elements iteratively (generic)...");
	timer();
	for(int i=0; i<trials; i++) {
	    Integer x = new Integer(searchKey[i]);
	    int q = binarySearch(iarr, 0, size-1, x);
	    if(q != -1) { succ++; }
	}
	System.out.println("Successful searches: " + succ);
	timer();
    }

    public static void timer()
    {
        if(phase == 0) {
	    startTime = System.currentTimeMillis();
	    phase = 1;
	} else {
	    endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            System.out.println("Time: " + elapsedTime + " msec.");
            memory();
            phase = 0;
        }
    }

    public static void memory() {
        long memAvailable = Runtime.getRuntime().totalMemory();
        long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
        System.out.println("Memory: " + memUsed/1000000 + " MB / " + memAvailable/1000000 + " MB.");
    }
}
