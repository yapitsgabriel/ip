package atlas.exceptions;

/**
 * Represents an exception thrown for events when the end date is before the start date.
 */
public class InvalidDateRangeException extends Exception {
    public InvalidDateRangeException() {
        super("FROM date cannot be before the TO date. ");
    }
}