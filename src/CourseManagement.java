import com.jfoenix.controls.JFXSnackbar;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import model.Course;

import java.util.function.Predicate;

public class CourseManagement {
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
    Parent courseQuery;
    @FXML
    CourseGrid courseGridController;
    @FXML
    CourseQuery courseQueryController;

    @FXML Pane pane;
    ListProperty<Course> courses;

    JFXSnackbar bar;

    public Boolean isCourseInList(Course target){
        String courseNo = target.getCourseNo();
        String teacherNo = target.getTeacherNo();
        for(Course c:courses){
            if(c.getCourseNo() == courseNo && c.getTeacherNo() == teacherNo){
                return false;
            }
        }
        return true;
    }

    public void selectCourse(Integer index) {
        Course course = (Course) courseTable.getItems().get(index);

        if (course.getStatus() == "已选入") {
            deleteCourse(index);
//            bar.close();

//            bar.show("该课程已在课表中",1000);
        } else if (!courseGridController.checkAvalble(course.getPositions())) {
            bar.close();
            bar.show("课时冲突", 1000);
        } else {
            course.setStatus("已选入");
            courses.removeIf(new Predicate<Course>() {
                public boolean test(Course c) {
                    return c.getCourseNo() == course.getCourseNo() && c.getTeacherNo() == course.getTeacherNo();
                }
            });
            courses.add(index, course);
        }

    }

    public void deleteCourse(Integer index) {
        Course course = (Course) courseTable.getItems().get(index);
        courses.removeIf(new Predicate<Course>() {
            public boolean test(Course c) {
                return c.getCourseNo() == course.getCourseNo() && c.getTeacherNo() == course.getTeacherNo();
            }
        });
        if (course.getStatus() == "已选入") {
            course.setStatus("");
            courses.add(index, course);
        }
    }

    public void  initialize() throws Exception{
        bar = new JFXSnackbar(pane);
        System.out.println(courseGridController);
        courseQueryController.initialize();
        courses = new SimpleListProperty<Course>();
        this.courses.setValue(Main.courseFactory());
        courseGridController.bindCourses(courses);
        System.out.println("Father init");
        courseQueryController.slectedCourseProperty.addListener((o, old, nVal)->{
            System.out.println(nVal);
            if(isCourseInList(nVal)){
                courses.add(nVal);
            } else {
                bar.close();
                bar.show("该课程已在课程列表中",1000);
//                bar.enqueue(new JFXSnackbar.SnackbarEvent("该课程已在课程列表中"));
            }
        });
        courseTable.setRowFactory(tv -> {
            TableRow<Course> row = new TableRow<>();
            row.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.DELETE) {
                    Integer index = courseTable.getSelectionModel().getSelectedIndex();
                    deleteCourse(index);
                }
            });
            return row;
        });
        courseTable.setItems(courses);
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("选入");
        cm.getItems().add(mi1);
        mi1.setOnAction(e-> {
            Integer index = courseTable.getSelectionModel().getSelectedIndex();
            selectCourse(index);
        });
        MenuItem mi2 = new MenuItem("移除");
        cm.getItems().add(mi2);
        mi2.setOnAction(e->{
            Integer index = courseTable.getSelectionModel().getSelectedIndex();
            deleteCourse(index);
        });
        courseTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    cm.show(courseTable, t.getScreenX(), t.getScreenY());
                }
            }
        });

        courseTable.setRowFactory(tv -> {
            TableRow<Course> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Integer index = row.getIndex();
                    selectCourse(index);
                }
            });
            return row;
        });

//        courses.add(new Course("0932SY01","机电系统创新实践","2","1000","蔡红霞","五7-9 含实验","不开",0,0,"","","","",""));
//        System.out.println(courseGrid.getParent());
//        Parent courseGrid = FXMLLoader.load(getClass().getResource("/main/CourseGrid.fxml"));
//        sp.getChildren().add(courseGrid);
//        sp.getItems().add(0,courseGrid);
//        coursePane.getChildren().add(courseGrid);
//        sp.getItems().add(courseGrid);
    }
}
