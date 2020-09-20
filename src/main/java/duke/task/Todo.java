package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
