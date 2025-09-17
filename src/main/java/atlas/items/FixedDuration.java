package atlas.items;

/**
 * Represents a fixed duration item.
 */
public class FixedDuration extends Item {
    private int duration;

    /**
     * Constructor that only takes in name and duration.
     *
     * @param name Name of item.
     * @param duration Duration of item
     */

    public FixedDuration(String name, int duration) {
        super(name);
        this.duration = duration;
    }

    /**
     * Constructor that  takes in name, duration, and whether the item is completed.
     *
     * @param isDone Completion status of item.
     * @param name Name of item.
     * @param duration Duration of item
     */
    public FixedDuration(boolean isDone, String name, int duration) {
        super(isDone, name);
        this.duration = duration;
    }

    /**
     * Converts the item to the format for the file for saving.
     *
     * @return File-formatted text string
     */
    @Override
    public String fileFormat() {
        return "F | " + getIsDone() + " | " + getName() + " | " + duration;
    }

    @Override
    public String toString() {
        return "[F] " + super.toString() + " (duration: " + duration + " hours)";
    }
}
