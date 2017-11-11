package ua.nure.kn_15_6.dudar.db;

public class DaoFactoryImpl extends DaoFactory {
    @Override
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
