import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import model.CourseGrade;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class gradeManagement implements Initializable {
    @FXML
    private ChoiceBox termChoiceBox;
    @FXML
    TableView courseGradeTable;

    ListProperty<CourseGrade> courseGrades;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseGrades = new SimpleListProperty<CourseGrade>();
        courseGrades.setValue(FXCollections.observableList(new ArrayList<CourseGrade>()));
        courseGradeTable.setItems(courseGrades);
        String[] terms = {"2017_3", "2017_6", "2017_9"};
        termChoiceBox.setItems(FXCollections.observableArrayList(terms));
        termChoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov,old_val,new_val)-> {
                    search(buildURL(terms[new_val.intValue()]));
                }
        );

        search("");

    }

    void search(String url) {
        try {
            String sampleJsonString = fileRead("./src/gradeSample.json");
            Gson gson = new Gson();
            JsonObject jsonObject = new JsonParser().parse(sampleJsonString).getAsJsonObject();
            CourseGrade[] courseGrades = gson.fromJson(jsonObject.get("course_grades").toString(), CourseGrade[].class);
            System.out.println(courseGrades);
            this.courseGrades.clear();
            for (CourseGrade cg : courseGrades) {
                this.courseGrades.add(cg);
            }
            System.out.println(courseGrades);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String buildURL(String term)
    {
        String url = "url";
        return url;
    }

    String fileRead(String fileName) throws Exception {
        File file = new File(fileName);//定义一个file对象，用来初始化FileReader
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";
        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
//            System.out.println(s);
        }
        bReader.close();
        return sb.toString();
    }
}
