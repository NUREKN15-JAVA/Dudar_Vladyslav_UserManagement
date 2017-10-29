package ua.nure.kn_15_6.dudar;

/**
 * Created by Vlad on 05.10.2017.
 */
public class Constants {
    //Exception messages
    public static final String NO_NAME_ERR = "Name is not initialized.";
    public static final String NO_BIRTHDATE_ERR = "Birth date is not specified.";
    public static final String ERROR_TO_LOAD_JDBC = "ERROR: failed to load HSQLDB JDBC driver.";

    //SQL Queries
    public static final String CREATE_USER = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
}
