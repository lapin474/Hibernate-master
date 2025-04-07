package jm.task.core.Hibernate.dao;

import jm.task.core.ConsoleUtils;
import jm.task.core.Hibernate.model.HibernateUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jm.task.core.Hibernate.util.HibernateUtil;
import jm.task.core.UserDao;
import jm.task.core.jdbc.model.JDCBUser;

import java.util.ArrayList;
import java.util.List;



public class UserDaoHibernateImpl implements UserDao {

    private static final String create_Users_Table = "CREATE TABLE IF NOT EXISTS users (" +
            "id BIGINT NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR(50), " +
            "last_name VARCHAR(50), " +
            "age TINYINT, " +
            "PRIMARY KEY (id))";
    private static final String drop_Users_Table = "DROP TABLE IF EXISTS users";
    private static final String save_User = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
    private static final String remove_User_By_Id = "DELETE FROM users WHERE id = ?";
    private static final String get_All_Users = "SELECT * FROM users";
    private static final String clean_Users_Table = "DELETE FROM users";


    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(create_Users_Table).executeUpdate();
            transaction.commit();

            ConsoleUtils.printSuccess("✅ Таблица 'users' успешно создана!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            ConsoleUtils.printError("❌ Ошибка при создании таблицы");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(drop_Users_Table).executeUpdate();
            transaction.commit();
            ConsoleUtils.printSuccess("✅ Таблица 'users' успешно удалена!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            ConsoleUtils.printError("❌ Ошибка при удалении таблицы");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(save_User)
                    .setParameter(1, name)
                    .setParameter(2, lastName)
                    .setParameter(3, age)
                    .executeUpdate();

            session.flush(); // Принудительное сохранение изменений в базу данных
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(remove_User_By_Id).setParameter(1, id).executeUpdate();

            session.flush(); // Принудительное сохранение изменений в базу данных
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<HibernateUser> getAllUsers() {
        Transaction transaction = null;
        List<HibernateUser> users = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            users = session.createQuery("from HibernateUser", HibernateUser.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback(); // безопасно
            }
            e.printStackTrace();
        }

        return users;
    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(clean_Users_Table).executeUpdate();

            session.flush(); // Принудительное сохранение изменений в базу данных
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
