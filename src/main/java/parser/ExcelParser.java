package main.java.parser;

import main.java.activity.CourseActivity;
import java.util.List;

public interface ExcelParser {
    List<List<CourseActivity>> parseCourses(List<String> courseNames);
}
