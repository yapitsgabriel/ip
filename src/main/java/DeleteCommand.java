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
