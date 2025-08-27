public class EmptyTaskNameException extends Exception {
    public EmptyTaskNameException() {
        super("Task name cannot be empty.");
    }
}
