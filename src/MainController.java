import com.jfoenix.controls.JFXSnackbar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {
    Stage stage;
    Scene loginScene, mainScene;
    @FXML
    AnchorPane pane;
    @FXML private VBox leftVBoxPane;
    @FXML private BorderPane bp;
    private Parent[] subScenes;
    public JFXSnackbar bar;

    public void initialize() throws Exception{
        bar = new JFXSnackbar(pane);
        subScenes = new Parent[7];
        subScenes[0] = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        subScenes[1] = FXMLLoader.load(getClass().getResource("MyCourses.fxml"));
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
                    try{
                        switchCenter(vb.getId());
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

                });
                idIndex+=1;
            }
        }
    }

    public JFXSnackbar getBar(){
        return bar;
    }

    private void switchCenter(String index) throws  Exception{
        Integer i = Integer.parseInt(index);
        if(subScenes[i] == null) {
            switch (i){
                case 1:subScenes[1] = FXMLLoader.load(getClass().getResource("MyCourses.fxml")); break;
                case 2:subScenes[2] = FXMLLoader.load(getClass().getResource("CourseManagement.fxml")); break;
                case 3:subScenes[3] = FXMLLoader.load(getClass().getResource("CourseNote.fxml")); break;
                case 4:subScenes[4] = FXMLLoader.load(getClass().getResource("CourseQuery.fxml")); break;
                case 5:subScenes[5] = FXMLLoader.load(getClass().getResource("GradeManagement.fxml")); break;
                case 6:subScenes[6] = FXMLLoader.load(getClass().getResource("AboutUs.fxml")); break;
            }

        }

        bp.setCenter(subScenes[Integer.parseInt(index)]);
    }
}
