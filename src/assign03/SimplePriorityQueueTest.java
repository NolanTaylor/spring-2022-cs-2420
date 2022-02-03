package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assign01.MathVector;

class SimplePriorityQueueTest {

	@SuppressWarnings("unchecked")
	private ArrayList<String> testArrLi = new ArrayList<String>();
	private SimplePriorityQueue<String> bigQ = new SimplePriorityQueue<String>();
	private SimplePriorityQueue<Integer> smallQ = new SimplePriorityQueue<Integer>();
	private SimplePriorityQueue<String> mTQ = new SimplePriorityQueue<String>();
	OrderedByMagnitude comparatorQ = new OrderedByMagnitude();
	private SimplePriorityQueue<Point> pointQ = new SimplePriorityQueue<Point>(comparatorQ);
	private ArrayList<Point> arrLiPoi = new ArrayList<Point>();


	
	@BeforeEach
	void setUp() throws Exception {		
		smallQ.insert(8);
		smallQ.insert(19);
		smallQ.insert(21);
		smallQ.insert(0);
		smallQ.insert(-2);
		smallQ.insert(7);
		smallQ.insert(34);
		smallQ.insert(-19);
		
		testArrLi.add("yee haw");
		testArrLi.add("hee haw");
		testArrLi.add("nee naw");
		testArrLi.add("mee maw");	
		testArrLi.add("jee jaw");	
		testArrLi.add("fee faw");	
		testArrLi.add("sheeeeeeesh");	
		testArrLi.add("yoink");	
		testArrLi.add("skeetskeet");	
		testArrLi.add("crumpet");	
		testArrLi.add("skadoooosh");	
		testArrLi.add("juice");	
		testArrLi.add("p");	
		testArrLi.add("skreeeee");	
		testArrLi.add("mmmph");	
		testArrLi.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		testArrLi.add("(^*^&%$^");	
		testArrLi.add("");	
		testArrLi.add("____");	
		testArrLi.add("     ");	
		testArrLi.add("the fitness gram pacer test is a multistage aerobic capacity test");	
		testArrLi.add("sneepsnoop");	
		
		bigQ.insertAll(testArrLi);
		
		pointQ.insert(new Point(3, 4));
		pointQ.insert(new Point(1, 7));
		pointQ.insert(new Point(1, -7));
		pointQ.insert(new Point(0, 7));

		arrLiPoi.add(new Point(2,7));
		arrLiPoi.add(new Point(1,23));
		arrLiPoi.add(new Point(7,8));
		arrLiPoi.add(new Point(8,9));
		arrLiPoi.add(new Point(4,2));
		arrLiPoi.add(new Point(5,6));
		arrLiPoi.add(new Point(0,3));
		arrLiPoi.add(new Point(1,0));
		arrLiPoi.add(new Point(3,9));
		arrLiPoi.add(new Point(9,7));

		
		smallQ.getList();
		bigQ.getList();
		pointQ.getList();
	}
	
	@Test
	void testSmallQInsert() {
		assertFalse(smallQ.contains(12));
		smallQ.insert(12);
		assertTrue(smallQ.contains(12));
	}
	
	@Test
	void testSmallQContains() {
		assertTrue(smallQ.contains(8));
	}
	
	@Test
	void testSmallSize() {
		assertEquals(8, smallQ.size());
	}
	
	@Test
	void testSmallFindMax() {
		assertEquals(34, smallQ.findMax());
	}
	
	@Test
	void testSmallDeleteMax() {
		assertEquals(34, smallQ.deleteMax());
		assertEquals(7, smallQ.size());
	}
	
	@Test
	void testSmallContains( ) {
		assertTrue(smallQ.contains(0));
		assertFalse(smallQ.contains(4));
	}
	
	@Test
	void testSmallisEmpty() {
		assertFalse(smallQ.isEmpty());
	}
	
	@Test
	void testSmallClear() {
		smallQ.clear();
		assertEquals(0, smallQ.size());
		assertTrue(smallQ.isEmpty());
	}
	
