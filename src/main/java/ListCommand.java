public class ListCommand implements Command {
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        ui.printList(itemList);
    }
}
