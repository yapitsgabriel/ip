package atlas.exceptions;

/**
 * Represents an exception thrown when task name is empty
 */
public class EmptyTaskNameException extends Exception {
    public EmptyTaskNameException() {
        super("Task name cannot be empty.");
    }
}
