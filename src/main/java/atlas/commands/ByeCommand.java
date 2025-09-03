package atlas.commands;

import atlas.storage.Storage;
import atlas.items.ItemList;
import atlas.ui.Ui;

/**
 * Represents a command that terminates the program session.
 * When executed, it displays a goodbye message and saves the current state to data/atlas.txt
 */
public class ByeCommand implements Command {

    /**
     * Executes the bye command.
     * Displays a goodbye message and saves the current state to
     * data/atlas.txt
     *
     * @param itemList the list of items to be saved
     * @param ui the user interface used to display messages
     * @param storage the storage system used to save data to
     */
    @Override
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        storage.save(itemList);
        return ui.bye();
    }
}
