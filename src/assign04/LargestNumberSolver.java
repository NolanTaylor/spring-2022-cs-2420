package assign04;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * An object that can find the largest possible number made from combining
 * integers in a given integer array. The object can also find the sum
 * of each largest number from and Kth largest from a list.
 * 
 * @author Nolan Taylor and Alex Brett
 *
 */

public class LargestNumberSolver {
	/**
	 * Sorting the array by insertion sort
	 * 
	 * @param <T>
	 * @param array to be sorted
	 * @param cmp comparator by which they should be organized
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		// for each value in the array after 0
        for (int i = 1; i < arr.length; i++) {
            T value = arr[i];
            
            int j = i - 1;
            
            // while the current value should come after i, shift j up by 1
            while (j >= 0 && cmp.compare(arr[j], value) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            
            // set j + 1 and loop again
            arr[j + 1] = value;
        }
    }
	
	/**
	 * Find the combination of integers in an array that makes the largest
	 * integer
	 * @param array to find largest integer from
	 * @return largest int in the form of BigInteger
	 */
	@SuppressWarnings("unchecked")
	public static BigInteger findLargestNumber(Integer[] arr) {
		Integer[] temp = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) 
			temp[i] = arr[i];
		
		insertionSort(temp, (o1, o2) -> {
			
			String combo1 = "" + o1 + o2; 
			String combo2 = "" + o2 + o1;
			
			int combo1IntEdition = Integer.parseInt(combo1);
			int combo2IntEdition = Integer.parseInt(combo2);
			
			if (combo1IntEdition > combo2IntEdition) {
				return -1;
			}
			else if (combo2IntEdition > combo1IntEdition) {
				return 1;
			}
			else {
				return 0;
			}
		
		});
		
		StringBuilder bigNumber = new StringBuilder();
		BigInteger finalInt;
		if (temp.length == 0) {
			finalInt = new BigInteger("0");
			return finalInt;
		}
		
		for(int i = 0; i < temp.length; i++) 
			bigNumber.append(temp[i]);
		
		finalInt = new BigInteger(bigNumber.toString());
		
