public class Event extends Task {
    protected String eventAt;

    public Event(String taskDescription, String eventAt) {
        super(taskDescription);
        this.eventAt = eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt + ")";
    }
}
