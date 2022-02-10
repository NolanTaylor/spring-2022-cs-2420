package assign04;

import java.math.BigInteger;
import java.util.Comparator;

/**
 * A comparator to compare BigIntegers
 * 
 * @author Nolan Taylor and Alex Brett
 *
 */

public class BigComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		return ((BigInteger) o2).compareTo((BigInteger) o1);
	}

}
