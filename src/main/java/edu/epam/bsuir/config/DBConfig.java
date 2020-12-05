package edu.epam.bsuir.config;

import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The {@code DBConfig} class is
 * a configuration class used to store values in
 * db.properties file needed for a server
 * to connect to a DB.
 *
 * In addition the {@code DBConfig} class
 * contains values for {@code ConnectionPool} class
 * to properly work.
 *
 * @author Maria Kartovitskaya
 */
public class DBConfig {

    /** Property - DB url. */
    private String dbUrl;

    /** Property - DB user login. */
    private String dbUser;

    /** Property - DB user password. */
    private String dbPassword;

    /** Property - DB driver. */
    private String dbDriver;

    /**
     * Property - initial number of connections
     * to a DB of a
     * {@code ConnectionPool} class.
     * */
    private int initPoolSize;

    /**
     * Property - maximum number of connections
     * to a DB of a
     * {@code ConnectionPool} class.
     * */
    private int maxPoolSize;

    /**
     * Property - increase connections number of a
     * {@code ConnectionPool} class
     * if initPoolSize is overflown.
     * */
    private int poolIncreaseStep;

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    private static DBConfig instance;

    private DBConfig() {
        initProperties();
    }

    private void initProperties() {
        dbUrl = resourceBundle.getString("dbUrl");
        dbUser = resourceBundle.getString("dbUser");
        dbPassword = resourceBundle.getString("dbPassword");
        dbDriver = resourceBundle.getString("dbDriver");
        initPoolSize = Integer.parseInt(resourceBundle.getString("initPoolSize"));
        maxPoolSize = Integer.parseInt(resourceBundle.getString("maxPoolSize"));
        poolIncreaseStep = Integer.parseInt(resourceBundle.getString("poolIncreaseStep"));
    }

    public static DBConfig getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DBConfig();
        }
        return instance;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public int getInitPoolSize() {
        return initPoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public int getPoolIncreaseStep() {
        return poolIncreaseStep;
    }

    @Override
    public String toString() {
        return "DBConfig{" +
                "dbUrl='" + dbUrl + '\'' +
                ", dbUser='" + dbUser + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", dbDriver='" + dbDriver + '\'' +
                ", initPoolSize=" + initPoolSize +
                ", maxPoolSize=" + maxPoolSize +
                ", poolIncreaseStep=" + poolIncreaseStep +
                '}';
    }
}