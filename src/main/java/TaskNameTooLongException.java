public class TaskNameTooLongException extends Exception{
    public TaskNameTooLongException() {
        super("Task name cannot be longer than 55 characters.");
    }
}
