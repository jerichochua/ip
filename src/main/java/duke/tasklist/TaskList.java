package duke.tasklist;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private final int MAX_SIZE = 100;
    private final ArrayList<Task> userTasks = new ArrayList<>(MAX_SIZE);
    private int userTasksCount = 0;

    public void addTask(Task task) {
        userTasks.add(task);
    }

    public Task getTask(int index) {
        return userTasks.get(index);
    }

    public int getUserTasksCount() {
        return userTasksCount;
    }

    public void setTaskStatus(String status) {
        if (status.equals("1")) {
            userTasks.get(userTasksCount).markAsDone();
        }
        userTasksCount++;
    }

    public void addTodo(String description) throws EmptyDescriptionException {
        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        }
        userTasks.add(new Todo(description));
        addTaskSuccess();
    }

    public void addDeadline(String[] arguments) {
        try {
            userTasks.add(new Deadline(arguments[0], arguments[1]));
            addTaskSuccess();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tDescription or deadline cannot be empty!");
        }
    }

    public void addEvent(String[] arguments) {
        try {
            userTasks.add(new Event(arguments[0], arguments[1]));
            addTaskSuccess();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tDescription or event date/time cannot be empty!");
        }
    }

    public void addTaskSuccess() {
        System.out.println("\tAdded: " + userTasks.get(userTasksCount));
        userTasksCount++;
    }

    public Task markTaskAsDone(int taskNumber) throws DukeException {
        if (taskNumber > userTasksCount) {
            throw new DukeException();
        }

        userTasks.get(taskNumber - 1).markAsDone();
        return userTasks.get(taskNumber - 1);
    }

    public Task deleteTask(int taskNumber) throws DukeException {
        if (taskNumber > userTasksCount) {
            throw new DukeException();
        }

        Task removedTask = userTasks.remove(taskNumber - 1);
        userTasksCount--;

        return removedTask;
    }
}
