import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");

        do {
            userInput = in.nextLine();
            System.out.println("\t" + userInput);
        } while (!userInput.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
    }
}
