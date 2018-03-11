import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Course;
import model.Student;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.lang.reflect.Type;
import java.util.Map;

public class MyCourses {
//    public ListPropertyBase<Course> courses  = new ListPropertyBase<Course>();
    public static Course[] courses;
    public static Color[] colors;
    @FXML
    HBox hbox;
    @FXML
    VBox vbox;
    public void initialize() throws Exception {
        Parent courseGrid = FXMLLoader.load(getClass().getResource("/CourseGrid.fxml"));
        System.out.println(courseGrid);
        vbox.getChildren().add(courseGrid);
            vbox.setVgrow(courseGrid,Priority.ALWAYS);
        }
        public void getCourses(){
            OkHttpClient client = new OkHttpClient();
//        RequestBody body = RequestBody.create(Main.JSON, "{\"card_id\":\"" + id + "\",\"password\":\"" + password + "\"}");
            Request request = new Request.Builder()
                    .url("https://www.shuhelper.cn/api/my-course/?token="+Main.student.getValue().getToken()).build();
            String data = "";
            try {
                Response response = client.newCall(request).execute();
                data = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> responseData = gson.fromJson(data, type);
            String courseDataEncrypted = responseData.get("data");

            System.out.println(data);
//            System.out.println(student.getName());
//            Main.setStudent(student);
//            return data;
        } catch (Exception e) {
            System.out.print("http error");
        }
//        return data;
    }
}
