public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String printTaskAndIcon() {
        return String.format("[%s] %s", getStatusIcon(), getTaskDescription());
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
