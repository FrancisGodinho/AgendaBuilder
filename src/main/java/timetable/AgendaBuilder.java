package main.java.timetable;

import java.util.*;

import main.java.activity.CourseActivity;
import main.java.parser.ExcelParser;

public class AgendaBuilder {

    ExcelParser parser;

    public AgendaBuilder(ExcelParser parser){
        this.parser = parser;
    }

    public List<CourseActivity> buildAgenda_test(List<String> courses){
        List<List<CourseActivity>> allCourses = parser.parseCourses(courses);
        return build(allCourses, 0, new ArrayList<>());
    }

    private List<CourseActivity> build(List<List<CourseActivity>> courses, int curr_course, List<CourseActivity> agenda){
        if(curr_course == courses.size()){
            if(validAgenda(agenda))
                return agenda;
            return new ArrayList<>();
        }

        List<CourseActivity> course = courses.get(curr_course);
        for(CourseActivity section : course){
            agenda.add(section);

            List<CourseActivity> validAgenda = build(courses, curr_course + 1, agenda);
            if(validAgenda.size() > 0)
                return validAgenda;

            agenda.remove(section);
        }
        return new ArrayList<>();

    }

    /**
     * Checks if a list of courses can be a valid agenda
     * (valid if they don't conflict with each other)
     * @param agenda A list of courses that are part of the agenda
     * @return True if the agenda is valid, and false otherwise
     */
    private boolean validAgenda(List<CourseActivity> agenda){
        for(CourseActivity course : agenda){
            for(CourseActivity compareCourse : agenda){
                if(agenda.indexOf(course) == agenda.indexOf(compareCourse))
                    continue;
                if(course.doesConflict(compareCourse))
                    return false;
            }
        }
        return true;
    }


}
