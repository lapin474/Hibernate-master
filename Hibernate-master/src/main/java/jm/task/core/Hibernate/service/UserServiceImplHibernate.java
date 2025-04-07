package jm.task.core.Hibernate.service;

import jm.task.core.Hibernate.dao.UserDaoHibernateImpl;
import jm.task.core.Hibernate.model.HibernateUser;
import jm.task.core.User;
import jm.task.core.UserDao;
import jm.task.core.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImplHibernate implements UserService {
    private final UserDao userDao;

    public UserServiceImplHibernate() throws SQLException {
        this.userDao = new UserDaoHibernateImpl();
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

    public List<? extends User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}