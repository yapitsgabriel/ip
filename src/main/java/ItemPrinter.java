import java.io.IOException;

public class ItemPrinter {

    public static void printLine() {
        System.out.println("    ______________________________________________________________");
    }

    public static void smallSpace(String message) {
        System.out.println("     " + message);
    }

    public static void bigSpace(String message) {
        System.out.println("        " + message);
    }

    public static void hello() {
        ItemPrinter.printLine();
        ItemPrinter.smallSpace("Hello, I'm Atlas!");
        ItemPrinter.smallSpace("What do you want to do?");
        ItemPrinter.printLine();
    }

    public static void bye() throws IOException {
        ItemPrinter.printLine();
        ItemPrinter.smallSpace("Bye! See you next time :)");
        ItemPrinter.printLine();
    }

    public static void printHelpMenu() {
        ItemPrinter.printLine();
        ItemPrinter.smallSpace("I don't understand what you mean. You can try these prompts: ");
        ItemPrinter.smallSpace("• list");
        ItemPrinter.smallSpace("• todo <task name>");
        ItemPrinter.smallSpace("• deadline <task name> /by <deadline>");
        ItemPrinter.smallSpace("• event <task name> /from <start date> /to <end date>");
        ItemPrinter.smallSpace("• mark <task number>");
        ItemPrinter.smallSpace("• unmark <task number>");
        ItemPrinter.smallSpace("• delete <task number>");
        ItemPrinter.printLine();
    }
}
