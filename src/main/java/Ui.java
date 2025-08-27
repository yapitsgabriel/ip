public class Ui {
    
    public static void printLine() {
        System.out.println("    ______________________________________________________________");
    }

    public static void smallSpace(String message) {
        System.out.println("     " + message);
    }

    public static void bigSpace(String message) {
        System.out.println("        " + message);
    }

    public void hello() {
        printLine();
        smallSpace("Hello, I'm Atlas!");
        smallSpace("What do you want to do?");
        printLine();
    }

    public void bye() {
        printLine();
        smallSpace("Bye! See you next time :)");
        printLine();
    }

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

    public void printTodo(Item item, int listSize) {
        Ui.printLine();
        Ui.smallSpace("Got it! I've added this item:");
        Ui.bigSpace(item.toString());
        Ui.smallSpace("Now you have " + listSize + " item(s) in the list.");
        Ui.printLine();
    }

    public void printList(ItemList itemList) {
        int length = itemList.listSize();
        Ui.printLine();
        if (length == 0) {
            Ui.smallSpace("There are no items in your list. Enjoy your day!");
        } else {
            Ui.smallSpace("Here are the items in your list:");
            for (int i = 0; i < itemList.listSize(); i++) {
                Ui.bigSpace(Integer.toString(i + 1) + ". " + itemList.getItem(i).toString());
            }
        }
        Ui.printLine();
    }

    public void printError(String message) {
        Ui.printLine();
        Ui.smallSpace(message);
        Ui.printLine();
    }
}
