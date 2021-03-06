package main.java.parser;

import main.java.activity.CourseActivity;
import main.java.activity.UBC_CourseSection;
import main.java.util.Day;
import main.java.util.Duration;
import main.java.util.TimeInstance;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.*;

public class UBCExcelParser implements ExcelParser {


    String filePath;

    public UBCExcelParser(String excelFilePath){
        this.filePath = excelFilePath;
    }

    public List<List<CourseActivity>> parseCourses(List<String> courseNames){
        ExcelReader excelReader = new ExcelReader(filePath);
        XSSFSheet dataSheet = excelReader.getData();

        Iterator<Row> rowIt = dataSheet.iterator();
        Map<String, List<CourseActivity>> returnList = new HashMap<>();

        //skip the header of excel file
        rowIt.next();

        while(rowIt.hasNext()) {
            Row row = rowIt.next();

            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            if(row.getCell(0) == null)
                continue;

            String courseName = row.getCell(0).toString().toUpperCase();
            int courseNum = (int)row.getCell(1).getNumericCellValue();

            if(!courseNames.contains(courseName.toUpperCase() + courseNum))
                continue;

            String lab = row.getCell(3) == null ? " " : row.getCell(3).toString();
            String tutorial = row.getCell(4) == null ? " " : row.getCell(4).toString();
            String discussion = row.getCell(5) == null ? " " : row.getCell(5).toString();
            UBC_CourseSection currSection = new UBC_CourseSection((int)row.getCell(2).getNumericCellValue(), lab, tutorial, discussion);

            List<Duration> durList = new ArrayList<>();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                TimeInstance currentStart;
                TimeInstance currentEnd;

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

            //add the new course to a map
            if (returnList.containsKey(courseName + courseNum))
                    returnList.get(courseName + courseNum).add(new CourseActivity(courseName, courseNum, currSection, durList));
            else
                returnList.put(courseName + courseNum, new ArrayList<>(Collections.singletonList(new CourseActivity(courseName, courseNum, currSection, durList))));

        }
        excelReader.closeFile();
        return new ArrayList<>(returnList.values());
    }

}
