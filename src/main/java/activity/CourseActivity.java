package main.java.activity;

import main.java.util.Duration;
import main.java.util.TimeInstance;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity{

    private String courseName;
    private int courseNum;
    private CourseSection courseSection;
    private List<Duration> courseTimes;

    public CourseActivity(String courseName, int courseNum, CourseSection courseSection, List<Duration> courseTimes){
        this.courseName = courseName;
        this.courseNum = courseNum;
        this.courseSection = courseSection;

        this.courseTimes = new ArrayList<>();
        this.courseTimes.addAll(courseTimes);
    }

    public CourseActivity(String courseName, int courseNum, CourseSection courseSection, TimeInstance... courseTimes){

        if(courseTimes.length % 2 != 0)
            throw new IllegalArgumentException("Course times is missing an end time");

        this.courseName = courseName;
        this.courseNum = courseNum;
        this.courseSection = courseSection;
        this.courseTimes = new ArrayList<>();
        for(int i = 0; i < courseTimes.length; i += 2){
            Duration dur = new Duration(courseTimes[i], courseTimes[i + 1]);
            this.courseTimes.add(dur);
        }
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
    public List<Duration> getCourseTimes(){
        List<Duration> retList = new ArrayList<>();
        for(Duration dur : courseTimes)
            retList.add(dur);
        return retList;
    }

    /**
     * determines if the activities conflict
     * @param otherActivity the other activity that is being compared
     * @return true if the conflict, false otherwise
     */
    public boolean doesConflict(CourseActivity otherActivity){

        List<Duration> testDurations = otherActivity.getCourseTimes();

        for(Duration testDur : testDurations)
            for(Duration currDur : courseTimes)
                if(durConflict(currDur, testDur))
                    return true;

        return false;
    }

    /**
     * Determines if two durations conflict
     * @param dur1 the first duration
     * @param dur2 the second duration
     * @return true if the two durations conflict, false otherwise
     */
    private boolean durConflict(Duration dur1, Duration dur2){
        return !((dur1.getFirstTime().isAfter(dur2.getFirstTime()) && dur1.getFirstTime().isAfter(dur2.getSecondTime())) ||
                (dur2.getFirstTime().isAfter(dur1.getFirstTime()) && dur2.getFirstTime().isAfter(dur1.getSecondTime())));
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
        int result = Math.abs(courseNum + courseName.hashCode() + courseSection.hashCode() + courseTimes.hashCode());
        return result;
    }


}
