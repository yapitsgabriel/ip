public class DeadlineDateException extends Exception {
    static final String SPACE = "     ";
    static final String LINE = "    " + "______________________________________________________________";

    public DeadlineDateException() {
        super();
        System.out.println(LINE);
        System.out.println(SPACE + "Oops! Please follow the following format:");
        System.out.println(SPACE + "deadline <item name> /by <deadline>");
        System.out.println(LINE);
    }
}
