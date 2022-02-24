package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ArrayListSorter {
	
	private static int seed = 69420;
	private static int insertionThreshold = 2;
	private static int pivotType = 2;
	
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> array) {
		sort(array, 0, array.size() - 1);
	}
	
	private static <T extends Comparable<? super T>> void sort(ArrayList<T> array, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			
			// if the size is less than threshold, sort by insertion
			if (right - left <= insertionThreshold) {
				InsertionSorter.insertionSort(array);
			}
			else {
				// otherwise, sort each side by recursion
				sort(array, left, mid);
				sort(array, mid + 1, right);
			}
		
			merge(array, left, right, mid);
		
		}
	}
	
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> array, int left, int right, int mid) {
			
		// size of the left and right sides
		int size1 = mid - left + 1;
		int size2 = right - mid;
		
		// temporary arrays to hold the og array while merging
		ArrayList<T> tempLeft = new ArrayList<T>();
		ArrayList<T> tempRight = new ArrayList<T>();
		
		// fill temp arrays
		for (int i = 0; i < size1; i++) {
			tempLeft.add(array.get(left + i));
		}
		for (int i = 0; i < size2; i++) {
			tempRight.add(array.get(mid + i + 1));
		}
		
		int i = 0;
		int j = 0;
		int k = left;
		
		// sort the temp arrays into the og array
		while (i < size1 && j < size2) {
			if (tempLeft.get(i).compareTo(tempRight.get(j)) <= 0) {
                array.set(k, tempLeft.get(i));
                i++;
            }
            else {
                array.set(k, tempRight.get(j));
                j++;
            }
            k++;
		}
		
		// complete the og array
		while (j < size2) {
			array.set(k, tempRight.get(j));
            j++;
            k++;
        }
	}
	
	/*
	static <T extends Comparable<? super T>> int partition(ArrayList<T> arr, int low, int high)
	{
	     
	    // pivot
	    int pivot = high;
	     
	    // Index of smaller element and
	    // indicates the right position
	    // of pivot found so far
	    int i = (low - 1);
	 
	    for(int j = low; j <= high - 1; j++)
	    {
	         
	        // If current element is smaller
	        // than the pivot
	        if (arr.get(j).compareTo(arr.get(pivot)) < 0)
	        {
	             
	            // Increment index of
	            // smaller element
	            i++;
	            swap(arr, i, j);
	        }
	    }
	    swap(arr, i + 1, high);
	    return (i + 1);
	}
	 
	static <T extends Comparable<? super T>> void quickSort(ArrayList<T> arr, int low, int high)
	{
	    if (low < high)
	    {
	         
	        // pi is partitioning index, arr[p]
	        // is now at right place
	        int pi = partition(arr, low, high);
	 
	        // Separately sort elements before
	        // partition and after partition
	        quickSort(arr, low, pi - 1);
	        quickSort(arr, pi + 1, high);
	    }
	}
	*/
	
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> array) {
		//quickSort(array, 0, array.size() - 1);
		partition(array, 0, array.size() - 1);
	}
	
	private static <T extends Comparable<? super T>> void partition(ArrayList<T> array, int low, int high) {
		
		if (low < high) {
			int pivot = findPivot(array, low, high);
			int store = low - 1;
			
			// loop through first part
			for (int i = low; i <= high; i++) {
				if (array.get(i).compareTo(array.get(pivot)) <= 0) {
					store++;
					if (store == pivot) {
						pivot = i;
					}
					else if (i == pivot) {
						pivot = store;
					}
					swap(array, store, i);
				}
			}
			
			swap(array, store, pivot);
			
			partition(array, low, store - 1);
			partition(array, store + 1, high);
		}
	}
	
	private static <T extends Comparable<? super T>> void swap(ArrayList<T> array, int index1, int index2) {
		T temp = array.get(index1);
		array.set(index1, array.get(index2));
		array.set(index2, temp);
	}
	
	private static <T extends Comparable<? super T>> int findPivot(ArrayList<T> array, int low, int high) {
		int pivot = 0;
		
		switch (pivotType) {
		case 0:
			return low;
		case 1:
			int firstIndex = low;
			int middleIndex = (high + low) / 2;
			int lastIndex = high;
			
			ArrayList<T> arr = new ArrayList<T>();
			arr.add(array.get(low));
			arr.add(array.get((high + low) / 2));
			arr.add(array.get(high));
			
			InsertionSorter.insertionSort(arr);
			
			if (arr.get(1).equals(array.get(firstIndex))) {
				return firstIndex;
			}
			else if (arr.get(1).equals(array.get(middleIndex))) {
				return middleIndex;
			}
			else if (arr.get(1).equals(array.get(lastIndex))) {
				return lastIndex;
			}
			
		case 2:
			return low + (int) (Math.random() * (high - low));
		}
		
		return pivot;
	}
	
	public static ArrayList<Integer> generateAscending(int size) {
		ArrayList<Integer> ascending = new ArrayList<Integer>();
		
		for (int i = 1; i <= size; i++) {
			ascending.add(i);
		}
		
		return ascending;
	}
	
	public static ArrayList<Integer> generatePermuted(int size) {
		ArrayList<Integer> permuted = generateAscending(size);
		Random rand = new Random(seed);
		
		Collections.shuffle(permuted, rand);
		
		return permuted;
	}
	
	public static ArrayList<Integer> generateDescending(int size) {
		ArrayList<Integer> descending = new ArrayList<Integer>();
		
		for (int i = size; i > 0; i--) {
			descending.add(i);
		}
		
		return descending;
	}

}
