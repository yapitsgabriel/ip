package atlas.items;

/**
 * Represents an abstract class Item.
 */
public abstract class Item {
    private String name;
    private boolean isDone;

    /**
     * Constructor that only takes in the name, isDone is set to false by default.
     *
     * @param name Name of task.
     */
    public Item(String name) {
        this.isDone = false;
        this.name = name;
    }

    /**
     * Constructor that takes in both the name and isDone.
     * @param isDone Whether the task is completed.
     * @param name Name of task.
     */
    public Item(boolean isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public abstract String fileFormat();

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
