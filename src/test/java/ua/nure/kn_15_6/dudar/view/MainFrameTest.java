package ua.nure.kn_15_6.dudar.view;

import com.mockobjects.dynamic.Mock;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.db.DaoFactory;
import ua.nure.kn_15_6.dudar.db.MockDaoFactory;
import ua.nure.kn_15_6.dudar.util.Message;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static ua.nure.kn_15_6.dudar.Constants.formatter;

public class MainFrameTest extends JFCTestCase {

    private MainFrame mainFrame;
    private Mock mockUserDao;
    private List<User> users;

    public void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
//        properties.setProperty("dao.db.UserDao", MockUserDao.class.getName());
        properties.setProperty("dao.factory", MockDaoFactory.class.getName());
        DaoFactory.init(properties);

        initUsers();

        mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
        mockUserDao.expectAndReturn("findAll", users);

        setHelper(new JFCTestHelper());
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);


    }

    private void initUsers() {
        users  = new ArrayList<>();
        String firstName = "John";
        String lastName = "Smith";
        LocalDate date = LocalDate.now();
        User user = new User(1L, firstName, lastName, date);
        users.add(user);
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
        assertEquals(Message.getString("UserTableModel.id"),table.getColumnName(0));
        assertEquals(Message.getString("UserTableModel.first_name"),table.getColumnName(1));
        assertEquals(Message.getString("UserTableModel.last_name"),table.getColumnName(2));

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

    public void testEditUser() throws Exception {
        String firstName = "John";
        String lastName = "McCane";
        LocalDate date = LocalDate.now();

        User user = users.get(0);
        User expectedUser = new User(1L, firstName, lastName, date);
        mockUserDao.expectAndReturn("update", user, expectedUser);

        ArrayList<User> users = new ArrayList<>();
        users.add(expectedUser);
        mockUserDao.expectAndReturn("findAll", users);

        JTable userTable = (JTable) find(JTable.class, "userTable");
        assertEquals(1, userTable.getRowCount());

        userTable.setRowSelectionInterval(0, 0);

        JButton editButton = (JButton) find(JButton.class, "editButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, editButton));

        find(JPanel.class, "editPanel");
        find(JTextField.class, "firstNameField");
        JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
        find(JTextField.class, "dateOfBirthField");
        JButton okButton = (JButton) find(JButton.class, "okButton");
        find(JButton.class, "cancelButton");

        lastNameField.setText("");
        getHelper().sendString(new StringEventData(this, lastNameField, lastName));
        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

        find(JPanel.class, "browsePanel");
        userTable = (JTable) find(JTable.class, "userTable");
        assertEquals(1, userTable.getRowCount());
    }

    public void testDeleteUser() throws Exception {
        User expectedUser = users.get(0);
        mockUserDao.expect("delete", expectedUser);
        mockUserDao.expectAndReturn("findAll", new ArrayList<>());

        JTable userTable = (JTable) find(JTable.class, "userTable");
        assertEquals(1, userTable.getRowCount());

        userTable.setRowSelectionInterval(0, 0);

        JButton editButton = (JButton) find(JButton.class, "deleteButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, editButton));

//        JFrame deleteFrame = (JFrame) find(JFrame.class, "deleteFrame");
//
//        JButton yesBtn = getButton(deleteFrame, "Yes");
//        getHelper().enterClickAndLeave(new MouseEventData(this, yesBtn));

        find(JPanel.class, "browsePanel");
        userTable = (JTable) find(JTable.class, "userTable");
        assertEquals(0, userTable.getRowCount());
    }

    public static JButton getButton(Container container, String text) {
        JButton btn = null;
        List<Container> children = new ArrayList<>(25);
        for (Component child : container.getComponents()) {
            System.out.println(child);
            if (child instanceof JButton) {
                JButton button = (JButton) child;
                if (text.equals(button.getText())) {
                    btn = button;
                    break;
                }
            } else if (child instanceof Container) {
                children.add((Container) child);
            }
        }
        if (btn == null) {
            for (Container cont : children) {
                JButton button = getButton(cont, text);
                if (button != null) {
                    btn = button;
                    break;
                }
            }
        }
        return btn;
    }

    public void testDetails() throws Exception {
        mockUserDao.expectAndReturn("findAll", users);

        JTable userTable = (JTable) find(JTable.class, "userTable");
        assertEquals(1, userTable.getRowCount());

        userTable.setRowSelectionInterval(0, 0);

        JButton detailsButton = (JButton) find(JButton.class, "detailsButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, detailsButton));

        find(JPanel.class, "detailsPanel");
        find(JLabel.class, "idLabel");
        find(JLabel.class, "firstNameLabel");
        find(JLabel.class, "lastNameLabel");
        find(JLabel.class, "dateOfBirthLabel");
        JButton cancelButton = (JButton) find(JButton.class, "cancelButton");

        getHelper().enterClickAndLeave(new MouseEventData(this, cancelButton));

        find(JPanel.class, "browsePanel");
        userTable = (JTable) find(JTable.class, "userTable");
        assertEquals(1, userTable.getRowCount());
    }
}
