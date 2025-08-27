public class NewTodoCommand implements Command{
    String input;

    public NewTodoCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            Item item = itemList.newTodo(input);
            ui.printTodo(item, itemList.listSize());
        } catch (EmptyTaskNameException e) {
            ui.printError(e.getMessage());
        }
    }
}
