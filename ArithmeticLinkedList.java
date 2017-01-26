import java.awt.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;


public class ArithmeticLinkedList {

	public static void main(String args[])

	{
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		LinkedList<Integer> k = new LinkedList<Integer>();
		k.add(4);
		k.add(5);
		k.add(6);
		
		LinkedList<Integer> result =new LinkedList<Integer>();
		int sizeofl = l.size();
		int sizeofk = k.size();
		
		Integer sum=0;
		Integer carry =0;
		
		for(int i=sizeofl-1;i>=0;i--)
		{
			sum=l.get(i)+k.get(i)+carry;
			if(sum>10)
			{
				sum=sum%10;
				carry=1;
			}
			else
				carry=0;
			result.add(sum);
			
		}
	
		Iterator<Integer> i1=result.iterator();
		while(i1.hasNext())
		{
			System.out.print(i1.next());
		}
	}
}
