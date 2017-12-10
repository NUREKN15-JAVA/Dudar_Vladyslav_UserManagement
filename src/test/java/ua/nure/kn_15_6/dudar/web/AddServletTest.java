package ua.nure.kn_15_6.dudar.web;

import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.view.web.AddServlet;

import java.text.DateFormat;
import java.time.LocalDate;

public class AddServletTest extends MockServletTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    public void testAdd() {
        User user = new User("John", "Dao", LocalDate.now());
        User createdUser = user.copy();
        createdUser.setId(1000L);
        mockUserDao.expectAndReturn("create", user, createdUser);

        addRequestParameter("ok", "OK");
        addRequestParameter("firstName", user.getFirstName());
        addRequestParameter("lastName", user.getLastName());
        addRequestParameter("date", DateFormat.getDateInstance().format(user.getBirthDate()));
        doPost();
    }
}
