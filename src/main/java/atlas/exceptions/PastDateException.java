package atlas.exceptions;

/**
 * Represents an exception thrown for deadlines / events with dates that are before today.
 */
public class PastDateException extends Exception {
    public PastDateException() {
        super("Date cannot be before today.");
    }
}
