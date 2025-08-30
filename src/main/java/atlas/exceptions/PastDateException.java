package atlas.exceptions;

public class PastDateException extends Exception {
    public PastDateException() {
        super("Date cannot be before today.");
    }
}
