public class Todo {
    protected String name;
    protected boolean isDone;

    public Todo(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean checkDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[T] [X] " + name;
        } else {
            return "[T] [ ] " + name;
        }
    }
}
