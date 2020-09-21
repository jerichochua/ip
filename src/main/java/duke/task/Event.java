package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDate eventAt;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");

    public Event(String taskDescription, LocalDate eventAt) {
        super(taskDescription);
        this.eventAt = eventAt;
    }

    /**
     * @return the date/time of the Event task
     */
    public LocalDate getEventAt() {
        return eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt.format(formatter) + ")";
    }
}
