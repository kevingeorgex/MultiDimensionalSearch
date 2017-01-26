/*
 * Author Kevin A George
 */

// Ver 0.1:  Sat, Feb 28.  Initial description.
// Ver 1.0:  Tue, Mar 03.  Added more comments.  Modified decreaseKey().

import java.lang.Comparable;
import java.util.Arrays;

public class PriorityQueueIndexed<T extends Comparable<? super T> & PQIndex> {
    T[] queue;
    int size =0;
   
    
    /** Build a priority queue with a given array q */
    PriorityQueueIndexed(T[] q) 
    {
    	queue =q;
    	buildHeap();		// Build the heap from an unsorted unordered array
    }

    /** Create an empty priority queue of given maximum size */
    PriorityQueueIndexed(int n) 
    {
    	queue = (T[])new Comparable[n+1];
    }

    void insert(T x) {
	add(x);
    }

   
    
    void add(T x) 
    {
    	if(queue.length<=size)  
    	{
    		queue=this.resize();
    	}
    		queue[0]=x;
    		int hole=size+1;
    		while(queue[hole/2].compareTo(x)<0)   // Moving the bigger elements down to hole 
    		{
    			queue[hole]=queue[hole/2];
    			hole=hole/2;
    		}
    		queue[hole]=x;
    		size++;
    }

    

    /*
    * Add more elements if queue is full
    */
    T[] resize() {
		// TODO Auto-generated method stub
		return Arrays.copyOf(queue, queue.length*2);
	}

    
    
    T remove()
   	{
         if(this.isEmpty())
         {
       	  try {
   				throw new Exception("Invalid Key");
   			} catch (Exception e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   	  }
         
         return deleteMax();
       }
    
   

    /*
	 *  Remove the minimum element which is the top most element of the heap.
	 */
   
    T deleteMax()
    {
	  T rv= queue[1];
	  queue[1]=queue[size];
	  size--;
	  
	  percolateDown(1);
      rv.putIndex(-1);
      return rv;
    }

    // checks whether the queue is empty or not
    boolean isEmpty()
    {
    	return size==0;
    }
    
    
    /*
     * Checks whether a particular element is present or not
     */
    Boolean contains(T x) {
		if (x.getIndex() == -1)
			return false;
		else
			return true;
	}
    
    /** restore heap order property after the priority of x has decreased */
    void decreaseKey(T x) 
    {
	percolateUp(x.getIndex());
    }

   
    /*
     * Return the minimum element which is the top most element
     */
    T max() 
    { 
	return queue[1];
    }

    /** Priority of element at index i of queue has decreased.  It may violate heap order.
     *  Restore heap property */
    void percolateUp(int i)
    {
    	T x=queue[i];
    	queue[0]=x;
    	int hole=i;
    	while(queue[hole/2].compareTo(x)<0)   // Moving the bigger elements down to hole 
		{
			queue[hole]=queue[hole/2];
			queue[hole].putIndex(hole);
			hole=hole/2;
		}
		queue[hole]=x;
		x.putIndex(hole);
    }

    /** Create heap order for sub-heap rooted at node i.  Precondition: Heap order may be violated 
     *  at node i, but its children are roots of heaps that are fine.  Restore heap property */
    void percolateDown(int i) 
    {
    	int child=0;
    	if(size < (2*i))   // no children,nothing to do
    		return;
    	else if(size == (2*i))   // one child,swap child if its smaller than element at i
    	{
    		if(queue[i].compareTo(queue[2*i])<0)
    		{
    			T temp=queue[i];				//swapping
    			queue[i]=queue[2*i];
    			queue[2*i]=temp;
    			queue[i].putIndex(i);
    			queue[2*i].putIndex(2*i);
    		}
    	
    	}
    	else if(size > (2*i))   // two children,swap the smallest one with element at i
    	{
    		
    		if(queue[2*i].compareTo(queue[2*i+1])<0)   // comparing two children to find which one is smallest
    			child=(2*i)+1;
    		else 
    			child=(2*i);

    		if(queue[i].compareTo(queue[child])<0)
    		{
    		T temp=queue[i];				// swapping the smallest child and ith element if its smaller than ith element
			queue[i]=queue[child];
			queue[child]=temp;
			
			queue[i].putIndex(i);				//updating index values
			queue[child].putIndex(child);
			percolateDown(child);
    		}
    	}
     }
    

    
    /** Create a heap.  Precondition: none.  Array may violate heap order in many places. */
    void buildHeap() 
    {
    	for(int i=(queue.length-1)/2;i>0;i--)
    		percolateDown(i);
    	size=queue.length-1;
    }
}
