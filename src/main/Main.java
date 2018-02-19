package main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import login.Login;

public class Main extends Application {
    Stage stage;
    Scene loginScene, mainScene;
    @FXML private VBox leftVBoxPane;
    @FXML private BorderPane bp;
    private Parent[] subScenes;
    public void initialize() throws Exception{
        subScenes = new Parent[7];
        subScenes[0] = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        subScenes[1] = FXMLLoader.load(getClass().getResource("../myCourses/View.fxml"));
        subScenes[2] = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        subScenes[3] = FXMLLoader.load(getClass().getResource("../courseNote/View.fxml"));
        subScenes[4] = FXMLLoader.load(getClass().getResource("../courseQuery/View.fxml"));
        subScenes[5] = FXMLLoader.load(getClass().getResource("../gradeManagement/View.fxml"));
        subScenes[6] = FXMLLoader.load(getClass().getResource("../aboutUs/View.fxml"));
        bp.setCenter(subScenes[0]);
        Integer idIndex=0;
        String[] ids = {"welcome","myCourses"};
        for(Node nd:leftVBoxPane.getChildren()){
            if( nd instanceof VBox) {
                VBox vb = (VBox) nd;
                vb.setFillWidth(true);
                vb.setId(idIndex.toString());
                Label lb = (Label) vb.getChildren().get(0);
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
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage.setTitle("SHU model.Course Helper");
//        JFXDecorator decorator = new JFXDecorator(stage, root);
//        decorator.setCustomMaximize(false);
//        decorator.setText("SHU model.Course Helepr");
//        decorator.setText("SHU model.Course Helper");
        mainScene = new Scene(root, 800, 600);
//        mainScene.setUserAgentStylesheet("main.css");
//        mainScene = new Scene(root, 1280, 720);
        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.show();
        Stage dialog = new Stage();
        dialog.setScene(Login.getLoginDialog(dialog));
        dialog.initOwner(stage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void switchCenter(String index){
        bp.setCenter(subScenes[Integer.parseInt(index)]);
    }
}
