import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import jdk.nashorn.internal.parser.JSONParser;
import model.Course;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    @FXML
    Pagination pagination;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courses = new SimpleListProperty<Course>();
        courses.setValue( FXCollections.observableList(new ArrayList<Course>()));
        courseTable.setItems(courses);
        searchButton.setOnAction(e -> {
            pagination.setCurrentPageIndex(0);
            getSearchResult();
        });
        getSearchResult();
        pagination.currentPageIndexProperty().addListener(e->{
            getSearchResult();
        });
    }
    void getSearchResult(){
        try{
            search(buildURL());
        } catch (IOException io){
            System.out.println("io");
        }
    }
    @FXML
    void onKeyPressed(KeyEvent key){
        if(key.getCode() == KeyCode.ENTER){
            pagination.setCurrentPageIndex(0);
            getSearchResult();
        }
    }
    String buildURL(){
        String url = String.format("http://xk.shuhelper.cn/api/courses/?term=2017_3&type=advance&no=%S&name=%s&teacher=%s&time=%s&credit=%s&campus=%s&page=%s",
                courseNoField.getText(),
                courseNameField.getText(),
                teacherNameField.getText(),
                timeField.getText(),
                creditField.getText(),
                campusField.getText(),
                pagination.getCurrentPageIndex()+1);
        return url;
    }
    void search(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
        Integer pageCount = jsonObject.get("total").getAsInt()/30;
        pagination.setPageCount(pageCount+1);
        Course[] course = gson.fromJson(jsonObject.get("courses").toString(), Course[].class);
        courses.clear();
        for(Course c:course){
            courses.add(c);
        }
    }
}
