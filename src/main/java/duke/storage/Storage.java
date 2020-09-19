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
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "duke.txt";

    public Storage() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public ArrayList<Task> readFile() throws FileNotFoundException {
        ArrayList<Task> userTasks = new ArrayList<>(TaskList.MAX_SIZE);

        File file = new File(FILE_PATH);
        String task, typeOfTask, status, description, dateTime;
        String[] arguments;
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
                dateTime = arguments[3];
                Task deadline = new Deadline(description, dateTime);
                setTaskStatus(deadline, status);
                userTasks.add(deadline);
                break;
            case "E":
                dateTime = arguments[3];
                Task event = new Event(description, dateTime);
                setTaskStatus(event, status);
                userTasks.add(event);
                break;
            }
        }

        return userTasks;
    }

    private void setTaskStatus(Task task, String status) {
        if (status.equals("1")) {
            task.markAsDone();
        }
    }

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
                        isDone, task.getTaskDescription(), ((Deadline) task).getBy()));
            } else if (task.getClass() == Event.class) {
                file.write(String.format("E | %d | %s | %s\n",
                        isDone, task.getTaskDescription(), ((Event) task).getEventAt()));
            }
        }

        file.close();
    }
}
