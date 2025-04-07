package jm.task.core.jdbc.service;

import jm.task.core.User;
import jm.task.core.UserDao;
import jm.task.core.UserService;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.JDCBUser;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImplJDBC implements UserService {
    private final UserDao userDao;

    public UserServiceImplJDBC() throws SQLException {
        this.userDao = new UserDaoJDBCImpl();
    }

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public List<? extends User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
