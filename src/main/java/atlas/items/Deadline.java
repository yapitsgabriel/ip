package atlas.items;

import java.time.LocalDateTime;

import atlas.utilities.Parser;

/**
 * Represents a deadline item.
 */
public class Deadline extends Item {
    private LocalDateTime by;

    /**
     * Constructor that only takes in name and by date.
     *
     * @param name Name of item.
     * @param by Deadline of item.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Constructor that  takes in name, by date, and whether the item is completed.
     *
     * @param isDone Completion status of item.
     * @param name Name of item.
     * @param by Deadline of item.
     */
    public Deadline(boolean isDone, String name, LocalDateTime by) {
        super(isDone, name);
        this.by = by;
    }

    /**
     * Converts the item to the format for the file for saving.
     *
     * @return File-formatted text string
     */
    @Override
    public String fileFormat() {
        String date = Parser.formatDateForFile(by);
        return "D | " + getIsDone() + " | " + getName() + " | " + date;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + Parser.outputDate(by) + ")";
    }
}
