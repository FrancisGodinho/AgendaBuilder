package main.java.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import main.java.activity.CourseActivity;
import main.java.parser.ExcelParser;
import main.java.parser.UBCExcelParser;
import main.java.timetable.AgendaBuilder;

import java.util.ArrayList;
import java.util.List;


public class MainGUI extends Application {

    private Stage window;

    private ExcelParser xlParcer = new UBCExcelParser("course_data\\course_data.xlsx");

    private final String backgroundColor = "#ECECED", secondaryColor = "#324ea8";
    private final int width = 1000, height = 1000;

    private final TimeTableComponent tableComp = new TimeTableComponent((int)(0.67 * width), height + 10, this.backgroundColor , this.secondaryColor);
    private final AddCourseComponent addCourseComponent = new AddCourseComponent((int)(0.30 * width), height - 100, this.secondaryColor, this.backgroundColor, 7);

    private GridPane table;
    private final GridPane mainGrid = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("AgendaBuilder");
        window.getIcons().add(new Image("File:img\\AgendaBuilderFavicon.JPG"));
        window.setResizable(false);

        //create timetable and add course components
        this.table = this.tableComp.draw(new ArrayList<>());
        GridPane courseSelection = this.addCourseComponent.draw();

        //add to maingrid
        this.mainGrid.add(this.table, 1, 0, 1,2);
        this.mainGrid.add(courseSelection, 0, 0);
        this.mainGrid.add(generateComp(), 0, 1);

        //set style
        this.mainGrid.setStyle("-fx-background-color: " + this.backgroundColor);

        Scene scene = new Scene(mainGrid, width, height);
        this.window.setScene(scene);
        this.window.show();
    }

    private void generate(){

        //get the selected courses
        List<String> courseNames = this.addCourseComponent.getSelectedCourses();
        System.out.println(courseNames);
        this.addCourseComponent.clearList();

        //build a new agenda
        AgendaBuilder ab = new AgendaBuilder(this.xlParcer);
        List<CourseActivity> courses = ab.buildAgenda(courseNames);

        //remove current table from maingrid and add a new table
        this.mainGrid.getChildren().remove(this.table);
        this.table = this.tableComp.draw(courses);
        this.mainGrid.add(this.table, 1, 0, 1, 2);
    }

    private StackPane generateComp(){
        Button generate = new Button("Generate");

        generate.setStyle("-fx-background-color:" + this.backgroundColor + ";" + "-fx-font-size: 30;" + "-fx-border-color: grey;");
        generate.setOnAction(e->generate());
        generate.setMinWidth(0.3 * this.width - 20);
        generate.setMinHeight(60);

        StackPane generateComp = new StackPane();
        generateComp.setPadding(new Insets(10, 10, 25 ,10));
        generateComp.setStyle("-fx-background-color: " + this.secondaryColor);
        generateComp.getChildren().add(generate);

        return generateComp;
    }
}
