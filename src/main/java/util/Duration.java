package main.java.util;

public class Duration{


    TimeInstance timeInstance1, timeInstance2;

    public Duration(TimeInstance timeInstance1, TimeInstance timeInstance2){
        if(timeInstance1.equals(timeInstance2))
            throw new IllegalArgumentException("Cannot have duration of zero");
        if(timeInstance1.isAfter(timeInstance2))
            throw new IllegalArgumentException("The first time cannot be after the second time");

        this.timeInstance1 = timeInstance1;
        this.timeInstance2 = timeInstance2;
    }

    /**
     * get the first time
     * @return a copy of the first time
     */
    public TimeInstance getFirstTime() {
        return timeInstance1;
    }

    /**
     * get the second time
     * @return a copy of the second time
     */
    public TimeInstance getSecondTime() {
        return timeInstance2;
    }

    /**
     * get the duration in minutes
     * @return the duration in minutes
     */
    public int getDurationMin(){
        return timeInstance2.getTotalTimeMin() - timeInstance1.getTotalTimeMin();
    }


    @Override
    public boolean equals(Object o) {
        if(o instanceof Duration){
            Duration that = (Duration) o;
            return that.timeInstance1.equals(this.timeInstance1) && that.timeInstance2.equals(this.timeInstance2);
        }
        return false;
    }

    @Override
    public int hashCode(){
        int result = timeInstance1.hashCode() + timeInstance2.hashCode();
        return result;
    }

}
