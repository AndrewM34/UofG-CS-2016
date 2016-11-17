package students;

/**
 * Created by @author andrew on 11/11/16.
 */
public class CourseResult {

    // fields
    CourseSpec course;
    String yearTaken;
    Grade grade;

    // constructor
    public CourseResult(CourseSpec course, String yearTaken, Grade grade) {
        this.course = course;
        this.yearTaken = yearTaken;
        this.grade = grade;
    }

    // getters
    public CourseSpec getCourse() {
        return course;
    }

    public String getYearTaken() {
        return yearTaken;
    }

    public Grade getGrade() {
        return grade;
    }
}
