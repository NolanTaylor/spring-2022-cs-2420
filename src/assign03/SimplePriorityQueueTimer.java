package assign03;

import java.util.Random;

/**
 * This class records the time it takes to run the insert and findMax
 * functions of the SimplePriorityQueue class
 * 
 * @author Nolan Taylor and Alex Brett
 *
 */

public class SimplePriorityQueueTimer {

	public static void main(String[] args) {
		Random randomNumberGenerator = new Random();
		
		// Do 10000 lookups and use the average running time
		int timesToLoop = 10000;
		
		System.out.println("Insert times:");

		// For each problem size n . . .
		for(int n = 100000; n <= 2000000; n += 100000) {
			
			// Generate a new SimplePriorityQueue
			SimplePriorityQueue<Integer> q = new SimplePriorityQueue<Integer>();

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();

			for(int i = 0; i < timesToLoop; i++)
				// Insert a random number into the queue
				q.insert((int) Math.random());

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int i = 0; i < timesToLoop; i++) { // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}
		
		System.out.println("Find max times:");
		
		// For each problem size n . . .
		for(int n = 100000; n <= 2000000; n += 100000) {
			
			// Generate a new SimplePriorityQueue
			SimplePriorityQueue<Integer> q = new SimplePriorityQueue<Integer>();
			for (int i = 0; i < 20; i++) {
				q.insert((int) Math.random());
			}

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();

			for(int i = 0; i < timesToLoop; i++)
				// Find the max value in the queue
				q.findMax();

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int i = 0; i < timesToLoop; i++) { // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}
	}
}
