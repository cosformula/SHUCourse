import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import model.Course;
import model.Position;

import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;

public class CourseGrid  implements Initializable {
    @FXML
    GridPane gp;
    ListProperty<Course> courses;
    Boolean[][] timeTable;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        timeTable = new Boolean[][]{
                {true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true,true,true,true,true,true,true}
        };
        String[] colors = {"#2B2E4A", "#521262", "#903749", "#53354A", "#40514E", "#537780", "#3765a4", "#76a5a4", "#579870", "#e391b4", "#b8954e"};
        String[] weekdays = {"周一","周二","周三","周四","周五"};
        for(Integer i=0;i<14;i++){
            for(Integer j=0;j<6;j++){
                StackPane pane = new StackPane();
                pane.setStyle("-fx-background-color: whitesmoke;");
                if(i==0 && j!=0){
                    Label lb = new Label(weekdays[j-1]);
                    pane.getChildren().add(lb);
                }
                if(j==0&&i!=0){
                    pane.getChildren().add(new Label(i.toString()));
                }
                gp.add(pane,j,i);
            }
        }
        courses = new SimpleListProperty<Course>();
        courses.addListener((ListChangeListener<Course>) (change)-> {
            System.out.println("Detected a change! ");
            while (change.next()) {
                System.out.println(change.getAddedSize());
                System.out.println(change.getAddedSubList());
                System.out.println("Was added? " + change.wasAdded());
                System.out.println("Was removed? " + change.wasRemoved());
                System.out.println("Was replaced? " + change.wasReplaced());
                System.out.println("Was permutated? " + change.wasPermutated());
                for(Course course:change.getAddedSubList()){
                    if(course.getStatus()!="selected"){
                        continue;
                    }
                    else if(checkAvalble(course.getPositions())){
                        fillTimeTable(course.getPositions());
                    } else {
                        continue;
                    }
                    Random rand = new Random();
                    String color = colors[rand.nextInt(11)];
                    for(Position position:course.getPositions()){
                        VBox pane = new VBox();
                        pane.setAlignment(Pos.CENTER);
                        pane.setStyle("-fx-background-color:"+color+";");
                        Label courseLb = new Label(course.getCourseName());
                        Label teacherLb = new Label("("+course.getTeacherName()+")");
                        courseLb.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
                        teacherLb.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
                        pane.getChildren().addAll(courseLb,teacherLb);
                        gp.add(pane,position.getDay(),position.getStart(),1,position.getRowSpan());
                    }
                }
            }
        });
//        courses.setValue(main.Main.courseFactory());
    }
    public Boolean checkAvalble(LinkedList<Position> positions){
        for(Position position:positions) {
            Integer day=position.getDay();
            Integer end = position.getStart()+position.getRowSpan();
            for(Integer p=position.getStart();p<end;p++){
                System.out.println(day);
                System.out.println(p);
                if(timeTable[day-1][p-1] == false){
                    return false;
                }
            }
        }
        return true;
    }
    public void fillTimeTable(LinkedList<Position> positions) {
        for(Position position:positions) {
            Integer day=position.getDay();
            Integer end = position.getStart()+position.getRowSpan();
            for(Integer p=position.getStart();p<end;p++){
                timeTable[day-1][p-1] = false;
            }
        }
    }
    public void bindCourses(ListProperty<Course> courses){
        this.courses.bindBidirectional(courses);
    }
    public GridPane getPane(){
        return gp;
    }

    public void setCourses(ObservableList<Course> courses) {
        this.courses.setValue(courses);
//        this.courses.set(courses);
    }
}
