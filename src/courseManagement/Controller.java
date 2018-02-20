package courseManagement;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import main.CourseGrid;

public class Controller {
    @FXML
    HBox hbox;
    @FXML
    AnchorPane courseAnchor;
    @FXML
    SplitPane sp;
//    @FXML
//    Region courseRegion;
    @FXML
    Pane coursePane;
    @FXML
    TableView courseTable;
    public void  initialize() throws Exception{
        Parent courseGrid = FXMLLoader.load(getClass().getResource("/main/CourseGrid.fxml"));
//        sp.getChildren().add(courseGrid);
        sp.getItems().add(0,courseGrid);
        courseTable.setItems(main.Main.courseFactory());
//        coursePane.getChildren().add(courseGrid);
//        sp.getItems().add(courseGrid);
    }
}
