package edu.epam.bsuir.bean;

import java.util.Objects;

/**
 * Class for Student entity.
 *
 * @author Maria Kartovitskaya
 */
public class Student implements BaseBean {

    private static final long serialVersionUID = 5467994298333614128L;

    private int id;
    private String login;
    private String password;
    private double avgMark;

    public Student() {
    }

    public Student(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Student(String login, String password, double avgMark) {
        this.login = login;
        this.password = password;
        this.avgMark = avgMark;
    }

    public Student(int id, String login, String password, double avgMark) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.avgMark = avgMark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Double.compare(student.avgMark, avgMark) == 0 &&
                login.equals(student.login) &&
                password.equals(student.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, avgMark);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", avgMark=" + avgMark +
                '}';
    }
}
