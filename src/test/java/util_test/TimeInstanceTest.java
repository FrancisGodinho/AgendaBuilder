package test.java.util_test;

import main.java.util.Day;
import main.java.util.TimeInstance;
import org.junit.Assert;
import org.junit.Test;

public class TimeInstanceTest {

    @Test
    public void getTotalTimeTest(){
        TimeInstance timeInstance1 = new TimeInstance(Day.TUES, 5, 6);
        TimeInstance timeInstance2 = new TimeInstance(Day.FRI, 23, 59);
        TimeInstance timeInstance3 = new TimeInstance(Day.MON, 0, 0);

        Assert.assertEquals(1746, timeInstance1.getTotalTimeMin());
        Assert.assertEquals(7199, timeInstance2.getTotalTimeMin());
        Assert.assertEquals(0, timeInstance3.getTotalTimeMin());
    }

    @Test
    public void isAfterTest(){
        TimeInstance timeInstance1 = new TimeInstance(Day.TUES, 5, 6);
        TimeInstance timeInstance2 = new TimeInstance(Day.FRI, 23, 59);

        Assert.assertTrue(timeInstance2.isAfter(timeInstance1));
    }

    @Test
    public void addTimeTest(){
        TimeInstance timeInstance1 = new TimeInstance(Day.TUES, 5, 6);
        TimeInstance addition1 = new TimeInstance(Day.TUES, 5, 56);
        TimeInstance addition2 = new TimeInstance(Day.TUES, 6, 6);
        TimeInstance addition3 = new TimeInstance(Day.WED, 7, 8);

        Assert.assertEquals(addition1, timeInstance1.addTime(50));
        Assert.assertEquals(addition2, timeInstance1.addTime(60));
        Assert.assertEquals(addition3, timeInstance1.addTime(1562));
    }

    @Test
    public void equalsTest(){
        TimeInstance timeInstance1 = new TimeInstance(Day.WED, 22, 15);
        TimeInstance timeInstance2 = new TimeInstance(Day.WED, 22, 15);
        Assert.assertTrue(timeInstance1.equals(timeInstance2));
    }

}
