package model;

public class Course {
    String courseNo;//学号
    String courseName;//姓名
    String credit;//学分
    String teacher_no;//教师号
    String teacher_name;
    String time;
    String place;
    Integer capacity;//容量
    Integer enroll;//已选人数
    String campus;//校区
    String q_time;
    String q_place;
    String term;//2017_1/ 2017_2/2017_3
    String status;//selected/waited


    public Course(String courseNo, String courseName, String credit, String teacher_no, String teacher_name, String time, String place, Integer capacity, Integer enroll, String campus, String q_time, String q_place, String term, String status) {
        this.courseNo = courseNo;
        this.courseName = courseName;
        this.credit = credit;
        this.teacher_no = teacher_no;
        this.teacher_name = teacher_name;
        this.time = time;
        this.place = place;
        this.capacity = capacity;
        this.enroll = enroll;
        this.campus = campus;
        this.q_time = q_time;
        this.q_place = q_place;
        this.term = term;
        this.status = status;
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

    public String getTeacher_no() {
        return teacher_no;
    }

    public String getTeacher_name() {
        return teacher_name;
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

    public String getQ_time() {
        return q_time;
    }

    public String getQ_place() {
        return q_place;
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

    public void setTeacher_no(String teacher_no) {
        this.teacher_no = teacher_no;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
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

    public void setQ_time(String q_time) {
        this.q_time = q_time;
    }

    public void setQ_place(String q_place) {
        this.q_place = q_place;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
