package ua.nure.kn_15_6.dudar;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Created by Vlad on 30.09.2017.
 */
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public User() {
    }

    public User(Long id, String firstName, String lastName, LocalDate date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFullName() throws Exception{
        if (firstName == null || lastName == null)
            throw new IllegalArgumentException(Constants.ERR_NO_NAME);
        return String.format("%s, %s", getLastName(), getFirstName());
    }

    public long getAge() throws Exception {
        if (birthDate != null) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            throw new IllegalArgumentException(Constants.ERR_NO_BIRTHDATE);
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getId() == null && ((User) o).getId() == null) {
            return true;
        }

        return this.getId().equals(((User) o).getId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthDate.hashCode();
        return result;
    }
}
