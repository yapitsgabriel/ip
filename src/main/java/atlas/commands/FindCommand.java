package atlas.commands;

import atlas.exceptions.EmptyTaskNameException;
import atlas.items.ItemList;
import atlas.storage.Storage;
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
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            ItemList foundItemList = itemList.findItem(input);
            return ui.printMatchingList(foundItemList);
        } catch (EmptyTaskNameException e) {
            return ui.printError(e.getMessage());
        }
    }
}
