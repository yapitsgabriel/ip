public class NewEventCommand implements Command{
    String input;

    public NewEventCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            Item item = itemList.newEvent(input);
            ui.printTodo(item, itemList.listSize());
        } catch (EmptyTaskNameException | InvalidFormatEventException | InvalidDateFormatException | PastDateException | InvalidDateRangeException e) {
            ui.printError(e.getMessage());
        }
    }
}
