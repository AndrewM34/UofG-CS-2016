/**
 * Created by andrew on 17/10/16.
 */
public class FormerStudent extends StudentRecord {
    // fields
    private int graduationYear;
    private String degreeClass;

    // getters
    public int getGraduationYear() {
        return graduationYear;
    }

    public String getDegreeClass() {
        return degreeClass;
    }

    public FormerStudent(String studentName, String degreeProgramme, int graduationYear, String degreeClass) {
        super(studentName, degreeProgramme); // calls the constructor in StudentRecord
        this.graduationYear = graduationYear;
        this.degreeClass = degreeClass;
    }

    protected String getDetails() { // implement getDetails from StudentRecord
        return "Year of graduation: " + graduationYear + ", degree class: " + degreeClass;
    }
}
