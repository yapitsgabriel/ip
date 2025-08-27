public class Deadline extends Item {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadline(int isDone, String name, String by) {
        super(isDone, name);
        this.by = by;
    }

    @Override
    public String fileFormat() {
        return "T | " + this.isDone + " | " + this.name + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
