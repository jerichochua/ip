package duke.task;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String taskDescription, String dueDate) {
        super(taskDescription);
        this.dueDate = dueDate;
    }

    /**
     * @return the due date of the Deadline task
     */
    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
