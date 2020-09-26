package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    public static final String FILE_FORMAT = "E | %d | %s | %s" + System.lineSeparator();
    private static final String MESSAGE_FORMAT = "[E]%s (at: %s)";

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
        return String.format(MESSAGE_FORMAT, super.toString(), eventAt.format(formatter));
    }
}
