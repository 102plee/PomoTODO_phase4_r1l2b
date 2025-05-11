package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.exceptions.ParsingException;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TestTaskPhase3 {
    // TODO: design tests for new behaviour added to Task class
    private Task task;
    private Calendar today = Calendar.getInstance();
    private Date date = today.getTime();

    @BeforeEach
    void idk() {
        task = new Task("a##love;live");
    }

    @Test
    void testConstructor() throws ParsingException {

        task = new Task("ahahaha##love;live");

        assertEquals(task.getDescription(), "ahahaha");
    }

    @Test
    void testTags() throws ParsingException {

        task.addTag("hello");
        assertTrue(task.containsTag("hello"));
        task.removeTag("hello");
        assertEquals(task.containsTag("hello"), false);
        assertEquals(task.getTags().toString(), "[#love, #live]");
    }

    @Test
    void testPriority() throws ParsingException {

        assertEquals(task.getPriority().toString(), "DEFAULT");
        task.setPriority(new Priority(3));
        assertEquals(task.getPriority().toString(), "URGENT");
    }

    @Test
    void testStatus() throws ParsingException {

        task.setStatus(Status.IN_PROGRESS);
        assertEquals(task.getStatus(), Status.IN_PROGRESS);

    }

    @Test
    void testDueDate() throws ParsingException {

        task.setDueDate(new DueDate());
        assertEquals(task.getDueDate().toString(), new DueDate().toString());

    }

    @Test
    void testToString() throws ParsingException {

        String test = task.toString();
        assertTrue(test!="");


        Task tasktest = task;
        assertTrue(task.equals(tasktest));


    }


    @Test
    void testAddTag(){
        Tag tag = null;

        try {
            task.addTag(tag);
        } catch (NullArgumentException nae) {

        }

        String nullstring = null;
        try {
            task.removeTag(nullstring);
        } catch (EmptyStringException ese) {
            System.out.println("hi");
        }


    }

}