		return finalInt;
	}
	
	/**
	 * Same as find largest number but will throw an exception if the value returned
	 * does no fit in an int
	 * 
	 * @param arr
	 * @return
	 * @throws OutOfRangeException
	 */
	@SuppressWarnings("unchecked")
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
		Integer[] temp = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) 
			temp[i] = arr[i];
		
		insertionSort(temp, (o1, o2) -> {
			
			String combo1 = "" + o1 + o2; 
			String combo2 = "" + o2 + o1;
			
			int combo1IntEdition = Integer.parseInt(combo1);
			int combo2IntEdition = Integer.parseInt(combo2);
			
			if (combo1IntEdition > combo2IntEdition) {
				return -1;
			}
			else if (combo2IntEdition > combo1IntEdition) {
				return 1;
			}
			else {
				return 0;
			}
		
		});
		
		StringBuilder bigNumber = new StringBuilder();
		
		int finalInt = 0;
		if(temp.length == 0) 
			return finalInt;
		
		for(int i = 0; i < temp.length; i++) 
			bigNumber.append(temp[i]);
				
		try {	
			finalInt = Integer.parseInt(bigNumber.toString());
		}
		catch (NumberFormatException e) {
			throw new OutOfRangeException("int");
		}
		
		return finalInt;
	}
	
	/**
	 * Same as find largest number but will throw an exception if the value returned
	 * does no fit in an long
	 * @param arr
	 * @return
	 * @throws OutOfRangeException
	 */
	@SuppressWarnings("unchecked")
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
		Integer[] temp = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) 
			temp[i] = arr[i];
		
		insertionSort(temp, (o1, o2) -> {
			
			String combo1 = "" + o1 + o2; 
			String combo2 = "" + o2 + o1;
			
			int combo1IntEdition = Integer.parseInt(combo1);
			int combo2IntEdition = Integer.parseInt(combo2);
			
			if (combo1IntEdition > combo2IntEdition) {
				return -1;
			}
			else if (combo2IntEdition > combo1IntEdition) {
				return 1;
			}
			else {
				return 0;
			}
		
		});
		
		StringBuilder bigNumber = new StringBuilder();
		long finalInt = 0;
		if(temp.length == 0) 
			return finalInt;
		
		for(int i = 0; i < temp.length; i++) 
			bigNumber.append(temp[i]);
			
		try {
			finalInt = Long.parseLong(bigNumber.toString());
		}
		catch (NumberFormatException e) {
			throw new OutOfRangeException("long");
		}
		
		return finalInt;
	}

	/**
	 * Sums the largest numbers that can be made from the given arrays
	 * DOES NOT ALTER THE ARRAYS
	 * 
	 * @param list of int[]
	 * @return is a sum
	 */
	public static BigInteger sum(List<Integer[]> list) {
		BigInteger finalSum = new BigInteger("0");
		List<BigInteger> listOfBiggest = new ArrayList<BigInteger>();
		
		for (Integer[] arr : list) 
			listOfBiggest.add(findLargestNumber(arr));
		
		for (BigInteger big : listOfBiggest) 
			finalSum = finalSum.add(big);
			
		return finalSum;
	}
	/**
	 * Finds the kth largest int that can be created from the given list of arrays
	 * 
	 * @param list of Integer[]
	 * @param kth largest to find
	 * @return the array from which the kth largest is made
	 * @throws IllegalArgumentException if k is not a valid index
	 */
	@SuppressWarnings("unchecked")
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
		BigInteger[] arrayOfBiggest = new BigInteger[list.size()];
		
		HashMap<BigInteger, Integer[]> map = new HashMap<BigInteger, Integer[]>();
		
		for (int i = 0; i < list.size(); i++) {
			arrayOfBiggest[i] = findLargestNumber(list.get(i));
			map.put(arrayOfBiggest[i], list.get(i));
		}
		
		insertionSort(arrayOfBiggest, (o1, o2) -> {
			return ((BigInteger) o2).compareTo((BigInteger) o1);
		});
		
		if (k >= list.size() || k < 0) 
			throw new IllegalArgumentException();
		
		BigInteger kthBiggest = null;
		
		try {
			kthBiggest = arrayOfBiggest[k];
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		
		Integer[] kthBiggestCorrespondingArray = (Integer[]) map.get(kthBiggest);
		
		return kthBiggestCorrespondingArray;
	}
	
	/**
	 * Find the Kth largest number from largest combinations in the
	 * list, but with java's sort method instead of insertion sort.
	 * 
	 * @param list
	 * @param k
	 * @return
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public static Integer[] findKthLargestButWithJavaSortInsteadOfOurInsertionSort(List<Integer[]> list, int k) throws IllegalArgumentException {
		BigInteger[] arrayOfBiggest = new BigInteger[list.size()];
		
		HashMap<BigInteger, Integer[]> map = new HashMap<BigInteger, Integer[]>();
		
		for (int i = 0; i < list.size(); i++) {
			arrayOfBiggest[i] = findLargestNumber(list.get(i));
			map.put(arrayOfBiggest[i], list.get(i));
		}
		
		Arrays.sort(arrayOfBiggest);
		
		if (k >= list.size() || k < 0) 
			throw new IllegalArgumentException();
		
		BigInteger kthBiggest = null;
		
		try {
			kthBiggest = arrayOfBiggest[k];
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		
		Integer[] kthBiggestCorrespondingArray = (Integer[]) map.get(kthBiggest);
		
		return kthBiggestCorrespondingArray;
	}
	
	/**
	 * Generates a list of arrays from a txt file such that each line
	 * corresponds to one array of integers separated by blank spaces,
	 * and returns an empty list if the file does not exist.
	 * @param filename
	 * @return a list of arrays
	 */
	public static List<Integer[]> readFile(String filename) {
		File file = new File(filename);
		List<Integer[]> list = new ArrayList<Integer[]>();
		
		try (Scanner fileScanner = new Scanner(file);) {
			while (fileScanner.hasNextLine()){
				String input = fileScanner.nextLine();
		        String listOfNums[] = input.split(" ");
		        Integer[] temp = new Integer[listOfNums.length];
		        for (int i = 0; i < temp.length; i++) {
		        	temp[i] = Integer.parseInt((listOfNums[i]));
		        }
		        list.add(temp);
			}
			
		}
		catch (IOException e) {
			return list;
		}
		return list;
	}
	
}
