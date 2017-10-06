package ua.nure.kn_15_6.dudar;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

/**
 * Created by Vlad on 30.09.2017.
 */
public class TestUser extends TestCase {
    private User user;
    private LocalDate birthDate;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        user = new User();
        birthDate = LocalDate.of(1998, Month.AUGUST, 17);
    }

    public void testGetFullName() throws Exception {
        user.setFirstName("Vlad");
        user.setLastName("Dudar");
        assertEquals("Dudar, Vlad", user.getFullName());
    }

    public void testGetAge() throws Exception {

        user.setBirthDate(birthDate);
        assertEquals(Period.between(birthDate, LocalDate.now()).getYears(), user.getAge());
    }

    public void testGetFullNameWithException() {
        try {
            user.getFullName();
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), Constants.NO_NAME_ERR);
        }
    }

    public void testGetAgeWithException() {
        try {
            user.getAge();
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), Constants.NO_BIRTHDATE_ERR);
        }
    }
}
