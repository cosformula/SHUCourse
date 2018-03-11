import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXSnackbar;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Course;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyCourses {
    public static Color[] colors;
    @FXML
    HBox hbox;
    @FXML
    VBox vbox;
    @FXML
    AnchorPane pane;
    JFXSnackbar bar;
    @FXML
    Parent courseGrid;
    @FXML
    CourseGrid courseGridController;
    ListProperty<Course> courses;
    public void initialize() throws Exception {
        bar = new JFXSnackbar(pane);
        courses = new SimpleListProperty<Course>();
        courses.setValue(Main.courseFactory());
        courseGridController.bindCourses(courses);
        vbox.setVgrow(courseGrid, Priority.ALWAYS);
        bar.show("正在获取课程信息",1000);
        getData();
    }


    @FXML
    public void resolveData(String data){
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
        JsonElement courseListJson = new JsonParser().parse(jsonObject.get("data").getAsString());
        List<LinkedHashMap<String, Object>> courseDataList = new Gson().fromJson(courseListJson, new TypeToken<List<LinkedHashMap<String, Object>>>() {}.getType());
        LinkedList<Course> courseList = new LinkedList<Course>();
        for(LinkedHashMap<String, Object> courseData : courseDataList) {
            Course course = new Course(courseData);
            course.setStatus("已选入");
            course.setCourseName(courseData.get("name").toString());
            course.setCourseNo(courseData.get("no").toString());
            course.setTeacherName(courseData.get("teacher").toString());

            courseList.add(course);
        }
        courses.clear();
        for(Course c:courseList){
            System.out.println(c.getCourseName());
            courses.add(c);
        }
        Main.student.getValue().setCourses(courses);
    }
    @FXML
    public void renewData() throws Exception{
        bar.show("正在刷新课程信息",1000);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Main.HOST + "/my-course/sync?token=" + Main.student.getValue().getToken()).post(Main.student.getValue().buildBody()).build();
        Response response = client.newCall(request).execute();
        getData();
    }

    public void getData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Main.HOST + "/my-course/?token=" + Main.student.getValue().getToken()).build();
        String data = "";
        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            System.out.print("http error");
            return;
        }
        System.out.println(response);
        if(response.code()==404){
            try {
                renewData();
            } catch (Exception e) {
                System.out.print("renew failed");
                return;
            }
            return;
        }

        try{
            System.out.println(response.body());
            data = response.body().string();
        } catch (Exception ex) {
//            System.out.println(ex);
        }

        resolveData(data);
    }
}
