import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ItemParser {

    public static Item parseTodo(String input) throws EmptyException {
        if (input.length() < 6) {
            throw new EmptyException();
        }
        String name = input.substring(5).trim();

        if (name.isEmpty()) {
            throw new EmptyException();
        }

        return new Todo(name);
    }

    public static Item parseDeadline(String input) throws EmptyException, DeadlineDateException {
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
        LocalDateTime byDT = parseDate(by);

        return new Deadline(name, byDT);
    }

    public static Item parseEvent(String input) throws EmptyException, EventDateException {
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
        LocalDateTime fromDT = parseDate(from);

        // Get to
        rest = input.substring(i2);
        if (rest.length() < 5) {
            throw new EventDateException();
        }
        String to = input.substring(i2 + 4);
        LocalDateTime toDT = parseDate(to);

        return new Event(name, fromDT, toDT);
    }

    public static LocalDateTime parseDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(input, formatter);
    }

    public static String printDate(LocalDateTime input) {
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        String month = input.format(monthFormatter);
        String time = input.format(timeFormatter);
        return input.getDayOfMonth() + " " + month + " " + input.getYear() + " " + time;
    }
}
