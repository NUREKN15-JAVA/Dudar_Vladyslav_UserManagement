package ua.nure.kn_15_6.dudar.db;

import ua.nure.kn_15_6.dudar.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {
    private static final String DRIVER = "org.hsqldb.jdbcDriver";
    private static final String URL = "jdbc:hsqldb:file:/res/db/userdb";
    private static final String USER = "SA";
    private static final String PASS = "";

    @Override
    public Connection createConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(Constants.ERROR_TO_LOAD_JDBC);
        }

        return DriverManager.getConnection(URL, USER, PASS);
    }
}
