package assign03;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This object represents a Simple Priority Queue which stores a sorted list of
 * objects. The user can insert objects, find and delete the maximum object, find
 * the size of the array, and if it contains a specific object.
 * 
 * @author Nolan Taylor and Alex Brett
 *
 * @param <E>
 */

public class SimplePriorityQueue<E> implements PriorityQueue<E> {

	@SuppressWarnings("unchecked")
	private E[] array = (E[]) new Object[16];
	private boolean comparator = false;
	private Comparator functor;
	
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue () {
		
	}
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		comparator = true;
		functor = cmp;
	}
	
	//public boolean getComparator() {return comparator;}
	
	/**
	 * This function find the item of maximum value in the queue.
	 * 
	 * @returns E the greatest item (last in the list)
	 * 
	 */
	@Override
	public E findMax() throws NoSuchElementException {
		
		if (size() == 0)
			throw new NoSuchElementException();

		return array[size()-1];
	}
	
	/**
	 * This function deletes the largest item in the queue
	 * 
	 * @returns the deleted item
	 */
	@Override
	public E deleteMax() throws NoSuchElementException {
		E temp = findMax();
		array[size() - 1] = null;
		return temp;

	}
	
	/**
	 * Uses a binary search algorithm to find the index for which
	 * a value should be inserted to maintain a sorted array.
	 * The index where the value is greater than the previous
	 * index and less than or equal to the current index.
	 * 
	 * @param item to insert into the array
	 * @return index where the item should be inserted
	 */
	@SuppressWarnings("unchecked")
	private int binarySearch(E item) {
		int max = size();
		int min = 0;
		if(size() == 0)
			return 0;
		
		while (min <= max) {
			int mid = (min + max)/2;
			
			if (array[mid] == null) {
				return mid;
			}
			
			if (!comparator) {
				if (((Comparable<? super E>) item).compareTo(array[mid]) == 0) {
					return mid;
				} else if (((Comparable<? super E>) item).compareTo(array[mid]) > 0) {
					min = mid + 1;
				} else if (((Comparable<? super E>) item).compareTo(array[mid]) < 0) {
					max = mid - 1;
				}
			} else {
				if (functor.compare(item, array[mid]) == 0) {
					return mid;
				} else if (functor.compare(item, array[mid]) > 0) {
					min = mid + 1;
				} else if (functor.compare(item, array[mid]) < 0) {
					max = mid - 1;
				}
			}
		}
		
		return min;
	}
	
	/**
	 * This function doubles the size of the array.
	 */
	private void doubleArray() {
		// If the capacity of the backing array will be exceeded, resize 
		// by doubling.
		
		@SuppressWarnings("unchecked")
		E[] temp = (E[]) new Object[array.length * 2];
		for(int i = 0; i < size(); i++)
			temp[i] = array[i];
		array = temp;
	}
	
	/**
	 * This function shifts every element of the array after the
	 * specified index down one.
	 * 
	 * @param index to start shifting from
	 */
	private void shiftArray(int index) {
		for (int i = array.length - 1; i > index; i--) {
			array[i] = array[i - 1];
		}
	}
	
	/**
	 * This function inserts an item into the array in the correct
	 * index as to maintain the sorted nature of the array.
	 * 
	 * @param E, the item to insert
	 */
	@Override
	public void insert(E item) {
		if (array.length == size()) {
			doubleArray();
		}
		
		int index = binarySearch(item);
	
		shiftArray(index);
		
		array[index] = item;
	}
	
	/**
	 * A function that inserts all elements of a collection.
	 * 
	 * @param the collection to insert
	 */
	@Override
	public void insertAll(Collection<? extends E> coll) {
		for (E item : coll) {
			insert(item);
		}
		
	}
	
	/**
	 * A function that uses a binary search algorithm to see if the
	 * array contains a specified value.
	 * 
	 * @return true if the array contains the value
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(E item) {
		int max = size() - 1;
		int min = 0;
		
		// runs a loop as long as the search can half the remaining distance
		// or the value is found
		while(min <= max) {
			int mid = (min + max)/2;
			if (!comparator) {
				if (((Comparable<? super E>) item).compareTo(array[mid]) == 0) {
					return true;
				} else if (((Comparable<? super E>) item).compareTo(array[mid]) > 0) {
					min = mid + 1;
				} else if (((Comparable<? super E>) item).compareTo(array[mid]) < 0) {
					max = mid - 1;
				}
			} else {
				if (functor.compare(item, array[mid]) == 0) {
					return true;
				} else if (functor.compare(item, array[mid]) > 0) {
					min = mid + 1;
				} else if (functor.compare(item, array[mid]) < 0) {
					max = mid - 1;
				}
			}
		}			

		return false;
	}
	
	/**
	 * Calculates the number of non-null elements in the array
	 * 
	 * @return the size of the array
	 */
	@Override
	public int size() {
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				return count;
			}
			count++;
		}

		return count;
	}
	
	/**
	 * Determines in the array is empty or not
	 * 
	 * @return true if the array is empty
	 */
	@Override
	public boolean isEmpty() {
		if (array[0] == null)
			return true;
		
		return false;
	}
	
	/**
	 * Empties the array, turns all elements to null
	 */
	@Override
	public void clear() {
		for (int i = 0; i < size() - 1; i++) {
			array[i] = null;
		}
	}
	
	/**
	 * Prints the array
	 */
	public void getList() {
		System.out.println(Arrays.toString(array));
	}
}
	