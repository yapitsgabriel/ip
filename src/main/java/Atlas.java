import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Atlas {
    private List<Item> itemList;
    private Scanner s;

    public Atlas () {
        this.itemList = new ArrayList<Item>();
        this.s = new Scanner(System.in);
    }


    public void run() throws IOException {
        String input;
        hello();
        try {
            load();
        } catch (IOException e) {
            Printer.printLine();
            Printer.smallSpace(e.getMessage());
            Printer.printLine();
        }
        while (true) {
            input = s.nextLine();
            readInput(input);
        }
    }

    public void hello() {
        Printer.printLine();
        Printer.smallSpace("Hello, I'm Atlas!");
        Printer.smallSpace("What do you want to do?");
        Printer.printLine();
    }

    public void bye() throws IOException {
        Printer.printLine();
        Printer.smallSpace("Bye! See you next time :)");
        Printer.printLine();
        save();
    }

    public void load() throws IOException {
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
    }

    public void loadItem(String nextLine) {
        if (nextLine.startsWith("T")) {
            String[] parts = nextLine.split(Pattern.quote("|"));
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }
            itemList.add(new Todo(Integer.parseInt(parts[1]), parts[2]));
        } else if (nextLine.startsWith("D")) {
            String[] parts = nextLine.split(Pattern.quote("|"));
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }
            itemList.add(new Deadline(Integer.parseInt(parts[1]), parts[2], parts[3]));
        } else if (nextLine.startsWith("E")) {
            String[] parts = nextLine.split(Pattern.quote("|"));
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }
            itemList.add(new Event(Integer.parseInt(parts[1]), parts[2], parts[3], parts[4]));
        }
    }

    public void save() throws IOException {
        FileWriter f = new FileWriter("data/atlas_temp.txt", false);
        for (Item item : itemList) {
            f.write(item.fileFormat() + "\n");
        }
        f.close();
        Files.move(Path.of("data/atlas_temp.txt"), Path.of("data/atlas.txt"), StandardCopyOption.REPLACE_EXISTING);
    }

    public void readInput(String input) throws IOException {
        if (input.equals("bye")) {
            bye();
        } else if (input.equals("list")) {
            printList();
        } else if (input.matches("^mark \\d+$")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            markitemAsDone(index);
        } else if (input.matches("^unmark \\d+$")) {
            int index = Integer.parseInt(input.substring(7)) - 1;

            markitemAsNotDone(index);
        } else if (input.matches("^delete \\d+$")) {
            int index = Integer.parseInt(input.substring(7)) - 1;

            deleteItem(index);
        } else if (input.startsWith("todo")) {
            try {
                newTodo(input);
                printTodo();
            } catch (EmptyException e) {
                Printer.printLine();
                System.out.println(e.getMessage());
                Printer.printLine();
            }

        } else if (input.startsWith("deadline")) {
            try {
                newDeadline(input);
                printTodo();
            } catch (EmptyException | DeadlineDateException e) {
                Printer.printLine();
                System.out.println(e.getMessage());
                Printer.printLine();
            }
        } else if (input.startsWith("event")) {
            try {
                newEvent(input);
                printTodo();
            } catch (EmptyException | EventDateException e) {
                Printer.printLine();
                System.out.println(e.getMessage());
                Printer.printLine();
            }
        } else {
            printHelpMenu();
        }
    }

    public void newTodo(String input) throws EmptyException {
        if (input.length() < 6) {
            throw new EmptyException();
        }
        String name = input.substring(5).trim();

        if (name.isEmpty()) {
            throw new EmptyException();
        }

        itemList.add(new Todo(name));
    }

    public void newDeadline(String input) throws EmptyException, DeadlineDateException {
        if (input.length() < 10) {
            throw new EmptyException();
        } else if (!input.contains(" /by ")) {
            throw new DeadlineDateException();
        }

        // Get name
        int i = input.indexOf("/");
        String name = input.substring(9, i - 1);

        // Get by
        String rest = input.substring(i);
        if (rest.length() < 5) {
            throw new DeadlineDateException();
        }
        String by = input.substring(i + 4);

        // Create item
        itemList.add(new Deadline(name, by));
    }

    public void newEvent(String input) throws EmptyException, EventDateException {
        if (input.length() < 7) {
            throw new EmptyException();
        } else if (!(input.contains(" /from ") && input.contains(" /to "))) {
            throw new EventDateException();
        }

        // Get name
        int i1 = input.indexOf("/");
        int i2 = input.indexOf("/", i1 + 1);
        String name = input.substring(6, i1 - 1);

        // Get from
        String rest = input.substring(i1);
        if (rest.length() < 7) {
            throw new EventDateException();
        }
        String from = input.substring(i1 + 6, i2 - 1);

        // Get to
        rest = input.substring(i2);
        if (rest.length() < 5) {
            throw new EventDateException();
        }
        String to = input.substring(i2 + 4);

        itemList.add(new Event(name, from, to));
    }

    public void markitemAsDone(int index) {
        if (index < 0 || index > itemList.size() - 1) {
            Printer.printLine();
            Printer.smallSpace("Invalid number! Please choose a number between 1 and " + itemList.size());
            return;
        }
        itemList.get(index).markAsDone();
        Printer.printLine();
        Printer.smallSpace("Nice! I've marked this item as done:");
        Printer.bigSpace(itemList.get(index).toString());
        Printer.printLine();
    }

    public void markitemAsNotDone(int index) {
        if (index < 0 || index > itemList.size() - 1) {
            Printer.printLine();
            Printer.smallSpace("Invalid number! Please choose a number between 1 and " + itemList.size());
            return;
        }
        itemList.get(index).markAsNotDone();
        Printer.printLine();
        Printer.smallSpace("Okay, I've marked this item as not done yet:");
        Printer.bigSpace( itemList.get(index).toString());
        Printer.printLine();
    }

    public void deleteItem(int index) {
        if (index < 0 || index > itemList.size() - 1) {
            Printer.printLine();
            Printer.smallSpace("Invalid number! Please choose a number between 1 and " + itemList.size());
            return;
        }
        Printer.printLine();
        Printer.smallSpace("Okay, I've deleted this item: ");
        Printer.bigSpace(itemList.get(index).toString());
        Printer.printLine();
        itemList.remove(index);
    }

    public void printHelpMenu() {
        Printer.printLine();
        Printer.smallSpace("I don't understand what you mean. You can try these prompts: ");
        Printer.smallSpace("• list");
        Printer.smallSpace("• todo <item name>");
        Printer.smallSpace("• deadline <item name> /by <deadline>");
        Printer.smallSpace("• event <item name> /from <start date> /to <end date>");
        Printer.smallSpace("• mark <item number>");
        Printer.smallSpace("• unmark <item number>");
        Printer.smallSpace("• delete <item number>");
        Printer.printLine();
    }

    public void printList() {
        Printer.printLine();
        Printer.smallSpace("Here are the items in your list:");
        for (int i = 0; i < itemList.size(); i++) {
            Printer.bigSpace(Integer.toString(i + 1) + ". " + itemList.get(i).toString());
        }
        Printer.printLine();
    }

    public void printTodo() {
        Printer.printLine();
        Printer.smallSpace("Got it! I've added this item:");
        Printer.bigSpace(itemList.get(itemList.size() - 1).toString());
        Printer.smallSpace("Now you have " + itemList.size() + " item(s) in the list.");
        Printer.printLine();
    }

    public static void main(String[] args) throws IOException {
        Atlas atlas = new Atlas();
        atlas.run();
    }
}
