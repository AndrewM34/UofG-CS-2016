package students;

import java.util.Objects;

/**
 * Created by @author andrew on 11/11/16.
 */
public class CourseSpec {

    // fields
    String department, code, title;
    int credits;

    // constructor
    public CourseSpec (String department, String code, String title, int credits) {
        this.department = department;
        this.code = code;
        this.title = title;
        this.credits = credits;
    }

    // getters
    public String getDepartment() {
        return department;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseSpec that = (CourseSpec) o;
        return Objects.equals(department, that.department) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, code);
    }
}
