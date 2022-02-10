package assign04;

import java.util.Comparator;

/**
 * A comparator functor that compares two numbers and determines
 * which should come first to create the largest number.
 * 
 * @author Nolan Taylor and Alex Brett
 *
 */

public class LargestCombinedInt implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		
		String combo1 = "" + o1 + o2; 
		String combo2 = "" + o2 + o1;
		
		int combo1IntEdition = Integer.parseInt(combo1);
		int combo2IntEdition = Integer.parseInt(combo2);
		
		if (combo1IntEdition > combo2IntEdition) {
			return -1;
		}
		else if (combo2IntEdition > combo1IntEdition) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
