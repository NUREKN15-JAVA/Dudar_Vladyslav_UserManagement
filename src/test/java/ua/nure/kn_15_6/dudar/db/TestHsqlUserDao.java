package ua.nure.kn_15_6.dudar.db;

import junit.framework.TestCase;
import ua.nure.kn_15_6.dudar.User;

import java.sql.SQLException;
import java.time.LocalDate;

public class TestHsqlUserDao extends TestCase {
    private ConnectionFactory connectionFactory;
    private HsqlUserDao dao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        connectionFactory = new ConnectionFactoryImpl();
        dao = new HsqlUserDao(connectionFactory);
    }

    public void testCreate() {
        try {
            User user = new User();
            user.setFirstName("Alex");
            user.setLastName("Bess");
            user.setBirthDate(LocalDate.now());
            assertNull(user.getId());
            dao.create(user);
            assertNotNull(user);
            assertNotNull(user.getId()  );
        } catch (SQLException e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }
}
