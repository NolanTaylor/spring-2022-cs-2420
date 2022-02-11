package assign04;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LargestNumberSolverTest {
	private List<Integer[]> numberList = new ArrayList<Integer[]>();
	private Integer[] mtArr = new Integer[0];

	@BeforeEach
	void setUp() throws Exception {	
		numberList = LargestNumberSolver.readFile("./src/assign04/integers.txt");
	}
	
	@Test
	void testFindLargestNumber() {
		BigInteger biggieInt = new BigInteger("8851");
		assertEquals(biggieInt, LargestNumberSolver.findLargestNumber(numberList.get(7)));
	} 
	
	@Test
	void testFindLargestNumberMT() {
		BigInteger biggieInt = new BigInteger("0");
		assertEquals(biggieInt, LargestNumberSolver.findLargestNumber(mtArr));
	} 
	
	@Test
	void testFindLargestInt() {
		assertEquals(73647, LargestNumberSolver.findLargestInt(numberList.get(73)));
	}
	
	@Test
	void testFindLargestIntMT() {
		assertEquals(0, LargestNumberSolver.findLargestInt(mtArr));
	} 
	
	@Test
	void testFindLargestIntFail() {
		assertThrows(OutOfRangeException.class, () -> { LargestNumberSolver.findLargestInt(numberList.get(0)); });
	}
	
	@Test
	void testFindLargestLong() {
		assertThrows(OutOfRangeException.class, () -> { LargestNumberSolver.findLargestLong(numberList.get(0)); });

	}
	
	@Test
	void testFindLargestLongButTooLongForInt() {
		long longBoi = 762344312111l;
		assertThrows(OutOfRangeException.class, () -> { LargestNumberSolver.findLargestInt(numberList.get(47)); });
		assertEquals(longBoi, LargestNumberSolver.findLargestLong(numberList.get(47)));

	}
	
	@Test
	void testFindLargestLongFail() {
		assertThrows(OutOfRangeException.class, () -> { LargestNumberSolver.findLargestLong(numberList.get(0)); });
	}
	
	@Test
	void testFindLargestLongMT() {
		assertEquals(0, LargestNumberSolver.findLargestInt(mtArr));
	} 
	
	@Test
	void testSum() {
		BigInteger biggieInt = new BigInteger("106593");
		List<Integer[]> biggieList = new ArrayList<Integer[]>();
		biggieList.add(numberList.get(7));
		biggieList.add(numberList.get(90));
		assertEquals(biggieInt, LargestNumberSolver.sum(biggieList));
	}
	
	@Test
	void testSumMT() {
		BigInteger biggieInt = new BigInteger("0");
		List<Integer[]> biggieList = new ArrayList<Integer[]>();
		biggieList.add(mtArr);
		biggieList.add(mtArr);
		biggieList.add(mtArr);
		assertEquals(biggieInt, LargestNumberSolver.sum(biggieList));
	}
	
	@Test
	void testkthLargest() {
		List<Integer[]> biggieList = new ArrayList<Integer[]>();
		biggieList.add(numberList.get(7));
		biggieList.add(numberList.get(90));
		Integer[] expectedArray = new Integer[] {88, 51};
		assertTrue(Arrays.equals(expectedArray, LargestNumberSolver.findKthLargest(biggieList, 1)));
	}
	
	@Test
	void testKthLargestThrowsNeg() {
		assertThrows(IllegalArgumentException.class, () -> { LargestNumberSolver.findKthLargest(numberList, -1); });
	}
	
	@Test
	void testKthLargestThrowsMax() {
		assertThrows(IllegalArgumentException.class, () -> { LargestNumberSolver.findKthLargest(numberList, 10000); });
	}
	
	@Test
	void testKthLargestMT() {
		List<Integer[]> biggieList = new ArrayList<Integer[]>();		
		assertThrows(IllegalArgumentException.class, () -> { LargestNumberSolver.findKthLargest(biggieList, 0); });
	}
	
	@Test
	void testfindLargestNumberUnaltered() {
		Integer[] arr = new Integer[] {1,2,3};
		Integer[] arr2 = new Integer[] {1,2,3};
		LargestNumberSolver.findLargestNumber(arr);
		for (int i = 0; i < arr2.length; i++) 
			assertTrue(arr[i] == arr2[i]);
	}
	
	@Test
	void testfindLargestIntUnaltered() {
		Integer[] arr = new Integer[] {1,2,3};
		Integer[] arr2 = new Integer[] {1,2,3};
		LargestNumberSolver.findLargestInt(arr);
		for (int i = 0; i < arr2.length; i++) 
			assertTrue(arr[i] == arr2[i]);
	}
	
	@Test
	void testfindLargestLongUnaltered() {
		Integer[] arr = new Integer[] {1,2,3};
		Integer[] arr2 = new Integer[] {1,2,3};
		LargestNumberSolver.findLargestLong(arr);
		for (int i = 0; i < arr2.length; i++) 
			assertTrue(arr[i] == arr2[i]);
	}
	
	/*@Test
	void testComparatorGreater() {
		assertEquals(-1, cmp.compare(7,  11));
	}
	
	@Test
	void testComparatorLess() {
		assertEquals(1, cmp.compare(12,  9));
	}
	
	@Test
	void testComparatorEquals() {
		assertEquals(0, cmp.compare(4,  44));
	}*/
	
}
