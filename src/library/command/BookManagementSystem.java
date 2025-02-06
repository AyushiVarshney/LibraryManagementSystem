package library.command;

import java.util.ArrayList;
import java.util.List;

public class BookManagementSystem {
    private List<Command> commandHistory = new ArrayList<>();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.add(command);
    }
}
