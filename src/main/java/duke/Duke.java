package duke;

import duke.command.Command;
import duke.exception.IllegalCommandException;
import duke.exception.IllegalDescriptionException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    private static Parser parser;
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Sets up the required objects and loads the tasks from the file.
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();

        try {
            storage = new Storage();
            tasks = new TaskList(storage.readFile());
        } catch (IOException e) {
            ui.printFileLoadError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the application until the application terminates.
     */
    public void run() {
        boolean isExit = false;

        ui.printWelcomeMessage();

        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command command = parser.parseUserInput(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (StringIndexOutOfBoundsException | IllegalCommandException e) {
                ui.printToUser("\tWrong command entered!");
            } catch (NullPointerException | NumberFormatException e) {
                ui.printToUser("\tInvalid task number entered!");
            } catch (IllegalDescriptionException e) {
                ui.printToUser("\tDescription cannot contain '|'!");
            }
        }

        ui.printExitMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
