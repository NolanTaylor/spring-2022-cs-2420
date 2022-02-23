package assign05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayListSorterTester {
	
	private ArrayList<Integer> list = new ArrayList<Integer>();
	private ArrayList<Integer> sortedList = new ArrayList<Integer>();
	private ArrayList<Integer> emptyList = new ArrayList<Integer>();
	private ArrayList<String> stringList = new ArrayList<String>();
	private ArrayList<String> sortedStringList = new ArrayList<String>();
	private ArrayList<Integer> duplicateList = new ArrayList<Integer>();
	private ArrayList<Integer> sortedDuplicateList = new ArrayList<Integer>();

	
	@BeforeEach
	void setUp() throws Exception {
		list.add(5);
		list.add(2);
		list.add(4);
		list.add(3);
		list.add(7);
		list.add(1);
		list.add(6);
		
		stringList.add("yee haw");
		stringList.add("hee haw");
		stringList.add("nee naw");
		stringList.add("mee maw");	
		stringList.add("jee jaw");	
		stringList.add("fee faw");	
		stringList.add("sheeeeeeesh");	
		stringList.add("yoink");	
		stringList.add("skeetskeet");	
		stringList.add("crumpet");
		stringList.add("skadoooosh");
		stringList.add("juice");	
		stringList.add("p");	
		stringList.add("skreeeee");	
		stringList.add("mmmph");	
		stringList.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		stringList.add("(^*^&%$^");	
		stringList.add("");	
		stringList.add("____");	
		stringList.add("     ");	
		stringList.add("the fitness gram pacer test is a multistage aerobic capacity test");	
		stringList.add("sneepsnoop");
		
		duplicateList.add(1);
		duplicateList.add(7);
		duplicateList.add(3);
		duplicateList.add(3);
		duplicateList.add(1);
		duplicateList.add(3);
		duplicateList.add(7);
		
		sortedList.add(1);
		sortedList.add(2);
		sortedList.add(3);
		sortedList.add(4);
		sortedList.add(5);
		sortedList.add(6);
		sortedList.add(7);
		
		sortedStringList.add("");
		sortedStringList.add("     ");
		sortedStringList.add("(^*^&%$^");
		sortedStringList.add("____");
		sortedStringList.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		sortedStringList.add("crumpet");
		sortedStringList.add("fee faw");
		sortedStringList.add("hee haw");
		sortedStringList.add("jee jaw");
		sortedStringList.add("juice");
		sortedStringList.add("mee maw");
		sortedStringList.add("mmmph");
		sortedStringList.add("nee naw");
		sortedStringList.add("p");
		sortedStringList.add("sheeeeeeesh");
		sortedStringList.add("skadoooosh");
		sortedStringList.add("skeetskeet");
		sortedStringList.add("skreeeee");
		sortedStringList.add("sneepsnoop");
		sortedStringList.add("the fitness gram pacer test is a multistage aerobic capacity test");
		sortedStringList.add("yee haw");
		sortedStringList.add("yoink");
		
		sortedDuplicateList.add(1);
		sortedDuplicateList.add(1);
		sortedDuplicateList.add(3);
		sortedDuplicateList.add(3);
		sortedDuplicateList.add(3);
		sortedDuplicateList.add(7);
		sortedDuplicateList.add(7);

	}
	
	@Test
	public void testAscending() {
		ArrayList<Integer> ascending = ArrayListSorter.generateAscending(4);
		ArrayList<Integer> cmpAscending = new ArrayList<Integer>();
		cmpAscending.add(1);
		cmpAscending.add(2);
		cmpAscending.add(3);
		cmpAscending.add(4);
		
		assertEquals(cmpAscending, ascending);
	}
	
	@Test
	public void testPermuted() {
		ArrayList<Integer> permuted = ArrayListSorter.generatePermuted(4);
		ArrayList<Integer> cmpPermuted = new ArrayList<Integer>();
		cmpPermuted.add(1);
		cmpPermuted.add(2);
		cmpPermuted.add(3);
		cmpPermuted.add(4);
		
		assertTrue(!permuted.equals(cmpPermuted));
	}
	
	@Test
	public void testDescending() {
		ArrayList<Integer> descending = ArrayListSorter.generateDescending(4);
		ArrayList<Integer> cmpDescending = new ArrayList<Integer>();
		cmpDescending.add(4);
		cmpDescending.add(3);
		cmpDescending.add(2);
		cmpDescending.add(1);
		
		assertEquals(cmpDescending, descending);
	}
	
	/*

	@Test
	public void testSwap() {
		ArrayList<Integer> normal = new ArrayList<Integer>();
		normal.add(1);
		normal.add(2);
		normal.add(3);
		
		System.out.println(normal.toString());
		ArrayListSorter.swap(normal, 0, 2);
		System.out.println(normal.toString());
	}
	
	@Test
	public void testFindPivotMedian() {
		ArrayList<String> listie = new ArrayList<String>();
		listie.add("a");
		listie.add("c");
		listie.add("b");
		
		System.out.println(ArrayListSorter.findPivot(listie, 0, 2));
	}
	
	*/
	
	@Test
	public void testMergeSort() {
		ArrayListSorter.mergesort(list);
		assertEquals(sortedList, list);
	}
	
	@Test
	public void testMergeEmpty() {
		ArrayList<Integer> newMT = new ArrayList<Integer>();
		ArrayListSorter.mergesort(emptyList);
		assertEquals(newMT, emptyList);
	}
	
	@Test
	public void testMergeBigPermuted() {
		// make sure no errors are thrown
		ArrayListSorter.mergesort(ArrayListSorter.generatePermuted(1000));
	}
	
	@Test
	public void testMergeString() {
		ArrayListSorter.mergesort(stringList);
		assertEquals(sortedStringList, stringList);
	}
	
	@Test
	public void testMergeDuplicates() {
		ArrayListSorter.mergesort(duplicateList);
		assertEquals(sortedDuplicateList, duplicateList);
	}
	
	@Test
	public void testQuickSort() {
		ArrayListSorter.quicksort(list);
		assertEquals(sortedList, list);
	}
	
	@Test
	public void testQuickEmpty() {
		ArrayList<Integer> newMT = new ArrayList<Integer>();
		ArrayListSorter.quicksort(emptyList);
		assertEquals(newMT, emptyList);
	}
	
	@Test
	public void testQuickBigPermuted() {
		ArrayListSorter.quicksort(ArrayListSorter.generatePermuted(1000));
	}
	
	@Test
	public void testQuickString() {
		ArrayListSorter.quicksort(stringList);
		assertEquals(sortedStringList, stringList);
	}
	
	@Test
	public void testQuickDuplicates() {
		ArrayListSorter.quicksort(duplicateList);
		assertEquals(sortedDuplicateList, duplicateList);
	}

}
