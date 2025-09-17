package atlas.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import atlas.exceptions.EmptyTaskNameException;
import atlas.exceptions.InvalidDateFormatException;
import atlas.exceptions.InvalidDateRangeException;
import atlas.exceptions.InvalidDurationException;
import atlas.exceptions.InvalidFormatDeadlineException;
import atlas.exceptions.InvalidFormatEventException;
import atlas.exceptions.InvalidFormatFixedDurationException;
import atlas.exceptions.InvalidTaskNumberException;
import atlas.exceptions.MissingTaskNumberException;
import atlas.exceptions.PastDateException;
import atlas.items.Todo;
import org.junit.jupiter.api.Test;

public class ItemListTest {
    @Test
    public void newTodoTest() throws EmptyTaskNameException {
        ItemList itemList = new ItemList();
        String input = "todo Read book";
        itemList.newTodo(input);
        assertEquals(1, itemList.listSize());
        assertEquals("Read book", itemList.getItem(0).getName());
    }

    @Test
    public void markItemTest() throws EmptyTaskNameException, InvalidTaskNumberException, MissingTaskNumberException {
        ItemList itemList = new ItemList();
        String input = "todo Read book";
        itemList.newTodo(input);
        assertFalse(itemList.getItem(0).getIsDone());
        itemList.markItemAsDone("mark 1");
        assertTrue(itemList.getItem(0).getIsDone());
        itemList.markItemAsNotDone("unmark 1");
        assertFalse(itemList.getItem(0).getIsDone());
    }

    @Test
    public void deleteItem() throws EmptyTaskNameException, InvalidTaskNumberException, MissingTaskNumberException {
        ItemList itemList = new ItemList();
        String input = "todo Read book";
        itemList.newTodo(input);
        assertEquals(1, itemList.listSize());
        itemList.deleteItem("delete 1");
        assertEquals(0, itemList.listSize());
    }

    @Test
    public void testNewDeadline() throws EmptyTaskNameException, InvalidFormatDeadlineException, InvalidDateFormatException, PastDateException {
        ItemList itemList = new ItemList();
        String input = "deadline Submit report /by 01/12/2025 12:34";
        itemList.newDeadline(input);
        assertEquals(1, itemList.listSize());
        assertEquals("Submit report", itemList.getItem(0).getName());
        assertEquals("[D] [ ] Submit report (by: 1 Dec 2025 12:34 pm)", itemList.getItem(0).toString());
    }

    @Test
    public void testNewEvent() throws EmptyTaskNameException, InvalidFormatEventException, InvalidDateFormatException, PastDateException, InvalidDateRangeException {
        ItemList itemList = new ItemList();
        String input = "event Meeting /from 01/12/2025 12:34 /to 01/12/2025 14:34";
        itemList.newEvent(input);
        assertEquals(1, itemList.listSize());
        assertEquals("Meeting", itemList.getItem(0).getName());
        assertEquals("[E] [ ] Meeting (from: 1 Dec 2025 12:34 pm to: 1 Dec 2025 02:34 pm)", itemList.getItem(0).toString());
    }

    @Test
    public void testNewFixedDuration() throws EmptyTaskNameException, InvalidFormatFixedDurationException, InvalidDurationException {
        ItemList itemList = new ItemList();
        String input = "fixedDuration Study /duration 3";
        itemList.newFixedDuration(input);
        assertEquals(1, itemList.listSize());
        assertEquals("Study", itemList.getItem(0).getName());
        assertEquals("[F] [ ] Study (duration: 3 hours)", itemList.getItem(0).toString());
    }

    @Test
    public void testFindItem() throws EmptyTaskNameException {
        ItemList itemList = new ItemList();
        itemList.newTodo("todo Read book");
        itemList.newTodo("todo Write book");
        itemList.newTodo("todo Buy groceries");
        assertEquals(3, itemList.listSize());

        ItemList foundItems = itemList.findItem("find book");
        assertEquals(2, foundItems.listSize());
        assertEquals("Read book", foundItems.getItem(0).getName());
        assertEquals("Write book", foundItems.getItem(1).getName());
    }

    @Test
    public void testFindItemNotFound() throws EmptyTaskNameException {
        ItemList itemList = new ItemList();
        itemList.newTodo("todo Read book");
        itemList.newTodo("todo Write book");
        assertEquals(2, itemList.listSize());

        ItemList foundItems = itemList.findItem("find movie");
        assertEquals(0, foundItems.listSize());
    }

    @Test
    public void testMultipleOperations() throws EmptyTaskNameException, InvalidTaskNumberException, MissingTaskNumberException {
        ItemList itemList = new ItemList();

        // Add multiple items
        itemList.newTodo("todo Task 1");
        itemList.newTodo("todo Task 2");
        itemList.newTodo("todo Task 3");
        assertEquals(3, itemList.listSize());

        // Mark some as done
        itemList.markItemAsDone("mark 1");
        itemList.markItemAsDone("mark 3");
        assertTrue(itemList.getItem(0).getIsDone());
        assertFalse(itemList.getItem(1).getIsDone());
        assertTrue(itemList.getItem(2).getIsDone());

        // Delete one item
        itemList.deleteItem("delete 2");
        assertEquals(2, itemList.listSize());
        assertEquals("Task 1", itemList.getItem(0).getName());
        assertEquals("Task 3", itemList.getItem(1).getName());

        // Unmark one item
        itemList.markItemAsNotDone("unmark 1");
        assertFalse(itemList.getItem(0).getIsDone());
        assertTrue(itemList.getItem(1).getIsDone());
    }

    @Test
    public void testMarkInvalidTaskNumber() {
        ItemList itemList = new ItemList();
        assertThrows(InvalidTaskNumberException.class, () -> {
            itemList.markItemAsDone("mark 1");
        });
        assertThrows(InvalidTaskNumberException.class, () -> {
            itemList.markItemAsNotDone("unmark 1");
        });
    }

    @Test
    public void testDeleteInvalidTaskNumber() {
        ItemList itemList = new ItemList();
        assertThrows(InvalidTaskNumberException.class, () -> {
            itemList.deleteItem("delete 1");
        });
    }

    @Test
    public void testMarkMissingTaskNumber() {
        ItemList itemList = new ItemList();
        assertThrows(MissingTaskNumberException.class, () -> {
            itemList.markItemAsDone("mark");
        });
        assertThrows(MissingTaskNumberException.class, () -> {
            itemList.markItemAsNotDone("unmark");
        });
    }

    @Test
    public void testDeleteMissingTaskNumber() {
        ItemList itemList = new ItemList();
        assertThrows(MissingTaskNumberException.class, () -> {
            itemList.deleteItem("delete");
        });
    }

    @Test
    public void testLoadItem() {
        ItemList itemList = new ItemList();
        Todo todo = new Todo("Loaded task");
        itemList.loadItem(todo);
        assertEquals(1, itemList.listSize());
        assertEquals("Loaded task", itemList.getItem(0).getName());
    }

    @Test
    public void testGetItem() throws EmptyTaskNameException {
        ItemList itemList = new ItemList();
        itemList.newTodo("todo First task");
        itemList.newTodo("todo Second task");

        assertEquals("First task", itemList.getItem(0).getName());
        assertEquals("Second task", itemList.getItem(1).getName());
    }
}
