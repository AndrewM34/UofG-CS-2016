package knapsack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *@author Andrew M, 2193329
 */
public class SortingStrategy implements PackingStrategy {

    /**
     * A method to find a packing of items by sorting by decreasing unit price
     * then simple add them by unit price
     *
     * @param capacity
     *            The capacity of the knapsack to use
     * @param items
     *            The list of items to use in the problem
     * @return packedItems
     *            The list of items packed as the solution
     */
    public List<Item> packItems (int capacity, List<Item> items) {
        List<Item> packedItems = new ArrayList<>();
        List<Item> allItems = new ArrayList<>(items);    

        Collections.sort(allItems, Collections.reverseOrder()); // sorts items

        int totalWeight = 0;

        for (Item item : allItems) { // for each item
            if ((totalWeight + item.getWeight()) <= capacity) { // if the weight does not exceed the capacity
                packedItems.add(item);  			// add the item
                totalWeight += item.getWeight();	// update the weight
            }
        }

        return packedItems;
    }
    
    public String getName() {
        return "Sorting strategy";
    }
}
