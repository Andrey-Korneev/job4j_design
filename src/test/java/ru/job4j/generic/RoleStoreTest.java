package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRoleIsPirate() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pirate"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Pirate"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pirate"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindNameIsPirate() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pirate"));
        store.add(new Role("1", "Hero"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Pirate"));
    }

    @Test
    public void whenReplaceThenNameIsHero() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pirate"));
        store.replace("1", new Role("1", "Hero"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Hero"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pirate"));
        store.replace("10", new Role("10", "Hero"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Pirate"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pirate"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenNameIsPirate() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Pirate"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getName(), is("Pirate"));
    }
}