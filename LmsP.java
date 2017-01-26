

import java.io.File;

import java.io.FileNotFoundException;

import java.util.Scanner;

/*
 * This program Implements Monotonic Sequence to gain maximum weight
 * by not considering penalties and considering penalties.
 * 
 * @author Shilpa Nagde
 * @author Vaishakhi Kulkarni
*/

public class LmsP
{

	public static void main(String[] args)
	{

		try

		{

			int j = 0;

			File file = new File("input.txt"); // Input File

			int[] var; // Array to store numbers

			int[] weight; // Array to store weights

			int soManyElements =0;

			int soManyPenalties =0;

			Scanner input = new Scanner(file);

			soManyElements = input.nextInt(); // No of Elements

			soManyPenalties = input.nextInt(); // No of Violations

			var = new int[soManyElements];

			weight = new int[soManyElements];

			System.out.printf("Total number %d \nTotal Penalty %d\n",soManyElements, soManyPenalties);

			while (input.hasNext()) 
				
				// Store the elements and weight in the corresponding array by scanning the input file.

			{

				var[j] = input.nextInt();

				weight[j] = input.nextInt();

				j++;

			}

			input.close(); // closing the file
			
			/*
			 * Function called to obtain monotonic sequence without violation
			 */
            
			System.out.println("LMS without considering penalties:");
			
			LMSwithoutviolations(var, weight, soManyElements, soManyPenalties); 

			/*
			 * Function called to obtain monotonic sequence with violation
			 */
			System.out.println(" ");
			System.out.println("LMS with considering penalties:");
			
			LMSwithviolations(var, weight, soManyElements, soManyPenalties);

		}
		
		/*
		Exception to handle if input file is not found in the system
		*/
		catch (FileNotFoundException ex) 

		{

			System.out.printf("ERROR: %s\n", ex);

		}

	}
    
	 /*
	  * Monotonic sequence without considering violations
	  * @param var[]         	 	Array to store the elements
	  * @param weight[]       		Array to store the weights 
	  * @param soManyElements 		Number of elements
	  * @param soManyPenalties      Number of Penalties
	  */
	public static void LMSwithoutviolations(int var[], int weight[],
			int soManyElements, int soManyPenalties)
	{

		int L[], M[], Use[];

		/* 
		 * To store the increasing monotonic sequence
		*/
		L = new int[soManyElements];

		Use = new int[soManyElements]; // For backtracking

		/* 
		 * Array to reverse the increasing monotonic sequence
		*/
		M = new int[soManyElements]; 

		if (soManyElements == 0) {

			System.out
					.println("The file is blank and cannot perform any operations");

		}

		else

		{
			/*
			 * Initializing the L[] array with integer minimum value
			 * Initializing the M[] array with zero
			*/

			for (int x = 0; x < soManyElements; x++)

			{

				L[x] = Integer.MIN_VALUE; 
				M[x] = 0;
			}

			L[0] = weight[0];			// Basecase

			Use[0] = 0;

		   /*
		    *To determine the increasing sequence by considering maximum weight
		    *and store the sequence in L[] while Use[] stores the index for back track
		   */
			for (int m = 1; m < soManyElements; m++)

			{

				Use[m] = m;

				L[m] = weight[m];

				for (int p = 0; p < m; p++)

				{

					if (var[p] < var[m] && L[m] < weight[m] + L[p])

					{

						L[m] = weight[m] + L[p];

						Use[m] = p;

					}

				}

			}

			int max = Integer.MIN_VALUE;

			int k = 0;

			/*
			 * To find maximum weight and store it in max
			 * Store the index in the variable k
			 */
			for (int m = 0; m < L.length; m++)

			{

				if (max < L[m])

				{

					max = L[m];

					k = m;

				}

			}
        
			System.out.println(" " + max);

			int m = k + 1;

			int n = k;
			int c = 0, count = 0;
			M[c] = m;

			Use[0] = 0;

			/* 
			 * To store the index of monotonic sequence in array M[] 
			 */
			while (k != Use[k]) 

			{

				m = Use[k] + 1;
				M[++c] = m;
				count++;
				k = Use[k];

			}

			int count1 = 0;
			count1 = count; // To store the length of monotonic sequence

			/*
			 * Display the index of monotonic elements
			 */

			for (int z = count; z >= 0; z--) 
			{
				System.out.print(" " + M[z]);
			}

			/* 
			 * To store the increasing sequence elements in array M[]
			 */

			k = n;
			c = 0;
			M[c] = var[k];
			
			while (k != Use[k])

			{
				M[++c] = var[Use[k]];
				k = Use[k];
		
			}

			/*
			 * To display the elements 
			*/ 
			System.out.println(" ");
			
			for (int z = count1; z >= 0; z--) 
			{
				System.out.print(" " + M[z]);
			}
		}

	}
	
