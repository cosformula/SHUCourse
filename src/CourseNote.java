
import com.jfoenix.controls.JFXSnackbar;
import javafx.beans.property.ListProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.io.FileWriter;
import model.Course;
import model.Student;

public class CourseNote implements Initializable {
    JFXSnackbar bar;
    ListProperty<Course> courses;

    static String stuId;

    @FXML
    Button save;

    @FXML
    private HTMLEditor Editor;

    @FXML
    AnchorPane pane;
    @FXML
    private Label lab;

    @FXML
    private TextArea ta;

    @FXML
    FlowPane flowPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stuId = Main.student.getValue().getId();
        courses = Main.student.getValue().courses;
        bar = new JFXSnackbar(pane);
        //按钮数组
        String[] buttonColors = {"yellow-100","lime-A100","light-green-500","cyan-600","orange-300","amber-200","purple-300","pink-200","cyan-600","#2B2E4A", "#521262", "#903749", "#53354A", "#40514E"};
        Button[] b = new Button[courses.size()];
        for(int i=0;i<courses.size();i++){
            Pane pane = new Pane();
            b[i] = new Button();
            b[i].getStyleClass().add(buttonColors[i]);
            b[i].setPrefWidth(122.0);
            b[i].setPrefHeight(54.0);
            pane.prefHeight(68);
            pane.setPrefSize(172,68);
            pane.getChildren().add(b[i]);
            flowPane.getChildren().add(pane);
        }
        save.getStyleClass().add("cyan-600");
        lab.getStyleClass().add("light-green-500");

        String[] colors = {"#2B2E4A", "#521262", "#903749", "#53354A", "#40514E", "#537780", "#3765a4", "#76a5a4", "#579870","#e391b4", "#b8954e"};
        String []name = new String[courses.size()];
        for(int i=0;i<courses.size();i++){
            name[i] = courses.get(i).getCourseName() ;
        }
        File directory = new File("./src/"+stuId);
        File []fn = new File[courses.size()];
        String []filename = new String[courses.size()];
        //创建文件夹
        try{
            if(!directory.exists() ){
                directory.mkdir() ;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        //笔记文件名
        for(int i=0;i<courses.size();i++){
            filename[i]= courses.get(i).getCourseNo()+"_" +courses.get(i).getTeacherName() +"_" +courses.get(i).getTerm() + ".txt";
            fn[i] = new File(directory,filename[i]);
        }
        //标签，按钮的缩放
        lab.setOnMouseEntered(e->{
            lab.setScaleX(1.2);
            lab.setScaleY(1.2);
        });
        lab.setOnMouseExited(e->{
            lab.setScaleX(1);
            lab.setScaleY(1);
        });
        save.setOnMouseEntered(e->{
            save.setScaleX(1.2);
            save.setScaleY(1.2);
        });
        save.setOnMouseExited(e->{
            save.setScaleX(1);
            save.setScaleY(1);
        });
        //文本区
        //JOptionPane.showMessageDialog(null,"请选择您的课程来打开或创建相关笔记哦(〃'▽'〃)");
        ta.setText("   左边是您的编辑器，您打开或新建的笔记都会在在那里显示，您可以选择喜欢的格式进行修改保存哦(^_−)☆！");
        ta.setEditable(false ) ;
        ta.setWrapText(true);
        ta.setFont(Font.font(13)) ;
        ta.getStyleClass().add(".orange-300");
        //标签的打开提示对话框事件
        lab.setOnMouseClicked(e->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION );
            alert.setTitle("温馨提示：");
            alert.setHeaderText("请选择您的课程来打开或创建相关笔记哦(〃'▽'〃)") ;
            alert.showAndWait() ;
        });

        final FileInputStream [] fis = {null};

        //循环设置课程按钮
        for (int i = 0; i < courses.size(); i++) {
            b[i].setText(name[i]);
            int finalI = i;
            b[i].setOnMouseEntered(e->{
                b[finalI].setScaleX(1.2);
                b[finalI].setScaleY(1.2);
            });
            int finalI1 = i;
            b[i].setOnMouseExited(e->{
                b[finalI1].setScaleX(1);
                b[finalI1].setScaleY(1);
            });
            //为按钮添加事件
            b[i].setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    for(int i=0;i<courses.size();i++) {
                        if (event.getSource() == b[i]){     //获得被点击按钮对象
                            if(fn[i].exists() ) {           //对应课程笔记文件已存在
                                if(fn[i].length()==0){
                                    Editor.setHtmlText("") ;    //文件为空则编辑器置空
                                }
                                else {                        //文件不为空则读取文件
                                    try {
                                        fis[0] = new FileInputStream(fn[i]);
                                        byte[] bytes = new byte[1024];
                                        int n = 0;
                                        while ((n = fis[0].read(bytes)) != -1) {
                                            String s = new String(bytes, 0, n);
                                            Editor.setHtmlText(s);    //将文件内容显示在Editor中
                                        }
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    bar.close();
                                    bar.show("笔记读取成功",1000);
                                }
                                int finalI = i;
                            }
                            //相关笔记文件不存在
                            else{
                                Editor.setHtmlText("") ;     //Editor置空
                                try {
                                    fn[i].createNewFile();   //新建文件
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                int finalI = i;
                            }
                            save.setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent event) {
                                    if (event.getSource() == save) {
                                        FileWriter fw = null;
                                        try {
                                            fw = new FileWriter(fn[finalI]);
                                            fw.write(Editor.getHtmlText());      //获取编辑器内容并写进对应笔记文件
                                            fw.flush();
                                            fw.close();
                                            bar.close();
                                            bar.show("笔记已保存",1000);
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }
    }

}

















