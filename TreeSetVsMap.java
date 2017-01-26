import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;


public class TreeSetVsMap {

	class doubleInteger implements Comparable<doubleInteger>
	{
		Integer key;
		Integer value;
		
		public doubleInteger(Integer a,Integer b) {
			key=a;
			value=b;
		}
	
		@Override
		public int compareTo(doubleInteger another) {
			// TODO Auto-generated method stub
			return this.key.compareTo(another.key);
		}

		public String toString() { return "(" + key + "," + value + ")"; }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TreeSetVsMap t1 = new TreeSetVsMap();
		t1.setvsMap();
			
		}
	

	void setvsMap()
	{
		TreeSet<doubleInteger> ts=new TreeSet<>();
		TreeMap<Integer, Integer> tm=new TreeMap<>();
		
		for(int i=29;i>=0;i--)
		{
			Integer key=new Integer(i%10);
			Integer value= new Integer(i);
			doubleInteger d1= new doubleInteger(key,value);
			ts.add(d1);
			tm.put(key,value);
			
		}
	System.out.println("Printing the contents of Treeset ");
		
	for(doubleInteger x: ts)
	
		System.out.print(x+" ");
	
	 System.out.println();
	
	 System.out.println("Printing the contents of Tree map");
	
	for(Map.Entry<Integer, Integer> x:tm.entrySet())
	System.out.print(" ("+x.getKey()+","+x.getValue()+") ");
	}
}