	 /*
	  * Monotonic sequence with considering violations
	  * @param var[]         	 	Array to store the elements
	  * @param weight[]       		Array to store the weights 
	  * @param soManyElements 		Number of elements
	  * @param soManyPenalties      Number of Penalties
	  */
	
	public static void LMSwithviolations(int var[], int weight[],

	int soManyElements, int soManyPenalties)
	{

		int LMS[][], i, j, k;

		int Use[][];

		int Use_k[][];

		soManyPenalties = soManyPenalties + 1;

		int maxi = Integer.MIN_VALUE;                   

		/*
		 * Array to store monotonic sequence
		 */
		LMS = new int[soManyElements][soManyPenalties];

		/*
		 * Array used for back tracking by storing the elements 
		 */
		Use = new int[soManyElements][soManyPenalties];

		/*
		 * Array used for back tracking by storing the penalty value 
		 */
		Use_k = new int[soManyElements][soManyPenalties];

		/*
		 * Used to initializing the array LMS[],Use[][],Use_k[][]
		 */
		for (i = 0; i < soManyPenalties; i++)
		{

			for (j = 0; j < soManyElements; j++)
			{

				LMS[j][i] = Integer.MIN_VALUE; 

				Use[j][i] = 0;

				Use_k[j][i] = 0;

			}

		}

		/*
		 *To calculate maximum weight and to track the montonic sequence considering violations 
		 */
		for (j = 0; j < soManyPenalties; j++) 
		
		{

			LMS[0][j] = weight[0];

			for (i = 1; i < soManyElements; i++)
			{

				LMS[i][j] = weight[i];

				Use[i][j] = i;


				for (k = 0; k < i; k++)
				{
					/*
					 * When penalty = 0 used to calculate maximum weight
					 */

					if (var[k] < var[i] && LMS[i][j] < weight[i] + LMS[k][j])
					{

						LMS[i][j] = weight[i] + LMS[k][j];

						Use[i][j] = k;

						Use_k[i][j] = j;

					} 
					/*
					 * When penalty > 0 used to calculate maximum weight
					 */
					else if (var[k] >= var[i]
							&& j > 0

							&& LMS[i][j] < weight[i] + LMS[k][j - 1]
									- (var[k] - var[i]) * weight[i]) {

						LMS[i][j] = weight[i] + LMS[k][j - 1]
								- (var[k] - var[i]) * weight[i];

						Use[i][j] = k;

						Use_k[i][j] = j - 1;

					}

				}

			}

		}

		int row_index = 0, column_index = 0;

		/*
		 * Find maximum weight and its corresponding index values
		 */
		for (j = 0; j < soManyPenalties; j++)
		{

			for (i = 0; i < soManyElements; i++)
			{

				if (maxi < LMS[i][j]) {
					maxi = LMS[i][j];
					row_index = i;
					column_index = j;

				}


			}

		}

		System.out.println("" + maxi);

		/*
		 * To display the index of elements in an array
		 */
		String str = " ";
		int m = 0;
		int r = row_index;
		int c = column_index;
		while (Use[row_index][column_index] != row_index) 
		{

			str = row_index + 1 + " " + str;

			m = Use[row_index][column_index];

			column_index = Use_k[row_index][column_index];

			row_index = m;

		}

		str = row_index + 1 + " " + str;

		System.out.println(str);

		/*
		 * To display monotonic sequence elements considering violations 
		 */
		m = 0;

		str = "";
		row_index = r;
		column_index = c;

		while (Use[row_index][column_index] != row_index)
		{

			str = var[row_index] + " " + str;

			m = Use[row_index][column_index];

			column_index = Use_k[row_index][column_index];

			row_index = m;

		}

		str = var[row_index] + " " + str;

		System.out.println(str);

	}

}
