/*
 * Author Kevin A George
 * Program to implement cuckoo Hashing,which is a technique to avoid collisiion
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;


public class CuckooHashing {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CuckooHashing cook = new CuckooHashing();
		cook.doHashing();
		
	}

	void doHashing()
	{	
		int flag=0;						
		int threshold=13,counter=0;			// threshold is the max limit of iterations between first hash function and second hash function
		int size=20;
		Integer savevalue=0,savevalue1=0;
		Integer array[]=new Integer[size+1];		// Array which contains the elements
		HashSet<Integer> hs =new HashSet<Integer>();  // Hash set using which overflow table is maintained
		
		for(int i=30;i>0;i--)
		{
			counter=0;
			flag=0;
			int result = hashFunctionOne(i);
			
			
			// collision happens and pass it to second hash function
			if(array[result]!=null)
			{
				
				savevalue = array[result];		// Popping the Value from First field and inserting element there
				array[result] = new Integer(i);
				result = hashFunctionTwo(i);  		// calling second hash function
				counter++;						// incrementing counter value 
				
				while(array[result]!=null&&counter<=threshold)  // collision happened for second time and until we get a Free cell
				{
					 savevalue1 = array[result];	// Popping out value and inserting saved value 
					array[result] = savevalue;
					result = hashFunctionOne(i);  // calling First hash function
					counter++;
					
					if(array[result]!=null)  // Executes when collision happens for second time
					{
						savevalue = array[result];
						array[result] = savevalue1;	
						result= hashFunctionTwo(i);  // calling second hash function
						counter++;
					}
					else
					{
						array[result]= savevalue1;
						flag=1;							// Any time the element finds a space inside array,it breaks out of the loop
						break;
						
					}
						
				}	
				
			}
			else  // No collision at all case
			{
				array[result] = new Integer(i);
				flag=1;
			}
			
			if(flag==0&&counter<=threshold) // special case , works only if a collision happened only one time
				array[result]=savevalue;
		
			else if(flag==0&&counter>threshold)  // works if maximum attempts are violated where item is put in over flow table
			{
				Integer value = new Integer(savevalue);
				hs.add(value);                     // Overflow table
			}
		}
		
		System.out.println("The array values are ");
		System.out.println(Arrays.toString(array));  // Printing the array
		System.out.println("Overflow table contains");  // Printing the contents in over flow table
		for(Integer x: hs)
			System.out.print(x+" ");
	
	
	}

	
	// First hash Function
	int hashFunctionOne(int a)
	{
		Random rand = new Random();
		int result =a % (rand.nextInt(20)+1);
		return result;
	}

	
	//Second Hash function
	int hashFunctionTwo(int a)
	{
		int result = a % 20;
		return result;
	}
}

/*
Output
The array values are 
[1, 23, 16, 3, 10, 18, 11, 7, 20, 9, 27, 22, 12, 13, 14, 19, 17, 26, 24, 30, null]
Overflow table contains
2 4 21 5 6 8 25 28 29 15 
 * 
 * 
 */

