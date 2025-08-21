import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Atlas {
    static final String SPACE = "     ";
    static final String LINE = SPACE + "____________________________________________________________";
    private static Todo[] todoList = new Todo[100];
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
            else if (!s1.isEmpty()) {
                newTodo(s1);
            }
        }

    }
}
