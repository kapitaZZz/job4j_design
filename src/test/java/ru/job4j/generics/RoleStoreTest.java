package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

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