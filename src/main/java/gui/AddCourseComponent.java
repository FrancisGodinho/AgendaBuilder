package main.java.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AddCourseComponent {

    private final GridPane addCourseComp = new GridPane();
    private final VBox courseList = new VBox();

    private final int height, width, packageHeight = 50, controlButtonHeight = 20, packagePadding = 10;
    private final String backgroundColor, secondaryColor;

    private final int maxNumCourses;

    private final Map<String, Set<String>> courseMap = new HashMap<>();
    private final Set<String> selectedCourses = new HashSet<>();

    public AddCourseComponent(int width, int height, String backgroundColor, String secondaryColor, int maxNumCourses){
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.secondaryColor = secondaryColor;
        this.maxNumCourses = maxNumCourses;
    }

    /**
     * Create a GUI so that the user is able to add the courses which will be used to generate the timetable
     * @return a GridPane object that represents the addCourse component of the GUI
     */
    public GridPane draw(){

        initCourseMap();

        this.addCourseComp.setStyle("-fx-background-color: " + this.backgroundColor);
        this.addCourseComp.setMinHeight(this.height);
        this.addCourseComp.setMinWidth(this.width);
        this.addCourseComp.setPadding(new Insets(20, 0, 20, 0));
        this.addCourseComp.setVgap(20);

        this.courseList.setSpacing(20);
        this.courseList.setPadding(new Insets(this.packagePadding));
        this.courseList.getChildren().add(addPackage());

        this.addCourseComp.add(logo(), 0, 0);
        this.addCourseComp.add(this.courseList, 0, 1);

        return this.addCourseComp;
    }

    /**
     * Get all the users selected courses. This should also clear/reset the add user component
     * @return a list of all the courses that the user has selected
     */
    public List<String> getSelectedCourses(){
        return new ArrayList<>(this.selectedCourses);
    }

    /**
     * Clear the list of selected courses
     */
    public void clearList(){
        int numItems = this.courseList.getChildren().size();

        for(int i = 0; i < numItems - 1; i++)
            this.courseList.getChildren().remove(0);

        this.selectedCourses.clear();
    }

    /**
     * Create a new logo
     */
    private ImageView logo(){
        ImageView logo = new ImageView(new Image("File:img\\AgendaBuilderHeader.png"));
        logo.setPreserveRatio(true);
        logo.setFitWidth(this.width + 26);

        return logo;
    }

    /**
     * Create a label package containing the course name
     * to the addCourse Component
     * @return A GridPane representing the label package
     */
    private GridPane labelPackage(String courseName, String courseNum){
        GridPane labelPackage = new GridPane();
        labelPackage.setStyle("-fx-background-color: " + this.secondaryColor);

        //image
        ImageView view = new ImageView(new Image("File:img/exit_button.PNG"));
        view.setFitHeight(this.controlButtonHeight);
        view.setPreserveRatio(true);

        //exit button
        Button exit = new Button();
        exit.setGraphic(view);
        exit.setStyle("-fx-background-color: " + this.secondaryColor);
        exit.setTooltip(new Tooltip("Delete Course"));
        exit.setOnAction(e->{
            this.courseList.getChildren().remove(labelPackage);
            this.selectedCourses.remove(courseName + courseNum);
        });

        //label text
        Label courseInfo = new Label(courseName + " " + courseNum);
        courseInfo.setAlignment(Pos.CENTER);
        courseInfo.setMinWidth(this.width - this.controlButtonHeight - this.packagePadding);
        courseInfo.setMinHeight(this.packageHeight);
        courseInfo.setPadding(new Insets(0, 0, 0, this.controlButtonHeight + this.packagePadding));
        courseInfo.setFont(new Font(20));

        labelPackage.add(courseInfo, 0, 0);
        labelPackage.add(exit, 1, 0);

        return labelPackage;
    }

    /**
     * Generate a package which allows the user to remove it and
     * see course information (should contain a labelPackage)
     * @return A GridPane representing the package
     */
    private GridPane addPackage(){
        GridPane addPackage = new GridPane();
        addPackage.setStyle("-fx-background-color: " + this.secondaryColor);
        addPackage.setMinHeight(this.packageHeight);
        addPackage.setAlignment(Pos.CENTER);

        //image
        ImageView view = new ImageView(new Image("File:img/add_button.png"));
        view.setFitHeight(this.controlButtonHeight);
        view.setPreserveRatio(true);

        //course list
        ChoiceBox<String> courseNames = new ChoiceBox<>();
        courseNames.setMinWidth(0.4 * (this.width - 2 * this.packagePadding));
        courseNames.setMinHeight(0.6 * this.packageHeight);
        courseNames.getItems().addAll(this.courseMap.keySet());

        //course number
        ChoiceBox<String> courseNums = new ChoiceBox<>();
        courseNums.setMinWidth(0.25 * (this.width - 2 * this.packagePadding));
        courseNums.setMinHeight(0.6 * this.packageHeight);
        courseNums.getItems().addAll();

        //set course numbers
        courseNames.setOnAction(e->setCourseNums(courseNums, courseNames.getValue()));

        //add button
        Button add = new Button();
        add.setGraphic(view);
        add.setStyle("-fx-background-color: " + this.secondaryColor);
        add.setTooltip(new Tooltip("Add Course"));
        add.setOnAction(e->addCourse(courseNames, courseNums, addPackage));

        //add to package
        HBox courseInfoBox = new HBox();
        courseInfoBox.setAlignment(Pos.CENTER);
        courseInfoBox.setMinWidth(this.width - this.controlButtonHeight - this.packagePadding);
        courseInfoBox.setSpacing(30);
        courseInfoBox.getChildren().addAll(courseNames, courseNums);

        addPackage.add(courseInfoBox, 0, 0);
        addPackage.add(add, 1, 0);
        return addPackage;
    }

    /**
     * Adds a course to the list
     * @param courseNames the ComboBox which contains the selected course name
     * @param courseNums the ComboBox which contains the selected course number
     * @param addPackage the addPackage component
     */
    private void addCourse(ChoiceBox<String> courseNames, ChoiceBox<String> courseNums, GridPane addPackage){

        if(this.courseList.getChildren().size() == this.maxNumCourses + 1){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot add more than " + this.maxNumCourses + " courses.", ButtonType.CLOSE);
            alert.setTitle("Error");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("File:img\\AgendaBuilderFavicon.JPG"));
            alert.showAndWait();
            return;
        }

        String courseName = courseNames.getSelectionModel().getSelectedItem();
        String courseNum = courseNums.getSelectionModel().getSelectedItem();

        if(courseNum == null)
            return;
        if(this.selectedCourses.contains(courseName + courseNum)){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Course has already been added.", ButtonType.CLOSE);
            alert.setTitle("Error");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("File:img\\AgendaBuilderFavicon.JPG"));
            alert.showAndWait();
            return;
        }

        courseNames.getSelectionModel().clearSelection();
        courseNums.getSelectionModel().clearSelection();

        this.courseList.getChildren().remove(addPackage);
        this.courseList.getChildren().add(labelPackage(courseName, courseNum));
        this.courseList.getChildren().add(addPackage);

        this.selectedCourses.add(courseName + courseNum);
    }

    /**
     * Retrive all the possible course numbers that correlate with the courseName
     * and then adds it to the ChoiceBox
     * @param courseNums the ChoiceBox that the course numbers will be added to
     * @param courseName the name of the course used to determine the course numbers
     */
    private void setCourseNums(ChoiceBox<String> courseNums, String courseName){
        if(courseName == null){
            courseNums.getItems().clear();
            return;
        }
        courseNums.getItems().clear();
        courseNums.getItems().addAll(this.courseMap.get(courseName));
    }

    /**
     * Reads an excel file named "courses.txt" which contains all the possible
     * courses that can be selected. Then, parses the data and stores it in a
     * map for future use.
     */
    private void initCourseMap(){
        try {
            File myObj = new File("course_data\\courses.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitData = data.split("\\s+");
                String courseName = splitData[0];
                String courseNum = splitData[1];

                if(!this.courseMap.containsKey(courseName))
                    this.courseMap.put(courseName, new HashSet<>(Arrays.asList(courseNum)));
                else
                    this.courseMap.get(courseName).add(courseNum);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error reading file: 'courses.txt'");
        }
    }

}
