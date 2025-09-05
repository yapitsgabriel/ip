package atlas.commands;

import atlas.storage.Storage;
import atlas.items.ItemList;
import atlas.ui.Ui;

/**
 * Represents a command that prints out all the lists
 */
public class ListCommand implements Command {

    /**
     * Executes the list command.
     * @param itemList the list of items to be displayed
     * @param ui the user interface used to display messages
     * @param storage the storage system used to save data to
     */
    @Override
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        return ui.printList(itemList);
    }
}
