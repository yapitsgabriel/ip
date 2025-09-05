package atlas;

import atlas.commands.Command;
import atlas.storage.Storage;
import atlas.items.ItemList;
import atlas.ui.Ui;
import atlas.utilities.Parser;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents an Atlas object.
 */
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

    /**
     * Runs Atlas.
     */
    public void run() {
        String input;
        ui.hello();
        try {
            itemList = storage.load();
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
        while (true) {
            input = scanner.nextLine();
            Command c = Parser.parseCommand(input);
            c.execute(itemList, ui, storage);
        }
    }

    public String getResponse(String input) {
        Command command = Parser.parseCommand(input);
        return command.execute(itemList, ui, storage);
    }

    public static void main(String[] args){
        Atlas atlas = new Atlas();
        atlas.run();
    }
}
