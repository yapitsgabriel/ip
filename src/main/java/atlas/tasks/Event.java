package atlas.tasks;

import atlas.utilities.Parser;
import java.time.LocalDateTime;

public class Event extends Item {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public Event(int isDone, String name, LocalDateTime from, LocalDateTime to) {
        super(isDone, name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String fileFormat() {
        return "E | " + getIsDone() + " | " + getName() + " | " + from + " | " + to.toString();
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + Parser.formatDate(from) + " to: " + Parser.formatDate(to) + ")";
    }
}
