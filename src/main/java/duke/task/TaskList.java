package duke.task;

import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidIndexException;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * represents the task list and deals with operations to alter the task list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * class constructor if no array list of tasks is given
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * class constructor if an array list of tasks is given
     * @param tasks the array list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * returns the number of tasks in the task list as an integer
     * @return the number of tasks in the task list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * marks the given task as done based on its task number
     * @param taskNumber the index of the task to be marked as done
     * @return the task after it has been marked as done
     * @throws InvalidIndexException if the given index of the task does not exist
     */
    public Task done(int taskNumber) throws InvalidIndexException {
        Task task = getTaskNumber(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    /**
     * deletes the given task based on its task number
     * @param taskNumber the index of the task to be deleted
     * @return the task that was deleted
     * @throws InvalidIndexException if the given index of the task does not exist
     */
    public Task delete(int taskNumber) throws InvalidIndexException {
        Task task = getTaskNumber(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        return task;
    }

    public ToDo addToDo(String input) throws EmptyDescriptionException {
        if (input.length() <= 5) {
            throw new EmptyDescriptionException("oops! the description of a todo cannot be empty");
        }
        ToDo todo = new ToDo(input.substring(5));
        tasks.add(todo);
        return todo;
    }

    public Task addDeadline(String input) throws EmptyDescriptionException, EmptyDateException {
        if (input.length() <= 9) {
            throw new EmptyDescriptionException("oops! the description of a deadline cannot be empty");
        } else if (!input.contains("/")) {
            throw new EmptyDateException("oops! the due date for the deadline was not specified");
        }
        Deadline deadline = new Deadline(input.substring(9));
        tasks.add(deadline);
        return deadline;
    }

    public Task addEvent(String input) throws EmptyDescriptionException, EmptyDateException {
        if (input.length() <= 6) {
            throw new EmptyDescriptionException("oops! the description of a event cannot be empty");
        } else if (!input.contains("/")) {
            throw new EmptyDateException("oops! the event date for the event was not specified");
        }
        Event event = new Event(input.substring(6));
        tasks.add(event);
        return event;
    }

    /**
     * returns the list of tasks that matches the given query string
     * @param queryString the given query to search for in the task list
     * @return an array list containing the tasks that match the query string
     */
    public ArrayList<Task> getMatchingTasks(String queryString) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        tasks.forEach(task -> {
            if (task.toString().contains(queryString)) {
                matchingTasks.add(task);
            }
        });
        return matchingTasks;
    }

    public ArrayList<Task> getTasksOnDate(LocalDate date) {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDate() != null) {
                if (task.getDate().equals(date)) {
                    tasksOnDate.add(task);
                }
            }
        }
        return tasksOnDate;
    }

    /**
     * returns the tasks stored in the task list in the form of an array list
     * @return the array list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * deletes all of the tasks in the list
     */
    public void deleteAll() {
        tasks.clear();
    }

    private Task getTaskNumber(int taskNumber) throws InvalidIndexException {
        try {
            return this.tasks.get(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            String message = "sorry! the task number: " +
                    (taskNumber + 1) +
                    " does not exist in your list";
            throw new InvalidIndexException(message);
        }
    }
}
