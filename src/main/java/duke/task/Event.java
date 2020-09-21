package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDateTime eventAt;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");

    public Event(String taskDescription, LocalDateTime eventAt) {
        super(taskDescription);
        this.eventAt = eventAt;
    }

    /**
     * @return the date/time of the Event task
     */
    public LocalDateTime getEventAt() {
        return eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt.format(formatter) + ")";
    }
}
