package atlas;

import java.io.IOException;
import java.util.Scanner;

import atlas.commands.Command;
import atlas.items.ItemList;
import atlas.storage.Storage;
import atlas.ui.Ui;
import atlas.utilities.Parser;

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
    public void loadData() {
        try {
            itemList = storage.load(itemList, ui);
        } catch (IOException e) {
            System.out.println(ui.printError(e.getMessage()));
        }
    }

    /**
     * Returns the initial greeting message.
     *
     * @return Hello message.
     */
    public String hello() {
        return ui.hello();
    }

    /**
     * Given a user input, get execute the command and return the relevant output.
     *
     * @param input User input.
     * @return Atlas's response.
     */
    public String getResponse(String input) {
        Command command = Parser.parseCommand(input);
        return command.execute(itemList, ui, storage);
    }

}
