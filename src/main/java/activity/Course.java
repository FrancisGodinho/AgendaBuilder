package main.java.activity;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private List<CourseActivity> activityList;

    public Course(){
        activityList = new ArrayList<>();
    }

    public Course(List<CourseActivity> courses){
        activityList = new ArrayList<>();
        for(CourseActivity activity : courses)
            activityList.add(activity);
    }

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




}
