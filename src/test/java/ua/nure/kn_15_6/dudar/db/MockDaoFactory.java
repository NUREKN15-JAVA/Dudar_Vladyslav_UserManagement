package ua.nure.kn_15_6.dudar.db;

import com.mockobjects.dynamic.Mock;

public class MockDaoFactory extends DaoFactory {
    private Mock mockUserDao;

    public MockDaoFactory() {
        this.mockUserDao = new Mock(UserDao.class);
    }

    @Override
    public UserDao getUserDao() {
        return (UserDao) mockUserDao.proxy();
    }

    public Mock getMockUserDao() {
        return mockUserDao;
    }
}
