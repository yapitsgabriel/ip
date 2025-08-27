public class HelpCommand implements Command {
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        ui.printHelpMenu();
    }
}
