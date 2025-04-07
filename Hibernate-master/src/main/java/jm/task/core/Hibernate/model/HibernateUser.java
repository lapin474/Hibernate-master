package jm.task.core.Hibernate.model;

import jm.task.core.User;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class HibernateUser extends User {

    // ID будет сгенерирован автоматически
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId(); // Используем геттер родительского класса
    }

    // Переопределяем необходимые методы, так как они есть в базовом классе User
    @Override
    @Column(name = "name", nullable = false)
    public String getName() {
        return super.getName();
    }

    @Override
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    @Column(name = "age")
    public Byte getAge() {
        return super.getAge();
    }

    // Конструктор без параметров
    public HibernateUser() {}

    // Конструктор с параметрами
    public HibernateUser(String name, String lastName, Byte age) {
        super(name, lastName, age); // Вызов конструктора родительского класса
    }

    // Конструктор с параметрами и id
    public HibernateUser(Long id, String name, String lastName, Byte age) {
        super(id, name, lastName, age); // Вызов конструктора родительского класса
    }

    // Переопределяем toString
    @Override
    public String toString() {
        return "HibernateUser{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", age=" + getAge() +
                '}';
    }
}
