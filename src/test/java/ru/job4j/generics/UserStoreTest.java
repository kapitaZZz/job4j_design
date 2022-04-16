package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    @Test
    public void whenAddAndFindThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        User result = store.findById("1");
        assertThat(result.getUsername(), is("Petr"));
    }

    @Test
    public void whenAddAndFindThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        User result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.add(new User("1", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername(), is("Petr"));
    }

    @Test
    public void whenReplaceThenUsernameIsMaxim() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.replace("1", new User("1", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername(), is("Maxim"));
    }

    @Test
    public void whenNoReplaceUserThenNoChangeUsername() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.replace("10", new User("10", "Maxim"));
        User result = store.findById("1");
        assertThat(result.getUsername(), is("Petr"));
    }

    @Test
    public void whenDeleteUserThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.delete("1");
        User result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.delete("10");
        User result = store.findById("1");
        assertThat(result.getUsername(), is("Petr"));
    }

    @Test
    public void testAddRole() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "ADMIN"));
        Role result = roleStore.findById("1");
        assertThat(result.getRole(), is("ADMIN"));
    }

    @Test
    public void testReplaceRole() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("2", "USER"));
        roleStore.replace("2", new Role("2", "ADMIN"));
        assertThat(roleStore.findById("2").getRole(), is("ADMIN"));
    }

    @Test
    public void testDeleteRole() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "USER"));
        roleStore.delete("1");
        assertNull(roleStore.findById("1"));
    }

}