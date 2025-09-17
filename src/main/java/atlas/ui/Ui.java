package atlas.ui;

import atlas.items.Item;
import atlas.items.ItemList;

/**
 * Represents the UI of Atlas.
 * Helps with string formatting, printing of the help menu, and printing of lists.
 */
public class Ui {
    /**
     * Returns hello message upon start up.
     */
    public String hello() {
        return "Hello, I'm Atlas. What do you want to do?";
    }

    /**
     * Returns goodbye message upon exiting.
     */
    public String bye() {
        return "Bye! See you next time :)";
    }

    /**
     * Returns help message.
     */
    public String printHelpMenu() {
        return "I don't understand what you mean. You can try these prompts: \n"
            + "• list\n"
            + "• todo <task name>\n"
            + "• deadline <task name> /by <deadline>\n"
            + "• event <task name> /from <start date> /to <end date>\n"
            + "• mark <task number>\n"
            + "• unmark <task number>\n"
            + "• delete <task number>";
    }

    /**
     * Returns a given item.
     *
     * @param item Item to be printed.
     * @param listSize Current size of list.
     */
    public String printTodo(Item item, int listSize) {
        return "Got it! I've added this item:\n" + item.toString() + "\n"
                + "Now you have " + listSize + " item(s) in the list.";
    }

    /**
     * Returns the entire list.
     *
     * @param itemList List to be printed.
     */
    public String printList(ItemList itemList) {
        int length = itemList.listSize();
        if (length == 0) {
            return "There are no items in your list. Enjoy your day!";
        }
        StringBuilder items = new StringBuilder();
        for (int i = 0; i < length; i++) {
            items.append((i + 1) + ". " + itemList.getItem(i).toString() + "\n");
        }
        return "Here are the items in your list\n" + items;
    }

    public String printMatchingList(ItemList itemList) {
        int length = itemList.listSize();
        if (length == 0) {
            return "There are no items matching your search :(";
        }
        StringBuilder items = new StringBuilder();
        for (int i = 0; i < length; i++) {
            items.append((i + 1) + ". " + itemList.getItem(i).toString() + "\n");
        }
        return "Here are the matching items in your list:\n" + items;
    }

    public String printDeleteItem(Item item) {
            return "Okay, I've deleted this item:\n" + item.toString();
        }

    public String printMarkItemAsDone(Item item) {
        return "Okay, I've marked this item as done: \n" + item.toString();
    }

    public String printMarkItemAsNotDone(Item item) {
        return "Okay, I've marked this item as not done yet: \n" + item.toString();
    }

    /**
     * Returns error message with proper formatting.
     *
     * @param message Error message to be printed.
     */
    public String printError(String message) {
        return message;
    }
}
