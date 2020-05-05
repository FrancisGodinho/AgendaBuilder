package test.java.timetable_test;

import main.java.activity.UBC_CourseActivity;
import main.java.activity.UBC_CourseSection;
import main.java.timetable.TimeTable;
import main.java.util.Day;
import main.java.util.TimeInstance;
import org.junit.Assert;
import org.junit.Test;

public class TimeTableTest {


    @Test
    public void addRemoveTest(){
        TimeInstance time1 = new TimeInstance(Day.MON, 9, 0);
        TimeInstance time2 = new TimeInstance(Day.MON, 12, 0);

        TimeInstance time3 = new TimeInstance(Day.MON, 12, 0);
        TimeInstance time4 = new TimeInstance(Day.MON, 14, 0);

        TimeInstance time5 = new TimeInstance(Day.MON, 11, 0);
        TimeInstance time6 = new TimeInstance(Day.MON, 13, 0);

        TimeInstance time7 = new TimeInstance(Day.MON, 12, 0);
        TimeInstance time8 = new TimeInstance(Day.MON, 13, 0);

        UBC_CourseSection sec1 = new UBC_CourseSection(201, "", "L2A", "");
        UBC_CourseSection sec2 = new UBC_CourseSection(202, "", "L2B", "");
        UBC_CourseSection sec3 = new UBC_CourseSection(201, "", "", "D2B");
        UBC_CourseSection sec4 = new UBC_CourseSection(202, "", "", "D2A");

        UBC_CourseActivity course1 = new UBC_CourseActivity("Elec", 201, sec1, time1, time2);
        UBC_CourseActivity course2 = new UBC_CourseActivity("Elec", 201, sec2, time3, time4);
        UBC_CourseActivity course3 = new UBC_CourseActivity("Math", 253, sec3, time5, time6);
        UBC_CourseActivity course4 = new UBC_CourseActivity("Math", 253, sec4, time7, time8);


        TimeTable timeTable = new TimeTable();

        timeTable.addActivity(course1);
        Assert.assertTrue(timeTable.doesConflict(course1));
        Assert.assertFalse(timeTable.doesConflict(course2));
        Assert.assertTrue(timeTable.doesConflict(course3));
        Assert.assertFalse(timeTable.doesConflict(course4));

        timeTable.removeActivity(new UBC_CourseActivity("Elec", 201, sec1, time1, time2));
        Assert.assertFalse(timeTable.doesConflict(course1));
        Assert.assertFalse(timeTable.doesConflict(course2));
        Assert.assertFalse(timeTable.doesConflict(course3));
        Assert.assertFalse(timeTable.doesConflict(course4));

    }

    @Test
    public void basicConflictTest(){
        TimeInstance time1 = new TimeInstance(Day.MON, 9, 0);
        TimeInstance time2 = new TimeInstance(Day.MON, 12, 0);

        TimeInstance time3 = new TimeInstance(Day.MON, 12, 0);
        TimeInstance time4 = new TimeInstance(Day.MON, 14, 0);

        TimeInstance time5 = new TimeInstance(Day.MON, 11, 0);
        TimeInstance time6 = new TimeInstance(Day.MON, 13, 0);

        TimeInstance time7 = new TimeInstance(Day.MON, 12, 0);
        TimeInstance time8 = new TimeInstance(Day.MON, 13, 0);

        UBC_CourseSection sec1 = new UBC_CourseSection(201, "", "L2A", "");
        UBC_CourseSection sec2 = new UBC_CourseSection(202, "", "L2B", "");
        UBC_CourseSection sec3 = new UBC_CourseSection(201, "", "", "D2B");
        UBC_CourseSection sec4 = new UBC_CourseSection(202, "", "", "D2A");

        UBC_CourseActivity course1 = new UBC_CourseActivity("Elec", 201, sec1, time1, time2);
        UBC_CourseActivity course2 = new UBC_CourseActivity("Elec", 201, sec2, time3, time4);
        UBC_CourseActivity course3 = new UBC_CourseActivity("Math", 253, sec3, time5, time6);
        UBC_CourseActivity course4 = new UBC_CourseActivity("Math", 253, sec4, time7, time8);


        TimeTable timeTable = new TimeTable();

        Assert.assertFalse(timeTable.doesConflict(course3));

        timeTable.addActivity(course1);
        Assert.assertFalse(timeTable.doesConflict(course2));
        Assert.assertTrue(timeTable.doesConflict(course3));
        Assert.assertFalse(timeTable.doesConflict(course4));

        timeTable.addActivity(course4);
        Assert.assertTrue(timeTable.doesConflict(course4));
        Assert.assertTrue(timeTable.doesConflict(course2));
    }

