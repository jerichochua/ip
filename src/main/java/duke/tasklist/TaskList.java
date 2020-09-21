package duke.tasklist;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

/**
 * Handles the task list.
 */
public class TaskList {
    public static final int MAX_SIZE = 100;
    private final ArrayList<Task> userTasks;
    private int userTasksCount;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        this(new ArrayList<>(MAX_SIZE));
    }

    /**
     * Initializes a new task list and the task count from an ArrayList of tasks.
     *
     * @param userTasks an ArrayList containing tasks
     */
    public TaskList(ArrayList<Task> userTasks) {
        this.userTasks = userTasks;
        userTasksCount = userTasks.size();
    }

    /**
     * Returns the task at the specified index of this list.
     *
     * @param index index of the task in this task list to return
     * @return a Task object at the specified index of this list
     */
    public Task getTask(int index) {
        return userTasks.get(index);
    }

    /**
     * Returns the number of tasks in this task list.
     *
     * @return the number of tasks in this task list
     */
    public int getUserTasksCount() {
        return userTasksCount;
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param description the description of the todo task
     * @return a Task object containing the todo task
     * @throws EmptyDescriptionException if no description is provided by the user
     */
    public Task addTodo(String description) throws EmptyDescriptionException {
        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        }

        Task todo = new Todo(description);
        userTasks.add(todo);
        userTasksCount++;

        return todo;
    }

    /**
     * Adds a deadline task to this task list.
     *
     * @param arguments the description and date/time of the deadline task
     * @return a Task object containing the deadline task
     * @throws EmptyDescriptionException if no description or date/time is provided by the user
     */
    public Task addDeadline(String[] arguments) throws EmptyDescriptionException {
        if (arguments.length != 2) {
            throw new EmptyDescriptionException();
        }

        Task deadline = new Deadline(arguments[0], arguments[1]);
        userTasks.add(deadline);
        userTasksCount++;

        return deadline;
    }

    /**
     * Adds an event task to this task list.
     *
     * @param arguments the description and date/time of the event task
     * @return a Task object containing the event task
     * @throws EmptyDescriptionException if no description or date/time is provided by the user
     */
    public Task addEvent(String[] arguments) throws EmptyDescriptionException {
        if (arguments.length != 2) {
            throw new EmptyDescriptionException();
        }

        Task event = new Event(arguments[0], arguments[1]);
        userTasks.add(event);
        userTasksCount++;

        return event;
    }

    /**
     * Marks a task in this task list as done.
     *
     * @param taskNumber the index of the task in this task list to mark as done
     * @return the Task object at the specified task number in this task list that is marked as done
     * @throws DukeException if the number provided by the user is invalid
     */
    public Task markTaskAsDone(int taskNumber) throws DukeException {
        if (taskNumber > userTasksCount || taskNumber < 1) {
            throw new DukeException();
        }

        userTasks.get(taskNumber - 1).markAsDone();
        return userTasks.get(taskNumber - 1);
    }

    /**
     * Deletes a task in this task list.
     *
     * @param taskNumber the index of the task in this task list to delete
     * @return the Task object at the specified task number in this task list that is deleted
     * @throws DukeException if the number provided by the user is invalid
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        if (taskNumber > userTasksCount || taskNumber < 1) {
            throw new DukeException();
        }

        Task removedTask = userTasks.remove(taskNumber - 1);
        userTasksCount--;

        return removedTask;
    }

    public ArrayList<Task> filterByKeywords(String keywords) {
        ArrayList<Task> filteredTasks = (ArrayList<Task>) userTasks.stream()
                .filter((s) -> s.getTaskDescription().contains(keywords))
                .collect(toList());

        return filteredTasks;
    }
}
