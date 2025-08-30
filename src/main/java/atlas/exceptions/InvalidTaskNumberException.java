package atlas.exceptions;

/**
 * Represents an exception thrown for task numbers that are invalid (e.g. negative / more than total number).
 */
public class InvalidTaskNumberException extends Exception{
    public InvalidTaskNumberException() {
        super("Please enter a valid task number.");
    }
}
