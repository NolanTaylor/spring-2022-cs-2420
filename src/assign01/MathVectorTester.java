package assign01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class assesses the correctness of the Vector class.
 * 
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a 
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true). 
 * 
 * @author Erin Parker & Nolan Taylor
 * @version January 13, 2022
 */
class MathVectorTester {
	
	private MathVector rowVec, rowVecTranspose, unitVec, sumVec, colVec, sumColVec, dotColVec, colVecTranspose;

	@BeforeEach
	void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][]{{3, 1, 2}});
		
		// Creates a column vector with three elements: 3.0, 1.0, 2.0
		rowVecTranspose = new MathVector(new double[][]{{3}, {1}, {2}});
		
		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][]{{1, 1, 1}});
		
		// Creates a row vector with three elements: 4.0, 2.0, 3.0
		sumVec = new MathVector(new double[][]{{4, 2, 3}});
		
		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}});
		
		// Creates a column vector with five elements: 4.0, 6.12, -7.1, 8.0, 56.0
		sumColVec = new MathVector(new double[][]{{4}, {6.12}, {-7.1}, {8}, {56}});
		
		// Creates a column vector with five elements: 1.0, 1.0, 3.5, -2.0, 5.0
		dotColVec = new MathVector(new double[][]{{1}, {1}, {3.5}, {-2}, {5}});
		
		// Creates a row vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVecTranspose = new MathVector(new double[][]{{-11, 2.5, 36, -3.14, 7.1}});
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void smallRowVectorEquality() {
		assertTrue(rowVec.equals(new MathVector(new double[][]{{3, 1, 2}})));
	}
	
	@Test
	void smallRowVectorInequality() {
		assertFalse(rowVec.equals(unitVec));
	}

	@Test
	public void createVectorFromBadArray() {
	  double arr[][] = {{1, 2}, {3, 4}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	void transposeSmallRowVector() {
		MathVector transposeResult = rowVec.transpose();
		assertTrue(transposeResult.equals(rowVecTranspose));
	}
	
	@Test
	public void addRowAndColVectors() {
	  assertThrows(IllegalArgumentException.class, () -> { rowVec.add(colVec); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	void addSmallRowVectors() {
		MathVector addResult = rowVec.add(unitVec);
		assertTrue(addResult.equals(sumVec));		
	}

	@Test
	void dotProductSmallRowVectors() {
		double dotProdResult = rowVec.dotProduct(unitVec);
		assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);		
	}
	
	@Test
	void smallRowVectorLength() {
		double vecLength = rowVec.magnitude();
		assertEquals(vecLength, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));		
	}
	
	@Test
	void smallRowVectorNormalize() {
		MathVector normalVec = rowVec.normalize();
		double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{3.0 / length, 1.0 / length, 2.0 / length}})));
	}
	
	@Test
	void smallColVectorToString() {
		String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1";
		assertEquals(resultStr, colVec.toString());		
	}

	@Test
	void smallColumnVectorEquality() {
		assertTrue(colVec.equals(new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}})));
	}
	
	@Test
	void smallColumnVectorInequality() {
		assertFalse(colVec.equals(rowVec));
	}
	
	@Test
	void createVectorFromEmptyArray() {
		double arr[][] = {};
		assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	}
	
	@Test
	void transposeSmallColumnVector() {
		MathVector transposeResult = colVec.transpose();
		assertTrue(transposeResult.equals(colVecTranspose));
	}
	
	@Test
	void addSmallColumnVectors() {
		MathVector addResult = colVec.add(sumColVec);
		assertTrue(addResult.equals(new MathVector(new double[][]{{-11 + 4}, {2.5 + 6.12}, {36 - 7.1}, {-3.14 + 8}, {7.1 + 56}})));
	}
	
	@Test
	void dotProductSmallColumnVectors() {
		double dotProdResult = colVec.dotProduct(dotColVec);
		assertEquals(dotProdResult, -11.0 * 1.0 + 2.5 * 1.0 + 36 * 3.5 + -3.14 * -2.0 + 7.1 * 5.0);		
	}
	
	@Test
	void smallColumnVectorLength() {
		double vecLength = colVec.magnitude();
		assertEquals(vecLength, Math.sqrt((-11.0) * (-11.0) + 2.5 * 2.5 + 36 * 36 + (-3.14) * (-3.14) + 7.1 * 7.1));
	}
	
	@Test
	void smallColumnVectorNormalize() {
		MathVector normalVec = colVec.normalize();
		double length = Math.sqrt((-11.0) * (-11.0) + 2.5 * 2.5 + 36 * 36 + (-3.14) * (-3.14) + 7.1 * 7.1);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{-11.0 / length}, {2.5 / length}, {36 / length}, {-3.14 / length}, {7.1 / length}})));
	}
	
	@Test
	void smallRowVectorToString() {
		String resultStr = "3.0 1.0 2.0";
		assertEquals(resultStr, rowVec.toString());
	}
	
	@Test
	void createVectorZeroRows() {
		double arr[][] = {{}, {1, 2}};
		assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	}
	
	@Test
	void createVectorZeroColumns() {
		double arr[][] = {{1, 2}, {}};
		assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	}
}
