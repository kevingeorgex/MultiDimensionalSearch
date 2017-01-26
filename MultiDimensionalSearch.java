/**
 * Code for Project 3. 
 * @author Kevin A George
 */

import java.util.*;
import java.io.*;


class Node implements Comparable<Node>, PQIndex {
	Long id;
	List<Integer> categories;
	Double amount;
	static final int NUM_CATEGORIES = 1000;
	int numberOfPurchases;
	

	// constructor
	Node() {
		id = 0L;
		categories = new ArrayList<Integer>();
		amount = 0.0;
	}

	// constructor
	Node(long id, List<Integer> alist) {
		this.id = id;
		this.categories = new ArrayList<Integer>();
		for (int x : alist) {
			categories.add(x);
		}
		this.amount = 0.0;
	}

	@Override
	public int compareTo(Node another) {
		return this.id.compareTo(another.id);

	}

	public boolean equals(Object obj) {
		Node n = (Node) obj;
		return id==n.id;
	}
	
	public String toString() {
		return "(" + id + "," + categories.toString() + "," + amount + ")";
	}

	@Override
	public void putIndex(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
}

public class MultiDimensionalSearch {
	static int[] categories;
	static final int NUM_CATEGORIES = 1000, MOD_NUMBER = 997;
	static int DEBUG = 9;
	private int phase = 0;
	private long startTime, endTime, elapsedTime;

	// Initialize Treeset
	static TreeMap<Long, Node> tree;   // Treeset for searching based on customer id
	static HashMap<Integer, TreeSet<Node>> hmap; // Hash Map for searching based on categories
	static TreeSet<Node> treesetAmount ; 	// Tree Set for calculating Range
	static HashMap<String, Integer> hashMapSame; // Hash Map for calculating same same

	public static void main(String[] args) throws FileNotFoundException {
		categories = new int[NUM_CATEGORIES];
		Scanner in;
		if (args.length > 0) {
			in = new Scanner(new File(args[0]));
		} else {
			in = new Scanner(System.in);
		}
		MultiDimensionalSearch x = new MultiDimensionalSearch();
		x.timer();
		tree = new TreeMap<>();
		hmap = new HashMap<>();
		hashMapSame = new HashMap<>();
		treesetAmount = new TreeSet<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				
				if(o1.amount>o2.amount) return -1;
				else if (o1.amount<o2.amount) return 1; 
				else 
					{	if(o1.id.equals(o2.id))
						 return 0;
					else if(o1.id>o2.id)
						return -1;
					else 
						return 1;
					}
			}
		}); 

		long rv = x.driver(in);
		System.out.println(rv);
		x.timer();
	}

	

	/**
	 * Read categories from in until a 0 appears. Values are copied into static
	 * array categories. Zero marks end.
	 * 
	 * @param in
	 *            : Scanner from which inputs are read
	 * @return : Number of categories scanned
	 */
	public static int readCategories(Scanner in) {
		int cat = in.nextInt();
		int index = 0;
		while (cat != 0) {
			categories[index++] = cat;
			cat = in.nextInt();
		}
		categories[index] = 0;
		return index;
	}

	public long driver(Scanner in) {
		String s;
		long rv = 0, id;
		int cat;
		double purchase;

		while (in.hasNext()) {
			s = in.next();
			if (s.charAt(0) == '#') {
				s = in.nextLine();
				continue;
			}
			if (s.equals("Insert")) {
				id = in.nextLong();
				readCategories(in);
				rv += insert(id, categories);
			} else if (s.equals("Find")) {
				id = in.nextLong();
				rv += find(id);
			} else if (s.equals("Delete")) {
				id = in.nextLong();
				rv += delete(id);
			} else if (s.equals("TopThree")) {
				cat = in.nextInt();
				rv += topthree(cat);
			} else if (s.equals("AddInterests")) {
				id = in.nextLong();
				readCategories(in);
				rv += addinterests(id, categories);
			} else if (s.equals("RemoveInterests")) {
				id = in.nextLong();
				readCategories(in);
				rv += removeinterests(id, categories);
			} else if (s.equals("AddRevenue")) {
				id = in.nextLong();
				purchase = in.nextDouble();
				rv += addrevenue(id, purchase);
			} else if (s.equals("Range")) {
				double low = in.nextDouble();
				double high = in.nextDouble();
				rv += range(low, high);
			} else if (s.equals("SameSame")) {
				rv += samesame();
			} else if (s.equals("NumberPurchases")) {
				id = in.nextLong();
				rv += numberpurchases(id);
			} else if (s.equals("End")) {
				return rv % 997;
			} else {
				System.out
						.println("Houston, we have a problem.\nUnexpected line in input: "
								+ s);
				System.exit(0);
			}
		}
		// This can be inside the loop, if overflow is a problem
		rv = rv % MOD_NUMBER;

		return rv;
	}

	public void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	public void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / "
				+ memAvailable / 1000000 + " MB.");
	}

	// Following functions need to be written
	
	/*
	 * add a new customer (with amount = 0) who
      is interested in the given set of categories.  Returns 1 if the
      operation was successful, and -1 if there is already another
      customer with the same id (in which case no changes are made).
      param
	 @param : id,array of categories of the customer
	 */ 
	int insert(long id, int[] categories) {
		Integer j = 0;
		String str;
		Integer value=1;
		List<Integer> alist = new ArrayList<Integer>(categories.length);
		for (int i : categories) {
			if (i != 0)
				{
				alist.add(i);
				}
			else
				break;
		}

		 
		
		/*
		 * Each element of tree map has key as id value and value as respective
		 * node object of that id value
		 */
		// Creating node class object with details as id,categories,amount

		Node node = new Node(id, alist);

		// checking whether tree already has this id value
		if (tree.get(node.id) != null) 
			{
			return -1;
			} 
		else {
			value =1;  // count of number of customers for same same
			tree.put(node.id, node); // Inserting the entry to tree map.
			treesetAmount.add(node);
			if(node.categories.size()>=5)  // if categories is less than 5 , don't have to put in hash map.
			{
				str = converttostring(alist);
		
				if(hashMapSame.get(str)!=null)   // hash map already contains the specific combination of categories
				{
					hashMapSame.put(str, hashMapSame.get(str)+1);
				}
			
				else                                 // new cmobination of categories
				{
					hashMapSame.put(str,value);
				}
			}
			for (Integer a : alist) {
				if (hmap.get(a) != null)							// Hash map doesn't have an entry for this category x
				{	
					TreeSet<Node> treeSet = hmap.get(a);			// creating an entry in hash map with key as category
					treeSet.add(node);
					
				} else {											// Hash map already has an entry
					TreeSet<Node> treeSet = new TreeSet<Node>(
							new Comparator<Node>() {

								@Override
								public int compare(Node o1, Node o2) {
							
									if(o1.amount>o2.amount) return -1;
									else if (o1.amount<o2.amount) return 1; 
									else 										
										{	if(o1.id.equals(o2.id))
											 return 0;
										else if(o1.id>o2.id)
											return -1;
										else 
											return 1;
										}
								}
							});

					treeSet.add(node);
					hmap.put(a, treeSet);
				}
			}

			return 1;
		}
	}
