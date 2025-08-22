public class EventDateException extends Exception {
    static final String SPACE = "     ";
    static final String LINE = "    " + "______________________________________________________________";

    public EventDateException() {
        super();
        System.out.println(LINE);
        System.out.println(SPACE + "Oops! Please follow the following format:");
        System.out.println(SPACE + "event <item name> /from <start date> /to <end date>");
        System.out.println(LINE);
    }
}