    @Test
    public void conflictTest2(){
        TimeInstance time1 = new TimeInstance(Day.MON, 9, 0);
        TimeInstance time2 = new TimeInstance(Day.MON, 12, 0);

        TimeInstance time3 = new TimeInstance(Day.MON, 12, 0);
        TimeInstance time4 = new TimeInstance(Day.MON, 14, 0);

        TimeInstance time5 = new TimeInstance(Day.MON, 11, 0);
        TimeInstance time6 = new TimeInstance(Day.MON, 13, 0);

        TimeInstance time7 = new TimeInstance(Day.MON, 12, 0);
        TimeInstance time8 = new TimeInstance(Day.MON, 13, 0);

        TimeInstance time9 = new TimeInstance(Day.WED, 8, 0);
        TimeInstance time10 = new TimeInstance(Day.WED, 9, 0);

        TimeInstance time11 = new TimeInstance(Day.THURS, 15, 0);
        TimeInstance time12 = new TimeInstance(Day.THURS, 16, 0);

        TimeInstance time13 = new TimeInstance(Day.TUES, 9, 0);
        TimeInstance time14 = new TimeInstance(Day.TUES, 12, 0);

        UBC_CourseSection sec1 = new UBC_CourseSection(201, "", "L2A", "");
        UBC_CourseSection sec2 = new UBC_CourseSection(202, "", "L2B", "");
        UBC_CourseSection sec3 = new UBC_CourseSection(201, "", "", "D2B");
        UBC_CourseSection sec4 = new UBC_CourseSection(202, "", "", "D2A");

        UBC_CourseActivity course1 = new UBC_CourseActivity("Elec", 201, sec1, time1, time2, time9, time10, time11, time12);
        UBC_CourseActivity course2 = new UBC_CourseActivity("Elec", 201, sec2, time3, time4, time1, time2);
        UBC_CourseActivity course3 = new UBC_CourseActivity("Math", 253, sec3, time5, time6, time9, time10);
        UBC_CourseActivity course4 = new UBC_CourseActivity("Math", 253, sec4, time9, time10, time7, time8);
        UBC_CourseActivity course5 = new UBC_CourseActivity("Cpen", 261, sec1, time11, time12, time13, time14);
        UBC_CourseActivity course6 = new UBC_CourseActivity("Cpen", 261, sec3, time13, time14);


        TimeTable timeTable = new TimeTable();

        timeTable.addActivity(course1);
        Assert.assertTrue(timeTable.doesConflict(course1));
        Assert.assertTrue(timeTable.doesConflict(course2));
        Assert.assertTrue(timeTable.doesConflict(course3));
        Assert.assertTrue(timeTable.doesConflict(course4));
        Assert.assertTrue(timeTable.doesConflict(course5));
        Assert.assertFalse(timeTable.doesConflict(course6));

        timeTable.removeActivity(course1);
        Assert.assertFalse(timeTable.doesConflict(course5));

        timeTable.addActivity(course5);
        Assert.assertTrue(timeTable.doesConflict(course1));
        Assert.assertFalse(timeTable.doesConflict(course2));
        Assert.assertFalse(timeTable.doesConflict(course3));
        Assert.assertFalse(timeTable.doesConflict(course4));
        Assert.assertTrue(timeTable.doesConflict(course5));
        Assert.assertTrue(timeTable.doesConflict(course6));

        timeTable.addActivity(course4);
        Assert.assertTrue(timeTable.doesConflict(course3));
        Assert.assertTrue(timeTable.doesConflict(course2));
        Assert.assertTrue(timeTable.doesConflict(course4));
        Assert.assertTrue(timeTable.doesConflict(course1));
        Assert.assertTrue(timeTable.doesConflict(course5));
        Assert.assertTrue(timeTable.doesConflict(course6));

        timeTable.removeActivity(course4);
        Assert.assertTrue(timeTable.doesConflict(course1));
        Assert.assertFalse(timeTable.doesConflict(course2));
        Assert.assertFalse(timeTable.doesConflict(course3));
        Assert.assertFalse(timeTable.doesConflict(course4));
        Assert.assertTrue(timeTable.doesConflict(course5));
        Assert.assertTrue(timeTable.doesConflict(course6));

        timeTable.addActivity(course4);
        timeTable.removeActivity(course5);
        Assert.assertTrue(timeTable.doesConflict(course3));
        Assert.assertTrue(timeTable.doesConflict(course2));
        Assert.assertTrue(timeTable.doesConflict(course4));
        Assert.assertTrue(timeTable.doesConflict(course1));
        Assert.assertFalse(timeTable.doesConflict(course5));
        Assert.assertFalse(timeTable.doesConflict(course6));

    }
}