/*
 *  Function to convert the combination of category to a string
 *  @param: carraylist of categories
 */
	private String converttostring(List<Integer> alist) 
	{
		Collections.sort(alist);
		StringBuilder name = new StringBuilder();
		for (int x : alist) 
		{
		name =name.append(String.format("%04d", x));
		}
		return name.toString();
	}

/*
 * return amount spent by customer until now (or -1, if
      such a customer does not exist).
      @param: id value of customer.
 */
	int find(long id) {

		if (tree.get(id) != null) {
			Node node = tree.get(id);
			return node.amount.intValue();
		} else
			return -1;
	}
/*
 * delete customer's records from storage.  Returns the
      amount field of the deleted customer (or -1, if such an id did not exist).
 */
	int delete(long id) {

		if (tree.get(id) != null) {     //  node is present in tree
			Node node = tree.get(id);
			for(Integer x:node.categories)
			{
				TreeSet<Node> treeSet = hmap.get(x);
				treeSet.remove(node);
			}
			tree.remove(id);
			
			treesetAmount.remove(node);	
			String str =converttostring(node.categories);
			hashMapSame.put(str, hashMapSame.get(str)-1);
			
			return node.amount.intValue();

		} else									// No node in tree
			return -1;
	}
	
/*
 * given a category k, find the top three customers
      (in terms of amount spent) who are interested in category k.
      Returns the sum of the amounts of the top three customers,
      truncated to just dollars.
      @param: cat ( category value)
 */
	int topthree(int cat) {
		
		Double total=0.0;
		int count =0;
		TreeSet<Node> treeSet = hmap.get(cat);
		for (Node x : treeSet)
		{
	
			total = total + x.amount;
			count++;
			if (count >= 3)						// selecting only top 3 values,count variable maintained to keep the count 
				break;
		
		}
		return total.intValue();
	}

	/*
	 * Add new interests to the list of
      a customer's categories.  Some of them may already be in the
      list of categories of this customer.  Return the number of new
      categories added to that customer's record.
      @param: id,array of categories
	 */
	int addinterests(long id, int[] categories) 
	{
		String str;
		Integer value =1;
		boolean flag ;
		int count =0;
		if(tree.get(id)!=null)    // id present in tree
		{
			Node node = tree.get(id);
		if(node.categories.size()>=5)						// More than 5 categories
			{
				 str = converttostring(node.categories);  // Converting the combination for categories to string
			
				hashMapSame.put(str, hashMapSame.get(str)-1);  // Deleting the combination of categories from same same hash map
			}	
			for(int x:categories)
			{
			 if(x!=0)  // Categories size is defined as 999 which contains remaining zeros
			 {
					
				flag=false;			// To check whether the category is already in the customers node
				
				for(Integer y:node.categories)
				{
					if(y.equals(Integer.valueOf(x)))
					{
						 flag = true;
						 break;	
					}
				}
			  if(flag==false)   // New category for the customer
			  {
				  node.categories.add(Integer.valueOf(x));
				 if(hmap.get(x)!=null)        // category present in hash map
				 {
					 TreeSet<Node> treeSet = hmap.get(x);
					 treeSet.add(node);
					 
				 }
				 else    // New category all together among all customers
				 {
					 TreeSet<Node> treeSet = new TreeSet<Node>(
								new Comparator<Node>() {

									@Override
									public int compare(Node o1, Node o2)  {
										
										if(o1.amount>o2.amount) return -1;
										else if (o1.amount<o2.amount) return 1; 
										else 
											{	if(o1.id.equals(o2.id))
												 return 0;
											else if(o1.id>o2.id)
												return -1;
											else 
												return 1;
											}
									}
								});

						treeSet.add(node);
						hmap.put(Integer.valueOf(x), treeSet); 
				 }
				  count++;
			  }
			 } 
			 else
				 break;	//  End if which checks for zeros in categories  
			} // End for
			
			//  Updating hash map for same same function
		
			if(node.categories.size()>=5)
			{
			str = converttostring(node.categories);
			
			
			 if(hashMapSame.get(str)!=null)					// Combination of categories is already present
			 {
				hashMapSame.put(str, hashMapSame.get(str)+1);
			 } 
			
			 else											// New combination of categories
			 {
				hashMapSame.put(str,value);
			 }
			
			}
			return count;
		}
		else
		return -1;
	}

