package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE users (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20)," +
                "lastName VARCHAR(20), age TINYINT)";
        try (
                Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(createTableSQL); // executeUpdate лучше подходит для DDL-команд
            System.out.println("✅ Таблица 'users' успешно создана (если ее не было)!");
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при создании таблицы: " + e.getMessage());
        }

    }

    public void dropUsersTable() {
        String dropTableSQL = "DROP TABLE IF EXISTS users";

        try (
                Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(dropTableSQL); // executeUpdate лучше подходит для DDL-команд
            System.out.println("✅ Таблица 'users' успешно удалена (если она была)!");
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при удалении таблицы: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertUserSQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        User user = new User(name, lastName, age);

        try (
                Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("✅ Пользователь успешно добавлен!");
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при добавлении пользователя: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String deleteUserSQL = "DELETE FROM users WHERE id = " + id;

        try (
                Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(deleteUserSQL); // executeUpdate лучше подходит для DML-команд
            System.out.println("✅ Пользователь успешно удален!");
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при удалении пользователя: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String getAllUsersSQL = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllUsersSQL)) {

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при получении всех пользователей: " + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanTableSQL = "DELETE FROM users";

        try (
                Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(cleanTableSQL); // executeUpdate лучше подходит для DML-команд
            System.out.println("✅ Таблица 'users' успешно очищена!");
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при очистке таблицы: " + e.getMessage());
        }
    }
}
