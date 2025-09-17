package atlas.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testEventConstructor_withNameAndDates() {
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event("Meeting", from, to);
        assertEquals("Meeting", event.getName());
        assertFalse(event.getIsDone());
        assertEquals("[E] [ ] Meeting (from: 1 Dec 2025 12:34 pm to: 1 Dec 2025 02:34 pm)", event.toString());
    }

    @Test
    public void testEventConstructor_withStatusNameAndDates() {
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event(true, "Meeting", from, to);
        assertEquals("Meeting", event.getName());
        assertTrue(event.getIsDone());
        assertEquals("[E] [X] Meeting (from: 1 Dec 2025 12:34 pm to: 1 Dec 2025 02:34 pm)", event.toString());
    }

    @Test
    public void testEventConstructor_withStatusFalse() {
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event(false, "Meeting", from, to);
        assertEquals("Meeting", event.getName());
        assertFalse(event.getIsDone());
        assertEquals("[E] [ ] Meeting (from: 1 Dec 2025 12:34 pm to: 1 Dec 2025 02:34 pm)", event.toString());
    }

    @Test
    public void testMarkAsDone() {
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event("Meeting", from, to);
        assertFalse(event.getIsDone());
        event.markAsDone();
        assertTrue(event.getIsDone());
        assertEquals("[E] [X] Meeting (from: 1 Dec 2025 12:34 pm to: 1 Dec 2025 02:34 pm)", event.toString());
    }

    @Test
    public void testMarkAsNotDone() {
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event(true, "Meeting", from, to);
        assertTrue(event.getIsDone());
        event.markAsNotDone();
        assertFalse(event.getIsDone());
        assertEquals("[E] [ ] Meeting (from: 1 Dec 2025 12:34 pm to: 1 Dec 2025 02:34 pm)", event.toString());
    }

    @Test
    public void testFileFormat_notDone() {
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event("Meeting", from, to);
        assertEquals("E | false | Meeting | 01/12/2025 12:34 | 01/12/2025 14:34", event.fileFormat());
    }

    @Test
    public void testFileFormat_done() {
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event(true, "Meeting", from, to);
        assertEquals("E | true | Meeting | 01/12/2025 12:34 | 01/12/2025 14:34", event.fileFormat());
    }

    @Test
    public void testToString_notDone() {
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event("Meeting", from, to);
        assertEquals("[E] [ ] Meeting (from: 1 Dec 2025 12:34 pm to: 1 Dec 2025 02:34 pm)", event.toString());
    }

    @Test
    public void testToString_done() {
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event(true, "Meeting", from, to);
        assertEquals("[E] [X] Meeting (from: 1 Dec 2025 12:34 pm to: 1 Dec 2025 02:34 pm)", event.toString());
    }

    @Test
    public void testEventWithDifferentDates() {
        LocalDateTime from = LocalDateTime.of(2025, 6, 15, 9, 0);
        LocalDateTime to = LocalDateTime.of(2025, 6, 17, 17, 30);
        Event event = new Event("Conference", from, to);
        assertEquals("Conference", event.getName());
        assertEquals("E | false | Conference | 15/06/2025 09:00 | 17/06/2025 17:30", event.fileFormat());
        assertEquals("[E] [ ] Conference (from: 15 Jun 2025 09:00 am to: 17 Jun 2025 05:30 pm)", event.toString());
    }

    @Test
    public void testEventWithMorningTime() {
        LocalDateTime from = LocalDateTime.of(2025, 1, 1, 8, 0);
        LocalDateTime to = LocalDateTime.of(2025, 1, 1, 10, 0);
        Event event = new Event("Morning meeting", from, to);
        assertEquals("Morning meeting", event.getName());
        assertEquals("E | false | Morning meeting | 01/01/2025 08:00 | 01/01/2025 10:00", event.fileFormat());
        assertEquals("[E] [ ] Morning meeting (from: 1 Jan 2025 08:00 am to: 1 Jan 2025 10:00 am)", event.toString());
    }

    @Test
    public void testEventWithEveningTime() {
        LocalDateTime from = LocalDateTime.of(2025, 12, 31, 22, 0);
        LocalDateTime to = LocalDateTime.of(2026, 1, 1, 2, 0);
        Event event = new Event("New Year party", from, to);
        assertEquals("New Year party", event.getName());
        assertEquals("E | false | New Year party | 31/12/2025 22:00 | 01/01/2026 02:00", event.fileFormat());
        assertEquals("[E] [ ] New Year party (from: 31 Dec 2025 10:00 pm to: 1 Jan 2026 02:00 am)", event.toString());
    }

    @Test
    public void testEventWithSpecialCharacters() {
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event("Meeting | with | special chars", from, to);
        assertEquals("Meeting | with | special chars", event.getName());
        assertEquals("E | false | Meeting | with | special chars | 01/12/2025 12:34 | 01/12/2025 14:34", event.fileFormat());
        assertEquals("[E] [ ] Meeting | with | special chars (from: 1 Dec 2025 12:34 pm to: 1 Dec 2025 02:34 pm)", event.toString());
    }

    @Test
    public void testEventWithSingleDigitDateTime() {
        LocalDateTime from = LocalDateTime.of(2025, 5, 5, 5, 5);
        LocalDateTime to = LocalDateTime.of(2025, 5, 5, 6, 6);
        Event event = new Event("Early meeting", from, to);
        assertEquals("E | false | Early meeting | 05/05/2025 05:05 | 05/05/2025 06:06", event.fileFormat());
        assertEquals("[E] [ ] Early meeting (from: 5 May 2025 05:05 am to: 5 May 2025 06:06 am)", event.toString());
    }

    @Test
    public void testEventSpanningMultipleDays() {
        LocalDateTime from = LocalDateTime.of(2025, 7, 1, 9, 0);
        LocalDateTime to = LocalDateTime.of(2025, 7, 5, 17, 0);
        Event event = new Event("Summer workshop", from, to);
        assertEquals("Summer workshop", event.getName());
        assertEquals("E | false | Summer workshop | 01/07/2025 09:00 | 05/07/2025 17:00", event.fileFormat());
        assertEquals("[E] [ ] Summer workshop (from: 1 Jul 2025 09:00 am to: 5 Jul 2025 05:00 pm)", event.toString());
    }
}