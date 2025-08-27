import java.time.LocalDateTime;

public class Event extends Item {
    protected LocalDateTime from;
    protected LocalDateTime to;

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
        return "T | " + this.isDone + " | " + this.name + " | " + this.from + " | " + this.to.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.printDate(from) + " to: " + Parser.printDate(to) + ")";
    }
}
