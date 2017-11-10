package ua.nure.kn_15_6.dudar.db;

import ua.nure.kn_15_6.dudar.Constants;
import ua.nure.kn_15_6.dudar.User;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {
    protected static final String USER_DAO = "ua.nure.kn_15_6.dudar.db.UserDao";
    private static final String DAO_FACTORY = "dao.factory";
    protected static Properties properties;

    private static volatile DaoFactory instance;

    static{
        properties = new Properties();
        try {
            properties.load(DaoFactory.class.getClassLoader().getResourceAsStream(
                    "settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected DaoFactory() {
    }

    public static void init(Properties prop) {
        properties = prop;
        instance = null;
    }

    public static DaoFactory getInstance() {
        DaoFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (DaoFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    Class factoryClass = null;
                    try {
                        factoryClass = Class.forName(properties
                                .getProperty(DAO_FACTORY));
                        instance = localInstance = (DaoFactory) factoryClass.newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return localInstance;
    }

    protected ConnectionFactory getConnectionFactory() {
        String driver = properties.getProperty("connection.driver");
        String url = properties.getProperty("connection.url");
        String user = properties.getProperty("connection.user");
        String password = properties.getProperty("connection.password");
        return new ConnectionFactoryImpl(driver, url, user, password);
    }

    public abstract UserDao getUserDao();
}
