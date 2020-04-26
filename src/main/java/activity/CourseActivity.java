package main.java.activity;

import main.java.util.TimeInstance;
import main.java.util.Duration;
import java.util.List;

public class CourseActivity {

    private String courseName;
    private int courseNum;
    private CourseSection courseSection;
    private List<Duration> courseTimes;

    public CourseActivity(String courseName, int courseNum, CourseSection courseSection, List<Duration> courseTimes){
        //TODO: Complete this method
    }

    public CourseActivity(String courseName, int courseNum, CourseSection courseSection, TimeInstance... courseTimes){
        //TODO: Complete this method
    }

    /**
     * gets the course name
     * @return the course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * gets the course num
     * @return the course num
     */
    public int getCourseNum(){
        return courseNum;
    }

    /**
     * gets the course section
     * @return the course section
     */
    public CourseSection getCourseSection(){
        return courseSection;
    }

    /**
     * gets a list of the course times
     * @return a list of the course times
     */
    public List<Duration>getCourseTimes(){
        //TODO: Complete this method
        return null;
    }

    /**
     * determines if the activities conflict
     * @param otherActivity the other activity that is being compared
     * @return true if the conflict, false otherwise
     */
    public boolean doesConflict(CourseActivity otherActivity){
        //TODO: Complete this method
        //must do this in O(n) time
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof CourseActivity){
            CourseActivity that = (CourseActivity) o;
            return courseName.equals(that.courseName) && courseNum == that.courseNum &&
                    courseSection.equals(that.courseSection) && courseTimes.equals(that.courseTimes);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = courseNum ^ (courseNum >>> 32);
        result = 31 * result + courseName.hashCode() ^ (courseName.hashCode() >>> 32);
        result = 31 * result + courseSection.hashCode() ^ (courseSection.hashCode() >>> 32);
        result = 31 * result + courseTimes.hashCode() ^ (courseTimes.hashCode() >>> 32);
        return result;
    }

}
