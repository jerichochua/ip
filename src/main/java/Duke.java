import java.util.Scanner;

public class Duke {
    public static void listUserTasks(Task[] userTasks, int taskCount) {
        System.out.println("\tHere are your tasks:");
        for (int i = 1; i <= taskCount; i++) {
            System.out.println("\t" + i + ". " + userTasks[i-1].getTaskDescription());
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
