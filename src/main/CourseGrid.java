package main;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.*;
import model.Course;
import model.Position;

import java.util.Random;

public class CourseGrid {
    @FXML
    GridPane gp;
    ListProperty<Course> courses;
    public void initialize() throws Exception {
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
                    Random rand = new Random();
                    String color = colors[rand.nextInt(11)];
                    for(Position position:course.getPositions()){
                        VBox pane = new VBox();
                        pane.setAlignment(Pos.CENTER);
                        pane.setStyle("-fx-background-color:"+color+";");
                        Label courseLb = new Label(course.getCourseName());
                        Label teacherLb = new Label("("+course.getTeacher_name()+")");
                        courseLb.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
                        teacherLb.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
                        pane.getChildren().addAll(courseLb,teacherLb);
                        gp.add(pane,position.getDay(),position.getStart(),1,position.getRowSpan());
                    }
                }
            }
        });
        courses.setValue(main.Main.courseFactory());

        courses.add(new Course("0932SY01","机电系统创新实践","2","1000","蔡红霞","五7-9 含实验","不开",0,0,"","","","",""));
    }
    public GridPane getPane(){
        return gp;
    }

    public void setCourses(ObservableList<Course> courses) {
        this.courses.set(courses);
    }
}
