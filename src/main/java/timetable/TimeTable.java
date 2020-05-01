package main.java.timetable;

import main.java.activity.UBC_CourseActivity;
import main.java.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TimeTable {

    private List<UBC_CourseActivity> activityList;

    public TimeTable(){
        activityList = new ArrayList<>();
    }

    /**
     * Add activity to the timetable
     * @param activity the activity to be added (cannot conflict)
     * @return true if the activity does not conflict and is added
     *          successfully, false otherwise.
     */
    public boolean addActivity(UBC_CourseActivity activity){
        if(activity == null)
            throw new IllegalArgumentException("Activity cannot be null");
        return activityList.add(activity);
    }

    /**
     * Remove the activity from the timetable
     * @param activity the activity to be removed
     * @return true if the activity is removed successfully, false otherwise
     */
    public boolean removeActivity(UBC_CourseActivity activity){
        return activityList.remove(activity);
    }

    /**
     * Determines whether the activity conflicts with anything in the timetable
     * @param activity the activity to check
     * @return true if the activity conflicts, false otherwise
     */
    public boolean doesConflict(UBC_CourseActivity activity){
        List<Duration> testDurs = activity.getCourseTimes();

        for(UBC_CourseActivity currAct : activityList){
            List<Duration> currDurs = currAct.getCourseTimes();

            for(Duration testDur : testDurs)
                for(Duration currDur : currDurs)
                    if(durConflict(testDur, currDur))
                        return false;


        }
        return true;
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

    /**
     * Produces a list of all courses that are part of this time table
     * @return a list of all courses
     */
    public List<UBC_CourseActivity> allCourses(){
        List<UBC_CourseActivity> retList = new ArrayList<>();
        for(UBC_CourseActivity act : activityList)
            retList.add(act);
        return retList;
    }


    @Override
    public boolean equals(Object o){
        if(o instanceof TimeTable){
            TimeTable that = (TimeTable) o;
            return this.activityList.equals(that.activityList);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return activityList.hashCode();
    }




}
