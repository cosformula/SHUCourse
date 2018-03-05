import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {
    Stage stage;
    Scene loginScene, mainScene;
    @FXML private VBox leftVBoxPane;
    @FXML private BorderPane bp;
    private Parent[] subScenes;
    public void initialize() throws Exception{
        subScenes = new Parent[7];
        subScenes[0] = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        subScenes[1] = FXMLLoader.load(getClass().getResource("MyCourses.fxml"));
        subScenes[2] = FXMLLoader.load(getClass().getResource("CourseManagement.fxml"));
        subScenes[3] = FXMLLoader.load(getClass().getResource("CourseNote.fxml"));
        subScenes[4] = FXMLLoader.load(getClass().getResource("CourseQuery.fxml"));
        subScenes[5] = FXMLLoader.load(getClass().getResource("GradeManagement.fxml"));
        subScenes[6] = FXMLLoader.load(getClass().getResource("AboutUs.fxml"));
        bp.setCenter(subScenes[0]);
        Integer idIndex=0;
        String[] ids = {"welcome","myCourses"};
        for(Node nd:leftVBoxPane.getChildren()){
            if( nd instanceof VBox) {
                VBox vb = (VBox) nd;
                vb.setFillWidth(true);
                vb.setId(idIndex.toString());
                Label lb = (Label) vb.getChildren().get(1);
                lb.getStyleClass().add("white-text");
                vb.setOnMouseEntered(e->{
                    vb.setScaleX(1.5);
                    vb.setScaleY(1.5);
                });
                vb.setOnMouseExited(e->{
                    vb.setScaleX(1);
                    vb.setScaleY(1);
                });
                vb.setOnMouseClicked(e->{
                    System.out.println(vb.getId());
                    switchCenter(vb.getId());
                });
                idIndex+=1;
            }
        }
    }
    private void switchCenter(String index){
        bp.setCenter(subScenes[Integer.parseInt(index)]);
    }
}
