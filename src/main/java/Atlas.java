import java.util.Scanner;

public class Atlas {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello, I'm Atlas.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner s = new Scanner(System.in);
        String s1 = s.nextLine();;

        while (!s1.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(s1);
            System.out.println("____________________________________________________________");
            s1 = s.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye! See you next time :)");
        System.out.println("____________________________________________________________");
    }
}
