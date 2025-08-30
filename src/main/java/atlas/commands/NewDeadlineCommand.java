package atlas.commands;

import atlas.exceptions.EmptyTaskNameException;
import atlas.exceptions.InvalidDateFormatException;
import atlas.exceptions.InvalidFormatDeadlineException;
import atlas.exceptions.PastDateException;
import atlas.storage.Storage;
import atlas.tasks.Item;
import atlas.tasks.ItemList;
import atlas.ui.Ui;

public class NewDeadlineCommand implements Command {
    private String input;

    public NewDeadlineCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            Item item = itemList.newDeadline(input);
            ui.printTodo(item, itemList.listSize());
        } catch (EmptyTaskNameException | InvalidFormatDeadlineException | InvalidDateFormatException
                | PastDateException e) {
            ui.printError(e.getMessage());
        }
    }
}
