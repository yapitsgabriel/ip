package atlas.items;

public abstract class Item {
    private String name;
    private int isDone;

    public Item(String name) {
        this.isDone = 0;
        this.name = name;
    }

    public Item(int isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getIsDone() {
        return isDone;
    }

    public void markAsDone() {
            isDone = 1;
    }

    public void markAsNotDone() {
        isDone = 0;
    }

    public abstract String fileFormat();

    @Override
    public String toString() {
        if (isDone == 1) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