/*
 *  Remove some categories from the
      list of categories associated with a customer.  Return the
      number of categories left in the customer's record.
      It is possible that the user has no categories of interest after
      this step (if all his categories of interest are removed).
      @param: id,array of categories
 */
	int removeinterests(long id, int[] categories) {
		
		String str;
		Integer value =1;
		if(tree.get(id)!=null)  // id present in tree
		{
			Node node = tree.get(id);
			if(node.categories.size()>=5){
			 str = converttostring(node.categories);  // Converting the combination of categories to string
			hashMapSame.put(str, hashMapSame.get(str)-1);  // Deleting the combination of categories from same same hash map
			}			
			
			for(int x:categories)  // Remove each category in the category array from node
			{
				if((x!=0)&&(node.categories.contains(x)))  //  Else its remaining zeroes in category array.
				{
				  node.categories.remove(Integer.valueOf(x));
				  TreeSet<Node> treeSet = hmap.get(Integer.valueOf(x));
				  treeSet.remove(node);
				
				}
				else if(x==0)
					break;
			}
			
		//  Updating hash map for same same function
					str = converttostring(node.categories);
					
					
					if(hashMapSame.get(str)!=null)
					{
						hashMapSame.put(str, hashMapSame.get(str)+1);
					}
					
					else
					{
						hashMapSame.put(str,value);
					}
					
					
			
		return node.categories.size();
		}
		else
		return -1;
	}
	
/*
 *  update customer record by adding a
      purchase amount spent by a customer on our company's product.
      Returns the net amount spent by the customer after adding this
      purchase, truncated to just dollars (-1 if no such customer exists).
  @param: id,purchase(updated amount of customer)
 */
	int addrevenue(long id, double purchase) {
		
		Boolean flag =false;
		Node node = tree.get(id);
		
		for(Integer x:node.categories)
		{
			TreeSet<Node> treeset = hmap.get(x);
			treeset.remove(node);						// Node is removed from hash map
		}	
		
		treesetAmount.remove(node);
		
		node.amount = node.amount +(purchase); 			// Amount is updated
		flag =true;
			
		for(Integer x:node.categories)
		{												// Node is put back in
			TreeSet<Node> treeset = hmap.get(x);
			treeset.add(node);
		}		
		
    	treesetAmount.add(node);						// Tree set to maintain Range values 
		
		node.numberOfPurchases++;
		
		return node.amount.intValue();

	}

	// Following functions are optional for CS 4V95.019:
	
	/*
	 * number of customers whose amount is at least
      "low" and at most "high".
      @param: low,high
	 */
	int range(double low, double high) 
	{
		int count =0;
		for(Node x:treesetAmount)
		{
			if(x.amount>high)
				continue;
			else if(x.amount>=low && x.amount<=high)
			{
				count++;
			}
			else
				break;
		}
		
		return count; 
	}
/*
 * customers who have exactly the same set of 5 or
      more categories of interest.
 */
	int samesame() 
	{
		int count =0;
		for(int x:hashMapSame.values())
		{
			if(x>1)
			count=count+x;
			
		}
		return count;
	}
/*
 * The number of times customer has purchased
      products (i.e., number of calls to AddRevenue for this customer).
      Return -1 if no such customer exists.
      @param: id of customer
 */
	int numberpurchases(long id) {
		
		Node node = tree.get(id);
		return node.numberOfPurchases;
	}
}