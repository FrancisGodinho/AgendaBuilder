package main.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimeInstance {

    private Day day;
    private int hour, min;
    private static final List<Day> dayList = new ArrayList<>(Arrays.asList(Day.MON, Day.TUES, Day.WED, Day.THURS, Day.FRI));

    public TimeInstance(Day day, int hour, int min){
        this.hour = hour;
        this.min = min;
        this.day = day;
    }

    /**
     * Determine the day of this TimeInstance
     * @return the day (enum)
     */
    public Day getDay(){
        return day;
    }

    /**
     * Determine the hour of the TimeInstance
     * @return the hour \in [0, 23]
     */
    public int getHour(){
        return hour;
    }

    /**
     * Determine the min of the TimeInstance
     * @return the minute \in [0, 59]
     */
    public int getMin(){
        return min;
    }

    /**
     * Determine the total time of the TimeInstance
     * @return the total time in minutes \in [0, 7199].
     *          0 corresponds to MON at 12:00 am, and 7199
     *          corresponds to FRI at 11:59 pm.
     */
    public int getTotalTimeMin(){
        return (dayList.indexOf(day)) * 24 * 60 + hour * 60 + min;
    }

    /**
     * Determines if thisTime is after or equal to otherTime
     * @param otherTime the other time to compare
     * @return true if thisTime is after or equal to otherTime
     */
    public boolean isAfter(TimeInstance otherTime){
        return otherTime.getTotalTimeMin() <= this.getTotalTimeMin();
    }

    /**
     * Adds time to this time
     * @param minutes the minutes we want to add
     * @return a new time which holds the value of thisTime + minutes
     */
    public TimeInstance addTime(int minutes){
        int numDays = minutes / (24 * 60);
        minutes -= numDays * 24 * 60;

        int numHours = minutes / 60;
        minutes -= numHours * 60;

        return new TimeInstance(dayList.get(dayList.indexOf(this.day) + numDays), this.hour + numHours, this.min + minutes);
    }


    @Override
    public boolean equals(Object o) {
        if(o instanceof TimeInstance){
            TimeInstance that = (TimeInstance) o;
            return this.hour == that.hour && this.min == that.min && this.day.equals(that.day);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = min ^ (min >>> 32);
        result = 31 * result + hour ^ (hour >>> 32);
        result = 31 * result + day.hashCode() ^ (day.hashCode() >>> 32);
        return result;
    }
}
