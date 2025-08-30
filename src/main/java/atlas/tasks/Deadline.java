package atlas.tasks;

import atlas.utilities.Parser;
import java.time.LocalDateTime;

public class Deadline extends Item {
    protected LocalDateTime by;
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    public Deadline(int isDone, String name, LocalDateTime by) {
        super(isDone, name);
        this.by = by;
    }

    @Override
    public String fileFormat() {
        return "T | " + this.isDone + " | " + this.name + " | " + this.by.toString();
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + Parser.printDate(by) + ")";
    }
}
