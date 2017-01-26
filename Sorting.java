import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Sorting {

	private static int size = 10000;
	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	public static void main(String[] args) {

		Random rand = new Random();
		int[] arr = new int[size];
		Integer[] iarr = new Integer[size];
		for (int i = 0; i < size; i++) {
			arr[i] = rand.nextInt(10 * size);
		}
		System.out.println("Number of elements: " + size);
		
		System.out.print("Library Array sort: ");
		timer();
		Arrays.sort(arr);
		timer();

		for (int i = 0; i < size; i++) {
			iarr[i] = new Integer(arr[i]);
		}

		Comparator<Integer> comp = new Comparator<Integer>() {
			public int compare(Integer d1, Integer d2) {
				return d1.compareTo(d2);
			}
		};

		System.out.print("Merge sort: ");
		timer();
		sortOneMergeSort(iarr, comp); 
		timer();

		System.out.print("Quick sort: ");
		timer();
		sortUsingQuickSort(iarr, 0, iarr.length - 1);
		timer();

		timer();
		System.out.print("Bubble sort: ");
		BubbleSort(arr);
		timer();

	/*	timer();
		bubbleSortGenric(iarr);
		System.out.print("genric bubble sort: ");
		timer();
		*/

	}
	//simple array of integers passed(non-genric)
	public static void BubbleSort(int[] arr) {
		int temp;
		for (int i = 0; i < arr.length - 1; i++) {

			for (int j = 1; j < arr.length - i; j++) {
				if (arr[j - 1] > arr[j]) {
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	//generic version
	public static <T extends Comparable<T>> void bubbleSortGenric(T[] arr) {
		T temp;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < (arr.length - i); j++) {
				if ((((Comparable) (arr[j]))
						.compareTo(arr[j + 1])) > 0) {
					temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	public static <T extends Comparable<T>> void sortUsingQuickSort(T[] arr,
			int l, int r) {
		if (l < r) {
			int i = l, j = r;
			T x = arr[(i + j) / 2];

			do {
				while (arr[i].compareTo(x) < 0)
					i++;
				while (x.compareTo(arr[j]) < 0)
					j--;

				if (i <= j) {
					T tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
					i++;
					j--;
				}

			} while (i <= j);

			sortUsingQuickSort(arr, l, j);
			sortUsingQuickSort(arr, i, r);
		}
	}

	public static <E> void sortOneMergeSort(E[] a, Comparator<? super E> comp) {
		mergeSort(a, 0, a.length - 1, comp);
	}

	private static <E> void mergeSort(E[] a, int l, int r,
			Comparator<? super E> comp) {
		if (l == r)
			return;
		int mid = (l + r) / 2;
		// Sort the first and the second half
		mergeSort(a, l, mid, comp);
		mergeSort(a, mid + 1, r, comp);
		merge(a, l, mid, r, comp);
	}

	private static <E> void merge(E[] a, int l, int mid, int r,
			Comparator<? super E> comp) {
		int n = r - l + 1;
		Object[] values = new Object[n];

		int fromValue = l;

		int middleValue = mid + 1;

		int index = 0;

		while (fromValue <= mid && middleValue <= r) {
			if (comp.compare(a[fromValue], a[middleValue]) < 0) {
				values[index] = a[fromValue];
				fromValue++;
			} else {
				values[index] = a[middleValue];
				middleValue++;
			}
			index++;
		}

		while (fromValue <= mid) {
			values[index] = a[fromValue];
			fromValue++;
			index++;
		}
		while (middleValue <= r) {
			values[index] = a[middleValue];
			middleValue++;
			index++;
		}

		for (index = 0; index < n; index++)
			a[l + index] = (E) values[index];
	}

	public static void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " nsec.");
			//memory();
			phase = 0;
		}
	}

	public static void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / "
				+ memAvailable / 1000000 + " MB.");
	}
}
