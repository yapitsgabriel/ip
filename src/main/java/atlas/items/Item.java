package atlas.items;

public abstract class Item {
    private String name;
    private boolean isDone;

    public Item(String name) {
        this.isDone = false;
        this.name = name;
    }

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
