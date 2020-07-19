package main.java.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MainGUI extends Application {

    private Stage window;

    private final String backgroundColor = "#ECECED", secondaryColor = "#324ea8";
    private final int width = 1000, height = 1000;
    private final TimeTableComponent tableComp = new TimeTableComponent((int)(0.67 * width), height + 10, this.backgroundColor , this.secondaryColor);
    private final GridPane mainGrid = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("AgendaBuilder");
        window.setResizable(false);


        //left component
        GridPane cComp = new GridPane();
        cComp.setPadding(new Insets(500, 150, 500, 150));
        cComp.setStyle("-fx-background-color: " + this.secondaryColor);

        //table component
        GridPane table = this.tableComp.draw(null);

        //add to mainGrid
        this.mainGrid.add(table, 1, 0);
        this.mainGrid.add(cComp, 0, 0);

        //set style and
        this.mainGrid.setStyle("-fx-background-color: " + this.backgroundColor);

        Scene scene = new Scene(mainGrid, width, height);
        this.window.setScene(scene);
        this.window.show();
    }
}
