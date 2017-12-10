package ua.nure.kn_15_6.dudar.web;

import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.view.web.BrowseServlet;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BrowseServletTest extends MockServletTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    public void testBrowse() throws Exception {
        User user = new User(1000L, "John", "Doe", LocalDate.now());
        List list = Collections.singletonList(user);
        mockUserDao.expectAndReturn("findAll", list);
        doGet();

        Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull(collection);
        assertSame(list, collection);
    }

    public void testEdit() {
        User user = new User(1000L, "John", "Dao", LocalDate.now());
        mockUserDao.expectAndReturn("find", 1000L, user);
        addRequestParameter("edit", "Edit");
        addRequestParameter("id", "1000");
        doPost();

        User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull(userInSession);
        assertSame(user, userInSession);
    }

    public void testDelete() {
        User user = new User(1000L, "John", "Dao", LocalDate.now());
        mockUserDao.expectAndReturn("find", 1000L, user);
        mockUserDao.expect("delete", user);

        addRequestParameter("id", user.getId().toString());
        addRequestParameter("delete", "Delete");
        doPost();
    }

    public void testDetails() {
        User user = new User(1000L, "John", "Dao", LocalDate.now());
        mockUserDao.expectAndReturn("find", 1000L, user);

        addRequestParameter("details", "Details");
        addRequestParameter("id", user.getId().toString());
        addRequestParameter("firstName", user.getFirstName());
        addRequestParameter("lastName", user.getLastName());
        addRequestParameter("date", DateFormat.getDateInstance().format(user.getBirthDate()));
        doPost();

        User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull(userInSession);
        assertSame(user, userInSession);
    }
}
