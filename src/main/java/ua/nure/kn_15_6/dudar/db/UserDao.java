package ua.nure.kn_15_6.dudar.db;

import ua.nure.kn_15_6.dudar.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface UserDao {
    User create(User user) throws SQLException;
    void update(User user) throws SQLException;
    void delete(User user) throws SQLException;
    User find(Long id) throws SQLException;
    Collection<User> findAll() throws SQLException;

    ConnectionFactory getConnectionFactory();
    void setConnectionFactory(ConnectionFactory connectionFactory);
}
