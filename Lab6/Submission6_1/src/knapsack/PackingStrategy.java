package knapsack;

import java.util.List;

/**
 * An interface specifying the required behaviour of a strategy for solving the
 * knapsack packing problem.
 * 
 * @author Mary Ellen Foster
 *
 */
public interface PackingStrategy {
	/**
	 * Implements this strategy on the given problem instance (knapsack capacity
	 * and item list), and returns the selected list of items.
	 * 
	 * @param capacity
	 *            The capacity of the knapsack to use
	 * @param items
	 *            The list of items to use in the problem
	 * @return The items selected to be included in the knapsack
	 */
	public List<Item> packItems(int capacity, List<Item> items);

	/**
	 * Returns a string representing the current packing strategy.
	 * 
	 * @return A string describing this packing strategy
	 */
	public String getName();
}
