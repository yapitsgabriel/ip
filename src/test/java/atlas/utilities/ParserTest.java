package atlas.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import atlas.exceptions.CorruptedFileException;
import atlas.exceptions.EmptyTaskNameException;
import atlas.exceptions.InvalidDateFormatException;
import atlas.exceptions.InvalidDateRangeException;
import atlas.exceptions.InvalidDurationException;
import atlas.exceptions.InvalidFormatDeadlineException;
import atlas.exceptions.InvalidFormatEventException;
import atlas.exceptions.InvalidFormatFixedDurationException;
import atlas.exceptions.InvalidTaskNumberException;
import atlas.exceptions.MissingTaskNumberException;
import atlas.exceptions.PastDateException;
import atlas.items.Deadline;
import atlas.items.Event;
import atlas.items.FixedDuration;
import atlas.items.Item;
import atlas.items.Todo;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

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

    // FixedDuration Parser Tests
    @Test
    public void testParseFixedDuration_validInput() throws EmptyTaskNameException, InvalidFormatFixedDurationException, InvalidDurationException {
        String input = "fixedDuration Study /duration 3";
        Item item = Parser.parseFixedDuration(input);
        assertInstanceOf(FixedDuration.class, item);
        assertEquals("[F] [ ] Study (duration: 3 hours)", item.toString());
    }

    @Test
    public void testParseFixedDuration_emptyTaskName_exceptionThrown() {
        String input = "fixedDuration";
        assertThrows(EmptyTaskNameException.class, () -> {
            Parser.parseFixedDuration(input);
        });
    }

    @Test
    public void testParseFixedDuration_invalidFormat_exceptionThrown() {
        String input = "fixedDuration Study without duration";
        assertThrows(InvalidFormatFixedDurationException.class, () -> {
            Parser.parseFixedDuration(input);
        });
    }

    @Test
    public void testParseFixedDuration_invalidDuration_exceptionThrown() {
        String input = "fixedDuration Study /duration abc";
        assertThrows(InvalidDurationException.class, () -> {
            Parser.parseFixedDuration(input);
        });
    }

    @Test
    public void testParseFixedDuration_zeroDuration_exceptionThrown() {
        String input = "fixedDuration Study /duration 0";
        assertThrows(InvalidDurationException.class, () -> {
            Parser.parseFixedDuration(input);
        });
    }

    @Test
    public void testParseFixedDuration_negativeDuration_exceptionThrown() {
        String input = "fixedDuration Study /duration -5";
        assertThrows(InvalidDurationException.class, () -> {
            Parser.parseFixedDuration(input);
        });
    }

    // Date Parser Tests
    @Test
    public void testParseDate_validInput() throws InvalidDateFormatException, PastDateException {
        String input = "01/12/2025 12:34";
        LocalDateTime result = Parser.parseDate(input);
        assertEquals(2025, result.getYear());
        assertEquals(12, result.getMonthValue());
        assertEquals(1, result.getDayOfMonth());
        assertEquals(12, result.getHour());
        assertEquals(34, result.getMinute());
    }

    @Test
    public void testParseDate_invalidFormat_exceptionThrown() {
        String input = "2025-12-01 12:34";
        assertThrows(InvalidDateFormatException.class, () -> {
            Parser.parseDate(input);
        });
    }

    @Test
    public void testParseDate_pastDate_exceptionThrown() {
        String input = "01/01/2020 12:34";
        assertThrows(PastDateException.class, () -> {
            Parser.parseDate(input);
        });
    }

    // Output Date Tests
    @Test
    public void testOutputDate() {
        LocalDateTime input = LocalDateTime.of(2025, 12, 1, 12, 34);
        String result = Parser.outputDate(input);
        assertEquals("1 Dec 2025 12:34 pm", result);
    }

    @Test
    public void testOutputDate_morningTime() {
        LocalDateTime input = LocalDateTime.of(2025, 6, 15, 9, 5);
        String result = Parser.outputDate(input);
        assertEquals("15 Jun 2025 09:05 am", result);
    }

    // IsDone Parser Tests
    @Test
    public void testParseIsDone_true() throws CorruptedFileException {
        assertEquals(true, Parser.parseIsDone("true"));
    }

    @Test
    public void testParseIsDone_false() throws CorruptedFileException {
        assertEquals(false, Parser.parseIsDone("false"));
    }

    @Test
    public void testParseIsDone_invalidInput_exceptionThrown() {
        assertThrows(CorruptedFileException.class, () -> {
            Parser.parseIsDone("yes");
        });
    }

    // Mark/Unmark/Delete Parser Tests
    @Test
    public void testParseMarkAsDone_validInput() throws InvalidTaskNumberException, MissingTaskNumberException {
        int result = Parser.parseMarkAsDone("mark 3", 5);
        assertEquals(2, result); // 0-indexed
    }

    @Test
    public void testParseMarkAsDone_invalidNumber_exceptionThrown() {
        assertThrows(InvalidTaskNumberException.class, () -> {
            Parser.parseMarkAsDone("mark 6", 5);
        });
    }

    @Test
    public void testParseMarkAsDone_missingNumber_exceptionThrown() {
        assertThrows(MissingTaskNumberException.class, () -> {
            Parser.parseMarkAsDone("mark", 5);
        });
    }

    @Test
    public void testParseMarkAsNotDone_validInput() throws InvalidTaskNumberException, MissingTaskNumberException {
        int result = Parser.parseMarkAsNotDone("unmark 2", 5);
        assertEquals(1, result); // 0-indexed
    }

    @Test
    public void testParseDelete_validInput() throws InvalidTaskNumberException, MissingTaskNumberException {
        int result = Parser.parseDelete("delete 1", 5);
        assertEquals(0, result); // 0-indexed
    }

    @Test
    public void testParseDelete_zeroIndex_exceptionThrown() {
        assertThrows(InvalidTaskNumberException.class, () -> {
            Parser.parseDelete("delete 0", 5);
        });
    }

    // Find Parser Tests
    @Test
    public void testParseFind_validInput() throws EmptyTaskNameException {
        String result = Parser.parseFind("find book", 5);
        assertEquals("book", result);
    }

    @Test
    public void testParseFind_emptyKeyword_exceptionThrown() {
        assertThrows(EmptyTaskNameException.class, () -> {
            Parser.parseFind("find", 5);
        });
    }

    // Duration Parser Tests
    @Test
    public void testParseDuration_validInput() throws InvalidDurationException {
        int result = Parser.parseDuration("5");
        assertEquals(5, result);
    }

    @Test
    public void testParseDuration_invalidInput_exceptionThrown() {
        assertThrows(InvalidDurationException.class, () -> {
            Parser.parseDuration("abc");
        });
    }

    @Test
    public void testParseDuration_zeroDuration_exceptionThrown() {
        assertThrows(InvalidDurationException.class, () -> {
            Parser.parseDuration("0");
        });
    }
}
