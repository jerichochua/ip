package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class TodoCommand extends Command {
    public static final String COMMAND_TODO = "todo";
    private final String arguments;

    public TodoCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task todo = tasks.addTodo(arguments);
            ui.printTaskAdded(todo);
            ui.printRemainingTasks(tasks);
        } catch (EmptyDescriptionException e) {
            ui.printToUser("\tDescription cannot be empty!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