	@Test
	void testBigQInsertAll() {
		bigQ.insertAll(testArrLi);
		assertEquals(44, bigQ.size());
	}
	
	@Test
	void testBigQContains() {
		assertTrue(bigQ.contains("yee haw"));
		assertTrue(bigQ.contains("yoink"));
	}
	
	@Test
	void testBigQFindMax() {
		assertEquals("yoink", bigQ.findMax());
	}
	
	@Test
	void testBigQDeleteMax() {
		assertEquals(22, bigQ.size());
		assertEquals("yoink", bigQ.deleteMax());
		assertEquals(21, bigQ.size());
	}
	
	@Test
	void testBigQInsert() {
		assertFalse(bigQ.contains("yayoink"));
		bigQ.insert("yayoink");
		assertTrue(bigQ.contains("yayoink"));
	}
	
	@Test
	void testBigQSize() {
		assertEquals(22, bigQ.size());
	}
	
	@Test
	void testBigisEmpty() {
		assertFalse(bigQ.isEmpty());
	}
	
	@Test
	void testBigQClear() {
		bigQ.clear();
		assertEquals(0, bigQ.size());
		assertTrue(bigQ.isEmpty());
	}
	
	@Test
	void testPointQInsert() {
		int size = pointQ.size();
		pointQ.insert(new Point(5, 3));
		assertEquals(size + 1, pointQ.size());
	}
	
	@Test
	void testPointQInsertAll() {
		int size = pointQ.size();
		pointQ.insertAll(arrLiPoi);
		assertEquals(size + arrLiPoi.size(), pointQ.size());
	}
	
	@Test
	void testPointQContains() {
		Point temp = new Point(6, 9);
		pointQ.insert(temp);
		assertEquals(pointQ.findMax(), temp);
		assertTrue(pointQ.contains(temp));
	}
	
	@Test
	void testPointFindMax() {
		Point test = new Point(3, 17);
		pointQ.insert(test);
		assertEquals(test, pointQ.findMax());
	}
	
	@Test
	void testPointDeleteMax() {
		Point test = new Point(3, 17);
		pointQ.insert(test);
		assertEquals(test, pointQ.deleteMax());
		assertFalse(pointQ.contains(test));
	}
	
	@Test
	void testPointQisEmpty() {
		assertFalse(pointQ.isEmpty());
	}
	
	@Test
	void testPointQClear() {
		pointQ.clear();
		assertEquals(0, pointQ.size());
		assertTrue(pointQ.isEmpty());
	}
		
	@Test
	void testMTQSize() {
		assertEquals(0, mTQ.size());
	}
	
	@Test
	void testMTQContains() {
		assertFalse(mTQ.contains("look at me, i'm a string"));
	}
	
	@Test
	void testMTQFindMax() {
		assertThrows(NoSuchElementException.class, () -> { mTQ.findMax(); });
	}
	
	@Test
	void testMTQDeleteMax() {
		assertThrows(NoSuchElementException.class, () -> { mTQ.deleteMax(); });
	}
	
	@Test
	void testMTQisEmpty() {
		assertTrue(mTQ.isEmpty());
	}
	
	@Test
	void testMTQClear() {
		mTQ.clear();
		assertEquals(0, mTQ.size());
		assertTrue(mTQ.isEmpty());
	}
	
	@Test
	void testInsertDuplicate() {
		assertEquals(34, smallQ.findMax());
		assertEquals(8, smallQ.size());
		smallQ.insert(smallQ.findMax());
		assertEquals(9, smallQ.size());
		assertEquals(34, smallQ.findMax());
	}
	
	@Test
	void testDeleteMaxDuplicate() {
		smallQ.insert(smallQ.findMax());
		smallQ.deleteMax();
		assertEquals(34, smallQ.findMax());
		smallQ.deleteMax();
		assertEquals(21, smallQ.findMax());
	}
	
	@Test
	void testContainsDuplicate() {
		bigQ.insert("yee haw");
		assertTrue(bigQ.contains("yee haw"));
	}
	
}
