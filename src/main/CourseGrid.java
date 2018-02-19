package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import model.Course;

public class CourseGrid {
    @FXML
    GridPane gp;
    public void initialize() throws Exception {
    }
    public GridPane getPane(){
        return gp;
    }
}
