public class DeadlineDateException extends Exception {
    public DeadlineDateException() {
        super("""
                     Oops! Please follow the following format:
                     deadline <item name> /by <deadline>
                """);
    }
}
