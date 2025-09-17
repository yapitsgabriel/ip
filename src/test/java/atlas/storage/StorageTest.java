package atlas.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import atlas.items.Deadline;
import atlas.items.Event;
import atlas.items.FixedDuration;
import atlas.items.ItemList;
import atlas.items.Todo;
import atlas.ui.Ui;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StorageTest {
    // Code generated from Claude on 17/09/2025
    // Prompt: Can you help me make comprehensive test cases for all the features of the app. Make sure you
    // use the exact same format as the test files that I already have.
    // Reviewed and tested by author.

    private Storage storage;
    private ItemList itemList;
    private Ui ui;
    private final String testDataFile = "data/atlas_test.txt";
    private final String testTempFile = "data/atlas_temp_test.txt";

    @BeforeEach
    public void setUp() throws IOException {
        storage = new Storage();
        itemList = new ItemList();
        ui = new Ui();

        // Create test directory if it doesn't exist
        Path dataDir = Path.of("data");
        if (!Files.exists(dataDir)) {
            Files.createDirectory(dataDir);
        }

        // Clean up any existing test files
        Files.deleteIfExists(Path.of(testDataFile));
        Files.deleteIfExists(Path.of(testTempFile));
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up test files
        Files.deleteIfExists(Path.of(testDataFile));
        Files.deleteIfExists(Path.of(testTempFile));
        Files.deleteIfExists(Path.of("data/atlas.txt"));
    }

    @Test
    public void testLoad_nonExistentFile_createsNewFile() throws IOException {
        // Delete the file if it exists
        Files.deleteIfExists(Path.of("data/atlas.txt"));

        ItemList result = storage.load(itemList, ui);

        // Should create empty file and return empty list
        assertTrue(Files.exists(Path.of("data/atlas.txt")));
        assertEquals(0, result.listSize());
    }

    @Test
    public void testLoad_emptyFile() throws IOException {
        // Create empty test file
        Files.createDirectories(Path.of("data/atlas.txt").getParent());
        Files.createFile(Path.of("data/atlas.txt"));

        ItemList result = storage.load(itemList, ui);

        assertEquals(0, result.listSize());
    }

    @Test
    public void testLoad_todoItem() throws IOException {
        // Create test file with todo item
        try (FileWriter writer = new FileWriter("data/atlas.txt")) {
            writer.write("T | false | Read book\n");
        }

        ItemList result = storage.load(itemList, ui);

        assertEquals(1, result.listSize());
        assertEquals("Read book", result.getItem(0).getName());
        assertFalse(result.getItem(0).getIsDone());
        assertTrue(result.getItem(0) instanceof Todo);
    }

    @Test
    public void testLoad_completedTodoItem() throws IOException {
        // Create test file with completed todo item
        try (FileWriter writer = new FileWriter("data/atlas.txt")) {
            writer.write("T | true | Read book\n");
        }

        ItemList result = storage.load(itemList, ui);

        assertEquals(1, result.listSize());
        assertEquals("Read book", result.getItem(0).getName());
        assertTrue(result.getItem(0).getIsDone());
    }

    @Test
    public void testLoad_deadlineItem() throws IOException {
        // Create test file with deadline item
        try (FileWriter writer = new FileWriter("data/atlas.txt")) {
            writer.write("D | false | Submit report | 01/12/2025 12:34\n");
        }

        ItemList result = storage.load(itemList, ui);

        assertEquals(1, result.listSize());
        assertEquals("Submit report", result.getItem(0).getName());
        assertFalse(result.getItem(0).getIsDone());
        assertTrue(result.getItem(0) instanceof Deadline);
    }

    @Test
    public void testLoad_eventItem() throws IOException {
        // Create test file with event item
        try (FileWriter writer = new FileWriter("data/atlas.txt")) {
            writer.write("E | false | Meeting | 01/12/2025 12:34 | 01/12/2025 14:34\n");
        }

        ItemList result = storage.load(itemList, ui);

        assertEquals(1, result.listSize());
        assertEquals("Meeting", result.getItem(0).getName());
        assertFalse(result.getItem(0).getIsDone());
        assertTrue(result.getItem(0) instanceof Event);
    }

    @Test
    public void testLoad_fixedDurationItem() throws IOException {
        // Create test file with fixed duration item
        try (FileWriter writer = new FileWriter("data/atlas.txt")) {
            writer.write("F | false | Study | 3\n");
        }

        ItemList result = storage.load(itemList, ui);

        assertEquals(1, result.listSize());
        assertEquals("Study", result.getItem(0).getName());
        assertFalse(result.getItem(0).getIsDone());
        assertTrue(result.getItem(0) instanceof FixedDuration);
    }

    @Test
    public void testLoad_multipleItems() throws IOException {
        // Create test file with multiple items
        try (FileWriter writer = new FileWriter("data/atlas.txt")) {
            writer.write("T | false | Read book\n");
            writer.write("D | true | Submit report | 01/12/2025 12:34\n");
            writer.write("E | false | Meeting | 01/12/2025 12:34 | 01/12/2025 14:34\n");
            writer.write("F | false | Study | 3\n");
        }

        ItemList result = storage.load(itemList, ui);

        assertEquals(4, result.listSize());
        assertTrue(result.getItem(0) instanceof Todo);
        assertTrue(result.getItem(1) instanceof Deadline);
        assertTrue(result.getItem(2) instanceof Event);
        assertTrue(result.getItem(3) instanceof FixedDuration);

        assertFalse(result.getItem(0).getIsDone());
        assertTrue(result.getItem(1).getIsDone());
        assertFalse(result.getItem(2).getIsDone());
        assertFalse(result.getItem(3).getIsDone());
    }

    @Test
    public void testSave_emptyList() throws IOException {
        ItemList emptyList = new ItemList();
        storage.save(emptyList, ui);

        assertTrue(Files.exists(Path.of("data/atlas.txt")));
        String content = Files.readString(Path.of("data/atlas.txt"));
        assertTrue(content.isEmpty() || content.trim().isEmpty());
    }

    @Test
    public void testSave_singleTodo() throws IOException {
        Todo todo = new Todo("Read book");
        itemList.loadItem(todo);

        storage.save(itemList, ui);

        assertTrue(Files.exists(Path.of("data/atlas.txt")));
        String content = Files.readString(Path.of("data/atlas.txt"));
        assertEquals("T | false | Read book\n", content);
    }

    @Test
    public void testSave_completedTodo() throws IOException {
        Todo todo = new Todo(true, "Read book");
        itemList.loadItem(todo);

        storage.save(itemList, ui);

        String content = Files.readString(Path.of("data/atlas.txt"));
        assertEquals("T | true | Read book\n", content);
    }

    @Test
    public void testSave_deadline() throws IOException {
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline("Submit report", deadline);
        itemList.loadItem(deadlineItem);

        storage.save(itemList, ui);

        String content = Files.readString(Path.of("data/atlas.txt"));
        assertEquals("D | false | Submit report | 01/12/2025 12:34\n", content);
    }

    @Test
    public void testSave_event() throws IOException {
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event("Meeting", from, to);
        itemList.loadItem(event);

        storage.save(itemList, ui);

        String content = Files.readString(Path.of("data/atlas.txt"));
        assertEquals("E | false | Meeting | 01/12/2025 12:34 | 01/12/2025 14:34\n", content);
    }

    @Test
    public void testSave_fixedDuration() throws IOException {
        FixedDuration task = new FixedDuration("Study", 3);
        itemList.loadItem(task);

        storage.save(itemList, ui);

        String content = Files.readString(Path.of("data/atlas.txt"));
        assertEquals("F | false | Study | 3\n", content);
    }

    @Test
    public void testSave_multipleItems() throws IOException {
        Todo todo = new Todo("Read book");
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline(true, "Submit report", deadline);
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event("Meeting", from, to);
        FixedDuration task = new FixedDuration("Study", 3);

        itemList.loadItem(todo);
        itemList.loadItem(deadlineItem);
        itemList.loadItem(event);
        itemList.loadItem(task);

        storage.save(itemList, ui);

        String content = Files.readString(Path.of("data/atlas.txt"));
        String[] lines = content.split("\\n");
        assertEquals(4, lines.length);
        assertEquals("T | false | Read book", lines[0]);
        assertEquals("D | true | Submit report | 01/12/2025 12:34", lines[1]);
        assertEquals("E | false | Meeting | 01/12/2025 12:34 | 01/12/2025 14:34", lines[2]);
        assertEquals("F | false | Study | 3", lines[3]);
    }

    @Test
    public void testSaveAndLoad_roundTrip() throws IOException {
        // Create original list with various items
        Todo todo = new Todo(true, "Read book");
        LocalDateTime deadline = LocalDateTime.of(2025, 12, 1, 12, 34);
        Deadline deadlineItem = new Deadline("Submit report", deadline);
        LocalDateTime from = LocalDateTime.of(2025, 12, 1, 12, 34);
        LocalDateTime to = LocalDateTime.of(2025, 12, 1, 14, 34);
        Event event = new Event(false, "Meeting", from, to);
        FixedDuration task = new FixedDuration(true, "Study", 3);

        itemList.loadItem(todo);
        itemList.loadItem(deadlineItem);
        itemList.loadItem(event);
        itemList.loadItem(task);

        // Save the list
        storage.save(itemList, ui);

        // Load it back
        ItemList loadedList = new ItemList();
        ItemList result = storage.load(loadedList, ui);

        // Verify everything matches
        assertEquals(4, result.listSize());

        // Check todo
        assertTrue(result.getItem(0) instanceof Todo);
        assertEquals("Read book", result.getItem(0).getName());
        assertTrue(result.getItem(0).getIsDone());

        // Check deadline
        assertTrue(result.getItem(1) instanceof Deadline);
        assertEquals("Submit report", result.getItem(1).getName());
        assertFalse(result.getItem(1).getIsDone());

        // Check event
        assertTrue(result.getItem(2) instanceof Event);
        assertEquals("Meeting", result.getItem(2).getName());
        assertFalse(result.getItem(2).getIsDone());

        // Check fixed duration
        assertTrue(result.getItem(3) instanceof FixedDuration);
        assertEquals("Study", result.getItem(3).getName());
        assertTrue(result.getItem(3).getIsDone());
    }

    @Test
    public void testSave_atomicWrite() throws IOException {
        Todo todo = new Todo("Test task");
        itemList.loadItem(todo);

        // Save should use temp file then move
        storage.save(itemList, ui);

        // Verify final file exists and temp file is cleaned up
        assertTrue(Files.exists(Path.of("data/atlas.txt")));
        assertFalse(Files.exists(Path.of("data/atlas_temp.txt")));

        String content = Files.readString(Path.of("data/atlas.txt"));
        assertEquals("T | false | Test task\n", content);
    }
}