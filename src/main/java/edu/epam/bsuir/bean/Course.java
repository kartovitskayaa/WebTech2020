package edu.epam.bsuir.bean;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Class for Course entity.
 *
 * @author Maria Kartovitskaya
 */
public class Course implements BaseBean {

    private static final long serialVersionUID = 7083926280874552117L;

    private int id;
    private String name;
    private boolean finished;
    private Timestamp startDate;
    private Timestamp endDate;
    private Lector lector;

    public Course() {
    }

    public Course(String name, boolean finished, Timestamp startDate, Timestamp endDate, Lector lector) {
        this.name = name;
        this.finished = finished;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lector = lector;
    }

    public Course(int id, String name, boolean finished, Timestamp startDate, Timestamp endDate, Lector lector) {
        this.id = id;
        this.name = name;
        this.finished = finished;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lector = lector;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id &&
                finished == course.finished &&
                name.equals(course.name) &&
                startDate.equals(course.startDate) &&
                endDate.equals(course.endDate) &&
                lector.equals(course.lector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, finished, startDate, endDate, lector);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ended=" + finished +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", lector=" + lector +
                '}';
    }
}
