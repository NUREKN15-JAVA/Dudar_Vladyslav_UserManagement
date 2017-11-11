package ua.nure.kn_15_6.dudar.view;

import com.mockobjects.dynamic.Mock;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.db.DaoFactory;
import ua.nure.kn_15_6.dudar.db.DaoFactoryImpl;
import ua.nure.kn_15_6.dudar.db.MockDaoFactory;
import ua.nure.kn_15_6.dudar.db.MockUserDao;
import ua.nure.kn_15_6.dudar.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import static ua.nure.kn_15_6.dudar.Constants.*;
import static ua.nure.kn_15_6.dudar.Constants.formatter;

public class MainFrameTest extends JFCTestCase {

    private MainFrame mainFrame;
    private Mock mockUserDao;

    public void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
//        properties.setProperty("dao.db.UserDao", MockUserDao.class.getName());
        properties.setProperty("dao.factory", MockDaoFactory.class.getName());
        DaoFactory.init(properties);

        mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
        mockUserDao.expectAndReturn("findAll", new ArrayList());

        setHelper(new JFCTestHelper());
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);


    }

    public void tearDown() throws Exception {
        try {
            mockUserDao.verify();
            mainFrame.setVisible(false);
            getHelper().cleanUp(this);
            super.tearDown();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Component find(Class componentClass, String name) {
        NamedComponentFinder finder = new NamedComponentFinder(componentClass, name);
        finder.setWait(0);
        Component component = finder.find(mainFrame,0);
        assertNotNull("Could not find component ‘" + name +"’", component);
        return component;
    }

    public void testBrowseControls() throws Exception {
        find(JPanel.class, "browsePanel");

        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(3, table.getColumnCount());
        assertEquals(Messages.getString("UserTableModel.id"),table.getColumnName(0));
        assertEquals(Messages.getString("UserTableModel.first_name"),table.getColumnName(1));
        assertEquals(Messages.getString("UserTableModel.last_name"),table.getColumnName(2));

        find(JButton.class, "addButton");
        find(JButton.class, "editButton");
        find(JButton.class, "deleteButton");
        find(JButton.class, "detailsButton");
    }

    public void testAddUser() throws Exception {
        String firstName = "John";
        String lastName = "Smith";
        LocalDate date = LocalDate.now();

        User user = new User(firstName, lastName, date);
        User expectedUser = new User(1L, firstName, lastName, date);
        mockUserDao.expectAndReturn("create", user, expectedUser);

        ArrayList<User> users = new ArrayList<>();
        users.add(expectedUser);
        mockUserDao.expectAndReturn("findAll", users);

        JTable userTable = (JTable) find(JTable.class, "userTable");
        assertEquals(0, userTable.getRowCount());

        JButton addButton = (JButton) find(JButton.class, "addButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

        find(JPanel.class, "addPanel");
        JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
        JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
        JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");
        JButton okButton = (JButton) find(JButton.class, "okButton");
        find(JButton.class, "cancelButton");

        getHelper().sendString(new StringEventData(this, firstNameField, firstName));
        getHelper().sendString(new StringEventData(this, lastNameField, lastName));
        getHelper().sendString(new StringEventData(this, dateOfBirthField, formatter.format(date)));
        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

        find(JPanel.class, "browsePanel");
        userTable = (JTable) find(JTable.class, "userTable");
        assertEquals(1, userTable.getRowCount());
    }
}
