package atlas.exceptions;

/**
 * Represents an exception thrown for commands where no task number is provided
 */
public class MissingTaskNumberException extends Exception {
    public MissingTaskNumberException(int size) {
        super("Please provide a task number between 1 and " + size + ". ");
    }
}