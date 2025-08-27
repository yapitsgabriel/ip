import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class Atlas {
    private Scanner s;
    private ItemFileManager itemFileManager;
    private ItemList itemList;

    public Atlas () {
        this.s = new Scanner(System.in);
        this.itemFileManager = new ItemFileManager();
        this.itemList = new ItemList();
    }

    public void run(){
        String input;
        ItemPrinter.hello();
        try {
            itemList = itemFileManager.load();
        } catch (IOException e) {
            ItemPrinter.printLine();
            ItemPrinter.smallSpace(e.getMessage());
            ItemPrinter.printLine();
        }
        while (true) {
            input = s.nextLine();
            readInput(input);
        }
    }

    public void readInput(String input) {
        input = input.trim();
        if (input.equals("bye")) {
            ItemPrinter.bye();
            itemFileManager.save(itemList);
        } else if (input.equals("list")) {
            itemList.printList();
        } else if (input.matches("^mark \\d+$")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            itemList.markitemAsDone(index);
        } else if (input.matches("^unmark \\d+$")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            itemList.markitemAsNotDone(index);
        } else if (input.matches("^delete \\d+$")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            itemList.deleteItem(index);
        } else if (input.startsWith("todo")) {
            try {
                itemList.newTodo(input);
                itemList.printTodo();
            } catch (EmptyTaskNameException e) {
                ItemPrinter.printLine();
                ItemPrinter.smallSpace(e.getMessage());
                ItemPrinter.printLine();
            }
        } else if (input.startsWith("deadline")) {
            try {
                itemList.newDeadline(input);
                itemList.printTodo();
            } catch (EmptyTaskNameException | InvalidFormatDeadlineException | InvalidDateFormatException | PastDateException e) {
                ItemPrinter.printLine();
                ItemPrinter.smallSpace(e.getMessage());
                ItemPrinter.printLine();
            }
        } else if (input.startsWith("event")) {
            try {
                itemList.newEvent(input);
                itemList.printTodo();
            } catch (EmptyTaskNameException | InvalidFormatEventException | InvalidDateFormatException | PastDateException | InvalidDateRangeException e) {
                ItemPrinter.printLine();
                ItemPrinter.smallSpace(e.getMessage());
                ItemPrinter.printLine();
            }
        } else {
            ItemPrinter.printHelpMenu();
        }
    }

    public static void main(String[] args){
        Atlas atlas = new Atlas();
        atlas.run();
    }
}
