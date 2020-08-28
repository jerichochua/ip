public class Event extends Task {
    protected String at;

    public Event(String taskDescription, String at) {
        super(taskDescription);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
