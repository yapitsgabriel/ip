package atlas.exceptions;

/**
 * Represents an exception thrown for fixed durations when the duration entered is not valid
 */
public class InvalidDurationException extends Exception {
    public InvalidDurationException() {
        super("Duration must be an integer larger than 0. ");
    }
}