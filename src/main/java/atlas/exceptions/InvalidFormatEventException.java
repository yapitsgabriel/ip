package atlas.exceptions;

/**
 * Represents an exception thrown for events with the wrong format.
 */
public class InvalidFormatEventException extends Exception {
    public InvalidFormatEventException() {
        super("Format: event <name> /from <datetime> /to <datetime>");
    }
}
