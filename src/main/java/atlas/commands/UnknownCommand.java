package atlas.commands;


import atlas.items.ItemList;
import atlas.storage.Storage;
import atlas.ui.Ui;

/**
 * Represents a command that creates a new todo
 */
public class UnknownCommand implements Command {
    /**
     * Executes the unknown command.
     *
     * @param itemList the list of items to be updated
     * @param ui the user interface used to display messages
     * @param storage the storage system used to save data to
     * @return The output string.
     */
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        return ui.printHelpMenu();
    }
}
