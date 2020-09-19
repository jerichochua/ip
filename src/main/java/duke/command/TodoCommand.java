package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.tasklist.TaskList;

public class TodoCommand extends Command {
    public static final String COMMAND_TODO = "todo";
    private final String arguments;

    public TodoCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks) {
        try {
            tasks.addTodo(arguments);
        } catch (EmptyDescriptionException e) {
            System.out.println("\tDescription cannot be empty!");
        }
    }
}
