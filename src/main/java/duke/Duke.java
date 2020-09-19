package duke;

import duke.command.Command;
import duke.exception.IllegalCommandException;
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

    public static void main(String[] args) {
        String userInput;
        Command command;
        boolean isExit = false;

        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();

        ui.printWelcomeMessage();

        try {
            storage = new Storage(tasks);
        } catch (IOException e) {
            ui.printToUser("\tError: The file cannot be opened or created!");
        }

        while (!isExit) {
            try {
                userInput = ui.getUserInput();
                command = parser.parseUserInput(userInput);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (IllegalCommandException e) {
                ui.printToUser("\tWrong command entered!");
            } catch (StringIndexOutOfBoundsException e) {
                ui.printToUser("\tDescription cannot be empty!");
            } catch (NullPointerException | NumberFormatException e) {
                ui.printToUser("\tInvalid task number entered!");
            }
        }

        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            ui.printToUser("\tError: The file cannot be written to!");
        }

        ui.printExitMessage();
    }
}
