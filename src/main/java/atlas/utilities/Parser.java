package atlas.utilities;

import atlas.commands.ByeCommand;
import atlas.commands.Command;
import atlas.commands.DeleteCommand;
import atlas.commands.HelpCommand;
import atlas.commands.ListCommand;
import atlas.commands.MarkCommand;
import atlas.commands.NewDeadlineCommand;
import atlas.commands.NewEventCommand;
import atlas.commands.NewTodoCommand;
import atlas.commands.UnmarkCommand;
import atlas.exceptions.EmptyTaskNameException;
import atlas.exceptions.InvalidDateFormatException;
import atlas.exceptions.InvalidDateRangeException;
import atlas.exceptions.InvalidFormatDeadlineException;
import atlas.exceptions.InvalidFormatEventException;
import atlas.exceptions.PastDateException;
import atlas.tasks.Deadline;
import atlas.tasks.Event;
import atlas.tasks.Item;
import atlas.tasks.Todo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Pattern;

public class Parser {

    public static Command parseCommand(String input) {
        input = input.trim();
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.matches("^mark \\d+$")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            return new MarkCommand(index);
        } else if (input.matches("^unmark \\d+$")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            return new UnmarkCommand(index);
        } else if (input.matches("^delete \\d+$")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            return new DeleteCommand(index);
        } else if (input.startsWith("todo")) {
            return new NewTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new NewDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new NewEventCommand(input);
        } else {
            return new HelpCommand();
        }
    }

    public static Item parseTodo(String input) throws EmptyTaskNameException {
        if (input.length() < 6) {
            throw new EmptyTaskNameException();
        }
        String name = input.substring(5).trim();

        if (name.isEmpty()) {
            throw new EmptyTaskNameException();
        }

        return new Todo(name);
    }

    public static Item parseDeadline(String input) throws EmptyTaskNameException, InvalidFormatDeadlineException, InvalidDateFormatException, PastDateException {
        if (input.length() < 10) {
            throw new EmptyTaskNameException();
        } else if (!input.contains(" /by ")) {
            throw new InvalidFormatDeadlineException();
        }

        // Get name
        String[] parts = input.split(Pattern.quote(" /by "));
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        String name = parts[0];
        String byText = parts[1];

        if (name.isEmpty() || byText.isEmpty()) {
            throw new InvalidFormatDeadlineException();
        }

        LocalDateTime by = parseDate(byText);

        return new Deadline(name, by);
    }

    public static Item parseEvent(String input) throws EmptyTaskNameException, InvalidFormatEventException, InvalidDateFormatException, PastDateException, InvalidDateRangeException {
        if (input.length() < 7) {
            throw new EmptyTaskNameException();
        } else if (!(input.contains(" /from ") && input.contains(" /to "))) {
            throw new InvalidFormatEventException();
        }

        // Get name
        String[] parts = input.split(Pattern.quote(" /from "));
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        String name = parts[0];
        String[] parts2 = parts[1].split(Pattern.quote(" /to "));
        for (int i = 0; i < parts.length; i++) {
            parts2[i] = parts2[i].trim();
        }
        String fromText = parts2[0];
        String toText = parts2[1];

        if (name.isEmpty() || fromText.isEmpty() | toText.isEmpty()) {
            throw new InvalidFormatEventException();
        }

        LocalDateTime from = parseDate(fromText);
        LocalDateTime to = parseDate(toText);

        if (from.isAfter(to)) {
            throw new InvalidDateRangeException();
        }

        return new Event(name, from, to);
    }

    public static LocalDateTime parseDate(String input) throws InvalidDateFormatException, PastDateException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(input, formatter);
            if (localDateTime.isBefore(LocalDateTime.now())) {
                throw new PastDateException();
            }
            return localDateTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    public static String printDate(LocalDateTime input) {
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        String month = input.format(monthFormatter);
        String time = input.format(timeFormatter);
        return input.getDayOfMonth() + " " + month + " " + input.getYear() + " " + time;
    }
}
