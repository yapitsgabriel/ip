package atlas.items;

/**
 * Represents a todo item.
 */
public class Todo extends Item {

    /**
     * Constructor that only takes in name.
     *
     * @param name Name of item.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructor that  takes in name and whether the item is completed.
     *
     * @param isDone Completion status of item.
     * @param name Name of item.
     */
    public Todo(int isDone, String name) {
        super(isDone, name);
    }

    /**
     * Converts the item to the format for the file for saving.
     *
     * @return File-formatted text string
     */
    @Override
    public String fileFormat() {
        return "T | " + this.getIsDone() + " | " + this.getName();
    }


    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
