package main.java.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;

public class AddCourseComponent {


    private final GridPane addCourseComp = new GridPane();

    private int height, width;
    private String backgroundColor, secondaryColor;

    private int maxNumCourses;

    public AddCourseComponent(int width, int height, String backgroundColor, String secondaryColor, int maxNumCourses){
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.secondaryColor = secondaryColor;
        this.maxNumCourses = maxNumCourses;
    }

    public GridPane draw(){
        //TODO: Complete this method

        //This is example code that creates a blue rectangle
        Label rectangle = new Label("Add Course Component");
        rectangle.setAlignment(Pos.CENTER);
        rectangle.setMinHeight(this.height);
        rectangle.setMinWidth(this.width);
        rectangle.setStyle("-fx-background-color: " + this.secondaryColor);

        this.addCourseComp.add(rectangle, 0, 0);

        return this.addCourseComp;
    }

    public List<String> getSelectedCourses(){
        //TODO: Complete this method
        return null;
    }


}
