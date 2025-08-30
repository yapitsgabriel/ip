package atlas.items;

import static org.junit.jupiter.api.Assertions.assertEquals;

import atlas.exceptions.EmptyTaskNameException;
import org.junit.jupiter.api.Test;

public class ItemListTest {
    @Test
    public void newTodoTest() throws EmptyTaskNameException {
        ItemList itemList = new ItemList();
        String input = "todo Read book";
        itemList.newTodo(input);
        assertEquals(1, itemList.listSize());
        assertEquals("Read book", itemList.getItem(0).name);
    }

    @Test
    public void markItemTest() throws EmptyTaskNameException {
        ItemList itemList = new ItemList();
        String input = "todo Read book";
        itemList.newTodo(input);
        assertEquals(0, itemList.getItem(0).isDone);
        itemList.markItemAsDone(0);
        assertEquals(1, itemList.getItem(0).isDone);
        itemList.markItemAsNotDone(0);
        assertEquals(0, itemList.getItem(0).isDone);
    }

    public void deleteItem() throws EmptyTaskNameException {
        ItemList itemList = new ItemList();
        String input = "todo Read book";
        itemList.newTodo(input);
        assertEquals(1, itemList.listSize());
        itemList.deleteItem(0);
        assertEquals(0, itemList.listSize());
    }
}
