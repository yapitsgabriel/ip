package atlas.items;

import atlas.utilities.Parser;
import java.time.LocalDateTime;

/**
 * Represents a deadline item.
 */
public class Event extends Item {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor that only takes in name, from date and to date.
     *
     * @param name Name of item.
     * @param from Start date of item.
     * @param to End date of item.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor that takes in name, from date and to date and whether the item is completed.
     *
     * @param isDone Completion status of item.
     * @param name Name of item.
     * @param from Start date of item.
     * @param to End date of item.
     */
    public Event(boolean isDone, String name, LocalDateTime from, LocalDateTime to) {
        super(isDone, name);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the item to the format for the file for saving.
     *
     * @return File-formatted text string
     */
    @Override
    public String fileFormat() {
        String fromDate = String.format("%02d", from.getDayOfMonth()) + "/" + from.getMonthValue() + "/" + from.getYear() + " "
            + from.getHour() + ":" + from.getMinute();
        String toDate = String.format("%02d", to.getDayOfMonth()) + "/" + to.getMonthValue() + "/" + to.getYear() + " " + to.getHour()
            + ":" + to.getMinute();
        return "E | " + getIsDone() + " | " + getName() + " | " + fromDate + " | " + toDate;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + Parser.outputDate(from) + " to: " + Parser.outputDate(to) + ")";
    }
}
