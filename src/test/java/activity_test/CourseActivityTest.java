package test.java.activity_test;

import main.java.activity.CourseActivity;
import main.java.activity.CourseSection;
import main.java.util.Day;
import main.java.util.Duration;
import main.java.util.TimeInstance;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;
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

        CourseSection section = new CourseSection(001);
        List<Duration> expectedList = new ArrayList<>(Arrays.asList(dur1, dur2));
        CourseActivity activity = new CourseActivity("ELEC", 201, section, start1, end1, start2, end2);

        List<Duration> resultList = activity.getCourseTimes();
        Assert.assertTrue(resultList.equals(expectedList));

    }


//must finish ============================================================================= //TODO: FINISH THIS THING
    @Test
    public void doesConflitTest1(){

        //first activity
        TimeInstance start1 = new TimeInstance(Day.MON, 12, 00);
        TimeInstance end1 = new TimeInstance(Day.MON, 13, 00);
        TimeInstance start2 = new TimeInstance(Day.WED, 12, 00);
        TimeInstance end2 = new TimeInstance(Day.WED, 13, 00);

        CourseSection section = new CourseSection(001);
        CourseActivity activity1 = new CourseActivity("ELEC", 201, section, start1, end1, start2, end2);

        //second activity
        start1 = new TimeInstance(Day.MON, 12, 00);
        end1 = new TimeInstance(Day.MON, 13, 00);
        start2 = new TimeInstance(Day.WED, 12, 00);
        end2 = new TimeInstance(Day.WED, 13, 00);

        section = new CourseSection(001);
        CourseActivity activity2 = new CourseActivity("ELEC", 201, section, start1, end1, start2, end2);

        //third activity
        start1 = new TimeInstance(Day.MON, 12, 00);
        end1 = new TimeInstance(Day.MON, 13, 00);
        start2 = new TimeInstance(Day.WED, 12, 00);
        end2 = new TimeInstance(Day.WED, 13, 00);

        section = new CourseSection(001);
        CourseActivity activity3 = new CourseActivity("ELEC", 201, section, start1, end1, start2, end2);

        //fourth activity
        start1 = new TimeInstance(Day.MON, 12, 00);
        end1 = new TimeInstance(Day.MON, 13, 00);
        start2 = new TimeInstance(Day.WED, 12, 00);
        end2 = new TimeInstance(Day.WED, 13, 00);

        section = new CourseSection(001);
        CourseActivity activity4 = new CourseActivity("ELEC", 201, section, start1, end1, start2, end2);



    }
}
