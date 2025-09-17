package atlas.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testTodoConstructor_withName() {
        Todo todo = new Todo("Read book");
        assertEquals("Read book", todo.getName());
        assertFalse(todo.getIsDone());
        assertEquals("[T] [ ] Read book", todo.toString());
    }

    @Test
    public void testTodoConstructor_withNameAndStatus() {
        Todo todo = new Todo(true, "Read book");
        assertEquals("Read book", todo.getName());
        assertTrue(todo.getIsDone());
        assertEquals("[T] [X] Read book", todo.toString());
    }

    @Test
    public void testTodoConstructor_withNameAndStatusFalse() {
        Todo todo = new Todo(false, "Read book");
        assertEquals("Read book", todo.getName());
        assertFalse(todo.getIsDone());
        assertEquals("[T] [ ] Read book", todo.toString());
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("Read book");
        assertFalse(todo.getIsDone());
        todo.markAsDone();
        assertTrue(todo.getIsDone());
        assertEquals("[T] [X] Read book", todo.toString());
    }

    @Test
    public void testMarkAsNotDone() {
        Todo todo = new Todo(true, "Read book");
        assertTrue(todo.getIsDone());
        todo.markAsNotDone();
        assertFalse(todo.getIsDone());
        assertEquals("[T] [ ] Read book", todo.toString());
    }

    @Test
    public void testFileFormat_notDone() {
        Todo todo = new Todo("Read book");
        assertEquals("T | false | Read book", todo.fileFormat());
    }

    @Test
    public void testFileFormat_done() {
        Todo todo = new Todo(true, "Read book");
        assertEquals("T | true | Read book", todo.fileFormat());
    }

    @Test
    public void testToString_notDone() {
        Todo todo = new Todo("Read book");
        assertEquals("[T] [ ] Read book", todo.toString());
    }

    @Test
    public void testToString_done() {
        Todo todo = new Todo(true, "Read book");
        assertEquals("[T] [X] Read book", todo.toString());
    }

    @Test
    public void testTodoWithSpecialCharacters() {
        Todo todo = new Todo("Read book | with | special chars");
        assertEquals("Read book | with | special chars", todo.getName());
        assertEquals("T | false | Read book | with | special chars", todo.fileFormat());
        assertEquals("[T] [ ] Read book | with | special chars", todo.toString());
    }

    @Test
    public void testTodoWithEmptyString() {
        Todo todo = new Todo("");
        assertEquals("", todo.getName());
        assertEquals("T | false | ", todo.fileFormat());
        assertEquals("[T] [ ] ", todo.toString());
    }

    @Test
    public void testTodoWithWhitespace() {
        Todo todo = new Todo("   Read book   ");
        assertEquals("   Read book   ", todo.getName());
        assertEquals("T | false |    Read book   ", todo.fileFormat());
        assertEquals("[T] [ ]    Read book   ", todo.toString());
    }
}