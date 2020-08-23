import java.util.Scanner;

public class Duke {
    public static void listUserTasks(Task[] userTasks, int taskCount) {
        System.out.println("\tHere are your tasks:");
        for (int i = 1; i <= taskCount; i++) {
            System.out.format("\t%d. %s\n", i, userTasks[i-1].printTaskAndIcon());
        }
    }

    public static void markTaskAsDone(Task[] userTasks, int taskNumber) {
        if (userTasks[taskNumber-1].isDone()) {
            System.out.println("\tThis task has already been marked as done.");
        } else {
            userTasks[taskNumber-1].markAsDone();
            System.out.println("\tI have marked the following task as done:");
            System.out.format("\t\t%s\n", userTasks[taskNumber-1].printTaskAndIcon());
        }
    }

    public static void main(String[] args) {
        String userInput;
        Task[] userTasks = new Task[100];
        int userTasksCount = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");

        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                listUserTasks(userTasks, userTasksCount);
            } else if (userInput.contains("done") && userInput.length() > 4) {
                int taskNumber = Integer.parseInt(userInput.substring(5));
                if (taskNumber > userTasksCount) {
                    System.out.println("\tTask does not exist!");
                } else {
                    markTaskAsDone(userTasks, taskNumber);
                }
            } else {
                Task userTask = new Task(userInput);
                userTasks[userTasksCount] = userTask;
                userTasksCount++;
                System.out.println("\tadded: " + userInput);
            }
            userInput = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
