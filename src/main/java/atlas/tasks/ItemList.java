package atlas.tasks;

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

public class ItemList {

    private List<Item> itemList;

    public ItemList() {
        this.itemList = new ArrayList<Item>();
    }

    public ItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Item newTodo(String input) throws EmptyTaskNameException {
        Item newItem = Parser.parseTodo(input);
        itemList.add(newItem);
        return newItem;
    }

    public Item newDeadline(String input) throws EmptyTaskNameException, InvalidFormatDeadlineException, InvalidDateFormatException, PastDateException {
        Item newItem = Parser.parseDeadline(input);
        itemList.add(newItem);
        return newItem;
    }

    public Item newEvent(String input) throws EmptyTaskNameException, InvalidFormatEventException, InvalidDateFormatException, PastDateException, InvalidDateRangeException {
        Item newItem = Parser.parseEvent(input);
        itemList.add(newItem);
        return newItem;
    }

    public void loadItem(Item item) {
        itemList.add(item);
    }

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
     * Finds all items matching a given input.
     * @param input Input given by user.
     */
    public void findItem(String input) {
        ItemList output = new ItemList();
        for (int i = 0; i < itemList.size(); i++) {
            Item current = itemList.get(i);
            if (current.getName().contains(input)) {
                output.loadItem(current);
            }
        }
        Ui.printMatchingList(output);
    }

    public int listSize() {
        return itemList.size();
    }

    public Item getItem(int i) {
        return itemList.get(i);
    }
}
