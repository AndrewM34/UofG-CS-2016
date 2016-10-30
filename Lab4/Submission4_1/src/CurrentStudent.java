/**
 * Created by andrew on 17/10/16.
 */
public class CurrentStudent extends StudentRecord {

    // fields
    private int yearOfStudy;
    private double tuitionAmount;
    private double totalPayments;

    // getters
    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public double getTuitionAmount() {
        return tuitionAmount;
    }

    public double getTotalPayments() {
        return totalPayments;
    }

    // setters
    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    /**
     * throws an exception if the amount to pay is not valid
     *
     * @param amount The amount to pay
     */
    public void makePayment(double amount) {
        if (amount <= getBalance() && amount > 0) { // is a valid amount
            totalPayments += amount;                // then update totalPayments
        } else {                                    // invalid amount, throw exception
        	throw new IllegalArgumentException("Invalid payment amount of: "+amount);
        }
    }

    /**
     * Returns the current student payment balance.
     */
    public double getBalance() {
        return tuitionAmount - totalPayments;
    }

    public CurrentStudent(String studentName, String degreeProgramme, int yearOfStudy, double tuitionAmount){
        super(studentName, degreeProgramme); // call the constructor for StudentRecord
        this.yearOfStudy = yearOfStudy;
        this.tuitionAmount = tuitionAmount;
    }

    protected String getDetails() { // implements getDetails from StudentRecord
    	// format money values nicely, in current locale
        java.text.NumberFormat cf = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.getDefault());
        return "Year of study: " + yearOfStudy + ", tuition amount: " + cf.format(tuitionAmount) + ", current payments: " + cf.format(totalPayments);
    }
}
