package ua.nure.kn_15_6.dudar;

import java.time.format.DateTimeFormatter;

/**
 * Created by Vlad on 05.10.2017.
 */
public class Constants {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

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
    public static final String SQL_SELECT_BY_FL_NAME = "SELECT * FROM users WHERE firstname=? AND lastname=?";

    //Buttons parameters
    public static final String PARAM_ADD = "add";
    public static final String PARAM_EDIT = "edit";
    public static final String PARAM_DELETE = "delete";
    public static final String PARAM_DETAILS = "details";
    public static final String PARAM_OK = "ok";
    public static final String PARAM_CANCEL = "cancel";
    public static final String PARAM_BACK = "back";

    //Textfield parameters
    public static final String PARAM_ID = "id";
    public static final String PARAM_FIRST_NAME = "firstName";
    public static final String PARAM_LAST_NAME = "lastName";
    public static final String PARAM_DATE = "date";

    //Attributes keys
    public static final String KEY_ERR = "error";
    public static final String KEY_USER = "user";
    public static final String KEY_USERS = "users";
}
