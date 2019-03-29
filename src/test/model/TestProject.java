package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.exceptions.ParsingException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProject {
    private Project project;
    private List<Task> tasks = new ArrayList();

    @BeforeEach
    void beforeeach() {
        project = new Project("Wow!");
    }

    @Test
    void testadd() throws ParsingException {
        project.add(new Task("Hello!##af"));
        assertEquals(project.getNumberOfTasks(), 1);
        project.remove(new Task("Hello!##af"));
        assertEquals(project.getNumberOfTasks(), 0);
    }

    @Test
    void testgetProgress() throws ParsingException {
        project.add(new Task("sup##af"));
        Task testTask = new Task("sup##af");
        testTask.setStatus(Status.DONE);
        project.add(testTask);
        assertEquals(project.getProgress(), 0);

    }

    @Test
    void testisCompleted() throws ParsingException {
        assertEquals(project.isCompleted(), false);
        Task testTask = new Task("sup##af");
        testTask.setStatus(Status.DONE);
        project.add(testTask);
    }

    @Test
    void testContains() throws ParsingException {
        Task testTask = new Task("sup##af");
        project.add(testTask);
        assertEquals(project.contains(new Task("HI##af")), false);
        assertEquals(project.contains(testTask), true);


    }

}