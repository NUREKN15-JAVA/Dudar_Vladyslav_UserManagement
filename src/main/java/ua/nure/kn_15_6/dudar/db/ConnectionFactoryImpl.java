package ua.nure.kn_15_6.dudar.db;

import ua.nure.kn_15_6.dudar.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {
    private String driver;
    private String url;
    private String user;
    private String pass;

    public ConnectionFactoryImpl() {
    }

    public ConnectionFactoryImpl(String driver, String url, String user, String pass) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    @Override
    public Connection createConnection() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(Constants.ERR_FAILED_TO_LOAD_JDBC);
        }

        return DriverManager.getConnection(url, user, pass);
    }
}
