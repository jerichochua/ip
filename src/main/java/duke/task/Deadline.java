package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate dueDate;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");

    public Deadline(String taskDescription, LocalDate dueDate) {
        super(taskDescription);
        this.dueDate = dueDate;
    }

    /**
     * @return the due date of the Deadline task
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(formatter) + ")";
    }
}
