package main.java.activity;

import main.java.util.TimeInstance;
import main.java.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class UBC_CourseActivity extends CourseActivity {

    private String courseName;
    private int courseNum;
    private UBC_CourseSection courseSection;
    private List<Duration> courseTimes;

    public UBC_CourseActivity(String courseName, int courseNum, UBC_CourseSection courseSection, List<Duration> courseTimes) {
        super(courseName, courseNum, courseSection, courseTimes);
    }

    public UBC_CourseActivity(String courseName, int courseNum, UBC_CourseSection courseSection, TimeInstance... courseTimes){
        super(courseName, courseNum, courseSection, courseTimes);
    }


}
