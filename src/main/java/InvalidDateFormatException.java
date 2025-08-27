public class InvalidDateFormatException extends Exception{
    public InvalidDateFormatException() {
        super("Date must be in the format dd/MM/yyyy HH:mm");
    }
}
