package ru.job4j.generics;

import java.util.Objects;

public class Role extends Base {

    private String role;
    private String id;

    public Role(String id, String role) {
        super(id);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role1 = (Role) o;
        return Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }

    @Override
    public String toString() {
        return "Role{"
                + "role='" + role + '\''
                + '}';
    }
}
