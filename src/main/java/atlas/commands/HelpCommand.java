package atlas.commands;

import atlas.storage.Storage;
import atlas.items.ItemList;
import atlas.ui.Ui;

/**
 * Represents a command that shows a help message
 */
public class HelpCommand implements Command {

    /**
     * Executes the help command
     *
     * @param itemList the list of items
     * @param ui the user interface used to display messages
     * @param storage the storage system used to save data to
     * @return The output string.
     */
    @Override
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        return ui.printHelpMenu();
    }
}
