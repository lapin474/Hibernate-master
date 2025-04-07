package jm.task.core.jdbc.model;

import jm.task.core.User;

public class JDCBUser extends User {

    public JDCBUser() {
        super();
    }

    public JDCBUser(String name, String lastName, Byte age) {
        super(name, lastName, age);
    }

    public JDCBUser(long id, String name, String lastName, byte age) {
        super(id, name, lastName, age);
    }

    @Override
    public String toString() {
        return "JDCBUser{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", age=" + getAge() +
                '}';
    }
}
