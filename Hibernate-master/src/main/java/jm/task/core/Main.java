package jm.task.core;

import jm.task.core.Hibernate.model.HibernateUser;
import jm.task.core.Hibernate.service.UserServiceImplHibernate;
import jm.task.core.UserService;
import jm.task.core.jdbc.model.JDCBUser;
import jm.task.core.jdbc.service.UserServiceImplJDBC;
import jm.task.core.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userServiceJDBC = new UserServiceImplJDBC();
        UserService userServiceHibernate = new UserServiceImplHibernate();

        System.out.println("Тестирование Hibernate:");
        runUserTest(userServiceHibernate);

        System.out.println("Тестирование JDBC:");
        runUserTest(userServiceJDBC);
    }

    public static <T extends User> void runUserTest(UserService userService) throws SQLException {
        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1);

        System.out.println("Пользователи:");
        List<? extends User> users = userService.getAllUsers();
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
