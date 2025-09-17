package atlas.exceptions;

/**
 * Represents an exception thrown for unknown commands.
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("I don't understand what you mean. You can try these prompts: \n"
            + "• list\n"
            + "• todo <task name>\n"
            + "• deadline <task name> /by <deadline>\n"
            + "• event <task name> /from <start date> /to <end date>\n"
            + "• mark <task number>\n"
            + "• unmark <task number>\n"
            + "• delete <task number>");
    }
}
