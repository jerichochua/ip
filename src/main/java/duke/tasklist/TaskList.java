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

    public Task addTodo(String description) throws EmptyDescriptionException {
        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        }

        Task todo = new Todo(description);
        userTasks.add(todo);
        userTasksCount++;

        return todo;
    }

    public Task addDeadline(String[] arguments) throws EmptyDescriptionException {
        if (arguments.length != 2) {
            throw new EmptyDescriptionException();
        }

        Task deadline = new Deadline(arguments[0], arguments[1]);
        userTasks.add(deadline);
        userTasksCount++;

        return deadline;
    }

    public Task addEvent(String[] arguments) throws EmptyDescriptionException {
        if (arguments.length != 2) {
            throw new EmptyDescriptionException();
        }

        Task event = new Event(arguments[0], arguments[1]);
        userTasks.add(event);
        userTasksCount++;

        return event;
    }

    public Task markTaskAsDone(int taskNumber) throws DukeException {
        if (taskNumber > userTasksCount || taskNumber < 1) {
            throw new DukeException();
        }

        userTasks.get(taskNumber - 1).markAsDone();
        return userTasks.get(taskNumber - 1);
    }

    public Task deleteTask(int taskNumber) throws DukeException {
        if (taskNumber > userTasksCount || taskNumber < 1) {
            throw new DukeException();
        }

        Task removedTask = userTasks.remove(taskNumber - 1);
        userTasksCount--;

        return removedTask;
    }
}
