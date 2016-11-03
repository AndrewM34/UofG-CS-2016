package knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A test class for the knapsack-packing classes in JOOSE2 Laboratory 6, 2016.
 * 
 * @author Mary Ellen Foster
 */
public class KnapsackMain {

	// Parameters for the test case from the lab sheet
	private static final int[] WEIGHTS = { 3, 10, 4, 3, 5 };
	private static final int[] VALUES = { 6, 7, 7, 10, 9 };
	private static final int CAPACITY = 12;

	public static void main(String[] args) {
		// Construct an instance of each packing strategy -- comment out the
		// ones you haven't yet implemented.
		List<PackingStrategy> strategies = new ArrayList<>();
		strategies.add(new SimpleStrategy());
		strategies.add(new BruteForceStrategy());
		strategies.add(new SortingStrategy());

		// Test the items and capacity from the handout
		List<Item> testItems = new ArrayList<>();
		testItems = new ArrayList<>();
		for (int i = 0; i < WEIGHTS.length; i++) {
			testItems.add(new Item(WEIGHTS[i], VALUES[i]));
		}
		System.out.println("Items:");
		for (Item item : testItems) {
			System.out.println("- " + item);
		}

		for (PackingStrategy s : strategies) {
			testStrategy(s, testItems, CAPACITY);
		}

		// Create a random problem instance and test all strategies on it
		testItems.clear();
		Random random = new Random();
		int numItems = 15;
		for (int i = 0; i < numItems; i++) {
			int value = random.nextInt(10) + 1;
			int weight = random.nextInt(10) + 1;
			testItems.add(new Item(value, weight));
		}

		int testCapacity = random.nextInt(10) + 10;

		System.out.println("Test capacity: " + testCapacity);
		System.out.println("Items:");
		for (Item item : testItems) {
			System.out.println("- " + item);
		}
		for (PackingStrategy s : strategies) {
			testStrategy(s, testItems, testCapacity);
		}

	}

	/**
	 * Runs a single strategy on the given problem instance and prints out the
	 * results.
	 * 
	 * @param ps
	 *            The packing strategy to use
	 * @param items
	 *            The list of items to pack
	 * @param capacity
	 *            The knapsack capacity
	 */
	private static void testStrategy(PackingStrategy ps, List<Item> items, int capacity) {
		long startTime = System.nanoTime();
		List<Item> result = ps.packItems(capacity, items);
		double runTime = (System.nanoTime() - startTime) / 1_000_000.0;
		int totalValue = 0;
		int totalWeight = 0;
		List<Integer> ids = new ArrayList<>();
		for (Item item : result) {
			totalValue += item.getValue();
			totalWeight += item.getWeight();
			ids.add(item.getId());
		}
		System.out.println("Result for " + ps.getName() + ": " + ids);
		System.out.println("Total value: " + totalValue);
		System.out.println("Total weight: " + totalWeight);
		System.out.println("Run time: " + runTime + "ms");

	}

}
