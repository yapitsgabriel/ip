public class UnmarkCommand implements Command {
    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        itemList.markItemAsNotDone(index);
    }
}
