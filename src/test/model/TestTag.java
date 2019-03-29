package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestTag {
    // TODO: design tests for new behaviour added to Tag class

    private Tag tag;

    @BeforeEach
    public void runBefore() {
        tag = new Tag("Hello!");
    }

    @Test
    void testSomething() {

        Task task = new Task("hiya!##a;b");

        Set<Task> tasks = tag.getTasks();

        tag.addTask(task);
        assertTrue(tag.containsTask(task));
        tag.removeTask(task);
    }

    @Test
    void testGetTasks() {

    }

}
