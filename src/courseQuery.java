import com.google.gson.Gson;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jdk.nashorn.internal.parser.JSONParser;
import model.Course;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class courseQuery implements Initializable {
    @FXML
    Button searchButton;
    @FXML
    private TextField courseNoField;

    @FXML
    private TextField courseNameField;

    @FXML
    private TextField teacherNameField;

    @FXML
    private TextField timeField;

    @FXML
    private TextField creditField;

    @FXML
    private TextField campusField;

    ListProperty<Course> courses;
    @FXML
    TableView courseTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courses = new SimpleListProperty<Course>();
        courseTable.setItems(courses);
        searchButton.setOnAction(e -> {
            try{
                System.out.println(search(buildURL()));
            } catch (IOException io){
             System.out.println("io");
            }
        });
    }
    String buildURL(){
        String url = String.format("http://xk.shuhelper.cn/api/courses/?term=2017_3&type=advance&no=%S&name=%s&teacher=%s&time=%s&credit=%s&campus=%s&page=%s",
                courseNoField.getText(),
                courseNameField.getText(),
                teacherNameField.getText(),
                timeField.getText(),
                creditField.getText(),
                campusField.getText(),
                "1");
        return url;
    }
    String search(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        Gson gson = new Gson();
        Course[] course = gson.fromJson(response.body().string(), Course[].class);
        System.out.println(course);
        courses.addAll(course);
        return response.body().string();
    }
}
