import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Atlas {
    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        Integer c = 0;
        String space = "     ";
        String line = "____________________________________________________________";
        System.out.println(space + line);
        System.out.println(space + "Hello, I'm Atlas.");
        System.out.println(space + "What do you want to do today?");
        System.out.println(space + line);

        Scanner s = new Scanner(System.in);
        String s1;

        while (true) {
            s1 = s.nextLine();
            if (s1.equals("bye")) {
                System.out.println(space + line);
                System.out.println(space + "Bye! See you next time :)");
                System.out.println(space + line);
                break;
            } else if (s1.equals("list")) {
                System.out.println(space + line);
                for (int i = 0; i < c; i++) {
                    System.out.println(space + items.get(i));
                }
                System.out.println(space + line);
            } else {
                c++;
                System.out.println(space + line);
                System.out.println(space + "added: " + s1);
                System.out.println(space + line);
                items.add(Integer.toString(c) + ". " + s1);
            }

        }

    }
}
