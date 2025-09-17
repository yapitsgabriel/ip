package atlas.commands;

import atlas.exceptions.InvalidTaskNumberException;
import atlas.exceptions.MissingTaskNumberException;
import atlas.items.Item;
import atlas.items.ItemList;
import atlas.storage.Storage;
import atlas.ui.Ui;

/**
 * Represents a command that marks a task as done
 */
public class MarkCommand implements Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the mark as done command.
     *
     * @param itemList the list of items to be updated
     * @param ui the user interface used to display messages
     * @param storage the storage system used to save data to
     * @return The output string.
     */
    @Override
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            Item item = itemList.markItemAsDone(input);
            return ui.printMarkItemAsDone(item);
        } catch (InvalidTaskNumberException | MissingTaskNumberException e) {
            return ui.printError(e.getMessage());
        }
    }
}
