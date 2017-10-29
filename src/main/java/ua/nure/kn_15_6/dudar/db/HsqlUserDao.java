package ua.nure.kn_15_6.dudar.db;

import ua.nure.kn_15_6.dudar.Constants;
import ua.nure.kn_15_6.dudar.User;

import java.sql.*;
import java.util.Collection;

public class HsqlUserDao implements UserDao {
    private ConnectionFactory connectionFactory;

    public HsqlUserDao() {
    }

    public HsqlUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) throws SQLException {
        try(Connection conn = connectionFactory.createConnection();
            PreparedStatement ps = conn.prepareStatement(Constants.SQL_CREATE_USER)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setDate(3, Date.valueOf(user.getBirthDate()));
            int n = ps.executeUpdate();

            if (n != 1) {
                throw new SQLException("Number of the inserted rows: " + n);
            }

            try(CallableStatement cs = conn.prepareCall(Constants.SQL_IDENTITY);
                ResultSet keys = cs.executeQuery()) {

                if (keys.next()) {
                    user.setId(keys.getLong(1));
                } else {
                    throw new SQLException(Constants.ERR_NO_USER);
                }
            }
        }

        return user;
    }

    @Override
    public void update(User user) throws SQLException {

    }

    @Override
    public void delete(User user) throws SQLException {

    }

    @Override
    public User find(Long id) throws SQLException {
        return null;
    }

    @Override
    public Collection findAll() throws SQLException {
        return null;
    }
}
