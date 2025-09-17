package atlas.commands;

import atlas.exceptions.EmptyTaskNameException;
import atlas.storage.Storage;
import atlas.items.Item;
import atlas.items.ItemList;
import atlas.ui.Ui;

/**
 * Represents a command that creates a new todo
 */
public class NewTodoCommand implements Command {
    private String input;

    public NewTodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the new todo command.
     *
     * @param itemList the list of items to be updated
     * @param ui the user interface used to display messages
     * @param storage the storage system used to save data to
     * @return The output string.
     */
    @Override
    public String execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            Item item = itemList.newTodo(input);
            return ui.printItem(item, itemList.listSize());
        } catch (EmptyTaskNameException e) {
            return ui.printError(e.getMessage());
        }
    }
}
