package atlas.exceptions;

public class ItemNameTooLongException extends Exception{
    public ItemNameTooLongException() {
        super("Task name cannot be longer than 55 characters.");
    }
}
