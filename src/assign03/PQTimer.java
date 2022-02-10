package assign03;

import java.util.Random;

/**
 * This class collects running times for methods of SimplePriorityQueue.
 * 
 * @author Erin Parker
 * @version February 1, 2022
 */
public class PQTimer {
	
	public static void main(String[] args) {
		Random rng = new Random();

		int timesToLoop = 10000;

		int incr = 100000;
		for(int probSize = 100000; probSize <= 2000000; probSize += incr) {
			
			SimplePriorityQueue<Integer> myPQ = new SimplePriorityQueue<Integer>();

			for(int i = 0; i < probSize; i++) 
				myPQ.insert(i + 1);    // best case of insert: logN + 1 -> O(logN)

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			long stopTime, midpointTime, startTime = System.nanoTime();

			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Collect running times.
			startTime = System.nanoTime();
			for(int i = 0; i < timesToLoop; i++) {
				myPQ.insert(rng.nextInt(probSize));  // insert random value
				myPQ.deleteMax();  // remove an element from priority queue to keep size at N
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for(int i = 0; i < timesToLoop; i++) {
				rng.nextInt(probSize);
				myPQ.deleteMax();
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
			System.out.println(probSize + "  " + averageTime);
		}
	
		for(int probSize = 100000; probSize <= 2000000; probSize += incr) {
			
			SimplePriorityQueue<Integer> myPQ = new SimplePriorityQueue<Integer>();
	
			for(int i = 0; i < probSize; i++) 
				myPQ.insert(i + 1);    // best case of insert: logN + 1 -> O(logN)
	
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
	
			long stopTime, midpointTime, startTime = System.nanoTime();
	
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
	
			// Collect running times.
			startTime = System.nanoTime();
			for(int i = 0; i < timesToLoop; i++) {
				myPQ.findMax();  // find max value
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