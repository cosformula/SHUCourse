package main;

import model.Course;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableCourse extends JFrame {
    private Integer SINGLE_WIDTH = 120;
    private Integer SINGLE_HEIGHT = 40;

    Course[] courses;

    List columns = new LinkedList();
    List<Map<String,Integer>> cellInfos = new LinkedList();
    Color[] colors = {
            new Color(115,255,0),
            new Color(244,255,52),
            new Color(255,194,100),
            new Color(255,137,91),
            new Color(177,68,255),
            new Color(30,181,255),
            new Color(43,138,84),
            new Color(144,60,31),
            new Color(212,89,134),
            new Color(160,95,221)
    };

    String[] times = {
            "1  8:00 ~ 8:45",
            "2  8:55 ~ 9:40",
            "3  10:00 ~ 10:45",
            "4  10:55 ~ 11:40",
            "5  12:10 ~ 12:55",
            "6  13:05 ~ 13:50",
            "7  14:10 ~ 14:55",
            "8  15:05 ~ 15:50",
            "9  16:00 ~ 16:45",
            "10 16:55 ~ 17:40",
            "11 18:00 ~ 18:45",
            "12 18:55 ~ 19:40",
            "13 19:50 ~ 20:35"
    };

    TableCourse () {
        for(int i=0; i<6; i++) {
            List cells = new LinkedList();
            for(int j=0; j<14; j++) {
                JLabel jLabel = new JLabel("", JLabel.CENTER);
                jLabel.setBounds(i*SINGLE_WIDTH,j*SINGLE_HEIGHT,SINGLE_WIDTH,SINGLE_HEIGHT);
                jLabel.setBorder(BorderFactory.createLineBorder(Color.red));
                this.add(jLabel);
                cells.add(jLabel);
            }
        }

        this.setSize(SINGLE_WIDTH*6, SINGLE_HEIGHT*14);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateByCourseInfos (Course[] courses) {
        this.courses = courses;
        this.transIntoCellInfo();
        this.getContentPane().removeAll();
        JLabel jLabel1 = new JLabel("#", JLabel.CENTER);
        jLabel1.setBounds(0,0,SINGLE_WIDTH,SINGLE_HEIGHT);
        jLabel1.setBorder(new MatteBorder(0, 0, 1, 1, new Color(180,180,180)));
        this.add(jLabel1);
        for (int i=1; i<6; i++) {
            String text = "星期" + transIntIntoZH(i);
            JLabel jLabel = new JLabel(text, JLabel.CENTER);
            jLabel.setBounds(i*SINGLE_WIDTH,0,SINGLE_WIDTH,SINGLE_HEIGHT);
            jLabel.setBorder(new MatteBorder(0, 0, 1, 1, new Color(180,180,180)));
            this.add(jLabel);
        }
        for (int i=1; i<14; i++) {
            JLabel jLabel = new JLabel(times[i-1], JLabel.LEFT);
            jLabel.setBounds(0,i*SINGLE_HEIGHT,SINGLE_WIDTH,SINGLE_HEIGHT);
            jLabel.setBorder(new MatteBorder(0, 0, 1, 1, new Color(180,180,180)));
            this.add(jLabel);
        }
        for(int i=1; i<6; i++) {
            List cells = new LinkedList();
            for(int j=1; j<14; j++) {
                int flag = 0;
                int length = 0;
                int cellIndex = 0;
                for (int k=0; k<cellInfos.size(); k++) {
                    if (i==cellInfos.get(k).get("day")) {
                        if (j==cellInfos.get(k).get("startTime")) {
                            flag = 1;
                            length = cellInfos.get(k).get("endTime")-cellInfos.get(k).get("startTime")+1;
                            cellIndex = k;
                            break;
                        } else if(j>cellInfos.get(k).get("startTime") && j<=cellInfos.get(k).get("endTime")) {
                            flag = 2;
                        }
                    }
                }
                if (flag == 0) {
                    JLabel jLabel = new JLabel("", JLabel.CENTER);
                    jLabel.setBounds(i*SINGLE_WIDTH,j*SINGLE_HEIGHT,SINGLE_WIDTH,SINGLE_HEIGHT);
                    jLabel.setBorder(new MatteBorder(0, 0, 1, 1, new Color(180,180,180)));
                    this.add(jLabel);
                    cells.add(jLabel);
                } else if (flag == 1) {
                    Course course = courses[cellInfos.get(cellIndex).get("courseInfoIndex")];
                    String text = "<html><body>" + course.getCourseName() + "<br>"
                            + course.getTeacher_name()+ "<br>"  + "@" +
                            course.getTime() + "</body></html>";
                    JLabel jLabel = new JLabel(text, JLabel.CENTER);
                    jLabel.setFont(new Font("微软雅黑",1,15));
                    jLabel.setOpaque(true);
                    if (cellInfos.get(cellIndex).get("status") == 0) {
                        jLabel.setBackground(new Color(255,0,0));
                        jLabel.setBounds(i*SINGLE_WIDTH,j*SINGLE_HEIGHT,SINGLE_WIDTH,SINGLE_HEIGHT*length);
                    } else if (cellInfos.get(cellIndex).get("status") == 1) {
                        jLabel.setBackground(colors[cellInfos.get(cellIndex).get("bgColorIndex")]);
                        jLabel.setBounds(i*SINGLE_WIDTH,j*SINGLE_HEIGHT,SINGLE_WIDTH,SINGLE_HEIGHT*length);
                    } else {
                        jLabel.setBackground(colors[cellInfos.get(cellIndex).get("bgColorIndex")]);
                        jLabel.setBorder(new MatteBorder(3, 3, 3, 3, new Color(150,150,150)));
                        jLabel.setBounds(i*SINGLE_WIDTH,j*SINGLE_HEIGHT,SINGLE_WIDTH,SINGLE_HEIGHT*length);
                    }
                    jLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int mods = e.getModifiers();
                            if (e.isMetaDown()) {
                                JLabel tmpLabel = (JLabel)(e.getSource());
                                deleteByCourseName(getSubUtilSimple(tmpLabel.getText(),"<body>(.*?)<br>"));
                            } else if (e.getClickCount() == 2) { //双击事件
                                JLabel tmpLabel = (JLabel)(e.getSource());
                                confirmByCourseName(getSubUtilSimple(tmpLabel.getText(),"<body>(.*?)<br>"));
                            }
                        }
                    });
                    this.add(jLabel);
                    cells.add(jLabel);
                } else {
                    continue;
                }

            }
        }
        this.repaint();

    }

    private void transIntoCellInfo() {
        this.cellInfos.clear();
        this.cellInfos = new LinkedList();
        for(int i = 0; i < courses.length; i++){
            System.out.println(courses[i].getTime());
            Pattern p = Pattern.compile("([\\u4e00|\\u4e8c|\\u4e09|\\u56db|\\u4e94])([0-9]+)-([0-9]+)\\s*(?:([\\u5355|\\u53cc|])|\\((?:([0-9]+)-([0-9]+)\\u5468)\\)|\\((?:([0-9]+),([0-9]+)\\u5468)\\))*");
            Matcher m=p.matcher(courses[i].getTime());
            while (m.find()) {
                Map<String, Integer> cellInfo = new HashMap();
                cellInfo.put("day", transZhIntoInt(m.group(1)));
                cellInfo.put("startTime", Integer.parseInt(m.group(2)));
                cellInfo.put("endTime", Integer.parseInt(m.group(3)));
                cellInfo.put("courseInfoIndex", i);
                cellInfo.put("bgColorIndex",i);
                if (courses[i].getStatus().equals("selected")) {
                    cellInfo.put("status",1);
                } else if (courses[i].getStatus().equals("waited")) {
                    cellInfo.put("status",2);
                } else {
                    cellInfo.put("status",0);
                }
                this.cellInfos.add(cellInfo);
            }
        }
    }

    public void doSomeThingByCourseName(String courseName) {
        String courseNo = getCourseNobyCourseName(courseName);
        System.out.println(courseNo);
    }

    public void confirmByCourseName(String courseName) {
        String courseNo = getCourseNobyCourseName(courseName);
        System.out.println(courseNo + "确认选课");
    }

    public void deleteByCourseName(String courseName) {
        String courseNo = getCourseNobyCourseName(courseName);
        System.out.println(courseNo + "确认取消");
    }

    private String getCourseNobyCourseName(String courseName) {
        String courseNo = new String();
        for (int i = 0; i< courses.length; i++) {
            if (courses[i].getCourseName().equals(courseName)) {
                courseNo = courses[i].getCourseNo();
                break;
            }
        }
        return courseNo;
    }

    public static void main (String args[]) {
        TableCourse tableCourse = new TableCourse();

        Course[] courses = new Course[3];
        Course course1 = new Course("88122212","数据结构",
                "4","1001","张老师","一4-5","DJ204",
                100,20,"校本部","二4-5","J1101","2017_1",
                "selected");
        Course course2 = new Course("88124534","操作系统",
                "4","1001","李老师","四4-5 ","J204",
                100,20,"校本部","二4-5","J1101","2017_1",
                "waited");
        Course course3 = new Course("88122121","数据结构",
                "4","1001","张老师","三4-5研讨,五1-5上机","DJ204",
                100,20,"校本部","二4-5","J1101","2017_1",
                "");
        courses[0] = course1;
        courses[1] = course2;
        courses[2] = course3;
        tableCourse.updateByCourseInfos(courses);
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

    public String transIntIntoZH(int t) {
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

    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }
}
