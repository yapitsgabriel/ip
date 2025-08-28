package atlas.commands;

import atlas.storage.Storage;
import atlas.tasks.ItemList;
import atlas.ui.Ui;

public class DeleteCommand implements Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        itemList.deleteItem(index);
    }
}
