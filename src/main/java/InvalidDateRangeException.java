public class InvalidDateRangeException extends Exception{
    public InvalidDateRangeException() {
        super("FROM date cannot be before the TO date. ");
    }
}
