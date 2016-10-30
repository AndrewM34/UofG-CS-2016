/**
 * A small example class defining a simple bank account.
 * 
 * @author Mary Ellen Foster
 */
public class BankAccount {
	/** The ID of this bank account */
	private int id;
	/** The name of the account holder */
	private String name;
	/** The current account balance */
	int balance;

	/** Static field used to ensure unique bank account IDs */
	private static int NEXT_ID = 0;

	/**
	 * Creates a new bank account with the given name and balance and a unique ID.
	 */
	public BankAccount(String name, int balance) {
		this.name = name;
		this.balance = balance;
		this.id = NEXT_ID++;
	}

	/**
	 * Increases the balance of the account by the given amount (NB: no error checking!)
	 */
	public void deposit(int value) {
		this.balance += value;
	}

	/**
	 * Decreases the balance of the account by the given amount (NB: no error checking!)
	 */
	public void withdraw(int value) {
		this.balance -= value;
	}
	
	/**
	 * Returns the name of the account holder.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the current account balance.
	 */
	public int getBalance() {
		return this.balance;
	}

	/**
	 * Updates the name of the account holder.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Overrides the default toString() method to produce better output.
	 */
	public String toString() {
		return "Account id:" + id + " Owner:" + name + " balance:£" + balance;
	}

	/**
	 * A small main method to test out the BankAccount methods.
	 */
	public static void main(String[] args) {
		BankAccount b = new BankAccount("Mary", 100);
		b.deposit(250);
		b.setName("Susan");
		System.out.println(b);
	}
}
