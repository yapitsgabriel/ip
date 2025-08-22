import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Atlas {
    static final String SPACE = "     ";
    static final String BIGSPACE = "        ";
    static final String LINE = "    " + "____________________________________________________________";
    private static Item[] itemList = new Item[100];
    private static int c = 0;
    private static Scanner s = new Scanner(System.in);
    private static String input;
    private static List<String> items = new ArrayList<>();
    private static List<Boolean> done = new ArrayList<>();

    public static void hello() {
        System.out.println(LINE);
        System.out.println(SPACE + "Hello, I'm Atlas!");
        System.out.println(SPACE + "What do you want to do?");
        System.out.println(LINE);
    }

    public static void bye() {
        System.out.println(LINE);
        System.out.println(SPACE + "Bye! See you next time :)");
        System.out.println(LINE);
    }

    public static void printList() {
        System.out.println(LINE);
        System.out.println(SPACE + "Here are the tasks in your list:");
        for (int i = 0; i < c; i++) {
            System.out.println(BIGSPACE + Integer.toString(i + 1) + ". " + itemList[i].toString());
        }
        System.out.println(LINE);
    }

    public static void newTodo(String input) throws EmptyException {
        if (input.length() < 6) {
            throw new EmptyException();
        }
        String name = input.substring(5);

        itemList[c] = new Todo(name);
        System.out.println(LINE);
        System.out.println(SPACE + "Got it! I've added this task:");
        System.out.println(BIGSPACE + itemList[c].toString());
        c++;
        System.out.println(SPACE + "Now you have " + c + " task(s) in the list.");
        System.out.println(LINE);
    }

    public static void newDeadline(String input) throws EmptyException, DeadlineDateException {
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

        // Create task
        itemList[c] = new Deadline(name, by);
        System.out.println(LINE);
        System.out.println(SPACE + "Got it! I've added this task:");
        System.out.println(BIGSPACE + itemList[c].toString());
        c++;
        System.out.println(SPACE + "Now you have " + c + " task(s) in the list.");
        System.out.println(LINE);
    }

    public static void newEvent(String input) throws EmptyException, EventDateException {
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

        itemList[c] = new Event(name, from, to);
        System.out.println(LINE);
        System.out.println(SPACE + "Got it! I've added this task:");
        System.out.println(BIGSPACE + itemList[c].toString());
        c++;
        System.out.println(SPACE + "Now you have " + c + " task(s) in the list.");
        System.out.println(LINE);
    }

    public static void markTaskAsDone(int index) {
        itemList[index].markAsDone();
        System.out.println(LINE);
        System.out.println(SPACE + "Nice! I've marked this task as done:");
        System.out.println(BIGSPACE + itemList[index].toString());
        System.out.println(LINE);
    }

    public static void markTaskAsNotDone(int index) {
        itemList[index].markAsNotDone();
        System.out.println(LINE);
        System.out.println(SPACE + "Okay, I've marked this task as not done yet:");
        System.out.println(BIGSPACE + itemList[index].toString());
        System.out.println(LINE);
    }

    public static void deleteItem(int index) {
        System.out.println(LINE);
        System.out.println(SPACE + "Okay, I've deleted this task: ");
        System.out.println(BIGSPACE + itemList[index].toString());
        System.out.println(LINE);
        for (int i = index; i < c - 1; i++) {
            itemList[i] = itemList[i + 1];
        }
        c--;
    }

    public static void main(String[] args){
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
                markTaskAsDone(index);
            } else if (input.matches("^unmark \\d+$")) {
                int index = (input.charAt(7) - '0') - 1;
                markTaskAsNotDone(index);
            } else if (input.matches("^delete \\d+$")) {
                int index = (input.charAt(7) - '0') - 1;
                deleteItem(index);
            } else if (input.startsWith("todo")) {
                try {
                    newTodo(input);
                } catch (EmptyException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Oops! Remember to include the details for your item.");
                    System.out.println("    ____________________________________________________________");
                }

            } else if (input.startsWith("deadline")) {
                try {
                    newDeadline(input);
                } catch (EmptyException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Oops! Remember to include the details for your item.");
                    System.out.println("    ____________________________________________________________");
                } catch (DeadlineDateException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Oops! Please follow the following format:");
                    System.out.println("     deadline <task name> /by <deadline>");
                    System.out.println("    ____________________________________________________________");
                }
            } else if (input.startsWith("event")) {
                try {
                    newEvent(input);
                } catch (EmptyException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Oops! Remember to include the details for your item.");
                    System.out.println("    ____________________________________________________________");
                } catch (EventDateException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Oops! Please follow the following format:");
                    System.out.println("     event <task name> /from <start date> /to <end date>");
                    System.out.println("    ____________________________________________________________");
                }
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     I don't understand what you mean. Please type in something that you wanna achieve.");
                System.out.println("    ____________________________________________________________");
            }
        }
    }
}
