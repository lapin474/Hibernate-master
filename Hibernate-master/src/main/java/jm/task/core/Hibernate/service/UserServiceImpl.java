package jm.task.core.Hibernate.service;

import jm.task.core.Hibernate.dao.UserDaoImpl;
import jm.task.core.Hibernate.dao.UserDao;
import jm.task.core.Hibernate.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() throws SQLException {
        this.userDao = new UserDaoImpl();
    }

    public void createUsersTable() { userDao.createUsersTable(); }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}