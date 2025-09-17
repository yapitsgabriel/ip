package atlas.storage;

import atlas.exceptions.InvalidDateFormatException;
import atlas.exceptions.PastDateException;
import atlas.items.Deadline;
import atlas.items.Event;
import atlas.items.ItemList;
import atlas.items.Todo;
import atlas.ui.Ui;
import atlas.utilities.Parser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Represents the storage system of Atlas.
 * It loads tasks upon startup from a file, and saves it to the file upon exiting Atlas.
 */
public class Storage {
    /**
     * Loads tasks from file to the local ItemList.
     * @return An ItemList with all the loaded tasks.
     * @throws IOException
     */
    public ItemList load(ItemList itemList, Ui ui) throws IOException {
        if (!(new File("data/atlas.txt").exists())) {
            Path p = Path.of("data/atlas.txt");
            Files.createDirectories(p.getParent());
            Files.createFile(p);
        }

        File f = new File("data/atlas.txt");
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String nextLine = s.nextLine();
            loadItem(nextLine, itemList, ui);
        }
        return itemList;
    }

    /**
     * Loads individual tasks from the file to the local ItemList.
     * @param nextLine The next line of the input file.
     */
    public void loadItem(String nextLine, ItemList itemList, Ui ui) {
        if (nextLine.startsWith("T")) {
            String[] parts = nextLine.split(Pattern.quote("|"));
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }
            itemList.loadItem(new Todo(Parser.parseIsDone(parts[1]), parts[2]));
        } else if (nextLine.startsWith("D")) {
            String[] parts = nextLine.split(Pattern.quote("|"));
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }
            try {
                itemList.loadItem(new Deadline(Parser.parseIsDone(parts[1]), parts[2], Parser.parseDate(parts[3])));
            } catch (InvalidDateFormatException | PastDateException e) {
                ui.printError(e.getMessage());
            }

        } else if (nextLine.startsWith("E")) {
            String[] parts = nextLine.split(Pattern.quote("|"));
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }
            try {
                itemList.loadItem(new Event(Parser.parseIsDone(parts[1]), parts[2], Parser.parseDate(parts[3]), Parser.parseDate(parts[4])));
            } catch (InvalidDateFormatException | PastDateException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * Saves the itemList to the file.
     * @param itemList The itemList to be saved.
     */
    public void save(ItemList itemList, Ui ui) {
        try {
            FileWriter f = new FileWriter("data/atlas_temp.txt", false);
            for (int i = 0; i < itemList.listSize(); i++) {
                f.write(itemList.getItem(i).fileFormat() + "\n");
            }
            f.close();
            Files.move(Path.of("data/atlas_temp.txt"), Path.of("data/atlas.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }


    }


}
