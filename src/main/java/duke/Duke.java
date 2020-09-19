package duke;

import duke.command.Command;
import duke.exception.IllegalCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    private static final String COMMAND_BYE = "bye";

    private static Parser parser;
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public static void main(String[] args) {
        String userInput;
        Command command;

        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();

        ui.printWelcomeMessage();

        try {
            storage = new Storage(tasks);
        } catch (IOException e) {
            ui.printToUser("\tError: The file cannot be opened or created!");
        }

        do {
            userInput = ui.getUserInput();
            try {
                command = parser.parseUserInput(userInput);
                command.execute(tasks);
            } catch (IllegalCommandException e) {
                ui.printToUser("\tWrong command entered!");
            } catch (StringIndexOutOfBoundsException e) {
                ui.printToUser("\tDescription cannot be empty!");
            } catch (NullPointerException | NumberFormatException e) {
                ui.printToUser("\tInvalid task number entered!");
            }
        } while (!userInput.equals(COMMAND_BYE));

        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            ui.printToUser("\tError: The file cannot be written to!");
        }

        ui.printExitMessage();
    }
}
