package atlas.commands;

import atlas.storage.Storage;
import atlas.items.ItemList;
import atlas.ui.Ui;

public interface Command {
    void execute(ItemList itemList, Ui ui, Storage storage);
}
