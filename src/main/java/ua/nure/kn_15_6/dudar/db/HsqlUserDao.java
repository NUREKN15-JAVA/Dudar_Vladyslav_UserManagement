package ua.nure.kn_15_6.dudar.db;

import com.sun.org.apache.regexp.internal.RE;
import ua.nure.kn_15_6.dudar.Constants;
import ua.nure.kn_15_6.dudar.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

class HsqlUserDao implements UserDao {
    private ConnectionFactory connectionFactory;

    public HsqlUserDao() {
    }

    public HsqlUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) throws SQLException {
        try(Connection conn = connectionFactory.createConnection();
            PreparedStatement ps = conn.prepareStatement(Constants.SQL_INSERT_USER)) {

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
        try(Connection conn = connectionFactory.createConnection();
            PreparedStatement ps = conn.prepareStatement(Constants.SQL_UPDATE)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setDate(3, Date.valueOf(user.getBirthDate()));
            ps.setLong(4, user.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        try(Connection conn = connectionFactory.createConnection();
            PreparedStatement ps = conn.prepareStatement(Constants.SQL_DELETE)) {

            ps.setLong(1, user.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public User find(Long id) throws SQLException {
        try(Connection conn = connectionFactory.createConnection();
            PreparedStatement ps = conn.prepareStatement(Constants.SQL_SELECT_BY_ID)) {

            ps.setLong(1, id);
            try(ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    long userId = res.getLong(1);
                    String firstName = res.getString(2);
                    String lastName = res.getString(3);
                    LocalDate dateOfBirth = res.getDate(4).toLocalDate();
                    return new User(userId, firstName, lastName, dateOfBirth);
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public Collection<User> findAll() throws SQLException {
        List<User> allUsers;
        try(Connection conn = connectionFactory.createConnection();
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery(Constants.SQL_SELECT_ALL)) {

            allUsers = new LinkedList<>();
            while (res.next()) {
                long id = res.getLong(1);
                String firstName = res.getString(2);
                String lastName = res.getString(3);
                LocalDate dateOfBirth = res.getDate(4).toLocalDate();
                allUsers.add(new User(id, firstName, lastName, dateOfBirth));
            }
        }
        return allUsers;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
