package atlas.exceptions;

/**
 * Represents an exception thrown for tasks with names that are too long.
 */
public class ItemNameTooLongException extends Exception{
    public ItemNameTooLongException() {
        super("Task name cannot be longer than 55 characters.");
    }
}
