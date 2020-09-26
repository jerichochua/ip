package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public static final String FILE_FORMAT = "T | %d | %s" + System.lineSeparator();
    private static final String MESSAGE_FORMAT = "[T]%s";

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return String.format(MESSAGE_FORMAT, super.toString());
    }
}
