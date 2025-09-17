package atlas.exceptions;

/**
 * Represents an exception thrown for commands where the task number provided is invalid.
 */
public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException(int size) {
        super("Please provide a task number between 1 and " + size + ". ");
    }
}