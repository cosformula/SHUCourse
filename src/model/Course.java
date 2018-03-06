package model;

import com.google.gson.annotations.SerializedName;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Course {
    @SerializedName("course_no")
    SimpleStringProperty courseNo;//学号
    @SerializedName("course_name")
    SimpleStringProperty courseName;//姓名
    SimpleStringProperty credit;//学分
    @SerializedName("teacher_no")
    SimpleStringProperty teacherNo;//教师号
    @SerializedName("teacher_name")
    SimpleStringProperty teacherName;
    SimpleStringProperty time;

    SimpleStringProperty place;
    SimpleIntegerProperty capacity;//容量
    SimpleIntegerProperty enroll;//已选人数
    SimpleStringProperty campus;//校区
    @SerializedName("q_time")
    SimpleStringProperty qTime;

    public Course(LinkedHashMap<String, Object> courseData) {
        this.courseNo = new SimpleStringProperty((String) courseData.get("course_no"));
        this.courseName = new SimpleStringProperty((String) courseData.get("course_name"));
        this.credit = new SimpleStringProperty((String) courseData.get("credit"));
        this.teacherNo = new SimpleStringProperty((String) courseData.get("teacher_no"));
        this.teacherName = new SimpleStringProperty((String) courseData.get("teacher_name"));
        this.time = new SimpleStringProperty((String) courseData.get("time"));
        this.place = new SimpleStringProperty((String) courseData.get("place"));
        Double capacity = (Double)courseData.get("capacity");
        Double enroll = (Double)courseData.get("enroll");
        this.capacity = new SimpleIntegerProperty(capacity.intValue());
        this.enroll = new SimpleIntegerProperty(enroll.intValue());
        this.campus = new SimpleStringProperty((String) courseData.get("campus"));
        this.qTime =new SimpleStringProperty((String) courseData.get("q_time"));
        this.qPlace = new SimpleStringProperty((String) courseData.get("q_place"));
        this.term = new SimpleStringProperty((String) courseData.get("term"));
        this.status = new SimpleStringProperty();
        this.positions = calPositions();
    }


    public Course( String courseNo, String courseName, String credit, String teacherNo, String teacherName,
                   String time, String place, Integer capacity, Integer enroll, String campus, String qTime, String qPlace, String term, String status) {
        this.courseNo = new SimpleStringProperty(courseNo);
        this.courseName = new SimpleStringProperty(courseName);
        this.credit = new SimpleStringProperty(credit);
        this.teacherNo = new SimpleStringProperty(teacherNo);
        this.teacherName = new SimpleStringProperty(teacherName);
        this.time = new SimpleStringProperty(time);
        this.place = new SimpleStringProperty(place);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.enroll = new SimpleIntegerProperty(enroll);
        this.campus = new SimpleStringProperty(campus);
        this.qTime = new SimpleStringProperty(qTime);
        this.qPlace = new SimpleStringProperty(qPlace);
        this.term = new SimpleStringProperty(term);
        this.status = new SimpleStringProperty(status);
        this.positions = calPositions();
    }


    public String getCourseNo() {
        return courseNo.get();
    }

    public SimpleStringProperty courseNoProperty() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo.set(courseNo);
    }

    public String getCourseName() {
        return courseName.get();
    }

    public SimpleStringProperty courseNameProperty() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public String getCredit() {
        return credit.get();
    }

    public SimpleStringProperty creditProperty() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit.set(credit);
    }

    public String getTeacherNo() {
        return teacherNo.get();
    }

    public SimpleStringProperty teacherNoProperty() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo.set(teacherNo);
    }

    public String getTeacherName() {
        return teacherName.get();
    }

    public SimpleStringProperty teacherNameProperty() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName.set(teacherName);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getPlace() {
        return place.get();
    }

    public SimpleStringProperty placeProperty() {
        return place;
    }

    public void setPlace(String place) {
        this.place.set(place);
    }

    public int getCapacity() {
        return capacity.get();
    }

    public SimpleIntegerProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public int getEnroll() {
        return enroll.get();
    }

    public SimpleIntegerProperty enrollProperty() {
        return enroll;
    }

    public void setEnroll(int enroll) {
        this.enroll.set(enroll);
    }

    public String getCampus() {
        return campus.get();
    }

    public SimpleStringProperty campusProperty() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus.set(campus);
    }

    public String getqTime() {
        return qTime.get();
    }

    public SimpleStringProperty qTimeProperty() {
        return qTime;
    }

    public void setqTime(String qTime) {
        this.qTime.set(qTime);
    }

    public String getqPlace() {
        return qPlace.get();
    }

    public SimpleStringProperty qPlaceProperty() {
        return qPlace;
    }

    public void setqPlace(String qPlace) {
        this.qPlace.set(qPlace);
    }

    public String getTerm() {
        return term.get();
    }

    public SimpleStringProperty termProperty() {
        return term;
    }

    public void setTerm(String term) {
        this.term.set(term);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    @SerializedName("q_place")
    SimpleStringProperty qPlace;
    SimpleStringProperty term;//2017_1/ 2017_2/2017_3
    SimpleStringProperty status;//selected/waited/preview/locked

    public LinkedList<Position> getPositions() {
        return positions;
    }

    LinkedList<Position> positions;


    public void setPositions(LinkedList<Position> positions) {
        this.positions = positions;
    }

    public static String transIntIntoZH(int t) {
        if(t == 1){
            return "一";
        } else if (t == 2) {
            return "二";
        } else if (t == 3) {
            return "三";
        } else if (t == 4) {
            return "四";
        } else {
            return "五";
        }
    }
    public int transZhIntoInt(String t) {
        if(t.equals("一")){
            return 1;
        } else if (t.equals("二")) {
            return 2;
        } else if (t.equals("三")) {
            return 3;
        } else if (t.equals("四")) {
            return 4;
        } else {
            return 5;
        }
    }
    private LinkedList<Position> calPositions() {
        positions = new LinkedList<Position>();
        Integer i = 0;
        Pattern p = Pattern.compile("([\\u4e00|\\u4e8c|\\u4e09|\\u56db|\\u4e94])([0-9]+)-([0-9]+)\\s*(?:([\\u5355|\\u53cc|])|\\((?:([0-9]+)-([0-9]+)\\u5468)\\)|\\((?:([0-9]+),([0-9]+)\\u5468)\\))*");
        Matcher m=p.matcher(getTime());
        while (m.find()) {
            Map<String, Integer> cellInfo = new HashMap();
            Integer day =  transZhIntoInt(m.group(1));
            Integer start = Integer.parseInt(m.group(2));
            Integer rowSpane = Integer.parseInt(m.group(3)) - start + 1;
            positions.add(new Position(day,start,rowSpane));
        }
        return positions;
    }

}
