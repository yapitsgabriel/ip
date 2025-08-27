import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private ItemList itemList;

    public Storage() {
        this.itemList = new ItemList();
    }

    public ItemList load() throws IOException {
        if (!(new File("data/atlas.txt").exists())) {
            Path p = Path.of("data/atlas.txt");
            Files.createDirectories(p.getParent());
            Files.createFile(p);
        }
        File f = new File("data/atlas.txt");

        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String nextLine =s.nextLine();
            loadItem(nextLine);
        }
        return itemList;
    }

    public void loadItem(String nextLine) {
        if (nextLine.startsWith("T")) {
            String[] parts = nextLine.split(Pattern.quote("|"));
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }
            itemList.loadItem(new Todo(Integer.parseInt(parts[1]), parts[2]));
        } else if (nextLine.startsWith("D")) {
            String[] parts = nextLine.split(Pattern.quote("|"));
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }
            try {
                itemList.loadItem(new Deadline(Integer.parseInt(parts[1]), parts[2], Parser.parseDate(parts[3])));
            } catch (InvalidDateFormatException | PastDateException e) {
                Ui.printLine();
                Ui.smallSpace(e.getMessage());
                Ui.printLine();
            }

        } else if (nextLine.startsWith("E")) {
            String[] parts = nextLine.split(Pattern.quote("|"));
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }
            try {
                itemList.loadItem(new Event(Integer.parseInt(parts[1]), parts[2], Parser.parseDate(parts[3]), Parser.parseDate(parts[4])));
            } catch (InvalidDateFormatException | PastDateException e) {
                Ui.printLine();
                Ui.smallSpace(e.getMessage());
                Ui.printLine();
            }
        }
    }

    public void save(ItemList itemList){
        this.itemList = itemList;

        try {
            FileWriter f = new FileWriter("data/atlas_temp.txt", false);
            for (int i = 0; i < itemList.listSize(); i++) {
                f.write(itemList.getItem(i).fileFormat() + "\n");
            }
            f.close();
            Files.move(Path.of("data/atlas_temp.txt"), Path.of("data/atlas.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            Ui.printLine();
            Ui.smallSpace("There was an error saving the file.");
            Ui.printLine();
        }


    }


}
