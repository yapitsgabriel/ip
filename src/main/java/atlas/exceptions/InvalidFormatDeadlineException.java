package atlas.exceptions;

public class InvalidFormatDeadlineException extends Exception {
    public InvalidFormatDeadlineException() {
        super("Format: deadline <name> /by <datetime>");
    }
}
