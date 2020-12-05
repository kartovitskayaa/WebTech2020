package edu.epam.bsuir.bean;

import java.util.Objects;

/**
 * Class for Lector entity.
 *
 * @author Maria Kartovitskaya
 */
public class Lector implements BaseBean {

    private static final long serialVersionUID = 7045384298313604528L;

    private int id;
    private String login;
    private String password;

    public Lector() {
    }

    public Lector(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Lector(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lector lector = (Lector) o;
        return id == lector.id &&
                login.equals(lector.login) &&
                password.equals(lector.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
