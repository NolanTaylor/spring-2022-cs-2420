package assign05;

import java.util.ArrayList;
import java.util.List;

public class ArrayListSorterTimer {

	public static void main(String[] args) {
		
		int timesToLoop = 100;

		int incr = 1_000;
		
		System.out.println("Mergesort threshold 0");
		
		for(int probSize = 1_000; probSize <= 20_000; probSize += incr) {
			List<Integer[]> numberList = new ArrayList<Integer[]>();
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			long stopTime, midpointTime, startTime = System.nanoTime();

			while(System.nanoTime() - startTime < 1_000_000_000) { // empty block
				
			}

			// Collect running times.
			startTime = System.nanoTime();
			for(int i = 0; i < timesToLoop; i++) {
				ArrayListSorter.mergesort(ArrayListSorter.generatePermuted(probSize));
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for(int i = 0; i < timesToLoop; i++) {
				ArrayListSorter.generatePermuted(probSize);
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
