import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Atlas {
    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        List<Boolean> done = new ArrayList<>();
        int c = 0;
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
                    String num = Integer.toString(i + 1) + ". ";
                    if (done.get(i)) {
                        System.out.println(space + num + "[X] " + items.get(i));
                    } else {
                        System.out.println(space + num + "[ ] " + items.get(i));
                    }
                }
                System.out.println(space + line);
            } else if (s1.matches("^mark \\d+$")) {
                done.set((int) (s1.charAt(5) - '0') - 1, true);
                System.out.println();
            } else if (s1.matches("^unmark \\d+$")) {
                done.set((int) (s1.charAt(5) - '0') - 1, false);
            }
            else if (!s1.isEmpty()){
                c++;
                System.out.println(space + line);
                System.out.println(space + "added: [ ] " + s1);
                System.out.println(space + line);
                items.add(s1);
                done.add(false);
            } else {

            }

        }

    }
}
