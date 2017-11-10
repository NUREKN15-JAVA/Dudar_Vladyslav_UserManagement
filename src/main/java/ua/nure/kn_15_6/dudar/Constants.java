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
    public static final String ERR_FAILED_TO_GET_PROPERTIES = "Failed to get properties";

    //SQL Queries
    public static final String SQL_INSERT_USER = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
    public static final String SQL_IDENTITY = "call IDENTITY()";
    public static final String SQL_SELECT_ALL = "SELECT id, firstname, lastname, dateofbirth FROM users";
    public static final String SQL_UPDATE = "UPDATE users SET firstname=?, lastname=?, dateofbirth=? WHERE id=?";
    public static final String SQL_DELETE = "DELETE FROM users WHERE id=?";
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM users WHERE id=?";

    //Strings
    public static final String MAIN_FRAME_NAME = "User Management";

    //Components names
}
