package ua.nure.kn_15_6.dudar;

/**
 * Created by Vlad on 05.10.2017.
 */
public class Constants {
    //Exception messages
    public static final String ERR_NO_NAME = "Name is not initialized.";
    public static final String ERR_NO_BIRTHDATE = "Birth date is not specified.";
    public static final String ERR_FAILED_TO_LOAD_JDBC = "ERROR: failed to load HSQLDB JDBC driver.";
    public static final String ERR_NO_USER = "Such user doesn't exist in database";

    //SQL Queries
    public static final String SQL_CREATE_USER = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
    public static final String SQL_IDENTITY = "call IDENTITY()";

    public static final String SQL_FIND_ALL = "SELECT id, firstname, lastname, dateofbirth FROM users";
}
