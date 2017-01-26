/*
 * Author Kevin A George
 * Program to compare the efficiency of various sorting algorithms,quick sort,merge sort and insertion sort
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class SortingComparison {

	private static int capacity = 8000000;
	private static int size = 1000000;
	private static long startTime, endTime, elapsedTime;
	private static int phase = 0;
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = new int[capacity];
		int arr1[]= new int[capacity];
		Integer array[] =new Integer[capacity];
		Integer array1[]=new Integer[capacity];
		Random rand = new Random();
		for(int i=0;i<capacity;i++)
		{
			arr[i]=rand.nextInt(size*10);  // Creating 4 different arrays for 4 algorithms to sort.
			arr1[i]=arr[i];
			array[i]= arr[i];
			array1[i]=arr[i];
		}

		Comparator<Integer> comp = new Comparator<Integer>() {
			public int compare(Integer d1, Integer d2) {
				return d1.compareTo(d2);
			}
		};
		
	System.out.println("Total number of elements is "+ capacity);
	
	System.out.print("Inbuilt Java sort: ");
	timer();
	Arrays.sort(arr);
	timer();
	System.out.print(" Quick sort: ");
	timer();
	quickSort(array, 0, array.length-1);
	timer();
	System.out.print(" Merge sort: ");
	timer();
	mergeSort(array1, 0, array1.length - 1, comp);
	timer();
	System.out.print(" Insertion sort: ");
	timer();
	insertionSort(arr1);
	timer();
	
	}
	
	/*
	 * Function which implements Quick sort 
	 * param: Array of numbers,starting index(p), end index(r) 
	 */
	
	public static <T extends Comparable<T>> void quickSort(T array[],int p,int r)
	{
		if (p < r)
		{
			int i = p, j = r;
			T x = array[(i + j) / 2];
			do 
			 {
				while (array[i].compareTo(x) < 0)
					i++;
				while (x.compareTo(array[j]) < 0)
					j--;

				if (i <= j) {
					T tmp = array[i];
					array[i] = array[j];
					array[j] = tmp;
					i++;
					j--;
				}

			} while (i <= j);

			quickSort(array, p, j);
			quickSort(array, i, r);
		}
	}		
	
/*
 * Function which implements Merge sort 
 * param: Array of numbers,starting index(l), end index(r) and comparator
 */
	private static <E> void mergeSort(E[] a, int l, int r,Comparator<? super E> comp) 
	{
		if (l == r)
			return;
		int mid = (l + r) / 2;
		// Sort the first and the second half
		mergeSort(a, l, mid, comp);
		mergeSort(a, mid + 1, r, comp);
		merge(a, l, mid, r, comp);
	}

/*	Merge sort splits the array and merges later
 * param : Array of numbers,starting index(p),middle index(q) end index(r) and comparator
 */
	private static <E> void merge(E[] a, int p, int q, int r,Comparator<? super E> comp) 
	{
		int n = r - p + 1;
		Object[] values = new Object[n];

		int startindex = p;

		int middleindex = q + 1;

		int index = 0;

		while (startindex <= q && middleindex <= r) {
			if (comp.compare(a[startindex], a[middleindex]) < 0) {
				values[index] = a[startindex];
				startindex++;
			} else {
				values[index] = a[middleindex];
				middleindex++;
			}
			index++;
		}

		while (startindex <= q) {
			values[index] = a[startindex];
			startindex++;
			index++;
		}
		while (middleindex <= r) {
			values[index] = a[middleindex];
			middleindex++;
			index++;
		}

		for (index = 0; index < n; index++)
			a[p + index] = (E) values[index];
	}
	
	/*
	 * Function to implement insertion sort
	 * param array a
	 */
	public static void insertionSort(int[] a)
	{
		int x,j;
		for(int i=1;i<a.length;i++)
		{
			x=a[i];
			j=i;
			while(j>0 && a[j-1]>x)
			{
				a[j]=a[j-1];
				j=j-1;
				a[j]=x;
					
			}
		}	
	}
	public static void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " milli sec.");
			//memory();
			phase = 0;
		}
	}
	
}
