package duke.task;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected String eventAt;

    public Event(String taskDescription, String eventAt) {
        super(taskDescription);
        this.eventAt = eventAt;
    }

    /**
     * @return the date/time of the Event task
     */
    public String getEventAt() {
        return eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt + ")";
    }
}
