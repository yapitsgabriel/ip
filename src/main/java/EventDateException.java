public class EventDateException extends Exception {

    public EventDateException() {
        super("""
                     Oops! Please follow the following format:
                     event <item name> /from <start date> /to <end date>
                """);
    }
}
