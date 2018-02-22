package model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Course {
    @SerializedName("course_no")
    String courseNo;//学号
    @SerializedName("course_name")
    String courseName;//姓名
    String credit;//学分
    @SerializedName("teacher_no")
    String teacherNo;//教师号
    @SerializedName("teacher_name")
    String teacherName;
    String time;
    String place;
    Integer capacity;//容量
    Integer enroll;//已选人数
    String campus;//校区
    @SerializedName("q_time")
    String qTime;
    @SerializedName("q_place")
    String qPlace;
    String term;//2017_1/ 2017_2/2017_3
    String status;//selected/waited/preview/locked

    public LinkedList<Position> getPositions() {
        return positions;
    }

    LinkedList<Position> positions;


    public void setPositions(LinkedList<Position> positions) {
        this.positions = positions;
    }

    public Course(String courseNo, String courseName, String credit, String teacherNo, String teacherName, String time, String place, Integer capacity, Integer enroll, String campus, String qTime, String qPlace, String term, String status) {
        this.courseNo = courseNo;
        this.courseName = courseName;
        this.credit = credit;
        this.teacherNo = teacherNo;
        this.teacherName = teacherName;
        this.time = time;
        this.place = place;
        this.capacity = capacity;
        this.enroll = enroll;
        this.campus = campus;
        this.qTime = qTime;
        this.qPlace = qPlace;
        this.term = term;
        this.status = status;
        this.calPositions();
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
        Matcher m=p.matcher(time);
        while (m.find()) {
            Map<String, Integer> cellInfo = new HashMap();
            Integer day =  transZhIntoInt(m.group(1));
            Integer start = Integer.parseInt(m.group(2));
            Integer rowSpane = Integer.parseInt(m.group(3)) - start + 1;
            positions.add(new Position(day,start,rowSpane));
        }
        return positions;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCredit() {
        return credit;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getEnroll() {
        return enroll;
    }

    public String getCampus() {
        return campus;
    }

    public String getqTime() {
        return qTime;
    }

    public String getqPlace() {
        return qPlace;
    }

    public String getTerm() {
        return term;
    }

    public String getStatus() {
        return status;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setEnroll(Integer enroll) {
        this.enroll = enroll;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setqTime(String qTime) {
        this.qTime = qTime;
    }

    public void setqPlace(String qPlace) {
        this.qPlace = qPlace;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
