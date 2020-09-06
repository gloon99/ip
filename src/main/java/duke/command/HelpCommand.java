package duke.command;

import duke.message.Message;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * represents a command to display the possible commands
 */
public class HelpCommand extends Command {

    /**
     * class constructor
     */
    public HelpCommand() {
        super("help");
        this.isExit = false;
    }

    /**
     * returns the list of commands available to the user
     * @param tasks the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return the list of commands available to the user
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(Message.helpMessage());
    }
}
