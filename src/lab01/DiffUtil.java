package lab01;

/**
 * This class contains a utility method for finding the smallest difference.
 * 
 * @author Erin Parker & Nolan Taylor
 * @version January 21, 2022
 */
public class DiffUtil {

	/**
	 * Computes and returns the smallest difference (absolute value of subtraction) 
	 * among every pair of integers in the input array.
	 * @param arr - input array of integers
	 * @throws IllegalArgumentException - if the array contains less than two items
	 */
	public static int findSmallestDiff(int[] a) {
		if (a.length < 2)
			throw new IllegalArgumentException("Array must be > 1 element");
				
		int diff = a[0] - a[1];
				
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				int tmp_diff = a[i] - a[j];
						
				if (Math.abs(tmp_diff) < Math.abs(diff))
					diff = tmp_diff;
			}
		}
		
		return diff;
	}
}