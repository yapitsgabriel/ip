public class NewDeadlineCommand implements Command{
    String input;

    public NewDeadlineCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            Item item = itemList.newDeadline(input);
            ui.printTodo(item, itemList.listSize());
        } catch (EmptyTaskNameException | InvalidFormatDeadlineException | InvalidDateFormatException | PastDateException e) {
            ui.printError(e.getMessage());
        }
    }
}
