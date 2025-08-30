package atlas.ui;

import atlas.items.Item;
import atlas.items.ItemList;

/**
 * Represents the UI of Atlas.
 * Helps with string formatting, printing of the help menu, and printing of lists.
 */
public class Ui {

    /**
     * Prints a divider line
     */
    public static void printLine() {
        System.out.println("    ______________________________________________________________");
    }

    /**
     * Adds a small space before a given message.
     * @param message Message to be formatted.
     */
    public static void smallSpace(String message) {
        System.out.println("     " + message);
    }

    /**
     * Adds a big space before a given message.
     * @param message Message to be formatted.
     */
    public static void bigSpace(String message) {
        System.out.println("        " + message);
    }

    /**
     * Prints hello message upon start up.
     */
    public void hello() {
        printLine();
        smallSpace("Hello, I'm atlas.Atlas!");
        smallSpace("What do you want to do?");
        printLine();
    }

    /**
     * Prints goodbye message upon exiting.
     */
    public void bye() {
        printLine();
        smallSpace("Bye! See you next time :)");
        printLine();
    }

    /**
     * Prints help message.
     */
    public void printHelpMenu() {
        printLine();
        smallSpace("I don't understand what you mean. You can try these prompts: ");
        smallSpace("• list");
        smallSpace("• todo <task name>");
        smallSpace("• deadline <task name> /by <deadline>");
        smallSpace("• event <task name> /from <start date> /to <end date>");
        smallSpace("• mark <task number>");
        smallSpace("• unmark <task number>");
        smallSpace("• delete <task number>");
        printLine();
    }

    /**
     * Prints a given item.
     *
     * @param item Item to be printed.
     * @param listSize Current size of list.
     */
    public void printTodo(Item item, int listSize) {
        printLine();
        smallSpace("Got it! I've added this item:");
        bigSpace(item.toString());
        smallSpace("Now you have " + listSize + " item(s) in the list.");
        printLine();
    }

    /**
     * Prints the entire list.
     *
     * @param itemList List to be printed.
     */
    public void printList(ItemList itemList) {
        int length = itemList.listSize();
        printLine();
        if (length == 0) {
            smallSpace("There are no items in your list. Enjoy your day!");
        } else {
            smallSpace("Here are the items in your list:");
            for (int i = 0; i < length; i++) {
                bigSpace((i + 1) + ". " + itemList.getItem(i).toString());
            }
        }
        printLine();
    }

    /**
     * Prints error message with proper formatting.
     *
     * @param message Error message to be printed.
     */
    public void printError(String message) {
        printLine();
        smallSpace(message);
        printLine();
    }
}
