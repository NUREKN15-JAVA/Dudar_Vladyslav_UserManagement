package ua.nure.kn_15_6.dudar.db;

import junit.framework.TestCase;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import ua.nure.kn_15_6.dudar.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class TestHsqlUserDao extends DatabaseTestCase {
    private ConnectionFactory connectionFactory;
    private HsqlUserDao dao;

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
//        connectionFactory = new ConnectionFactoryImpl();
        return new DatabaseConnection(DaoFactory.getInstance().getConnectionFactory().createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dao = (HsqlUserDao) DaoFactory.getInstance().getUserDao();
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

    public void testFindAll() {
        try {
            Collection<User> allUsers = dao.findAll();
            assertNotNull("Collection is null", allUsers);
            assertEquals("Collection size", 1, allUsers.size());
        } catch (SQLException e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

    public void testFind() {
        try {
            final Long id = 0L;
            User foundUser = dao.find(id);
            assertNotNull("User is null", foundUser);
            assertEquals("Different id", id, foundUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

    public void testUpdate() {
        try {
            final Long id=0L;
            User foundUser = dao.find(id);
            assertNotNull("User is null", foundUser);
            foundUser.setFirstName("Josh");
            dao.update(foundUser);
            User updatedUser = dao.find(foundUser.getId());
            assertNotNull("Updated user is null", updatedUser);
            assertEquals(foundUser, updatedUser);
        } catch (SQLException e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

    public void testDelete() throws Exception {
        try {
            final Long id = 0L;
            User foundUser = dao.find(id);
            assertNotNull("User is null", foundUser);
            dao.delete(foundUser);
            User deletedUser = dao.find(foundUser.getId());
            assertNull(deletedUser);
        } catch (SQLException e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }
}
