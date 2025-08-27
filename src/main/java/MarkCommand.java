public class MarkCommand implements Command {
    int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        itemList.markItemAsDone(index);
    }
}
