package assign04;

import java.util.ArrayList;
import java.util.List;

public class LNSTimer {

	public static void main(String[] args) {
		
		int timesToLoop = 10;

		int incr = 10_000;
		System.out.println("Our Insertion Sort");
		for(int probSize = 10_000; probSize <= 200_000; probSize += incr) {
			List<Integer[]> numberList = new ArrayList<Integer[]>();
			for (int i = 0; i < probSize; i++) {
				int size = (int)(Math.random() * 5);
				Integer[] steve = new Integer[size];
				for (int j = 0; j < size; j++) {
					steve[j] = (int) (Math.random() * 1_000);
				}
				numberList.add(steve);
			}
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			long stopTime, midpointTime, startTime = System.nanoTime();

			while(System.nanoTime() - startTime < 1_000_000_000) { // empty block
			}

			// Collect running times.
			startTime = System.nanoTime();
			for(int i = 0; i < timesToLoop; i++) {
				LargestNumberSolver.findKthLargest(numberList, 1); // best case of insert: logN + 1 -> O(logN)

			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for(int i = 0; i < timesToLoop; i++) {
				
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
			System.out.println(probSize + "  " + averageTime);
						
		}
	
		System.out.println("Java Sort");
		for(int probSize = 10_000; probSize <= 200_000; probSize += incr) {
			List<Integer[]> numberList = new ArrayList<Integer[]>();
			for (int i = 0; i < probSize; i++) {
				int size = (int)(Math.random() * 5);
				Integer[] steve = new Integer[size];
				for (int j = 0; j < size; j++) {
					steve[j] = (int) (Math.random() * 1_000);
				}
				numberList.add(steve);
			}
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			long stopTime, midpointTime, startTime = System.nanoTime();

			while(System.nanoTime() - startTime < 1_000_000_000) { // empty block
			}

			// Collect running times.
			startTime = System.nanoTime();
			for(int i = 0; i < timesToLoop; i++) {
				LargestNumberSolver.findKthLargestButWithJavaSortInsteadOfOurInsertionSort(numberList, 1); // best case of insert: logN + 1 -> O(logN)

			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for(int i = 0; i < timesToLoop; i++) {
				
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
			System.out.println(probSize + "  " + averageTime);
		
		}

	}
}
