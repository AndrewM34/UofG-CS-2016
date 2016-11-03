package knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew M, 2193329
 */
public class SimpleStrategy implements PackingStrategy {

    /**
     * A method to implement the simple strategy, i.e. to add an item only if it doesn't exceed the weight available
     *
     * @param capacity
     *            The capacity of the knapsack to use
     * @param items
     *            The list of items to use in the problem
     * @return packedItems
     *            The list of items packed as the solution
     */
    public List<Item> packItems(int capacity, List<Item> items) {
        List<Item> packedItems = new ArrayList<>();
        int packedItemsWeight = 0;

        // go through each item
        // if it can fit then add it
        // else skip it
        for (Item item : items) {
            if (packedItemsWeight + item.getWeight() <= capacity) {
                packedItems.add(item);
                packedItemsWeight += item.getWeight();
            } else {
                // System.out.println("Item weighs too much: "+item.getWeight()+", in simplestrategy.");
            }
        }

        return packedItems;
    }

    public String getName() {
        return "Simple strategy";
    }
}
