import java.util.Scanner;

public class Duke {
    private static final int MAX_SIZE = 100;
    private static Task[] userTasks = new Task[MAX_SIZE];
    private static int userTasksCount = 0;

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");

        userInput = in.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                listUserTasks(userTasksCount);
            } else if (userInput.contains("done")) {
                int taskNumber = Integer.parseInt(userInput.substring(5));
                markTaskAsDone(taskNumber);
            } else {
                addUserTask(userInput);
            }
            userInput = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addUserTask(String task) {
        if (task.startsWith("todo")) {
            task = task.replace("todo", " ").trim();
            userTasks[userTasksCount] = new Todo(task);
        } else if (task.startsWith("deadline")) {
            task = task.replace("deadline", " ").trim();
            String[] taskSplit = task.split(" /by ");
            userTasks[userTasksCount] = new Deadline(taskSplit[0], taskSplit[1]);
        } else if (task.startsWith("event")) {
            task = task.replace("event", " ").trim();
            String[] taskSplit = task.split(" /at ");
            userTasks[userTasksCount] = new Event(taskSplit[0], taskSplit[1]);
        } else {
            return;
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
