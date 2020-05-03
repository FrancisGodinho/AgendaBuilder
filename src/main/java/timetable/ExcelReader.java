package main.java.timetable;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    private String path;
    private FileInputStream fis;
    private XSSFWorkbook workbook;

    public ExcelReader(String path){
        this.path = path;
    }

    public XSSFSheet getData(int sheetNum){

        File excelFile = new File(path);

        try{
            fis = new FileInputStream(excelFile);
        } catch (IOException e){
            System.out.println("Failed to open file");
        }

        try {
            // we create an XSSF Workbook object for our XLSX Excel File
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e){
            System.out.println("Failed to open worksheet");
        }

        // we get the correct sheet
        return workbook.getSheetAt(sheetNum);
    }

    public XSSFSheet getData(){
        return this.getData(0);
    }

    public boolean closeFile(){

        try {
            workbook.close();
        }catch (IOException e){
            System.out.println("Unable to close workbook");
        }
        try {
            fis.close();
        }catch (IOException e){
            System.out.println("Unable to close file");
        }
        return true;

    }



}
