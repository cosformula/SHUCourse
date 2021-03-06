import com.jfoenix.controls.JFXSnackbar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXBadge;
import model.Course;
import model.Student;
import okhttp3.MediaType;

import java.util.*;

public class Main extends Application {
    JFXBadge badge;
    Stage stage;
    Scene loginScene, mainScene;
    @FXML private VBox leftVBoxPane;
    @FXML private BorderPane bp;
    private Parent[] subScenes;
    public static Property<Student>  student;
    public static final String HOST = "http://shuhelper.cn:5000";
    public static void setStudent(Student stu){
        student.setValue(stu);
    }
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static ObservableList<Course> courseFactory() {
        List<Course> courseList = new ArrayList<Course>();
//        courseList.add(new Course("09326140","产品数据管理","2","1000","蔡红霞","一7-8 三9-10 含上机","不开",0,0,"","","","",""));
//        courseList.add(new Course("09326149","计算机辅助设计A","2","1000","蔡红霞","一11-13 三5-6 上机","不开",0,0,"","","","",""));
//        courseList.add(new Course("09326153","虚拟制造技术A","2","1000","蔡红霞","一9-10 三7-10 实验","不开",0,0,"","","","",""));
//        courseList.add(new Course("0932SY01","机电系统创新实践","2","1000","蔡红霞","五7-9 含实验","不开",0,0,"","","","",""));
        ObservableList<Course> coursesListObservable = FXCollections.observableList(courseList);
        return coursesListObservable;
    }
    public void start(Stage primaryStage) throws Exception{
        student  = new SimpleObjectProperty<Student>();
        //        stage.setResizable(false);
        Stage dialog = new Stage();
        dialog.setScene(Login.getLoginDialog(dialog));
        dialog.initOwner(stage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setOnCloseRequest(e->{
            Platform.exit();
        });
        dialog.showAndWait();

        stage = primaryStage;
//        stage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage.setTitle("SHU Course Helper");
//        JFXDecorator decorator = new JFXDecorator(stage, root);
//        decorator.setCustomMaximize(false);
//        decorator.setText("SHU Course Helepr");
//        decorator.setText("SHU model.Course Helper");
        mainScene = new Scene(root, 1024, 728);
        stage.setScene(mainScene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void switchCenter(String index){
        bp.setCenter(subScenes[Integer.parseInt(index)]);
    }
}
