package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime dueDate;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");

    public Deadline(String taskDescription, LocalDateTime dueDate) {
        super(taskDescription);
        this.dueDate = dueDate;
    }

    /**
     * @return the due date of the Deadline task
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(formatter) + ")";
    }
}
