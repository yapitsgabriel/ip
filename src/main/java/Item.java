public abstract class Item {
    protected String name;
    protected int isDone;

    public Item (String name) {
        this.isDone = 0;
        this.name = name;
    }

    public Item(int isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    public void markAsDone() {
            this.isDone = 1;
    }

    public void markAsNotDone() {
        this.isDone = 0;
    }

    public int checkDone() {
        return this.isDone;
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
