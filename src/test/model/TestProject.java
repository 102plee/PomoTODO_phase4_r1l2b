package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.exceptions.ParsingException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestProject {
    private Project project;
    private List<Task> tasks = new ArrayList();
    private Task task1;
    private Task task2;
    private Task task3;
    private Task task4;
    private Task task5;


    @BeforeEach
    void beforeeach() {
        project = new Project("Wow!");
        task1 = new Task("dance1##kek1");
        task2 = new Task("dance2##kek2");
        task3 = new Task("dance3##kek3");
        task4 = new Task("dance4##kek4");
        task5 = new Task("dance5##kek5");

        task1.setPriority(new Priority(1));
        task2.setPriority(new Priority(2));
        task3.setPriority(new Priority(3));
        task4.setPriority(new Priority(4));
        task5.setPriority(new Priority(1));
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

    @Test
    void testIterator() {
        project.add(task2);
        project.add(task1);
        project.add(task4);
        project.add(task3);
        project.add(task5);
        Project projecttest = new Project("afjioew##fioaw");
        projecttest.setPriority(new Priority(1));
        project.add(projecttest);
        Iterator<Todo> itr = project.iterator();
        assertEquals(task1, itr.next());
        assertEquals(task5, itr.next());
        assertEquals(projecttest, itr.next());
        assertEquals(task2, itr.next());
        assertEquals(task3, itr.next());
        assertEquals(task4, itr.next());

        try {
            itr.next();
            fail();
        } catch (Exception e) {

        }
    }


}