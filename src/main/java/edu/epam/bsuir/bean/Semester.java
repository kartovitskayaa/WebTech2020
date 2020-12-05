package edu.epam.bsuir.bean;

import java.util.Objects;

/**
 * Class for Semester entity.
 *
 * @author Maria Kartovitskaya
 */
public class Semester implements BaseBean {

    private Course course;
    private Student student;
    private int mark;
    private String message;

    public Semester() {
    }

    public Semester(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    public Semester(Course course, Student student, int mark, String message) {
        this.course = course;
        this.student = student;
        this.mark = mark;
        this.message = message;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return mark == semester.mark &&
                course.equals(semester.course) &&
                student.equals(semester.student) &&
                message.equals(semester.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, student, mark, message);
    }

    @Override
    public String toString() {
        return "Semester{" +
                "course=" + course +
                ", student=" + student +
                ", mark=" + mark +
                ", message='" + message + '\'' +
                '}';
    }
}
