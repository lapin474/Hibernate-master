package jm.task.core;

import jm.task.core.jdbc.model.JDCBUser;

import java.util.List;

public interface UserDao {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<? extends User> getAllUsers();

    void cleanUsersTable();
}
