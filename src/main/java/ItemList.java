import java.util.ArrayList;
import java.util.List;

public class ItemList {

    private List<Item> itemList;

    public ItemList() {
        this.itemList = new ArrayList<Item>();
    }

    public ItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void newTodo(String input) throws EmptyException {
        itemList.add(ItemParser.parseTodo(input));
    }

    public void newDeadline(String input) throws EmptyException, DeadlineDateException {
        itemList.add(ItemParser.parseDeadline(input));
    }

    public void newEvent(String input) throws EmptyException, EventDateException {
        itemList.add(ItemParser.parseEvent(input));
    }

    public void loadItem(Item item) {
        itemList.add(item);
    }

    public void markitemAsDone(int index) {
        if (index < 0 || index > itemList.size() - 1) {
            ItemPrinter.printLine();
            ItemPrinter.smallSpace("Invalid number! Please choose a number between 1 and " + itemList.size());
            return;
        }
        itemList.get(index).markAsDone();
        ItemPrinter.printLine();
        ItemPrinter.smallSpace("Nice! I've marked this item as done:");
        ItemPrinter.bigSpace(itemList.get(index).toString());
        ItemPrinter.printLine();
    }

    public void markitemAsNotDone(int index) {
        if (index < 0 || index > itemList.size() - 1) {
            ItemPrinter.printLine();
            ItemPrinter.smallSpace("Invalid number! Please choose a number between 1 and " + itemList.size());
            return;
        }
        itemList.get(index).markAsNotDone();
        ItemPrinter.printLine();
        ItemPrinter.smallSpace("Okay, I've marked this item as not done yet:");
        ItemPrinter.bigSpace( itemList.get(index).toString());
        ItemPrinter.printLine();
    }

    public void deleteItem(int index) {
        if (index < 0 || index > itemList.size() - 1) {
            ItemPrinter.printLine();
            ItemPrinter.smallSpace("Invalid number! Please choose a number between 1 and " + itemList.size());
            return;
        }
        ItemPrinter.printLine();
        ItemPrinter.smallSpace("Okay, I've deleted this item: ");
        ItemPrinter.bigSpace(itemList.get(index).toString());
        ItemPrinter.printLine();
        itemList.remove(index);
    }

    public int listSize() {
        return itemList.size();
    }

    public Item getItem(int i) {
        return itemList.get(i);
    }

    public void printList() {
        ItemPrinter.printLine();
        ItemPrinter.smallSpace("Here are the items in your list:");
        for (int i = 0; i < this.listSize(); i++) {
            ItemPrinter.bigSpace(Integer.toString(i + 1) + ". " + this.getItem(i).toString());
        }
        ItemPrinter.printLine();
    }

    public void printTodo() {
        ItemPrinter.printLine();
        ItemPrinter.smallSpace("Got it! I've added this item:");
        ItemPrinter.bigSpace(this.getItem(this.listSize() - 1).toString());
        ItemPrinter.smallSpace("Now you have " + this.listSize() + " item(s) in the list.");
        ItemPrinter.printLine();
    }
}
