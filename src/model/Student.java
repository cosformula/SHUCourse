package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import okhttp3.RequestBody;

public class Student {
    public String name;
    public String id;
    public String password;

    public Student() {
        this.courses = new SimpleListProperty<Course>();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RequestBody buildBody(){
        RequestBody body = RequestBody.create(Constant.JSON, "{\"card_id\":\"" + id + "\",\"password\":\"" + password + "\"}");
        return body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ListProperty<Course> coursesProperty() {
        return courses;
    }

    public void setCourses(ObservableList<Course> courses) {
        this.courses.set(courses);
    }

    public ListProperty<Course> courses;
    public void getGrade(){

    }
    public void getCourses(){

    }
}
