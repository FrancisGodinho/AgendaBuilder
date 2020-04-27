package main.java.activity;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private List<CourseActivity> activityList;

    /**
     * Create new Course
     */
    public Course(){
        activityList = new ArrayList<>();
    }

    /**
     * Create new Course
     * @param courses a list of course activities
     */
    public Course(List<CourseActivity> courses){
        activityList = new ArrayList<>();
        for(CourseActivity activity : courses)
            activityList.add(activity);
    }

    /**
     * Create a new Course
     * @param courses course activities
     */
    public Course(CourseActivity... courses){
        activityList = new ArrayList<>();
        for(CourseActivity activity : courses)
            activityList.add(activity);
    }

    /**
     * Add an activity to the course
     * @param activity the activity to add
     * @return true if the activity was added successfully, false otherwise
     */
    public boolean addActivity(CourseActivity activity){
        return activityList.add(activity);
    }

    /**
     * Determine the number of activities that are part of this course
     * @return the number of activities in this course
     */
    public int numActivities(){
        return activityList.size();
    }

    /**
     * Determine whether this course has an activity
     * @param activity the activity to check
     * @return true if the activity is part of the course, false otherwise
     */
    public boolean hasActivity(CourseActivity activity){
        return activityList.contains(activity);
    }


    @Override
    public boolean equals(Object o){
        if(o instanceof Course){
            Course that = (Course) o;
            return that.activityList.equals(this.activityList);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return activityList.hashCode();
    }

}
