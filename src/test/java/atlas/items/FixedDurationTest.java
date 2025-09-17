package atlas.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FixedDurationTest {
    // Code generated from Claude on 17/09/2025
    // Prompt: Can you help me make comprehensive test cases for all the features of the app. Make sure you
    // use the exact same format as the test files that I already have.
    // Reviewed and tested by author.

    @Test
    public void testFixedDurationConstructor_withNameAndDuration() {
        FixedDuration task = new FixedDuration("Study for exam", 3);
        assertEquals("Study for exam", task.getName());
        assertFalse(task.getIsDone());
        assertEquals("[F] [ ] Study for exam (duration: 3 hours)", task.toString());
    }

    @Test
    public void testFixedDurationConstructor_withStatusNameAndDuration() {
        FixedDuration task = new FixedDuration(true, "Study for exam", 3);
        assertEquals("Study for exam", task.getName());
        assertTrue(task.getIsDone());
        assertEquals("[F] [X] Study for exam (duration: 3 hours)", task.toString());
    }

    @Test
    public void testFixedDurationConstructor_withStatusFalse() {
        FixedDuration task = new FixedDuration(false, "Study for exam", 3);
        assertEquals("Study for exam", task.getName());
        assertFalse(task.getIsDone());
        assertEquals("[F] [ ] Study for exam (duration: 3 hours)", task.toString());
    }

    @Test
    public void testMarkAsDone() {
        FixedDuration task = new FixedDuration("Study for exam", 3);
        assertFalse(task.getIsDone());
        task.markAsDone();
        assertTrue(task.getIsDone());
        assertEquals("[F] [X] Study for exam (duration: 3 hours)", task.toString());
    }

    @Test
    public void testMarkAsNotDone() {
        FixedDuration task = new FixedDuration(true, "Study for exam", 3);
        assertTrue(task.getIsDone());
        task.markAsNotDone();
        assertFalse(task.getIsDone());
        assertEquals("[F] [ ] Study for exam (duration: 3 hours)", task.toString());
    }

    @Test
    public void testFileFormat_notDone() {
        FixedDuration task = new FixedDuration("Study for exam", 3);
        assertEquals("F | false | Study for exam | 3", task.fileFormat());
    }

    @Test
    public void testFileFormat_done() {
        FixedDuration task = new FixedDuration(true, "Study for exam", 3);
        assertEquals("F | true | Study for exam | 3", task.fileFormat());
    }

    @Test
    public void testToString_notDone() {
        FixedDuration task = new FixedDuration("Study for exam", 3);
        assertEquals("[F] [ ] Study for exam (duration: 3 hours)", task.toString());
    }

    @Test
    public void testToString_done() {
        FixedDuration task = new FixedDuration(true, "Study for exam", 3);
        assertEquals("[F] [X] Study for exam (duration: 3 hours)", task.toString());
    }

    @Test
    public void testFixedDurationWithSingleHour() {
        FixedDuration task = new FixedDuration("Quick task", 1);
        assertEquals("Quick task", task.getName());
        assertEquals("F | false | Quick task | 1", task.fileFormat());
        assertEquals("[F] [ ] Quick task (duration: 1 hours)", task.toString());
    }

    @Test
    public void testFixedDurationWithLargeDuration() {
        FixedDuration task = new FixedDuration("Big project", 100);
        assertEquals("Big project", task.getName());
        assertEquals("F | false | Big project | 100", task.fileFormat());
        assertEquals("[F] [ ] Big project (duration: 100 hours)", task.toString());
    }

    @Test
    public void testFixedDurationWithSpecialCharacters() {
        FixedDuration task = new FixedDuration("Task | with | special chars", 5);
        assertEquals("Task | with | special chars", task.getName());
        assertEquals("F | false | Task | with | special chars | 5", task.fileFormat());
        assertEquals("[F] [ ] Task | with | special chars (duration: 5 hours)", task.toString());
    }

    @Test
    public void testFixedDurationWithMinimalDuration() {
        FixedDuration task = new FixedDuration("Short meeting", 1);
        assertEquals("Short meeting", task.getName());
        assertEquals("F | false | Short meeting | 1", task.fileFormat());
        assertEquals("[F] [ ] Short meeting (duration: 1 hours)", task.toString());
    }

    @Test
    public void testFixedDurationWithMultipleDigitDuration() {
        FixedDuration task = new FixedDuration("Marathon coding", 24);
        assertEquals("Marathon coding", task.getName());
        assertEquals("F | false | Marathon coding | 24", task.fileFormat());
        assertEquals("[F] [ ] Marathon coding (duration: 24 hours)", task.toString());
    }

    @Test
    public void testFixedDurationWithEmptyName() {
        FixedDuration task = new FixedDuration("", 2);
        assertEquals("", task.getName());
        assertEquals("F | false |  | 2", task.fileFormat());
        assertEquals("[F] [ ]  (duration: 2 hours)", task.toString());
    }

    @Test
    public void testFixedDurationWithWhitespaceInName() {
        FixedDuration task = new FixedDuration("   Task with spaces   ", 4);
        assertEquals("   Task with spaces   ", task.getName());
        assertEquals("F | false |    Task with spaces    | 4", task.fileFormat());
        assertEquals("[F] [ ]    Task with spaces    (duration: 4 hours)", task.toString());
    }
}