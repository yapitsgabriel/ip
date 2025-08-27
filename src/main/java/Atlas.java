import java.io.IOException;
import java.util.Scanner;

public class Atlas {
    private Scanner s;
    private Storage storage;
    private ItemList itemList;
    private Ui ui;

    public Atlas () {
        this.s = new Scanner(System.in);
        this.storage = new Storage();
        this.itemList = new ItemList();
        this.ui = new Ui();
    }

    public void run(){
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
            input = s.nextLine();
            Command c = Parser.parseCommand(input);
            c.execute(itemList, ui, storage);
        }
    }

    public static void main(String[] args){
        Atlas atlas = new Atlas();
        atlas.run();
    }
}
