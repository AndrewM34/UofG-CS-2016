package students;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class representing a student record.
 *
 * @author Andrew Mcnab, 2193329
 */
public class StudentRecord {

    /**
     * The student name
     */
    private String name;
    /**
     * The student ID
     */
    private int id;
    /**
     * The degree programme
     */
    private String degreeProgramme;
    /**
     * The year of study
     */
    private int yearOfStudy;
    /**
     * ArrayList of all courses
     */
    private List<CourseSpec> currentCourses = new ArrayList<>();
    /**
     * List of courseResults
     */
    private List<CourseResult> courseResults = new ArrayList<>();

    /**
     * Static field to ensure unique IDs
     */
    private static int NEXT_ID = 1000;

    /**
     * Creates a new StudentRecord with the given properties and a uniquely
     * generated ID.
     *
     * @param name            Student name
     * @param degreeProgramme Degree programme
     * @param yearOfStudy     Year of study
     */
    public StudentRecord(String name, String degreeProgramme, int yearOfStudy) {
        this.name = name;
        this.degreeProgramme = degreeProgramme;
        this.yearOfStudy = yearOfStudy;
        this.id = NEXT_ID++;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the degreeProgramme
     */
    public String getDegreeProgramme() {
        return degreeProgramme;
    }

    /**
     * @return the yearOfStudy
     */
    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void addCourseResult(CourseSpec spec, String year, Grade grade) {
        courseResults.add(new CourseResult(spec, year, grade));
    }

    /**
     * method to return a list of distinct courses taken by a student.
     * Utilises the Stream API
     *
     * @return the list of past courses taken by a student
     */
    public List<CourseSpec> getPastCourses() {
        // map each courseResult to a CourseSpec, filter copies, make it an ArrayList
        return courseResults.stream().map(cr -> cr.course).distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @return an ArrayList<CourseSpec> of the current courses of this student
     */
    public List<CourseSpec> getCurrentCourses() {
        return new ArrayList<>(this.currentCourses);
    }

    /**
     * method to return the GPA of a student from courses taken
     * @return the non-weighted GPA (ignoring credit values)
     */
    public double computeGPA() {
        // cast courseResults as a stream, map each CourseResult to its grade (enum) then get the ordinal value
        // average the result
        return courseResults.stream().mapToDouble(cr -> cr.getGrade().ordinal()).average().orElse(0);
    }

//    /**
//     * compute a weighted GPA, taking into account course credits.
//     * sum(credits*grade)/sum(credits)
//     * @return weighted GPA of a student from course results
//     */
//    public double computeGPA() {
//        // map each courseResult to its ordinal value multiplied by its credits value, sum, then divide by the sum of the credits for each course
//        return courseResults.stream().mapToDouble(cr -> cr.getGrade().ordinal()*cr.course.getCredits()).sum()/courseResults.stream().mapToDouble(cr -> cr.course.getCredits()).sum();
//    }

    /**
     * adds a course to the ArrayList currentCourses
     *
     * @param course course to add
     */
    public void addCurrentCourse(CourseSpec course) {
        currentCourses.add(course);
    }

    /**
     * to return a full description of the current student record
     * including all previous courses, current courses, and the GPA
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        s.append("Student #"+this.id+": "+this.name+" -- "+this.degreeProgramme+" (year "+this.yearOfStudy+")\n");

        // append each current course detail
        s.append("Current courses:\n");
        for (CourseSpec cs : currentCourses) {
            s.append("- "+cs.getDepartment()+cs.getCode()+": '"+cs.getTitle()+"' ("+cs.getCredits()+" credits)\n");
        }

        s.append("\n");

        // append each past course detail
        s.append("Previous courses:\n");
        for (CourseResult cr : courseResults) {
            s.append("- "+cr.course.getDepartment()+cr.course.getCode()+": '"+cr.course.getTitle()+"' ("+cr.course.getCredits()+" credits)"+": "+cr.getGrade()+" ("+cr.yearTaken+")\n");
        }

        s.append("\nGPA: "+computeGPA());

        return s.toString();
    }

}
