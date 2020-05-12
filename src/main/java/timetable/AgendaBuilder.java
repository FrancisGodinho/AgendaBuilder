package main.java.timetable;
import main.java.activity.Course;


import java.util.*;

import main.java.activity.CourseActivity;
import main.java.course_graph.ActivityEdge;
import main.java.course_graph.ActivityGraph;
import main.java.course_graph.ActivityVertex;
import main.java.parser.ExcelParser;
import main.java.parser.UBCExcelParser;
import main.java.util.Duration;

public class AgendaBuilder {

    ActivityGraph courseGraph;
    ExcelParser parser;

    public AgendaBuilder(ExcelParser parser){
        this.courseGraph = new ActivityGraph();
        this.parser = parser;
    }


    public TimeTable buildAgenda(Course... courses){
        initGraph();
        int numColors = colorGraph();
        List<List<CourseActivity>> retList = getColorGroups(numColors);

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
        return new TimeTable();
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


    private void initGraph(){

        //add vertices to graph
        this.parser.initVertices(this.courseGraph);

        //add edges to graph
        Set<ActivityVertex> vertexSet = this.courseGraph.allVertices();

        for(ActivityVertex v : vertexSet)
            for(ActivityVertex w : vertexSet)
                if(!v.equals(w) && v.getActivity().doesConflict(w.getActivity()))
                    courseGraph.addEdge(new ActivityEdge(v, w));
    }


    //assume u have a list called sortedVertices, highest order vertex is first
    private int colorGraph(){

        List<ActivityVertex> sortedVertices = new ArrayList<>(courseGraph.allVertices());

        Set<Integer> usedColors = new HashSet<>();
        int color = 0;

        for(ActivityVertex currVertex : sortedVertices){
            if(currVertex.getColor() == -1) {

                currVertex.updateColor(color);

                Set<ActivityVertex> adjVertices = courseGraph.adjacentVertices(currVertex);
                Set<ActivityVertex> allVertices = courseGraph.allVertices();

                for (ActivityVertex nextVertex : allVertices)
                    if (!adjVertices.contains(nextVertex) && nextVertex.getColor() == -1)
                        nextVertex.updateColor(color);

                usedColors.add(color);
                color++;
            }
        }

        return usedColors.size();
    }




    public static void main(String[] args){

        String path = "E:\\Desktop\\Summer Coding Projects\\AgendaBuilder\\course_data\\course_data.xlsx";
        ExcelParser ubcParser = new UBCExcelParser(path);
        AgendaBuilder ab = new AgendaBuilder(ubcParser);

        ab.buildAgenda();

    }






}
