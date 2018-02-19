package main;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.stage.StageStyle;
import login.Login;
import com.jfoenix.controls.JFXBadge;
public class Main extends Application {
    JFXBadge badge;
    Stage stage;
    Scene loginScene, mainScene;
    @FXML private VBox leftVBoxPane;
    @FXML private BorderPane bp;
    private Parent[] subScenes;
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
//        stage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage.setTitle("SHU Course Helper");
//        JFXDecorator decorator = new JFXDecorator(stage, root);
//        decorator.setCustomMaximize(false);
//        decorator.setText("SHU Course Helepr");
//        decorator.setText("SHU model.Course Helper");
        mainScene = new Scene(root, 800, 600);
        stage.setScene(mainScene);
        stage.setResizable(false);
        Stage dialog = new Stage();
        dialog.setScene(Login.getLoginDialog(dialog));
        dialog.initOwner(stage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setOnCloseRequest(e->{
            Platform.exit();
        });
//        dialog.showAndWait();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void switchCenter(String index){
        bp.setCenter(subScenes[Integer.parseInt(index)]);
    }
}
