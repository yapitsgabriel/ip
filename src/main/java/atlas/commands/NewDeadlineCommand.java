package atlas.commands;

import atlas.exceptions.EmptyTaskNameException;
import atlas.exceptions.InvalidDateFormatException;
import atlas.exceptions.InvalidFormatDeadlineException;
import atlas.exceptions.PastDateException;
import atlas.storage.Storage;
import atlas.items.Item;
import atlas.items.ItemList;
import atlas.ui.Ui;

/**
 * Represents a command that creates a new deadline
 */
public class NewDeadlineCommand implements Command {
    private String input;

    public NewDeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the new deadline command.
     * @param itemList the list of items to be updated
     * @param ui the user interface used to display messages
     * @param storage the storage system used to save data to
     * @return The output string.
     */
    @Override
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            Item item = itemList.newDeadline(input);
            return ui.printTodo(item, itemList.listSize());
        } catch (EmptyTaskNameException | InvalidFormatDeadlineException | InvalidDateFormatException
                | PastDateException e) {
            return ui.printError(e.getMessage());
        }
    }
}
