package atlas.items;

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
import atlas.utilities.Parser;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of items.
 */
public class ItemList {

    private List<Item> itemList;

    /**
     * Initialise an empty list.
     */
    public ItemList() {
        this.itemList = new ArrayList<Item>();
    }

    /**
     * Initialise an ItemList with an existing List.
     *
     * @param itemList Existing List of Items.
     */
    public ItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    /**
     * Creates a new todo item.
     *
     * @param input Given input by user.
     * @return A Todo.
     * @throws EmptyTaskNameException If item name is empty.
     */
    public Item newTodo(String input) throws EmptyTaskNameException {
        Item newItem = Parser.parseTodo(input);
        itemList.add(newItem);
        return newItem;
    }

    /**
     * Creates a new deadline item.
     *
     * @param input Given input by user.
     * @return A new Deadline.
     * @throws EmptyTaskNameException If item name is empty.
     * @throws InvalidFormatDeadlineException If format of item is invalid.
     * @throws InvalidDateFormatException If format of date is invalid.
     * @throws PastDateException If date entered is before current date.
     */
    public Item newDeadline(String input) throws EmptyTaskNameException, InvalidFormatDeadlineException,
        InvalidDateFormatException, PastDateException {
        Item newItem = Parser.parseDeadline(input);
        itemList.add(newItem);
        return newItem;
    }

    /**
     * Creates a new event item.
     *
     * @param input Given input by user.
     * @return A new Event.
     * @throws EmptyTaskNameException If item name is empty.
     * @throws InvalidFormatEventException If format of item is invalid.
     * @throws InvalidDateFormatException If format of date is invalid.
     * @throws PastDateException If date entered is before current date.
     * @throws InvalidDateRangeException If end date is before start date.
     */
    public Item newEvent(String input) throws EmptyTaskNameException, InvalidFormatEventException,
        InvalidDateFormatException, PastDateException, InvalidDateRangeException {
        Item newItem = Parser.parseEvent(input);
        itemList.add(newItem);
        return newItem;
    }

    /**
     * Creates a new fixed duration item.
     *
     * @param input Given input by user.
     * @return A new Fixed Duration.
     * @throws EmptyTaskNameException If item name is empty.
     * @throws InvalidFormatFixedDurationException If format of item is invalid.
     */
    public Item newFixedDuration(String input) throws EmptyTaskNameException, InvalidFormatFixedDurationException, InvalidDurationException {
        Item newItem = Parser.parseFixedDuration(input);
        itemList.add(newItem);
        return newItem;
    }

    /**
     * Add item to ItemList.
     *
     * @param item Item to be added.
     */
    public void loadItem(Item item) {
        itemList.add(item);
    }

    /**
     * Marks an item as done.
     *
     * @param input Given input by user.
     * @return The marked item
     * @throws MissingTaskNumberException If task number is missing.
     * @throws InvalidTaskNumberException If task number is invalid.
     */
    public Item markItemAsDone(String input) throws MissingTaskNumberException, InvalidTaskNumberException {
        int index = Parser.parseMarkAsDone(input, itemList.size());
        Item item = itemList.get(index);
        item.markAsDone();
        return item;
    }

    /**
     * Marks an item as not done.
     *
     * @param input Given input by user.
     * @return The unmarked item
     * @throws MissingTaskNumberException If task number is missing.
     * @throws InvalidTaskNumberException If task number is invalid.
     */
    public Item markItemAsNotDone(String input) throws MissingTaskNumberException, InvalidTaskNumberException {
        int index = Parser.parseMarkAsNotDone(input, itemList.size());
        Item item = itemList.get(index);
        item.markAsNotDone();
        return item;
    }

    /**
     * Deletes the item.
     *
     * @param input Given input by user.
     * @return The deleted item
     * @throws MissingTaskNumberException If task number is missing.
     * @throws InvalidTaskNumberException If task number is invalid.
     */
    public Item deleteItem(String input) throws MissingTaskNumberException, InvalidTaskNumberException {
        int index = Parser.parseDelete(input, itemList.size());
        Item item = itemList.get(index);
        itemList.remove(item);
        return item;
    }

    /**
     * Finds all items matching a given input.
     * @param ui the user interface used to display messages
     * @param input Input given by user.
     * @return
     */
    public ItemList findItem(String input) throws EmptyTaskNameException {
        String name = Parser.parseFind(input, itemList.size());
        ItemList output = new ItemList();
        for (int i = 0; i < itemList.size(); i++) {
            Item current = itemList.get(i);
            if (current.getName().contains(name)) {
                output.loadItem(current);
            }
        }
        return output;
    }

    /**
     * Returns list size.
     *
     * @return Size of list.
     */
    public int listSize() {
        return itemList.size();
    }

    /**
     * Gets a certain item from the list.
     *
     * @param i Index of item to be gotten.
     */
    public Item getItem(int i) {
        return itemList.get(i);
    }
}
