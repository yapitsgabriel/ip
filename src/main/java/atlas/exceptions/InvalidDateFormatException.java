package atlas.exceptions;

/**
 * Represents an exception thrown when the date format is invalid
 */
public class InvalidDateFormatException extends Exception{
    public InvalidDateFormatException() {
        super("Please enter a valid date in the format dd/MM/yyyy HH:mm");
    }
}
