package assign01;

/**
 * This class represents a simple row or column vector of numbers.
 * In a row vector, the numbers are written horizontally (i.e., along the columns).
 * In a column vector, the numbers are written vertically (i.e., along the rows).
 * 
 * @author Erin Parker & Nolan Taylor
 * @version January 13, 2022
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data;      
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;
	
	/**
	 * Creates a new row or column vector.
	 * For a row vector, the input array is expected to have 1 row and a positive number of columns,
	 * and this number of columns represents the vector's length.
	 * For a column vector, the input array is expected to have 1 column and a positive number of rows,
	 * and this number of rows represents the vector's length.
	 * 
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the input 2D array is not 
	 *         compatible with a row or column vector
	 */
	public MathVector(double[][] data) {
		if(data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if(data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");
		
		if(data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		}
		else if(data[0].length == 1) {
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		}
		else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");
		
		// Create the array and copy data over.
		if(this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for(int i=0; i < this.data.length; i++) { 
			for(int j=0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}
	
	/**
	 * Determines whether this vector is "equal to" another vector, where equality is
	 * defined as both vectors being row (or both being column), having the same 
	 * vector length, and containing the same numbers in the same positions.
	 * 
	 * @param other - another vector to compare
	 */
	public boolean equals(Object other) {
		if(!(other instanceof MathVector))
			return false;
		
		MathVector otherVec = (MathVector)other;
		
		// check if both vectors are row vectors, if their rows are the same length, and
		// if the rows have the same values in the same positions
		if (isRowVector && otherVec.isRowVector) {
			if (data[0].length == otherVec.data[0].length) {
				for (int i = 0; i < data[0].length; i++) {
					if (data[0][i] != otherVec.data[0][i]) {
						return false;
					}
				}
			}
		}
		// then do the same if the vectors are column vectors
		else if (!isRowVector && !otherVec.isRowVector) {
			if (data.length == otherVec.data.length) {
				for (int i = 0; i < data.length; i++) {
					if (data[i][0] != otherVec.data[i][0]) {
						return false;
					}
				}
			}
		}
		// otherwise, the vectors are not the same type, and return false
		else {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Generates a returns a new vector that is the transposed version of this vector.
	 */
	public MathVector transpose() {
		// create a new vector with flipped row and column length
		double[][] newData = new double[data[0].length][data.length];
		MathVector newVec = new MathVector(newData);
		
		// put the row of the vector into the column of the newVec
		// or the other way around if the vector is a column vector
		if (isRowVector) {
			for (int i = 0; i < data[0].length; i++) {
				newVec.data[i][0] = data[0][i];
			}
		}
		else {
			for (int i = 0; i < data.length; i++) {
				newVec.data[0][i] = data[i][0];
			}
		}
		
		return newVec;
	}
	
	/**
	 * Generates and returns a new vector representing the sum of this vector and another vector.
	 * 
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */
	public MathVector add(MathVector other) {
		// create a new vector to hold the sum of the current vectors
		double[][] newData = new double[data.length][data[0].length];
		MathVector newVec = new MathVector(newData);
		
		// check if the vectors are of the same type and length. then if so, add
		// them together and return the sum in newVec
		if (isRowVector && other.isRowVector) {
			if (data[0].length == other.data[0].length) {
				for (int i = 0; i < data[0].length; i++) {
					newVec.data[0][i] = data[0][i] + other.data[0][i];
				}
			}
			else {
				throw new IllegalArgumentException("The length of the rows do not match.");
			}
		}
		else if (!isRowVector && !other.isRowVector) {
			if (data.length == other.data.length) {
				for (int i = 0; i < data.length; i++) {
					newVec.data[i][0] = data[i][0] + other.data[i][0];
				}
			}
			else {
				throw new IllegalArgumentException("The lengths of the columns do not match.");
			}
		}
		else {
			throw new IllegalArgumentException("The vector types do not match.");
		}
		
		return newVec;
	}
	
	/**
	 * Computes and returns the dot product of this vector and another vector.
	 * 
	 * @param other - another vector to be combined with this vector to produce the dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */	
	public double dotProduct(MathVector other) {
		double dotProduct = 0.0;
		
		// checks if the vectors of are the same type and have the same respective lengths. then, if
		// so, calculate the dot product and return the result
		if (isRowVector && other.isRowVector) {
			if (data[0].length == other.data[0].length) {
				for (int i = 0; i < data[0].length; i++) {
					dotProduct += data[0][i] * other.data[0][i];
				}
			}
			else {
				throw new IllegalArgumentException("The lengths of the rows do not match.");
			}
		}
		else if (!isRowVector && !other.isRowVector) {
			if (data.length == other.data.length) {
				for (int i = 0; i < data.length; i++) {
					dotProduct += data[i][0] * other.data[i][0];
				}
			}
			else {
				throw new IllegalArgumentException("The lengths of the columns do not match.");
			}
		}
		else {
			throw new IllegalArgumentException("The vector types do not match.");
		}
		
		return dotProduct;
	}
	
	/**
	 * Computes and returns this vector's magnitude (also known as a vector's length) .
	 */
	public double magnitude() {
		double length = 0.0;
		
		
		// sums the squares of all the values and returns the root
		if (isRowVector) {
			for (int i = 0; i < data[0].length; i++) {
				length += data[0][i] * data[0][i];
			}
		}
		else {
			for (int i = 0; i < data.length; i++) {
				length += data[i][0] * data[i][0];
			}
		}
		
		return Math.sqrt(length);
	}
	
	/** 
	 * Generates and returns a normalized version of this vector.
	 */
	public MathVector normalize() {
		double sum = 0.0;
		
		// create a new vector to hold the normalized vector
		double[][] newData = new double[data.length][data[0].length];
		MathVector newVec = new MathVector(newData);
		
		
		// loop through the vector once to find the sum of all the values' squares, then
		// divide each value by the root of the sum and put it into newVec and return newVec
		if (isRowVector) {
			for (int i = 0; i < data[0].length; i++) {
				sum += data[0][i] * data[0][i];
			}
			
			sum = Math.sqrt(sum);
			
			for (int i = 0; i < data[0].length; i++) {
				newVec.data[0][i] = data[0][i] / sum;
			}
		}
		else {
			for (int i = 0; i < data.length; i++) {
				sum += data[i][0] * data[i][0];
			}
			
			sum = Math.sqrt(sum);
			
			for (int i = 0; i < data.length; i++) {
				newVec.data[i][0] = data[i][0] / sum;
			}
		}
		
		return newVec;
	}
	
	/**
	 * Generates and returns a textual representation of this vector.
	 * For example, "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and 
	 * "1.0
	 *  2.0
	 *  3.0
	 *  4.0
	 *  5.0" for a sample column vector of length 5. 
	 *  In both cases, notice the lack of a newline or space after the last number.
	 */
	public String toString() {
		String string = "";
		
		if (isRowVector) {
			for (int i = 0; i < data[0].length; i++) {
				if (i == 0) {
					string += "" + data[0][i];
				}
				else {
					string += " " + data[0][i];
				}
			}
		}
		else {
			for (int i = 0; i < data.length; i++) {
				if (i == 0) {
					string += "" + data[i][0];
				}
				else {
					string += "\n" + data[i][0];
				}
			}
		}
		
		return string;
	}
}
