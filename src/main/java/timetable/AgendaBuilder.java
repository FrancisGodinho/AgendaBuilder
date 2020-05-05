package main.java.timetable;
import main.java.activity.Course;


import java.util.*;

import main.java.course_graph.ActivityEdge;
import main.java.course_graph.ActivityGraph;
import main.java.course_graph.ActivityVertex;
import main.java.parser.ExcelParser;
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
    private Set<Integer> colorGraph(){

        List<ActivityVertex> sortedVertices = new ArrayList<>();

        Set<Integer> usedColors = new HashSet<>();
        int color = 1;

        for(int i = 0; i < sortedVertices.size(); i ++){
            if(sortedVertices.get(i).getColor() == 0) {
                sortedVertices.get(i).updateColor(color);
                Set<ActivityVertex> adjVertices = courseGraph.adjacentVertices(sortedVertices.get(i));

                for (int j = 1; j < adjVertices.size(); j++) {
                    if (!adjVertices.contains(sortedVertices.get(j)) && (sortedVertices.get(j).getColor() == 0))
                        sortedVertices.get(j).updateColor(sortedVertices.get(i).getColor());
                }
                usedColors.add(color);
                color++;
            }
        }

        for(ActivityVertex currVertex : sortedVertices){
            if(currVertex.getColor() == 0) {
                currVertex.updateColor(color);
                Set<ActivityVertex> adjVertices = courseGraph.adjacentVertices(currVertex);

                for (ActivityVertex adjVertex : adjVertices)
                    if (!adjVertices.contains(adjVertex) && adjVertex.getColor() == 0)
                        adjVertex.updateColor(currVertex.getColor());

                usedColors.add(color);
                color++;
            }
        }

        return usedColors;
    }




    public static void main(String[] args){

        String path = "E:\\Desktop\\Summer Coding Projects\\AgendaBuilder\\course_data\\course_data.xlsx";
        ExcelParser ubcParser = new UBCExcelParser(path);
        AgendaBuilder ab = new AgendaBuilder(ubcParser);
        ab.initGraph();

    }






}
