package atlas.commands;

import atlas.exceptions.InvalidTaskNumberException;
import atlas.exceptions.MissingTaskNumberException;
import atlas.items.Item;
import atlas.storage.Storage;
import atlas.items.ItemList;
import atlas.ui.Ui;

/**
 * Represents the command to delete an Item from itemList
 */
public class DeleteCommand implements Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the delete command.
     *
     * @param itemList the list of items from which the item is deleted
     * @param ui the user interface used to display messages
     * @param storage the storage system used to save data to
     * @return The output string.
     */
    @Override
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            Item item = itemList.deleteItem(input);
            return ui.printDeleteItem(item);
        } catch (InvalidTaskNumberException | MissingTaskNumberException e) {
            return ui.printError(e.getMessage());
        }    }
}
