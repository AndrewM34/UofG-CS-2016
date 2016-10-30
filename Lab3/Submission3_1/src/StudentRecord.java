// A class for a student record
// @author Andrew Mcnab, 2193329

public class StudentRecord {
    // fields
    private int studentNumber;
    private String studentName;
    private String degreeProgramme;
    private int yearOfStudy;
    private double tuitionAmount;
    private double totalPayments;

    private static int nextStudentNumber = 0;

    // constructor

    /**
     *
     * @param studentName
     * @param degreeProgramme
     * @param yearOfStudy
     * @param tuitionAmount
     */
    public StudentRecord(String studentName, String degreeProgramme, int yearOfStudy, double tuitionAmount) {
        this.studentName = studentName;
        this.degreeProgramme = degreeProgramme;
        this.yearOfStudy = yearOfStudy;
        this.tuitionAmount = tuitionAmount;

        this.studentNumber = nextStudentNumber++; // assign a unique ID
        this.totalPayments = 0.00;                // initialise total payments as £0.00
    }

    public double getBalance() {
        return this.tuitionAmount - this.totalPayments;
    }

    public boolean makePayment(double amount) {
        if ((amount > 0) && (amount <= this.getBalance())) {
            this.totalPayments += amount;
            return true;
        }
        return false;
    }

    // setters and getters
    public void setDegreeProgramme(String degreeProgramme) { this.degreeProgramme = degreeProgramme; }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public int getStudentNumber() {
        return this.studentNumber;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public String getDegreeProgramme() {
        return this.degreeProgramme;
    }

    public int getYearOfStudy() {
        return this.yearOfStudy;
    }

    public double getTuitionAmount() {
        return this.tuitionAmount;
    }

    public double getTotalPayments() {
        return this.totalPayments;
    }

    @Override
    public String toString() { // override toString() to show relevant fields, and format currency nicely
        java.text.NumberFormat cf = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.getDefault());
        String tuitionAmountString = cf.format(tuitionAmount); // formats the two money amounts
        String totalPaymentsString = cf.format(totalPayments);
        return "Student ID: " + studentNumber + ", student name: " + studentName + ", degree program: "
                + degreeProgramme + ", year of study: " + yearOfStudy + ", tuition amount: " + tuitionAmountString
                + ", total payments: " + totalPaymentsString; // all relevant record information
    }

}