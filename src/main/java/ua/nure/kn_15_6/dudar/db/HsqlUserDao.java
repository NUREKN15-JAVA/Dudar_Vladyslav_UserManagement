package ua.nure.kn_15_6.dudar.db;

import ua.nure.kn_15_6.dudar.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class HsqlUserDao implements UserDao {
    private static String URL = "jdbc:hsqldb:file:/res/db/userdb";
    private Connection conn;

    public HsqlUserDao() {
//        conn = DriverManager.getConnection(URL, "SA", "");
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User find(Long id) {
        return null;
    }

    @Override
    public Collection findAll() {
        return null;
    }
}
