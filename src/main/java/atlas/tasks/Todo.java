package atlas.tasks;

public class Todo extends Item {

    public Todo(String name) {
        super(name);
    }

    public Todo(int isDone, String name) {
        super(isDone, name);
    }

    @Override
    public String fileFormat() {
        return "T | " + getIsDone() + " | " + getName();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
