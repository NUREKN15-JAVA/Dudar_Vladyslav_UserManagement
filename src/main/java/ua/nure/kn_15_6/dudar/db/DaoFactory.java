package ua.nure.kn_15_6.dudar.db;

import ua.nure.kn_15_6.dudar.Constants;
import ua.nure.kn_15_6.dudar.User;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
    private static final String USER_DAO = "ua.nure.kn_15_6.dudar.db.UserDao";
    private final Properties properties;

    private static volatile DaoFactory instance;

    private DaoFactory() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            System.err.println(Constants.ERR_FAILED_TO_GET_PROPERTIES);
        }
    }

    public static DaoFactory getInstance() {
        DaoFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (DaoFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DaoFactory();
                }
            }
        }
        return localInstance;
    }

    public ConnectionFactory getConnectionFactory() {
        String driver = properties.getProperty("connection.driver");
        String url = properties.getProperty("connection.url");
        String user = properties.getProperty("connection.user");
        String password = properties.getProperty("connection.password");
        return new ConnectionFactoryImpl(driver, url, user, password);
    }

    public UserDao getUserDao() {
        UserDao result = null;

        try {
            Class clazz = Class.forName(properties.getProperty(USER_DAO));
            UserDao userDao = (UserDao) clazz.newInstance();
            userDao.setConnectionFactory(getConnectionFactory());
            result = userDao;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get UserDao class");
        }

        return result;
    }
}
