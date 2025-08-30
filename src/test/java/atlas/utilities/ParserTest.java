package atlas.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseTodo_validInput() throws EmptyTaskNameException {
        String input = "todo Read book";
        Item item = Parser.parseTodo(input);
        assertInstanceOf(Todo.class, item);
        assertEquals("[T] [ ] Read book", item.toString());
    }

    @Test
    public void testParseTodo_emptyTaskName_exceptionThrown() {
        String input = "todo   ";
        assertThrows(EmptyTaskNameException.class, () -> {
            Parser.parseTodo(input);
        });
    }

    @Test
    public void testParseDeadline_validInput()
        throws EmptyTaskNameException, InvalidDateFormatException, InvalidFormatDeadlineException, PastDateException {
        String input = "deadline Read book /by 01/12/2025 12:34";
        Item item = Parser.parseDeadline(input);
        assertInstanceOf(Deadline.class, item);
        assertEquals("[D] [ ] Read book (by: 1 Dec 2025 12:34 pm)", item.toString());
    }

    @Test
    public void testParseDeadline_emptyTaskName_exceptionThrown() {
        String input = "deadline";
        assertThrows(EmptyTaskNameException.class, () -> {
            Parser.parseDeadline(input);
        });
    }

    @Test
    public void testParseDeadline_invalidFormatDeadline_exceptionThrown() {
        String input = "deadline   ";
        assertThrows(InvalidFormatDeadlineException.class, () -> {
            Parser.parseDeadline(input);
        });
    }

    @Test
    public void testParseDeadline_invalidDateFormat_exceptionThrown() {
        String input = "deadline Read book /by 01122025 1234";
        assertThrows(InvalidDateFormatException.class, () -> {
            Parser.parseDeadline(input);
        });
    }

    @Test
    public void testParseDeadline_pastDateException_exceptionThrown() {
        String input = "deadline Read book /by 01/12/2024 12:34";
        assertThrows(PastDateException.class, () -> {
            Parser.parseDeadline(input);
        });
    }

    @Test
    public void testParseEvent_validInput()
        throws EmptyTaskNameException, InvalidFormatEventException, InvalidDateFormatException, PastDateException, InvalidDateRangeException {
        String input = "event Read book /from 01/12/2025 12:34 /to 02/12/2025 12:34";
        Item item = Parser.parseEvent(input);
        assertInstanceOf(Event.class, item);
        assertEquals("[E] [ ] Read book (from: 1 Dec 2025 12:34 pm to: 2 Dec 2025 12:34 pm)", item.toString());
    }

    @Test
    public void testParseEvent_emptyTaskName_exceptionThrown() {
        String input = "event";
        assertThrows(EmptyTaskNameException.class, () -> {
            Parser.parseEvent(input);
        });
    }

    @Test
    public void testParseEvent_invalidFormatEvent_exceptionThrown() {
        String input = "event   ";
        assertThrows(InvalidFormatEventException.class, () -> {
            Parser.parseEvent(input);
        });
    }

    @Test
    public void testParseEvent_invalidDateFormat_exceptionThrown() {
        String input = "event Read book /from 01122025 1234 /to 02122025 1234";
        assertThrows(InvalidDateFormatException.class, () -> {
            Parser.parseEvent(input);
        });
    }

    @Test
    public void testParseEvent_pastDateException_exceptionThrown() {
        String input = "event Read book /from 01/12/2024 12:34 /to 01/12/2025 12:34";
        assertThrows(PastDateException.class, () -> {
            Parser.parseEvent(input);
        });
    }

    @Test
    public void testParseEvent_invalidDateRange_exceptionThrown() {
        String input = "event Read book /from 02/12/2025 12:34 /to 01/12/2025 12:34";
        assertThrows(InvalidDateRangeException.class, () -> {
            Parser.parseEvent(input);
        });
    }
}
