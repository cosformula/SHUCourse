package model;

public class Position {
    Integer day;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(Integer rowSpan) {
        this.rowSpan = rowSpan;
    }

    Integer start;
    Integer rowSpan;

    public Position(Integer day, Integer start, Integer rowSpan) {
        this.day = day;
        this.start = start;
        this.rowSpan = rowSpan;
    }
    public String toString(){
        return day.toString() + start.toString() + rowSpan.toString();
    }
}
