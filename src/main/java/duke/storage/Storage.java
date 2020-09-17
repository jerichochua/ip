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
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "duke.txt";

    public Storage() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void readFile(TaskList tasks) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        String task, typeOfTask;
        String[] arguments;

        Scanner readFile = new Scanner(file);
        while (readFile.hasNext()) {
            task = readFile.nextLine();
            arguments = task.split(" \\| ");
            typeOfTask = arguments[0];

            switch (typeOfTask) {
            case "T":
                tasks.addTask(new Todo(arguments[2]));
                break;
            case "D":
                tasks.addTask(new Deadline(arguments[2], arguments[3]));
                break;
            case "E":
                tasks.addTask(new Event(arguments[2], arguments[3]));
                break;
            }

            tasks.checkTaskStatus(arguments[1]);
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
