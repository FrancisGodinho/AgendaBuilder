package test.java.activity_test;

import main.java.activity.CourseActivity;
import main.java.activity.UBC_CourseSection;
import main.java.util.Day;
import main.java.util.Duration;
import main.java.util.TimeInstance;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseActivityTest {

    @Test
    public void getCourseTimesTest(){

        TimeInstance start1 = new TimeInstance(Day.MON, 12, 00);
        TimeInstance end1 = new TimeInstance(Day.MON, 13, 00);
        TimeInstance start2 = new TimeInstance(Day.WED, 12, 00);
        TimeInstance end2 = new TimeInstance(Day.WED, 13, 00);

        Duration dur1 = new Duration(start1, end1);
        Duration dur2 = new Duration(start2, end2);

        UBC_CourseSection section = new UBC_CourseSection(001, null, null, null);
        List<Duration> expectedList = new ArrayList<>(Arrays.asList(dur1, dur2));
        CourseActivity activity = new CourseActivity("ELEC", 201, section, start1, end1, start2, end2);

        List<Duration> resultList = activity.getCourseTimes();

        Assert.assertEquals(resultList, expectedList);
    }


    @Test
    public void doesConflitTest1(){

        //first activity
        TimeInstance start1 = new TimeInstance(Day.MON, 12, 00);
        TimeInstance end1 = new TimeInstance(Day.MON, 14, 00);
        TimeInstance start2 = new TimeInstance(Day.WED, 12, 00);
        TimeInstance end2 = new TimeInstance(Day.WED, 14, 00);

        UBC_CourseSection section = new UBC_CourseSection(001, null, null, null);
        CourseActivity activity1 = new CourseActivity("ELEC", 201, section, start1, end1, start2, end2);

        //second activity
        start1 = new TimeInstance(Day.MON, 13, 00);
        end1 = new TimeInstance(Day.MON, 15, 00);
        start2 = new TimeInstance(Day.WED, 13, 00);
        end2 = new TimeInstance(Day.WED, 15, 00);

        section = new UBC_CourseSection(002, null, null, null);
        CourseActivity activity2 = new CourseActivity("MATH", 253, section, start1, end1, start2, end2);

        //third activity
        start1 = new TimeInstance(Day.MON, 16, 00);
        end1 = new TimeInstance(Day.MON, 17, 00);
        start2 = new TimeInstance(Day.WED, 12, 30);
        end2 = new TimeInstance(Day.WED, 13, 00);

        section = new UBC_CourseSection(8, null, null, null);
        CourseActivity activity3 = new CourseActivity("MATH", 307, section, start1, end1, start2, end2);

        //fourth activity
        start1 = new TimeInstance(Day.WED, 9, 00);
        end1 = new TimeInstance(Day.WED, 12, 00);
        start2 = new TimeInstance(Day.FRI, 12, 00);
        end2 = new TimeInstance(Day.FRI, 13, 00);

        section = new UBC_CourseSection(006, null, null, null);
        CourseActivity activity4 = new CourseActivity("CPSC", 261, section, start1, end1, start2, end2);

        Assert.assertTrue(activity1.doesConflict(activity2));
        Assert.assertTrue(activity1.doesConflict(activity3));
        Assert.assertFalse(activity1.doesConflict(activity4));
        Assert.assertTrue(activity1.doesConflict(activity1));
        Assert.assertFalse(activity2.doesConflict(activity3));
        Assert.assertFalse(activity4.doesConflict(activity2));
        Assert.assertFalse(activity4.doesConflict(activity3));

        Assert.assertTrue(activity1.doesConflict(activity1));
        Assert.assertTrue(activity2.doesConflict(activity2));
        Assert.assertTrue(activity3.doesConflict(activity3));
        Assert.assertTrue(activity4.doesConflict(activity4));
    }
}
