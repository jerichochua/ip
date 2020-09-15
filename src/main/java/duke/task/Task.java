package duke.task;

public abstract class Task {
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

    public String getStatus() {
        return (isDone ? "Y" : "N");
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), taskDescription);
    }
}
