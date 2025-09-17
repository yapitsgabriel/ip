package atlas.storage;

import atlas.exceptions.CorruptedFileException;
import atlas.exceptions.InvalidDateFormatException;
import atlas.exceptions.InvalidDurationException;
import atlas.exceptions.PastDateException;
import atlas.items.Deadline;
import atlas.items.Event;
import atlas.items.FixedDuration;
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
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Represents the storage system of Atlas.
 * It loads tasks upon startup from a file, and saves it to the file upon exiting Atlas.
 */
public class Storage {
    /**
     * Loads tasks from file to the local ItemList.
     *
     * @param itemList The ItemList in which to load the items to.
     * @param ui The Ui to display messages to.
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
     *
     * @param nextLine The next line of the input file.
     * @param itemList The ItemList in which to load the items to.
     * @param ui The Ui to display messages to.
     */
    public void loadItem(String nextLine, ItemList itemList, Ui ui) {
        if (nextLine.startsWith("T")) {
            loadTodo(nextLine, itemList, ui);
        } else if (nextLine.startsWith("D")) {
            loadDeadline(nextLine, itemList, ui);
        } else if (nextLine.startsWith("E")) {
            loadEvent(nextLine, itemList, ui);
        } else if (nextLine.startsWith("F")) {
            loadFixedDuration(nextLine, itemList, ui);
        }
    }

    /**
     * Loads individual todos.
     *
     * @param nextLine The next line of the input file.
     * @param itemList The ItemList in which to load the items to.
     * @param ui The Ui to display messages to.
     */
    public void loadTodo(String nextLine, ItemList itemList, Ui ui) {
        String[] parts = nextLine.split(Pattern.quote("|"));
        assert parts.length == 3;
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        try {
            boolean isDone = Parser.parseIsDone(parts[1]);
            String name = parts[2];
            itemList.loadItem(new Todo(isDone, name));
        } catch(CorruptedFileException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Loads individual deadline items.
     *
     * @param nextLine The next line of the input file.
     * @param itemList The ItemList in which to load the items to.
     * @param ui The Ui to display messages to.
     */
    public void loadDeadline(String nextLine, ItemList itemList, Ui ui) {
        String[] parts = nextLine.split(Pattern.quote("|"));
        assert parts.length == 4;
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        try {
            boolean isDone = Parser.parseIsDone(parts[1]);
            String name = parts[2];
            LocalDateTime date = Parser.parseDate(parts[3]);
            itemList.loadItem(new Deadline(isDone, name, date));
        } catch (InvalidDateFormatException | PastDateException | CorruptedFileException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Loads individual events.
     *
     * @param nextLine The next line of the input file.
     * @param itemList The ItemList in which to load the items to.
     * @param ui The Ui to display messages to.
     */
    public void loadEvent(String nextLine, ItemList itemList, Ui ui) {
        String[] parts = nextLine.split(Pattern.quote("|"));
        assert parts.length == 5;
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        try {
            boolean isDone = Parser.parseIsDone(parts[1]);
            String name = parts[2];
            LocalDateTime start = Parser.parseDate(parts[3]);
            LocalDateTime end = Parser.parseDate(parts[4]);
            itemList.loadItem(new Event(isDone, name, start, end));
        } catch (InvalidDateFormatException | PastDateException | CorruptedFileException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Loads individual fixed duration items.
     *
     * @param nextLine The next line of the input file.
     * @param itemList The ItemList in which to load the items to.
     * @param ui The Ui to display messages to.
     */
    public void loadFixedDuration(String nextLine, ItemList itemList, Ui ui) {
        String[] parts = nextLine.split(Pattern.quote("|"));
        assert parts.length == 4;
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        try {
            boolean isDone = Parser.parseIsDone(parts[1]);
            String name = parts[2];
            int duration = Parser.parseDuration(parts[3]);
            itemList.loadItem(new FixedDuration(isDone, name, duration));
        } catch (InvalidDurationException | CorruptedFileException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Saves the itemList to the file.
     *
     * @param itemList The ItemList in which to load the items to.
     * @param ui The Ui to display messages to.
     */
    public void save(ItemList itemList, Ui ui) {
        try {
            FileWriter f = new FileWriter("data/atlas_temp.txt", false);
            for (int i = 0; i < itemList.listSize(); i++) {
                f.write(itemList.getItem(i).fileFormat() + "\n");
            }
            f.close();
            Files.move(Path.of("data/atlas_temp.txt"), Path.of("data/atlas.txt"),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }
}
