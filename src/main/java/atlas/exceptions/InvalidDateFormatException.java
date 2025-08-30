package atlas.exceptions;

public class InvalidDateFormatException extends Exception {
    public InvalidDateFormatException() {
        super("Please enter a valid date in the format dd/MM/yyyy HH:mm");
    }
}
