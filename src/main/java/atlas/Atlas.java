package atlas;

import atlas.commands.Command;
import atlas.storage.Storage;
import atlas.tasks.ItemList;
import atlas.ui.Ui;
import atlas.utilities.Parser;

import java.io.IOException;
import java.util.Scanner;

public class Atlas {
    private Scanner scanner;
    private Storage storage;
    private ItemList itemList;
    private Ui ui;

    public Atlas() {
        this.scanner = new Scanner(System.in);
        this.storage = new Storage();
        this.itemList = new ItemList();
        this.ui = new Ui();
    }

    public void run() {
        String input;
        ui.hello();

        try {
            itemList = storage.load();
        } catch (IOException e) {
            Ui.printLine();
            Ui.smallSpace(e.getMessage());
            Ui.printLine();
        }

        while (true) {
            input = scanner.nextLine();
            Command command = Parser.parseCommand(input);
            command.execute(itemList, ui, storage);
        }
    }

    public static void main(String[] args) {
        Atlas atlas = new Atlas();
        atlas.run();
    }
}
