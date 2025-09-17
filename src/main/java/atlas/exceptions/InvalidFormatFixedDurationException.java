package atlas.exceptions;

/**
 * Represents an exception thrown for events with the wrong format.
 */
public class InvalidFormatFixedDurationException extends Exception {
    public InvalidFormatFixedDurationException() {
        super("Format: fixedDuration <name> /duration <duration in hours>");
    }
}
