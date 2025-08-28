package atlas.commands;

import atlas.exceptions.EmptyTaskNameException;
import atlas.storage.Storage;
import atlas.tasks.Item;
import atlas.tasks.ItemList;
import atlas.ui.Ui;

public class NewTodoCommand implements Command {
    String input;

    public NewTodoCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            Item item = itemList.newTodo(input);
            ui.printTodo(item, itemList.listSize());
        } catch (EmptyTaskNameException e) {
            ui.printError(e.getMessage());
        }
    }
}
