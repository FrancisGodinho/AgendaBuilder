package test.java.util_test;

import main.java.util.Day;
import main.java.util.Duration;
import main.java.util.TimeInstance;
import org.junit.Assert;
import org.junit.Test;

public class DurationTest {

    @Test
    public void getDurationTest(){

        TimeInstance timeInstance1 = new TimeInstance(Day.MON, 12, 0);
        TimeInstance timeInstance2 = new TimeInstance(Day.MON, 13, 50);
        TimeInstance timeInstance3 = new TimeInstance(Day.MON, 14, 0);
        TimeInstance timeInstance4 = new TimeInstance(Day.TUES, 14, 0);

        Duration duration1 = new Duration(timeInstance1, timeInstance2);
        Assert.assertEquals(110, duration1.getDurationMin());

        Duration duration2 = new Duration(timeInstance3, timeInstance4);
        Assert.assertEquals(24 * 60, duration2.getDurationMin());

    }

}
