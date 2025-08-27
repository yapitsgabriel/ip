public class InvalidFormatEventException extends Exception {
    public InvalidFormatEventException() {
        super("Format: event <name> /from <datetime> /to <datetime>");
    }
}
