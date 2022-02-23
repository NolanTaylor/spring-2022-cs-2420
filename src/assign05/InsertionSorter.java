package assign05;

import java.util.ArrayList;

public class InsertionSorter {
	
	public static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> array) {
		// for each value in the array after 0
        for (int i = 1; i < array.size(); i++) {
            T value = array.get(i);
            
            int j = i - 1;
            
            // while the current value should come after i, shift j up by 1
            while (j >= 0 && array.get(j).compareTo(value) > 0) {
                array.set(j + 1, array.get(j));
                j = j - 1;
            }
            
            // set j + 1 and loop again
            array.set(j + 1, value);
        }
    }

}
