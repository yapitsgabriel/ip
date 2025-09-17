package atlas.commands;

import atlas.exceptions.EmptyTaskNameException;
import atlas.exceptions.InvalidDurationException;
import atlas.exceptions.InvalidFormatFixedDurationException;
import atlas.items.Item;
import atlas.items.ItemList;
import atlas.storage.Storage;
import atlas.ui.Ui;

/**
 * Represents a command that creates a new fixed duration
 */
public class NewFixedDurationCommand implements Command {
    private String input;

    public NewFixedDurationCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the new deadline command.
     * @param itemList the list of items to be updated
     * @param ui the user interface used to display messages
     * @param storage the storage system used to save data to
     */
    @Override
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            Item item = itemList.newFixedDuration(input);
            return ui.printTodo(item, itemList.listSize());
        } catch (EmptyTaskNameException | InvalidFormatFixedDurationException | InvalidDurationException e) {
            return ui.printError(e.getMessage());
        }
    }
}
