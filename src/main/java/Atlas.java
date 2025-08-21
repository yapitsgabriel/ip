import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Atlas {
    static final String SPACE = "     ";
    static final String LINE = SPACE + "____________________________________________________________";
    private static Item[] todoList = new Item[100];
    private static int c = 0;
    private static Scanner s = new Scanner(System.in);
    private static String s1;
    private static List<String> items = new ArrayList<>();
    private static List<Boolean> done = new ArrayList<>();

    public static void hello() {
        System.out.println(LINE);
        System.out.println(SPACE + "Hello, I'm Atlas!");
        System.out.println(SPACE + "How can I be of help?");
        System.out.println(LINE);
    }

    public static void bye() {
        System.out.println(LINE);
        System.out.println(SPACE + "Bye! See you next time :)");
        System.out.println(LINE);
    }

    public static void printList() {
        System.out.println(LINE);
        for (int i = 0; i < c; i++) {
            System.out.println(SPACE + Integer.toString(i + 1) + ". " + todoList[i].toString());
        }
        System.out.println(LINE);
    }

    public static void newTodo(String name) {
        todoList[c] = new Todo(name);
        System.out.println(LINE);
        System.out.println(SPACE + "added: " + todoList[c].toString());
        System.out.println(LINE);
        c++;
    }

    public static void newDeadline(String name, String by) {
        todoList[c] = new Deadline(name, by);
        System.out.println(LINE);
        System.out.println(SPACE + "added: " + todoList[c].toString());
        System.out.println(LINE);
        c++;
    }

    public static void newEvent(String name, String from, String to) {
        todoList[c] = new Event(name, from, to);
        System.out.println(LINE);
        System.out.println(SPACE + "added: " + todoList[c].toString());
        System.out.println(LINE);
        c++;
    }

    public static void markTaskAsDone(int index) {
        todoList[index].markAsDone();
        System.out.println(LINE);
        System.out.println(SPACE + todoList[index].toString());
        System.out.println(LINE);
    }

    public static void markTaskAsNotDone(int index) {
        todoList[index].markAsNotDone();
        System.out.println(LINE);
        System.out.println(SPACE + todoList[index].toString());
        System.out.println(LINE);
    }


    public static void main(String[] args) {
        hello();
        while (true) {
            s1 = s.nextLine();
            if (s1.equals("bye")) {
                bye();
                break;
            } else if (s1.equals("list")) {
                printList();
            } else if (s1.matches("^mark \\d+$")) {
                int index = (s1.charAt(5) - '0') - 1;
                markTaskAsDone(index);
            } else if (s1.matches("^unmark \\d+$")) {
                int index = (s1.charAt(7) - '0') - 1;
                markTaskAsNotDone(index);
            }
            else if (s1.startsWith("todo")) {
                newTodo(s1.substring(5));
            } else if (s1.startsWith("deadline")) {
                int i = s1.indexOf("/");
                String name = s1.substring(9, i - 1);
                String by = s1.substring(i + 1);
                newDeadline(name, by);
            } else if (s1.startsWith("event")) {
                int i1 = s1.indexOf("/");
                int i2 = s1.indexOf("/", i1 + 1);
                String name = s1.substring(6, i1 - 1);
                String from = s1.substring(i1 + 1, i2 - 1);
                String to = s1.substring(i2 + 1);
                newEvent(name, from, to);
            }
        }

    }
}
