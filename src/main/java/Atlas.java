import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Atlas {
    private List<Item> itemList;
    private int c;
    private Scanner s;
    private String input;

    public Atlas () {
        this.itemList = new ArrayList<Item>();
        this.c = 0;
        this.s = new Scanner(System.in);
    }

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

    public void run() {
        hello();
        while (true) {
            input = s.nextLine();
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.matches("^mark \\d+$")) {
                int index = (input.charAt(5) - '0') - 1;
                markitemAsDone(index);
            } else if (input.matches("^unmark \\d+$")) {
                int index = (input.charAt(7) - '0') - 1;
                markitemAsNotDone(index);
            } else if (input.matches("^delete \\d+$")) {
                int index = (input.charAt(7) - '0') - 1;
                deleteItem(index);
            } else if (input.startsWith("todo")) {
                try {
                    newTodo(input);
                } catch (EmptyException e) {
                }

            } else if (input.startsWith("deadline")) {
                try {
                    newDeadline(input);
                } catch (EmptyException | DeadlineDateException e) {
                }
            } else if (input.startsWith("event")) {
                try {
                    newEvent(input);
                } catch (EmptyException | EventDateException e) {
                }
            } else {
                printHelpMenu();
            }
        }
    }

    public void printHelpMenu() {
        printLine();
        smallSpace("I don't understand what you mean. You can try these prompts: ");
        smallSpace("• list");
        smallSpace("• todo <item name>");
        smallSpace("• deadline <item name> /by <deadline>");
        smallSpace("• event <item name> /from <start date> /to <end date>");
        smallSpace("• mark <item number>");
        smallSpace("• unmark <item number>");
        smallSpace("• delete <item number>");
        printLine();
    }

    public void printList() {
        printLine();
        smallSpace("Here are the items in your list:");
        for (int i = 0; i < c; i++) {
            bigSpace(Integer.toString(i + 1) + ". " + itemList.get(i).toString());
        }
        printLine();
    }

    public void newTodo(String input) throws EmptyException {
        if (input.length() < 6) {
            throw new EmptyException();
        }
        String name = input.substring(5);

        itemList.add(new Todo(name));
        printLine();
        smallSpace("Got it! I've added this item:");
        bigSpace(itemList.get(c).toString());
        c++;
        smallSpace("Now you have " + c + " item(s) in the list.");
        printLine();
    }

    public void newDeadline(String input) throws EmptyException, DeadlineDateException {
        if (input.length() < 10) {
            throw new EmptyException();
        } else if (!input.contains(" /by ")) {
            throw new DeadlineDateException();
        }

        // Get name
        int i = input.indexOf("/");
        String name = input.substring(9, i - 1);

        // Get by
        String rest = input.substring(i);
        if (rest.length() < 5) {
            throw new DeadlineDateException();
        }
        String by = input.substring(i + 4);

        // Create item
        itemList.add(new Deadline(name, by));
        printLine();
        smallSpace("Got it! I've added this item:");
        bigSpace(itemList.get(c).toString());
        c++;
        smallSpace("Now you have " + c + " item(s) in the list.");
        printLine();
    }

    public void newEvent(String input) throws EmptyException, EventDateException {
        if (input.length() < 7) {
            throw new EmptyException();
        } else if (!(input.contains(" /from ") && input.contains(" /to "))) {
            throw new EventDateException();
        }

        // Get name
        int i1 = input.indexOf("/");
        int i2 = input.indexOf("/", i1 + 1);
        String name = input.substring(6, i1 - 1);

        // Get from
        String rest = input.substring(i1);
        if (rest.length() < 7) {
            throw new EventDateException();
        }
        String from = input.substring(i1 + 6, i2 - 1);

        // Get to
        rest = input.substring(i2);
        if (rest.length() < 5) {
            throw new EventDateException();
        }
        String to = input.substring(i2 + 4);

        itemList.add(new Event(name, from, to));
        printLine();
        smallSpace("Got it! I've added this item:");
        bigSpace(itemList.get(c).toString());
        c++;
        smallSpace("Now you have " + c + " item(s) in the list.");
        printLine();
    }

    public void markitemAsDone(int index) {
        itemList.get(index).markAsDone();
        printLine();
        smallSpace("Nice! I've marked this item as done:");
        bigSpace(itemList.get(index).toString());
        printLine();
    }

    public void markitemAsNotDone(int index) {
        itemList.get(index).markAsNotDone();
        printLine();
        smallSpace("Okay, I've marked this item as not done yet:");
        bigSpace( itemList.get(index).toString());
        printLine();
    }

    public void deleteItem(int index) {
        printLine();
        smallSpace("Okay, I've deleted this item: ");
        bigSpace(itemList.get(index).toString());
        printLine();
        for (int i = index; i < c - 1; i++) {
            itemList.set(i, itemList.get(i + 1));
        }
        c--;
    }

    public static void main(String[] args){
        Atlas atlas = new Atlas();
        atlas.run();
    }
}
