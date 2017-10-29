package ua.nure.kn_15_6.dudar.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {
    private static String DRIVER = "org.hsqldb.jdbcDriver";
    private static String URL = "jdbc:hsqldb:file:/res/db/userdb";
    private static String USER = "SA";
    private static String PASS = "";

    @Override
    public Connection createConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ERROR: failed to load HSQLDB JDBC driver.");
        }

        return DriverManager.getConnection(URL, USER, PASS);
    }
}
