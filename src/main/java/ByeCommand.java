public class ByeCommand implements Command {
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        ui.bye();
        storage.save(itemList);
    }
}
