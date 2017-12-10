package ua.nure.kn_15_6.dudar.web;

import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.view.web.EditServlet;

import java.text.DateFormat;
import java.time.LocalDate;

public class EditServletTest extends MockServletTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(EditServlet.class);
    }

    public void testUpdate() {
        User user = new User(1000L, "John", "Dao", LocalDate.now());
        mockUserDao.expect("update", user);

        addRequestParameter("ok", "OK");
        addRequestParameter("id", user.getId().toString());
        addRequestParameter("firstName", user.getFirstName());
        addRequestParameter("lastName", user.getLastName());
        addRequestParameter("date", DateFormat.getDateInstance().format(user.getBirthDate()));
        doPost();
    }

    public void testEditEmptyFirstName() {
        addRequestParameter("ok", "OK");
        addRequestParameter("id", "1000");
        addRequestParameter("lastName", "Dao");
        addRequestParameter("date", "11.11.1111");
        doPost();

        String error = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Session don't return the error message", error);
    }

    public void testEditEmptyLastName() {
        addRequestParameter("ok", "OK");
        addRequestParameter("id", "1000");
        addRequestParameter("fistName", "John");
        addRequestParameter("date", "11.11.1111");
        doPost();

        String error = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Session don't return the error message", error);
    }

    public void testEditEmptyDate() {
        addRequestParameter("ok", "OK");
        addRequestParameter("id", "1000");
        addRequestParameter("fistName", "John");
        addRequestParameter("lastName", "Dao");
        doPost();

        String error = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Session don't return the error message", error);
    }
}
