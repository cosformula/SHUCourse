import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Course;

public class MyCourses {
//    public ListPropertyBase<Course> courses  = new ListPropertyBase<Course>();
    public static Course[] courses;
    public static Color[] colors;
    @FXML
    HBox hbox;
    @FXML
    VBox vbox;
    public void initialize() throws Exception {
        Parent courseGrid = FXMLLoader.load(getClass().getResource("/CourseGrid.fxml"));
        System.out.println(courseGrid);
        vbox.getChildren().add(courseGrid);
        vbox.setVgrow(courseGrid,Priority.ALWAYS);
    }
}
