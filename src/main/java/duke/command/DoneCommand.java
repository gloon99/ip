package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidIndexException;
import duke.task.Task;

/**
 * represents a command to mark the specified task as done
 */
public class DoneCommand extends Command {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public DoneCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    /**
     * marks the specified task as done and reflect this change in the storage.
     * finally, the method returns a message indicating that the operation was successful
     * @param tasks the list of tasks
     * @param ui the user interface object responsible for system related commands
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating task was successfully marked as done
     * @throws InvalidIndexException if the given task number does not exist in the list
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        int taskNumber = Integer.parseInt(fullCommand.substring(5));

        Task task = tasks.done(taskNumber);

        storage.save(tasks);
        return doneTaskMessage(task, tasks);
    }

    private String doneTaskMessage(Task deletedTask, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("yay! i have marked this task as done: \n    ")
                .append(deletedTask).append("\n")
                .append(tasks.numberOfTasks());
        return sb.toString();
    }
}
