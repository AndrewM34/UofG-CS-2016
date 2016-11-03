package knapsack;

/**
 * @author Andrew M, 2193329
 */
public class Item implements Comparable<Item> {
	
    // fields
    private int weight;
    private int value;
    private int id;

    private static int nextId = 0; // gives each object a unique id

    // constructor
    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.id = nextId++;
    }

    // getters
    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    /**
     * Implements compareTo from the Comparable interface
     * Used in SortingStrategy.packItems to sort a list
     *
     * @param otherItem
     */
    public int compareTo (Item otherItem) {
        float thisUnitPrice = (float) value/weight;
        float otherUnitPrice = (float) otherItem.getValue()/otherItem.getWeight();
        
        if (thisUnitPrice < otherUnitPrice) {
            return -1;
        } else if (thisUnitPrice > otherUnitPrice) {
            return 1;
        } else {
        	return 0;
        }
        
    }

    @Override
    public String toString() {
        return "Weight = " + weight + ", value = " + value + ", id = " + id;
    }
}
