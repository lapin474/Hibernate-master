package jm.task.core.Hibernate;

import jm.task.core.Hibernate.model.User;
import jm.task.core.Hibernate.service.UserServiceImpl;
import jm.task.core.Hibernate.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        System.out.println("Тестирование Hibernate:");

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1);

        System.out.println("Пользователи:");
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Нет пользователей в базе.");
        } else {
            users.forEach(System.out::println);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

        List<String> names = List.of("Alice", "Bob", "Charlie", "David");
        names.parallelStream()
                .forEachOrdered(name -> System.out.println(Thread.currentThread().getName() + " -> " + name));
    }
}
