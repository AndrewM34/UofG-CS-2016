/**
 * A class to represent a simplified student record.
 * 
 * @author Andrew Mcnab, 2193329
 */
public abstract class StudentRecord {

	// Fields
	private int studentNumber;
	private String studentName;
	private String degreeProgramme;

	// Static field to ensure unique IDs
	private static int NEXT_NUMBER = 1000;

	/**
	 * Creates a new StudentRecord with the given parameters.
	 */
	public StudentRecord(String studentName, String degreeProgramme) {
		this.studentName = studentName;
		this.degreeProgramme = degreeProgramme;
		this.studentNumber = NEXT_NUMBER++;
	}

	// Getters
	public int getStudentNumber() {
		return studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}	

	public String getDegreeProgramme() {
		return degreeProgramme;
	}

	// Setters
	public void setDegreeProgramme(String degreeProgramme) {
		this.degreeProgramme = degreeProgramme;
	}


	/**
	 * Returns a nice string representation of this student record
	 * modified to get details from the relevant subclass using getDetails()
	 */
	public String toString() {
		return "Student number: " + studentNumber + ", name: " + studentName + ", degree programme: " + degreeProgramme + ", " + getDetails();
	}

	protected abstract String getDetails(); // implemented in classes CurrentStudent and FormerStudent
}
