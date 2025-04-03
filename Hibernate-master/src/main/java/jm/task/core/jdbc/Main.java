package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util.getConnection();
        UserService userService = new UserServiceImpl(); // Используем сервис

        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1);
        System.out.println(userService.getAllUsers()); // Теперь выводим список пользователей
        userService.cleanUsersTable();
        userService.dropUsersTable();

        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        names.parallelStream()
                .forEach(name -> System.out.println(Thread.currentThread().getName() + " -> " + name));
    }
}