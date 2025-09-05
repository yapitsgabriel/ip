package atlas.commands;

import atlas.exceptions.EmptyTaskNameException;
import atlas.exceptions.InvalidDateFormatException;
import atlas.exceptions.InvalidDateRangeException;
import atlas.exceptions.InvalidFormatEventException;
import atlas.exceptions.PastDateException;
import atlas.storage.Storage;
import atlas.items.Item;
import atlas.items.ItemList;
import atlas.ui.Ui;

/**
 * Represents a command that creates a new deadline
 */
public class NewEventCommand implements Command {
    private String input;

    public NewEventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the new event command.
     * @param itemList the list of items to be updated
     * @param ui the user interface used to display messages
     * @param storage the storage system used to save data to
     */
    @Override
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            Item item = itemList.newEvent(input);
            return ui.printTodo(item, itemList.listSize());
        } catch (EmptyTaskNameException | InvalidFormatEventException | InvalidDateFormatException
                | PastDateException | InvalidDateRangeException e) {
            return ui.printError(e.getMessage());
        }
    }
}
