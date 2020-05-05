package main.java.timetable;



import main.java.activity.Course;

import java.io.IOException;


import java.util.*;

import main.java.course_graph.ActivityEdge;
import main.java.course_graph.ActivityGraph;
import main.java.course_graph.ActivityVertex;
import main.java.parser.ExcelParser;
import main.java.parser.ExcelReader;
import main.java.parser.UBCExcelParser;

public class AgendaBuilder {

    ActivityGraph courseGraph;
    ExcelParser parser;

    public AgendaBuilder(ExcelParser parser){

        this.courseGraph = new ActivityGraph();
        this.parser = parser;
    }


    public TimeTable buildAgenda(Course... courses){
        initGraph();
        colorGraph();
        return new TimeTable();
    }


    private void initGraph(){
        addVertices();
        addEdges();
    }

    private void addVertices(){


        this.parser.initVertices(this.courseGraph);



    }

    private void addEdges(){
        Set<ActivityVertex> vertexSet = this.courseGraph.allVertices();

        for(ActivityVertex v : vertexSet)
            for(ActivityVertex w : vertexSet)
                if(!v.equals(w) && v.getActivity().doesConflict(w.getActivity()))
                    courseGraph.addEdge(new ActivityEdge(v, w));
    }


    private void colorGraph(){
        //TODO: Color the graph
    }




    public static void main(String[] args) throws IOException {

        String path = "E:\\Desktop\\Summer Coding Projects\\AgendaBuilder\\course_data\\course_data.xlsx";
        ExcelParser ubcParser = new UBCExcelParser(path);
        AgendaBuilder ab = new AgendaBuilder(ubcParser);
        ab.addVertices();

    }






}
