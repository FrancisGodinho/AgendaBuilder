package main.java.timetable;



import jdk.dynalink.linker.support.TypeUtilities;
import main.java.activity.Course;

import java.io.File;
import java.io.IOException;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.*;
import java.util.zip.Adler32;

import main.java.activity.UBC_CourseActivity;
import main.java.activity.UBC_CourseSection;
import main.java.course_graph.ActivityEdge;
import main.java.course_graph.ActivityGraph;
import main.java.course_graph.ActivityVertex;
import main.java.util.Day;
import main.java.util.Duration;
import main.java.util.TimeInstance;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AgendaBuilder {

    ActivityGraph courseGraph;

    public AgendaBuilder(){
        courseGraph = new ActivityGraph();
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

                    int currentCell = cell.getColumnIndex();
                    int startCellVal = (int)Double.parseDouble(row.getCell(currentCell + 1).toString());
                    int endCellVal = (int)Double.parseDouble(row.getCell(currentCell + 2).toString());

                    int startHour = startCellVal / 100;
                    int startMin = startCellVal - (startHour * 100);
                    currentStart = new TimeInstance(Day.valueOf(Day.class, cell.toString()), startHour, startMin);

                    int endHour = endCellVal / 100;
                    int endMin = endCellVal - (endHour * 100);
                    currentEnd = new TimeInstance(Day.valueOf(Day.class, cell.toString()), endHour, endMin);

                    durList.add(new Duration(currentStart, currentEnd));

                }


            }

            this.courseGraph.addVertex(new ActivityVertex(new UBC_CourseActivity(courseName, courseNum, currSection, durList)));

            System.out.println("Course Activity: " + row.getRowNum() + ": " + courseName + ", " + courseNum + ", " + currSection.getLecture() +
                    ", " + currSection.getLab() + ", " + currSection.getTutorial() + ", " + currSection.getDiscussion());

            for(Duration d : durList)
                System.out.println(d.printDur());
            System.out.println();

        }

        excelReader.closeFile();


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


        AgendaBuilder ab = new AgendaBuilder();
        ab.addVertices();

    }






}
