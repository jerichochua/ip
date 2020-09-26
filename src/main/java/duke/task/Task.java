package duke.task;

/**
 * Represents a generic task.
 */
public abstract class Task {
    private static final String MESSAGE_FORMAT = "[%s] %s";
    protected String taskDescription;
    protected boolean isDone;

    /**
     * Initializes the Task object, when a description is provided.
     *
     * @param taskDescription description of the task
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * @return description of the task
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns the status of the task.
     *
     * @return True if the task is done, False otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * @return "Y" if the task is done, "N" otherwise
     */
    public String getStatus() {
        return (isDone ? "Y" : "N");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * @return string representation of the Task object
     */
    @Override
    public String toString() {
        return String.format(MESSAGE_FORMAT, getStatus(), taskDescription);
    }
}
