import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import model.Course;

public class courseManagement {
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
    @FXML
    Parent courseGrid;
    @FXML
    CourseGrid courseGridController;
    ListProperty<Course> courses;
    public void  initialize() throws Exception{
        System.out.println(courseGridController);
        courses = new SimpleListProperty<Course>();
        this.courses.setValue(Main.courseFactory());
        courseGridController.bindCourses(courses);
        courseTable.setItems(courses);
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Menu 1");
        cm.getItems().add(mi1);
        mi1.setOnAction(e->{
            Course course = (Course)courseTable.getSelectionModel().getSelectedItem();
            System.out.println(course.getCourseName());
        });
        MenuItem mi2 = new MenuItem("Menu 2");
        cm.getItems().add(mi2);

        courseTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    cm.show(courseTable, t.getScreenX(), t.getScreenY());
                }
            }
        });
        courses.add(new Course("0932SY01","机电系统创新实践","2","1000","蔡红霞","五7-9 含实验","不开",0,0,"","","","",""));
//        System.out.println(courseGrid.getParent());
//        Parent courseGrid = FXMLLoader.load(getClass().getResource("/main/CourseGrid.fxml"));
//        sp.getChildren().add(courseGrid);
//        sp.getItems().add(0,courseGrid);
//        coursePane.getChildren().add(courseGrid);
//        sp.getItems().add(courseGrid);
    }
}
