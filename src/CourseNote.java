
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.web.HTMLEditor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.io.FileWriter;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import model.Course;

public class CourseNote implements Initializable {

    @FXML
    Button b0;

    @FXML
    Button b1;

    @FXML
    Button b2;

    @FXML
    Button b3;

    @FXML
    Button b4;

    @FXML
    Button b5;

    @FXML
    Button b6;

    @FXML
    Button b7;

    @FXML
    Button save;

    @FXML
    private HTMLEditor Editor;

    ListProperty<Course> courses;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Button[] b = new Button[8];
        b[0] = b0;
        b[1] = b1;
        b[2] = b2;
        b[3] = b3;
        b[4] = b4;
        b[5] = b5;
        b[6] = b6;
        b[7] = b7;
        b0.getStyleClass().add("yellow-100");
        b1.getStyleClass().add("lime-A100");
        b2.getStyleClass().add("light-green-500");
        b3.getStyleClass().add("cyan-600");
        b4.getStyleClass().add("orange-300");
        b5.getStyleClass().add("amber-200");
        b6.getStyleClass().add("purple-300");
        b7.getStyleClass().add("pink-200");
        save.getStyleClass().add("cyan-600");

        courses = new SimpleListProperty<Course>();
        String[] colors = {"#2B2E4A", "#521262", "#903749", "#53354A", "#40514E", "#537780", "#3765a4", "#76a5a4", "#579870","#e391b4", "#b8954e"};
        String []name = new String[8];
        for(int i=0;i<8;i++){
            name[i] = i+ "";
        }

        File directory = new File("./src/Testfile");
        File []fn = new File[8];
        String []filename = new String[8];

        try{
            if(!directory.exists() ){
                directory.mkdir() ;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        for(int i=0;i<8;i++){
            filename[i]= i+ ".txt";
            fn[i] = new File(directory,filename[i]);
        }

        final FileInputStream [] fis = {null};

        for (int i = 0; i < 8; i++) {
            b[i].setText(name[i]);
            b[i].setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    for(int i=0;i<8;i++) {
                        if (event.getSource() == b[i]){
                            if(fn[i].exists() ) {
                                if(fn[i].length()==0){
                                    Editor.setHtmlText("") ;
                                }
                                else {
                                    try {
                                        fis[0] = new FileInputStream(fn[i]);

                                        byte[] bytes = new byte[1024];
                                        int n = 0;
                                        while ((n = fis[0].read(bytes)) != -1) {
                                            String s = new String(bytes, 0, n);
                                            Editor.setHtmlText(s);
                                        }
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                                int finalI = i;
                                save.setOnAction(new EventHandler<ActionEvent>() {
                                    public void handle(ActionEvent event) {
                                        if (event.getSource() == save) {
                                            FileWriter fw = null;
                                            try {
                                                fw = new FileWriter(fn[finalI]);
                                                fw.write(Editor.getHtmlText());
                                                fw.flush();
                                                fw.close();
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                    }
                                });
                            }
                            else{
                                Editor.setHtmlText("") ;
                                try {
                                    fn[i].createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                int finalI = i;
                                save.setOnAction(new EventHandler<ActionEvent>() {
                                    public void handle(ActionEvent event) {
                                        if (event.getSource() == save) {
                                            FileWriter fw = null;
                                            try {
                                                fw = new FileWriter(fn[finalI]);
                                                fw.write(Editor.getHtmlText());
                                                fw.flush();
                                                fw.close();
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            });
        }
    }
}

















