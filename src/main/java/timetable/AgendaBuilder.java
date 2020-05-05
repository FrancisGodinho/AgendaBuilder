package main.java.timetable;



import main.java.activity.Course;

import java.io.IOException;


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
        addVertices();
        addEdges();
    }

    private void addVertices(){

        ExcelReader excelReader = new ExcelReader("E:\\Desktop\\Summer Coding Projects\\AgendaBuilder\\course_data\\course_data.xlsx");
        XSSFSheet dataSheet = excelReader.getData();

        Iterator<Row> rowIt = dataSheet.iterator();

        while(rowIt.hasNext()) {
            Row row = rowIt.next();

            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            String courseName = row.getCell(0).toString();
            int courseNum = (int)row.getCell(1).getNumericCellValue();

            UBC_CourseSection currSection = new UBC_CourseSection((int)row.getCell(2).getNumericCellValue(),
                    row.getCell(3).toString(), row.getCell(4).toString(), row.getCell(5).toString());

            List<Duration> durList = new ArrayList<>();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                TimeInstance currentStart = null;
                TimeInstance currentEnd = null;

                if(cell.getColumnIndex() >= 6 && cell.getColumnIndex() % 3 == 0){

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
    private void colorGraph(){
        List<ActivityVertex> sortedVertices = new ArrayList<>();
        Set<ActivityVertex> adjVertices = new HashSet<>();
        List<Integer> Used_Colour = new ArrayList<>();
        int color = 1;

        for(int i = 0; i < sortedVertices.size(); i ++){
            if(sortedVertices.get(i).getColor() == 0) {
                sortedVertices.get(i).updateColor(color);
                adjVertices = adjacentVertices(sortedVertices.get(i));

                for (int j = 1; j < adjVertices.size(); j++) {
                    if (!adjVertices.contains(sortedVertices.get(j)) && (sortedVertices.get(j).getColor() == 0))
                        sortedVertices.get(j).updateColor(sortedVertices.get(i).getColor());
                }
                Used_Colour.add(color);
                color++;
            }
        }


    }





    public static void main(String[] args){

        String path = "E:\\Desktop\\Summer Coding Projects\\AgendaBuilder\\course_data\\course_data.xlsx";
        ExcelParser ubcParser = new UBCExcelParser(path);
        AgendaBuilder ab = new AgendaBuilder(ubcParser);
        ab.initGraph();

    }






}
