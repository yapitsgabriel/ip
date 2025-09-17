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
     *
     * @return Help message string.
     */
    public String printHelpMenu() {
        return "I don't understand what you mean. You can try these prompts: \n"
            + "• list\n"
            + "• todo <name>\n"
            + "• deadline <name> /by <deadline>\n"
            + "• event <name> /from <start date> /to <end date>\n"
            + "• fixedDuration <name> /duration <duration in hours>\n"
            + "• mark <item number>\n"
            + "• unmark <item number>\n"
            + "• delete <item number>"
            + "• find <search string>";
    }

    /**
     * Returns the confirmation message upon adding an item
     *
     * @param item Item to be printed.
     * @param listSize Current size of list.
     * @return Confirmation message string.
     */
    public String printItem(Item item, int listSize) {
        return "Got it! I've added this item:\n" + item.toString() + "\n"
                + "Now you have " + listSize + " item(s) in the list.";
    }

    /**
     * Returns the entire list.
     *
     * @param itemList List to be printed.
     * @return List in string form.
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

    /**
     * Returns the list given (for the find command).
     *
     * @param itemList List to be printed.
     * @return List in string form.
     */
    public String printMatchingList(ItemList itemList) {
        int length = itemList.listSize();
        if (length == 0) {
            return "There are no items matching your search :(";
        }
        StringBuilder items = new StringBuilder();
        for (int i = 0; i < length; i++) {
            items.append((i + 1)).append(". ").append(itemList.getItem(i).toString()).append("\n");
        }
        return "Here are the matching items in your list:\n" + items;
    }

    /**
     * Returns the confirmation message upon deleting an item.
     *
     * @param item Item to be deleted.
     * @return Confirmation message string.
     */
    public String printDeleteItem(Item item) {
            return "Okay, I've deleted this item:\n" + item.toString();
        }

    /**
     * Returns the confirmation message upon marking an item as done.
     *
     * @param item Item to be marked as done.
     * @return Confirmation message string.
     */
    public String printMarkItemAsDone(Item item) {
        return "Okay, I've marked this item as done: \n" + item.toString();
    }

    /**
     * Returns the confirmation message upon marking an item as not done.
     *
     * @param item Item to be marked as not done.
     * @return Confirmation message string.
     */
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
