package atlas.utilities;

import atlas.commands.ByeCommand;
import atlas.commands.Command;
import atlas.commands.DeleteCommand;
import atlas.commands.FindCommand;
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
import atlas.items.Deadline;
import atlas.items.Event;
import atlas.items.Item;
import atlas.items.Todo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Represents a parser.
 * Helps with translating user input into commands.
 */
public class Parser {

    /**
     * Turns a command by the user into a Command object.
     * @param input Given input by the user.
     * @return A command object.
     */
    private static final int TODO_COMMAND_LENGTH = 5;
    private static final int DEADLINE_COMMAND_LENGTH = 9;
    private static final int EVENT_COMMAND_LENGTH = 6;
    private static final int MARK_COMMAND_LENGTH = 5;
    private static final int UNMARK_COMMAND_LENGTH = 7;
    private static final int DELETE_COMMAND_LENGTH = 7;
    private static final int FIND_COMMAND_LENGTH = 5;


    private static void trimArrayElements(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
    }

    public static Command parseCommand(String input) {
        input = input.trim();
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.matches("^mark \\d+$")) {
            int index = Integer.parseInt(input.substring(MARK_COMMAND_LENGTH)) - 1;
            return new MarkCommand(index);
        } else if (input.matches("^unmark \\d+$")) {
            int index = Integer.parseInt(input.substring(UNMARK_COMMAND_LENGTH)) - 1;
            return new UnmarkCommand(index);
        } else if (input.matches("^delete \\d+$")) {
            int index = Integer.parseInt(input.substring(DELETE_COMMAND_LENGTH)) - 1;
            return new DeleteCommand(index);
        } else if (input.startsWith("todo")) {
            return new NewTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new NewDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new NewEventCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input.substring(FIND_COMMAND_LENGTH).trim());
        } else {
            return new HelpCommand();
        }
    }

    /**
     * Parses a todo command.
     *
     * @param input Given input by the user.
     * @return A new Todo.
     * @throws EmptyTaskNameException If item name is empty.
     */
    public static Item parseTodo(String input) throws EmptyTaskNameException {
        if (input.length() <= TODO_COMMAND_LENGTH) {
            throw new EmptyTaskNameException();
        }
        String name = input.substring(TODO_COMMAND_LENGTH).trim();

        if (name.isEmpty()) {
            throw new EmptyTaskNameException();
        }

        return new Todo(name);
    }

    /**
     * Parses a deadline command
     *
     * @param input Given input by user.
     * @return A new Deadline.
     * @throws EmptyTaskNameException If item name is empty.
     * @throws InvalidFormatDeadlineException If format of item is invalid.
     * @throws InvalidDateFormatException If format of date is invalid.
     * @throws PastDateException If date entered is before current date.
     */
    public static Item parseDeadline(String input) throws EmptyTaskNameException, InvalidFormatDeadlineException, InvalidDateFormatException, PastDateException {
        if (input.length() <= DEADLINE_COMMAND_LENGTH) {
            throw new EmptyTaskNameException();
        } else if (!input.contains(" /by ")) {
            throw new InvalidFormatDeadlineException();
        }

        // Get full item
        String item = input.substring(DEADLINE_COMMAND_LENGTH).trim();
        if (item.isEmpty()) {
            throw new EmptyTaskNameException();
        }

        // Get name
        String[] parts = item.split(Pattern.quote(" /by "));
        trimArrayElements(parts);

        String name = parts[0];
        String byText = parts[1];

        if (name.isEmpty() || byText.isEmpty()) {
            throw new InvalidFormatDeadlineException();
        }

        LocalDateTime by = parseDate(byText);

        return new Deadline(name, by);
    }

    /**
     * Parses an event command.
     *
     * @param input Given input by user.
     * @return A new Event.
     * @throws EmptyTaskNameException If item name is empty.
     * @throws InvalidFormatEventException If format of item is invalid.
     * @throws InvalidDateFormatException If format of date is invalid.
     * @throws PastDateException If date entered is before current date.
     * @throws InvalidDateRangeException If end date is before start date.
     */
    public static Item parseEvent(String input) throws EmptyTaskNameException, InvalidFormatEventException, InvalidDateFormatException, PastDateException, InvalidDateRangeException {
        if (input.length() <= EVENT_COMMAND_LENGTH) {
            throw new EmptyTaskNameException();
        } else if (!(input.contains(" /from ") && input.contains(" /to "))) {
            throw new InvalidFormatEventException();
        }

        // Get full item
        String item = input.substring(EVENT_COMMAND_LENGTH).trim();
        if (item.isEmpty()) {
            throw new EmptyTaskNameException();
        }

        // Get name
        String[] parts = item.split(Pattern.quote(" /from "));
        trimArrayElements(parts);
        String name = parts[0];
        String[] parts2 = parts[1].split(Pattern.quote(" /to "));
        trimArrayElements(parts2);

        String fromText = parts2[0];
        String toText = parts2[1];

        if (name.isEmpty() || fromText.isEmpty() || toText.isEmpty()) {
            throw new InvalidFormatEventException();
        }

        LocalDateTime from = parseDate(fromText);
        LocalDateTime to = parseDate(toText);

        if (from.isAfter(to)) {
            throw new InvalidDateRangeException();
        }

        return new Event(name, from, to);
    }

    /**
     * Parses a date command.
     * @param input Given input by user.
     * @return LocalDateTime object.
     * @throws InvalidDateFormatException If format of date is invalid.
     * @throws PastDateException If date entered is before current date.
     */
    public static LocalDateTime parseDate(String input) throws InvalidDateFormatException, PastDateException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(input, dateFormatter);
            if (dateTime.isBefore(LocalDateTime.now())) {
                throw new PastDateException();
            }
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    /**
     * Prints out a date in the relevant format.
     * @param input LocalDateTime input.
     * @return Formatted date string.
     */
    public static String outputDate(LocalDateTime input) {
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        String month = input.format(monthFormatter);
        String time = input.format(timeFormatter);
        return input.getDayOfMonth() + " " + month + " " + input.getYear() + " " + time;
    }
}
