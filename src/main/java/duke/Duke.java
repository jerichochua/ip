package duke;

import duke.exception.DukeException;
import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {
    private static final int MAX_SIZE = 100;
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static Task[] userTasks = new Task[MAX_SIZE];
    private static int userTasksCount = 0;

    public static void main(String[] args) throws DukeException {
        String userInput;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        userInput = in.nextLine();

        while (!userInput.equals(COMMAND_BYE)) {
            processUserInput(userInput);
            userInput = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void processUserInput(String userInput) throws DukeException {
        if (userInput.equals(COMMAND_LIST)) {
            listUserTasks(userTasksCount);
        } else if (userInput.contains(COMMAND_DONE)) {
            int taskNumber = Integer.parseInt(userInput.substring(5));
            markTaskAsDone(taskNumber);
        } else {
            addUserTask(userInput);
        }
    }

    public static void addUserTask(String task) throws DukeException {
        if (task.startsWith(COMMAND_TODO)) {
            task = task.replace("todo", " ").trim();
            if (task.length() == 0) {
                throw new DukeException("No description entered");
            }
            userTasks[userTasksCount] = new Todo(task);
        } else if (task.startsWith(COMMAND_DEADLINE)) {
            task = task.replace("deadline", " ").trim();
            String[] taskSplit = task.split(" /by ");
            try {
                userTasks[userTasksCount] = new Deadline(taskSplit[0], taskSplit[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\tDescription or deadline cannot be empty!");
                return;
            }
        } else if (task.startsWith(COMMAND_EVENT)) {
            task = task.replace("event", " ").trim();
            String[] taskSplit = task.split(" /at ");
            try {
                userTasks[userTasksCount] = new Event(taskSplit[0], taskSplit[1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\tDescription or event date/time cannot be empty!");
                return;
            }
        } else {
            throw new IllegalCommandException("Illegal command entered");
        }

        System.out.println("\tadded: " + userTasks[userTasksCount]);
        userTasksCount++;
        String addS = (userTasksCount > 1) ? "s" : "";
        System.out.println("\tYou now have " + userTasksCount + " task" + addS + " in your list.");
    }

    public static void listUserTasks(int taskCount) {
        System.out.println("\tHere are your tasks:");
        for (int i = 1; i <= taskCount; i++) {
            System.out.format("\t%d. %s\n", i, userTasks[i - 1]);
        }
    }

    public static void markTaskAsDone(int taskNumber) {
        userTasks[taskNumber - 1].markAsDone();
        System.out.println("\tI have marked the following task as done:");
        System.out.format("\t\t%s\n", userTasks[taskNumber - 1]);
    }
}
