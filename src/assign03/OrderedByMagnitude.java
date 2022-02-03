package assign03;

import java.util.Comparator;

public class OrderedByMagnitude implements Comparator<Point> {

	@Override
	public int compare(Point o1, Point o2) {
		if(o1.getMagnitude() > o2.getMagnitude())
			return 1;
		if(o1.getMagnitude() < o2.getMagnitude())
			return -1; 
		return 0;
	}

}
