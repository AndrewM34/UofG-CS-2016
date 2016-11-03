package knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew M, 2193329
 */
public class BruteForceStrategy implements PackingStrategy {

	/**
	 * A method to find a packing of items using a brute-force approach
	 *
	 * @param capacity
	 *            The capacity of the knapsack to use
	 * @param items
	 *            The list of items to use in the problem
	 * @return bestSolution The best solution from the power-set of all
	 *         solutions
	 */
	public List<Item> packItems(int capacity, List<Item> items) {
		// first find the power-set of the list items
		List<List<Item>> pItems = Utils.powerSet(items);

		// empty list for valid solutions from the power-set of items
		List<List<Item>> solutions = new ArrayList<>();

		// find each solution that does not exceed the capacity
		for (List<Item> packing : pItems) { // each list in pItems
			int totalWeight = 0;
			for (Item item : packing) { // each item in packing
				totalWeight += item.getWeight();
			}

			if (totalWeight <= capacity) { // if the packing is a valid one
				solutions.add(packing);    // add it to the list of solutions
			}
		}

		// now find the best value of these solutions
		// find the best value
		// if a value is larger than the previous highest value then make the
		// new solution the best one
		List<Item> bestSolution = new ArrayList<>();
		int bestValue = -1;
		
		for (List<Item> pack : solutions) { // for each packing solution
			int totalValue = 0;
			
			for (Item item : pack) { // for each item in the packing
				totalValue += item.getValue();  // sum the value
			}

			if (totalValue > bestValue) { // if the value exceeds the last highest
				bestValue = totalValue;   // update the bestValue
				bestSolution = pack;	  // make this packing the bestSolution
			}
		}

		return bestSolution;
	}

	public String getName() {
		return "Brute-force strategy";
	}
}
