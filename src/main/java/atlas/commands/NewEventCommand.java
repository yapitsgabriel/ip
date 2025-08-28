package atlas.commands;

import atlas.exceptions.EmptyTaskNameException;
import atlas.exceptions.InvalidDateFormatException;
import atlas.exceptions.InvalidDateRangeException;
import atlas.exceptions.InvalidFormatEventException;
import atlas.exceptions.PastDateException;
import atlas.storage.Storage;
import atlas.tasks.Item;
import atlas.tasks.ItemList;
import atlas.ui.Ui;

public class NewEventCommand implements Command {
    String input;

    public NewEventCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            Item item = itemList.newEvent(input);
            ui.printTodo(item, itemList.listSize());
        } catch (EmptyTaskNameException | InvalidFormatEventException | InvalidDateFormatException |
                 PastDateException | InvalidDateRangeException e) {
            ui.printError(e.getMessage());
        }
    }
}
