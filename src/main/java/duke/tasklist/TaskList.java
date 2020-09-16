package duke.tasklist;

import duke.exception.EmptyDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static final String FILE_PATH = "data/duke.txt";
    private final int MAX_SIZE = 100;
    private final ArrayList<Task> userTasks = new ArrayList<>(MAX_SIZE);
    private int userTasksCount = 0;

    public void printUserTasks() {
        System.out.println("\tHere are your tasks:");
        for (int i = 1; i <= userTasksCount; i++) {
            System.out.format("\t%d. %s\n", i, userTasks.get(i - 1));
        }
    }

    public void addTask(Task task) {
        userTasks.add(task);
    }

    public void checkTaskStatus(String status) {
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

    public void addDeadline(String arguments) {
        String[] argumentSplit = arguments.split(" /by ");
        try {
            userTasks.add(new Deadline(argumentSplit[0], argumentSplit[1]));
            addTaskSuccess();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tDescription or deadline cannot be empty!");
        }
    }

    public void addEvent(String arguments) {
        String[] argumentSplit = arguments.split(" /at ");
        try {
            userTasks.add(new Event(argumentSplit[0], argumentSplit[1]));
            addTaskSuccess();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tDescription or event date/time cannot be empty!");
        }
    }

    public void addTaskSuccess() {
        System.out.println("\tAdded: " + userTasks.get(userTasksCount));
        userTasksCount++;
        String addS = (userTasksCount > 1) ? "s" : "";
        System.out.println("\tYou now have " + userTasksCount + " task" + addS + " in your list.");
    }

    public void markTaskAsDone(int taskNumber) {
        try {
            userTasks.get(taskNumber - 1).markAsDone();
            System.out.println("\tI have marked the following task as done:");
            System.out.format("\t\t%s\n", userTasks.get(taskNumber - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tInvalid task number entered!");
        }
    }

    public void deleteTask(int taskNumber) {
        try {
            Task removedTask = userTasks.remove(taskNumber - 1);
            userTasksCount--;
            System.out.println("\tOk, I have removed this task:");
            System.out.format("\t\t%s\n", removedTask);
            String addS = (userTasksCount > 1) ? "s" : "";
            System.out.println("\tYou now have " + userTasksCount + " task" + addS + " left in your list!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tTask does not exist!");
        }
    }

    public void addTasksToFile() {
        Task task;
        int isDone;

        try {
            FileWriter file = new FileWriter(FILE_PATH);

            for (int i = 1; i <= userTasksCount; i++) {
                task = userTasks.get(i - 1);
                isDone = task.isDone() ? 1 : 0;
                if (task.getClass() == Todo.class) {
                    file.write(String.format("T | %d | %s\n",
                            isDone, task.getTaskDescription()));
                } else if (task.getClass() == Deadline.class) {
                    file.write(String.format("D | %d | %s | %s\n",
                            isDone, task.getTaskDescription(), ((Deadline) task).getBy()));
                } else if (task.getClass() == Event.class) {
                    file.write(String.format("E | %d | %s | %s\n",
                            isDone, task.getTaskDescription(), ((Event) task).getEventAt()));
                }
            }

            file.close();
        } catch (IOException e) {
            System.out.println("Something happened with the file creation...");
        }
    }
}
