package main.java.gui;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import main.java.activity.CourseActivity;
import main.java.util.Day;
import main.java.util.Duration;

import java.util.*;


public class TimeTableComponent{

    private GridPane table = new GridPane();

    private int height, width;
    private String backgroundColor, secondaryColour;

    private final int maxElements = 30, timeMinX = 40;
    private int elementWidth;
    private int elementHeight;

    private final int courseElemFontSize = 12;
    private final List<String> elementColors = new ArrayList<>(Arrays.asList("#e36c6c", "#6e7ffb", "#fb6ed1", "#f0a262", "#52e171", "#8634D3", "#34C9D3"));

    public TimeTableComponent(int width, int height, String backgroundColor, String secondaryColor){
        this.height = height;
        this.width = width;
        this.backgroundColor = backgroundColor;
        this.secondaryColour = secondaryColor;

        this.elementWidth = (this.width - this.timeMinX) / 5;
        this.elementHeight = this.height / this.maxElements;
    }

    /**
     * Draw the timetable
     * @param courses a list of courses which should be placed in the table
     * @return a GridPane element which is the timetable that has been created
     */
    public GridPane draw(List<CourseActivity> courses){
        table = new GridPane();
        table.setStyle("-fx-background-color: " + this.backgroundColor);

        addHeaders();

        List<Day> days = new ArrayList<>(Arrays.asList(Day.MON, Day.TUES, Day.WED, Day.THURS, Day.FRI));

        //create an empty table
        for(Day day : days)
            for(int i = 0; i < this.maxElements - 2; i++)
                table.add(emptyElement(), days.indexOf(day) + 1, i + 1);

        // add courses to table
        Map<Duration, CourseActivity> timeCourseMap = timeCourseMap(courses);
        for(Duration duration : timeCourseMap.keySet()) {

            String color = this.elementColors.get(courses.indexOf(timeCourseMap.get(duration)) % this.elementColors.size());
            int row = 2 * duration.getFirstTime().getHour() - 15 + duration.getFirstTime().getMin() / 30; // row formula: f(hour, min) = 2 * (hour - 7) - 1 + (min / 30) - 2
            int col = days.indexOf(duration.getFirstTime().getDay()) + 1;

            //remove first and second elements and add course + section information
            removeElement(row, col);
            table.add(courseElement(timeCourseMap.get(duration), color), col, row);
            if(duration.getDurationMin() > 30) {
                removeElement(row + 1, col);
                table.add(courseElementSection(timeCourseMap.get(duration), color), col, row + 1);
            }

            //fill in remaining space with blank, colored elements
            for (int i = 2; i < duration.getDurationMin() / 30; i++) {
                removeElement(row + i, col);
                table.add(emptyElement(color), days.indexOf(duration.getFirstTime().getDay()) + 1, row + i);
            }
        }

        return table;
    }

    /**
     * Removes an element from the table
     * @param row the row which the element is located in
     * @param col the column which the element is located in
     */
    private void removeElement(int row, int col){
        ObservableList<Node> childrens = table.getChildren();
        for(Node node : childrens)
            if(table.getRowIndex(node) == row && table.getColumnIndex(node) == col) {
                table.getChildren().remove(node);
                break;
            }

    }

    /**
     * Generates a map with durations as keys and courses as values
     * @param courses the courses from which the map will be created
     * @return a timeCourse map
     */
    private Map<Duration, CourseActivity> timeCourseMap(List<CourseActivity> courses){
        Map<Duration, CourseActivity> timeCourseMap = new HashMap<>();

        for(CourseActivity course : courses){
            List<Duration> durations = course.getCourseTimes();
            for(Duration duration : durations)
                timeCourseMap.put(duration, course);
        }

        return timeCourseMap;
    }

    /**
     * Add the days of the week and the times (creating an empty table)
     */
    private void addHeaders(){

        List<String> days = new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));

        for(int i = 0; i < (this.maxElements - 2) / 2; i++){
            Label time = new Label(i + 8 + ":00");
            time.setAlignment(Pos.CENTER_RIGHT);
            time.setMinWidth(this.timeMinX);
            time.setMinHeight(2 * this.elementHeight);
            time.setPadding(new Insets(0, 5, 0, 5));
            table.add(time, 0, 2 * i, 1, 2);
        }
        for(int i = 1; i < days.size() + 1; i++){
            Label day = new Label(days.get(i - 1));
            day.setAlignment(Pos.CENTER);
            day.setMinHeight(this.elementHeight);
            day.setMinWidth(this.elementWidth);
            day.setPadding(new Insets(3, 0, 3, 0));
            table.add(day, i, 0);
        }
    }

    /**
     * Return a course element with a background color of color
     * @param course the course name
     * @param color the color of the background
     * @return a Label which represents a course element
     */
    private Label courseElement(CourseActivity course, String color){
        Label elem = new Label(course.getCourseName() + " " + course.getCourseNum());
        elem.setAlignment(Pos.CENTER);
        elem.setFont(new Font(elem.getFont().getName(), this.courseElemFontSize));

        elem.setMinHeight(this.elementHeight);
        elem.setMinWidth(this.elementWidth);
        elem.setStyle("-fx-background-color: " + color);
        return elem;
    }

    /**
     * Return a course section element with a background color of color
     * @param course the course name
     * @param color the color of the background
     * @return a Label which represents a course element
     */
    private Label courseElementSection(CourseActivity course, String color){
        Label elem = new Label(courseSectionText(course));
        elem.setAlignment(Pos.CENTER);
        elem.setFont(new Font(elem.getFont().getName(), this.courseElemFontSize - 1));

        elem.setMinHeight(this.elementHeight);
        elem.setMinWidth(this.elementWidth);
        elem.setStyle("-fx-background-color: " + color);
        return elem;
    }

    /**
     * Generate text for a course section (only lab, tutorial and discussion section)
     * @param course the course used to generate the section text
     * @return the text that represents the section
     */
    private String courseSectionText(CourseActivity course){

        List<String> sections = course.getCourseSection().getSections();
        String text = " " + Integer.toString(course.getCourseSection().getLecture());
        for(String section : sections)
            text += " " + section;

        return text;
    }

    /**
     * Create an empty element with a border and with a default color
     * @return an empty element
     */
    private Label emptyElement(){
        Label elem = emptyElementNoBorder();
        elem.setStyle("-fx-border-color: " + this.secondaryColour + "; -fx-border-style: dotted;");
        return elem;
    }

    /**
     * Create an empty element with a border and with a unique background color
     * @param color the background color of the element
     * @return an empty element with a unique color (no border)
     */
    private Label emptyElement(String color){
        Label elem = emptyElementNoBorder();
        elem.setStyle("-fx-background-color: " + color + ";");
        return elem;
    }

    /**
     * Create an empty element without a border and with a default color
     * @return an empty element without a border
     */
    private Label emptyElementNoBorder(){
        Label elem = new Label();
        elem.setMinHeight(this.elementHeight);
        elem.setMinWidth(this.elementWidth);
        elem.setStyle("-fx-background-color: " + this.backgroundColor);
        return elem;
    }

}
