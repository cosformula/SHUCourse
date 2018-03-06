import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.CourseGrade;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GradeManagement implements Initializable {
    @FXML
    private ChoiceBox termChoiceBox;
    @FXML
    TableView courseGradeTable;
    @FXML
    WebView gradeWebView;
    @FXML
    LineChart<String, Double> gradeChart;
    @FXML
    CategoryAxis xAxis;
    @FXML
    NumberAxis yAxis;
    @FXML
    PieChart gradePieChart;

    ListProperty<CourseGrade> courseGrades;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //学期成绩
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
        //成绩大表
        WebEngine webEngine = gradeWebView.getEngine();
        webEngine.load(getClass().getResource("gradeTotalSampleUni.html").toExternalForm());
        //成绩走势
        //ObservableList<String> termName = FXCollections.observableArrayList();
        double [] termGrade={3.5,3.6,3.3};
        //termName.addAll(Arrays.asList(terms));
        //xAxis.setCategories(termName);
        //对于双轴图表，可以使用XYChart.Series类来定义多组数据
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        for (int i = 0; i < terms.length; i++) {
            //series.getData().add(new XYChart.Data<>(termName.get(i), termGrade[i]));
            series.getData().add(new XYChart.Data<>(terms[i], termGrade[i]));
        }
        gradeChart.getData().add(series);
        //成绩排名
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("排名占比", 66),
                        new PieChart.Data("其余比例", 230));
        gradePieChart.setData(pieChartData);
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
