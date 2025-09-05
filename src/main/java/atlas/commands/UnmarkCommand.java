package atlas.commands;

import atlas.storage.Storage;
import atlas.items.ItemList;
import atlas.ui.Ui;

/**
 * Represents a command that marks a task as not done
 */
public class UnmarkCommand implements Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark as not done command.
     * @param itemList the list of items to be updated
     * @param ui the user interface used to display messages
     * @param storage the storage system used to save data to
     */

    @Override
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        return itemList.markItemAsNotDone(ui, index);
    }
}
