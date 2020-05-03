package main.java.timetable;



import main.java.activity.Course;

import java.io.File;
import java.io.IOException;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import main.java.course_graph.ActivityGraph;
import main.java.course_graph.ActivityVertex;
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
        return new TimeTable();
    }



    private void initGraph(){
        //create all vertices
        //create all edges
    }

    private void addVertces(){
        //read data from excel sheet
        //create time instances
        //create sections
        //create activity vertex
        //create vertex
        //add vertex

        ExcelReader excelReader = new ExcelReader("E:\\Desktop\\Summer Coding Projects\\AgendaBuilder\\course_data\\course_data.xlsx");
        XSSFSheet dataSheet = excelReader.getData();

        Iterator<Row> rowIt = dataSheet.iterator();

        while(rowIt.hasNext()) {
            Row row = rowIt.next();

            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            int i = 0;

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                //TODO: Go through the cell and create a vertex






            }


        }

        excelReader.closeFile();





    }

    public void readExcel() throws IOException{
        File inputFile = new File("E:\\Desktop\\Summer Coding Projects\\AgendaBuilder\\course_data\\course_data.xlsx");





    }

    public static void main(String[] args) throws IOException {

        ExcelReader reader = new ExcelReader("E:\\Desktop\\Summer Coding Projects\\AgendaBuilder\\course_data\\course_data.xlsx");
        XSSFSheet sheet = reader.getData();

        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();

        while(rowIt.hasNext()) {
            Row row = rowIt.next();

            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.print(cell.toString() + ";");
            }

            System.out.println();
        }

        reader.closeFile();
    }






}
