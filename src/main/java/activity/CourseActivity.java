package main.java.activity;

import main.java.util.Duration;
import java.util.List;

public interface CourseActivity {

    /**
     * gets the course name
     * @return the course name
     */
    String getCourseName();

    /**
     * gets the course num
     * @return the course num
     */
    int getCourseNum();

    /**
     * gets the course section
     * @return the course section
     */
    CourseSection getCourseSection();

    /**
     * gets a list of the course times
     * @return a list of the course times
     */
    List<Duration> getCourseTimes();

    /**
     * determines if the activities conflict
     * @param otherActivity the other activity that is being compared
     * @return true if the conflict, false otherwise
     */
    boolean doesConflict(UBC_CourseActivity otherActivity);


}
