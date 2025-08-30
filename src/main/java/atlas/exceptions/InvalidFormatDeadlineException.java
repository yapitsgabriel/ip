package atlas.exceptions;

/**
 * Represents an exception thrown for deadlines with the wrong format.
 */
public class InvalidFormatDeadlineException extends Exception {
    public InvalidFormatDeadlineException() {
        super("Format: deadline <name> /by <datetime>");
    }
}
