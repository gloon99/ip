package duke.command;

import duke.exception.DukeFileLoadingErrorException;
import duke.message.Message;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.exception.InvalidIndexException;
import duke.task.Task;

/**
 * represents a command to deleted the specified task
 */
public class DeleteCommand extends Command {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    /**
     * deletes the task specified by the user and reflect this change in the storage.
     * finally, the method returns a message indicating that the operation was successful
     * @param tasks the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating the deletion of the task was successful
     * @throws InvalidIndexException if the given task number does not exist in the list
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws InvalidIndexException, DukeFileLoadingErrorException {
        int taskNumber = Integer.parseInt(fullCommand.substring(7));
        Task task = tasks.delete(taskNumber);
        storage.save(tasks.getTasks());
        return new CommandResult(Message.deletedTaskMessage(task, tasks));
    }
}
