package atlas.commands;


import atlas.items.ItemList;
import atlas.storage.Storage;
import atlas.ui.Ui;

/**
 * Represents a command that creates a new todo
 */
public class UnknownCommand implements Command {
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        return ui.printHelpMenu();
    }
}
