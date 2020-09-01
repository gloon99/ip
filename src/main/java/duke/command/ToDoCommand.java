package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.task.ToDo;

public class ToDoCommand extends TaskCommand {
    public ToDoCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        ToDo todo = new ToDo(fullCommand);
        tasks.add(todo);
        storage.save(tasks);
        System.out.println(addedTaskMessage(todo, tasks));
    }
}