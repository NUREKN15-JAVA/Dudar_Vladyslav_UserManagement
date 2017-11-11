package ua.nure.kn_15_6.dudar.db;

import ua.nure.kn_15_6.dudar.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockUserDao implements UserDao {
    long id = 0;
    Map<Long, User> users = new HashMap<>();

    @Override
    public User create(User user) throws SQLException {
        Long currentId = ++id;
        user.setId(currentId);
        users.put(currentId, user);
        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        users.put(user.getId(), user);
    }

    @Override
    public void delete(User user) throws SQLException {
        users.remove(user.getId());
        id--;
    }

    @Override
    public User find(Long id) throws SQLException {
        return users.get(id);
    }

    @Override
    public Collection<User> findAll() throws SQLException {
        return users.values();
    }

    @Override
    public ConnectionFactory getConnectionFactory() {
        return null;
    }

    @Override
    public void setConnectionFactory(ConnectionFactory connectionFactory) {

    }


}
