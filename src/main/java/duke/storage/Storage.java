package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading of tasks from a file and saving of tasks to a file.
 */
public class Storage {
    private static final String FILE_PATH = "duke.txt";

    /**
     * Creates a new file if it does not exist.
     *
     * @throws IOException if an I/O error has occurred
     */
    public Storage() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Reads the tasks from a file and returns an ArrayList of Task objects.
     *
     * @return an ArrayList of Task objects
     * @throws FileNotFoundException if the file is not found
     */
    public ArrayList<Task> readFile() throws FileNotFoundException {
        ArrayList<Task> userTasks = new ArrayList<>(TaskList.MAX_SIZE);

        File file = new File(FILE_PATH);
        String task, typeOfTask, status, description;
        String[] arguments;
        LocalDateTime dateTime;
        Scanner readFile = new Scanner(file);

        while (readFile.hasNext()) {
            task = readFile.nextLine();
            arguments = task.split(" \\| ");

            typeOfTask = arguments[0];
            status = arguments[1];
            description = arguments[2];

            switch (typeOfTask) {
            case "T":
                Task todo = new Todo(description);
                setTaskStatus(todo, status);
                userTasks.add(todo);
                break;
            case "D":
                dateTime = LocalDateTime.parse(arguments[3]);
                Task deadline = new Deadline(description, dateTime);
                setTaskStatus(deadline, status);
                userTasks.add(deadline);
                break;
            case "E":
                dateTime = LocalDateTime.parse(arguments[3]);
                Task event = new Event(description, dateTime);
                setTaskStatus(event, status);
                userTasks.add(event);
                break;
            }
        }

        return userTasks;
    }

    /**
     * Checks if a task from the file is marked as done, and mark it as done in the object.
     *
     * @param task the task from the file
     * @param status the status of the task from the file
     */
    private void setTaskStatus(Task task, String status) {
        if (status.equals("1")) {
            task.markAsDone();
        }
    }

    /**
     * Writes a given task list to a file.
     *
     * @param tasks the task list to be written to the file
     * @throws IOException if an I/O error has occurred
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter file = new FileWriter(FILE_PATH);
        Task task;
        int isDone;

        for (int i = 0; i < tasks.getUserTasksCount(); i++) {
            task = tasks.getTask(i);
            isDone = task.isDone() ? 1 : 0;
            if (task.getClass() == Todo.class) {
                file.write(String.format("T | %d | %s\n",
                        isDone, task.getTaskDescription()));
            } else if (task.getClass() == Deadline.class) {
                file.write(String.format("D | %d | %s | %s\n",
                        isDone, task.getTaskDescription(), ((Deadline) task).getDueDate()));
            } else if (task.getClass() == Event.class) {
                file.write(String.format("E | %d | %s | %s\n",
                        isDone, task.getTaskDescription(), ((Event) task).getEventAt()));
            }
        }

        file.close();
    }
}
