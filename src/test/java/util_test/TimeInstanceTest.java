package test.java.util_test;

import main.java.util.Day;
import main.java.util.TimeInstance;
import org.junit.Test;

public class TimeInstanceTest {

    @Test
    public void getTotalTimeTest(){
        TimeInstance timeInstance1 = new TimeInstance(Day.TUES, 5, 6);
        TimeInstance timeInstance2 = new TimeInstance(Day.FRI, 23, 59);
        TimeInstance timeInstance3 = new TimeInstance(Day.MON, 0, 0);

        int totalTime = timeInstance1.getTotalTimeMin();
        assert(totalTime == 1746);

        totalTime = timeInstance2.getTotalTimeMin();
        assert(totalTime == 7199);

        totalTime = timeInstance3.getTotalTimeMin();
        assert(totalTime == 0);
    }

    @Test
    public void isAfterTest(){
        TimeInstance timeInstance1 = new TimeInstance(Day.TUES, 5, 6);
        TimeInstance timeInstance2 = new TimeInstance(Day.FRI, 23, 59);

        assert(timeInstance2.isAfter(timeInstance1));
    }

    @Test
    public void equalsTest(){
        TimeInstance timeInstance1 = new TimeInstance(Day.WED, 22, 15);
        TimeInstance timeInstance2 = new TimeInstance(Day.WED, 22, 15);
        assert (timeInstance1.equals(timeInstance2));
    }

}
