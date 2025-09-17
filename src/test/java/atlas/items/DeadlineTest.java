package atlas.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    // Code generated from Claude on 17/09/2025
    // Prompt: Can you help me make comprehensive test cases for all the features of the app. Make sure you
    // use the exact same format as the test files that I already have.
    // Reviewed and tested by author.

    @Test
    public void testDeadlineConstructor_withNameAndDate() {
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline("Read book", deadline);
        assertEquals("Read book", deadlineItem.getName());
        assertFalse(deadlineItem.getIsDone());
        assertEquals("[D] [ ] Read book (by: 1 Dec 2025 12:34 pm)", deadlineItem.toString());
    }

    @Test
    public void testDeadlineConstructor_withStatusNameAndDate() {
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline(true, "Read book", deadline);
        assertEquals("Read book", deadlineItem.getName());
        assertTrue(deadlineItem.getIsDone());
        assertEquals("[D] [X] Read book (by: 1 Dec 2025 12:34 pm)", deadlineItem.toString());
    }

    @Test
    public void testDeadlineConstructor_withStatusFalse() {
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline(false, "Read book", deadline);
        assertEquals("Read book", deadlineItem.getName());
        assertFalse(deadlineItem.getIsDone());
        assertEquals("[D] [ ] Read book (by: 1 Dec 2025 12:34 pm)", deadlineItem.toString());
    }

    @Test
    public void testMarkAsDone() {
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline("Read book", deadline);
        assertFalse(deadlineItem.getIsDone());
        deadlineItem.markAsDone();
        assertTrue(deadlineItem.getIsDone());
        assertEquals("[D] [X] Read book (by: 1 Dec 2025 12:34 pm)", deadlineItem.toString());
    }

    @Test
    public void testMarkAsNotDone() {
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline(true, "Read book", deadline);
        assertTrue(deadlineItem.getIsDone());
        deadlineItem.markAsNotDone();
        assertFalse(deadlineItem.getIsDone());
        assertEquals("[D] [ ] Read book (by: 1 Dec 2025 12:34 pm)", deadlineItem.toString());
    }

    @Test
    public void testFileFormat_notDone() {
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline("Read book", deadline);
        assertEquals("D | false | Read book | 01/12/2025 12:34", deadlineItem.fileFormat());
    }

    @Test
    public void testFileFormat_done() {
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline(true, "Read book", deadline);
        assertEquals("D | true | Read book | 01/12/2025 12:34", deadlineItem.fileFormat());
    }

    @Test
    public void testToString_notDone() {
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline("Read book", deadline);
        assertEquals("[D] [ ] Read book (by: 1 Dec 2025 12:34 pm)", deadlineItem.toString());
    }

    @Test
    public void testToString_done() {
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline(true, "Read book", deadline);
        assertEquals("[D] [X] Read book (by: 1 Dec 2025 12:34 pm)", deadlineItem.toString());
    }

    @Test
    public void testDeadlineWithDifferentTime() {
        LocalDateTime deadline = LocalDateTime.of(2026, 1, 15, 23, 59);
        Deadline deadlineItem = new Deadline("Submit assignment", deadline);
        assertEquals("Submit assignment", deadlineItem.getName());
        assertEquals("D | false | Submit assignment | 15/01/2026 23:59", deadlineItem.fileFormat());
        assertEquals("[D] [ ] Submit assignment (by: 15 Jan 2026 11:59 pm)", deadlineItem.toString());
    }

    @Test
    public void testDeadlineWithMorningTime() {
        LocalDateTime deadline = LocalDateTime.of(2025, 6, 20, 9, 0);
        Deadline deadlineItem = new Deadline("Meeting", deadline);
        assertEquals("Meeting", deadlineItem.getName());
        assertEquals("D | false | Meeting | 20/06/2025 09:00", deadlineItem.fileFormat());
        assertEquals("[D] [ ] Meeting (by: 20 Jun 2025 09:00 am)", deadlineItem.toString());
    }

    @Test
    public void testDeadlineWithSpecialCharacters() {
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline("Read book | with | special chars", deadline);
        assertEquals("Read book | with | special chars", deadlineItem.getName());
        assertEquals("D | false | Read book | with | special chars | 01/12/2025 12:34", deadlineItem.fileFormat());
        assertEquals("[D] [ ] Read book | with | special chars (by: 1 Dec 2025 12:34 pm)", deadlineItem.toString());
    }

    @Test
    public void testDeadlineWithSingleDigitDateTime() {
        LocalDateTime deadline = LocalDateTime.of(2025, 5, 5, 5, 5);
        Deadline deadlineItem = new Deadline("Task", deadline);
        assertEquals("D | false | Task | 05/05/2025 05:05", deadlineItem.fileFormat());
        assertEquals("[D] [ ] Task (by: 5 May 2025 05:05 am)", deadlineItem.toString());
    }
}