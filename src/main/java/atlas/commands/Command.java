package atlas.commands;

import atlas.storage.Storage;
import atlas.tasks.ItemList;
import atlas.ui.Ui;

public interface Command {
    void execute(ItemList itemList, Ui ui, Storage storage);
}
