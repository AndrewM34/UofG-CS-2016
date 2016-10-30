
/**
 * Some sample calls to the classes to be created as part of the refactoring in JOOSE2 Laboratory 4.
 * 
 * @author Mary Ellen Foster
 */
public class StudentRecordRunner {

	public static void main(String[] args) {
		// Create a new object of each concrete subclass
		CurrentStudent cs = new CurrentStudent("Mary", "Computing Science", 4, 1820);
		FormerStudent fs = new FormerStudent("Mary", "French", 2010, "2:1");
		
		// Test out their toString methods
		System.out.println(cs);
		System.out.println(fs);
		
		// Should work
		cs.makePayment(500);
		System.out.println(cs);
		
		// Should cause an IllegalArgumentException
		cs.makePayment(5000);
	}

}
