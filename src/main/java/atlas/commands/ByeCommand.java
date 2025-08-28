package atlas.commands;

import atlas.storage.Storage;
import atlas.tasks.ItemList;
import atlas.ui.Ui;

public class ByeCommand implements Command {
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        ui.bye();
        storage.save(itemList);
    }
}
