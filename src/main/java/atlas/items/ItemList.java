package atlas.items;

import atlas.exceptions.EmptyTaskNameException;
import atlas.exceptions.InvalidDateFormatException;
import atlas.exceptions.InvalidDateRangeException;
import atlas.exceptions.InvalidFormatDeadlineException;
import atlas.exceptions.InvalidFormatEventException;
import atlas.exceptions.PastDateException;
import atlas.ui.Ui;
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
    public Item newDeadline(String input) throws EmptyTaskNameException, InvalidFormatDeadlineException, InvalidDateFormatException, PastDateException {
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
    public Item newEvent(String input) throws EmptyTaskNameException, InvalidFormatEventException, InvalidDateFormatException, PastDateException, InvalidDateRangeException {
        Item newItem = Parser.parseEvent(input);
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
     * Marks item as done.
     *
     * @param index Index of item to be marked.
     */
    public void markItemAsDone(int index) {
        if (index < 0 || index > itemList.size() - 1) {
            Ui.printLine();
            Ui.smallSpace("Invalid number! Please choose a number between 1 and " + itemList.size());
            return;
        }
        itemList.get(index).markAsDone();
        Ui.printLine();
        Ui.smallSpace("Nice! I've marked this item as done:");
        Ui.bigSpace(itemList.get(index).toString());
        Ui.printLine();
    }

    /**
     * Marks item as done.
     *
     * @param index Index of item to be marked.
     */
    public void markItemAsNotDone(int index) {
        if (index < 0 || index > itemList.size() - 1) {
            Ui.printLine();
            Ui.smallSpace("Invalid number! Please choose a number between 1 and " + itemList.size());
            return;
        }
        itemList.get(index).markAsNotDone();
        Ui.printLine();
        Ui.smallSpace("Okay, I've marked this item as not done yet:");
        Ui.bigSpace( itemList.get(index).toString());
        Ui.printLine();
    }

    /**
     * Deletes item.
     *
     * @param index Index of item to be deleted.
     */
    public void deleteItem(int index) {
        if (index < 0 || index > itemList.size() - 1) {
            Ui.printLine();
            Ui.smallSpace("Invalid number! Please choose a number between 1 and " + itemList.size());
            return;
        }
        Ui.printLine();
        Ui.smallSpace("Okay, I've deleted this item: ");
        Ui.bigSpace(itemList.get(index).toString());
        Ui.printLine();
        itemList.remove(index);
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
