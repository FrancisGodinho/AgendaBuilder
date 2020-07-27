package main.java.timetable;
import main.java.activity.Course;


import java.util.*;

import main.java.activity.CourseActivity;
import main.java.course_graph.ActivityEdge;
import main.java.course_graph.ActivityGraph;
import main.java.course_graph.ActivityVertex;
import main.java.parser.ExcelParser;
import main.java.util.Duration;

public class AgendaBuilder {

    ActivityGraph courseGraph;
    ExcelParser parser;

    public AgendaBuilder(ExcelParser parser){
        this.courseGraph = new ActivityGraph();
        this.parser = parser;
    }

    public List<CourseActivity> buildAgenda(List<String> courses){
        initGraph(courses);
        int numColors = colorGraph();
        List<List<CourseActivity>> timeTableList = getColorGroups(numColors);

        //TODO: REMOVE THIS LINE
        printTables(timeTableList);

        return getTimeTable(timeTableList, courses.size());
    }

    //TODO: REMOVE THIS METHOD
    private void printTables(List<List<CourseActivity>> retList){
        int i = 1;
        for(List<CourseActivity> activityList : retList){

            int j = 1;
            System.out.println("-----------TimeTable " + i + "-----------");
            for(CourseActivity activity : activityList){
                System.out.println("Course " + j + ": " + activity.getCourseName() + ", " + activity.getCourseNum() + ", " +
                        activity.getCourseSection().getLecture());
                for(Duration d : activity.getCourseTimes())
                    System.out.println("      "+d.printDur());
                j++;

            }
            System.out.println();
            i++;
        }
    }

    private List<CourseActivity> getTimeTable(List<List<CourseActivity>> timeTableList, int numCourses){

        for(List<CourseActivity> timeTable : timeTableList){

            if(timeTable.size() < numCourses)
                continue;

            Set<String> courseInfo = new HashSet<>();
            Iterator <CourseActivity> courses = timeTable.iterator();

            while(courses.hasNext()){
                CourseActivity course = courses.next();
                if(courseInfo.contains(course.getCourseName() + course.getCourseNum()))
                    courses.remove();
                else
                    courseInfo.add(course.getCourseName() + course.getCourseNum());
            }

            if(courseInfo.size() == numCourses)
                return timeTable;
        }

        return new ArrayList<>();
    }

    private List<List<CourseActivity>> getColorGroups(int numColorsUsed) {
        List<List<CourseActivity>> retList = new ArrayList<>();
        Set<ActivityVertex> vertexSet = this.courseGraph.allVertices();

        for (int i = 0; i < numColorsUsed; i++)
            retList.add(new ArrayList<>());

        for (ActivityVertex currentVertex : vertexSet)
            retList.get(currentVertex.getColor()).add(currentVertex.getActivity());

        return retList;
    }


    private void initGraph(List<String> courseNames){

        //add vertices to graph
        this.parser.initVertices(this.courseGraph, courseNames);

        //add edges to graph
        Set<ActivityVertex> vertexSet = this.courseGraph.allVertices();

        for(ActivityVertex v : vertexSet)
            for(ActivityVertex w : vertexSet)
                if(!v.equals(w) && v.getActivity().doesConflict(w.getActivity()))
                    courseGraph.addEdge(new ActivityEdge(v, w));
    }


    //assume u have a list called sortedVertices, highest order vertex is first
    // colors will be values from 0 onwards
    private int colorGraph(){


        List<ActivityVertex> sortedVertices = new ArrayList<>(courseGraph.allVertices());
        Set<Integer> colours = new HashSet<>();

        if (sortedVertices.size() == 0)
            return 0;

        colours.add(0);

        sortedVertices.get(0).updateColor(0);

        for(int i = 1; i < sortedVertices.size(); i++){
            ActivityVertex currVertex = sortedVertices.get(i);
            Set<ActivityVertex> neighbours = this.courseGraph.adjacentVertices(currVertex);

            colorVertex(currVertex, neighbours, colours);
            colours.add(currVertex.getColor());

        }
        return colours.size();
    }

    private void colorVertex(ActivityVertex vertex, Set<ActivityVertex> neighbours, Set<Integer> colorsUsed){

        int color = -1;

        //try to use a color from colorsUsed
        for(int currColor : colorsUsed){

            color = currColor;

            //check if the color is valid
            for(ActivityVertex n : neighbours)
                if(n.getColor() == currColor){
                    color = -1;
                    break;
                }

            // if color is valid (not -1) then break, otherwise try another color
            if(color != - 1)
                break;
        }

        if(color == -1)
            vertex.updateColor(colorsUsed.size());
        else
            vertex.updateColor(color);
    }







}
