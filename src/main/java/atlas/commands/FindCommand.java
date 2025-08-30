package atlas.commands;

import atlas.storage.Storage;
import atlas.items.ItemList;
import atlas.ui.Ui;

/**
 * Represents a command to find tasks with name matching a given input.
 */
public class FindCommand implements Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the find command.
     * @param itemList
     * @param ui
     * @param storage
     */
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        itemList.findItem(input);
    }
}
