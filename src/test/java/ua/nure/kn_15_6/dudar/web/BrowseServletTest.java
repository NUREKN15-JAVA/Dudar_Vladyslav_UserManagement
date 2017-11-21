package ua.nure.kn_15_6.dudar.web;

import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.view.web.BrowseServlet;

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
}
