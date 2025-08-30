package atlas.commands;

import atlas.storage.Storage;
import atlas.items.ItemList;
import atlas.ui.Ui;

/**
 * Represents the command to delete an Item from itemList
 */
public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     * @param itemList the list of items from which the item is deleted
     * @param ui the user interface used to display messages
     * @param storage the storage system used to save data to
     */
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        itemList.deleteItem(index);
    }
}
