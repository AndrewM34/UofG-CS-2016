package knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 * A class with a useful utility method for the knapsack packing problem.
 * 
 * @author Mary Ellen Foster
 */
public class Utils {

	/**
	 * Computes and returns the power-set of the given list -- that is, a list
	 * of all possible sub-sets of the given list.
	 * 
	 * @param items
	 *            The list of items to use as a starting point
	 * @return The power set, as a list of lists of the given type
	 */
	public static <T> List<List<T>> powerSet(List<T> items) {
		List<List<T>> result = new ArrayList<>();
		result.add(new ArrayList<>());
		for (T i : items) {
			List<List<T>> temp = new ArrayList<>();
			for (List<T> list : result) {
				List<T> copy = new ArrayList<>(list);
				copy.add(i);
				temp.add(copy);
			}
			result.addAll(temp);
		}
		return result;
	}

}
