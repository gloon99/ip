package duke.task;

import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * represents a deadline task
 */

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String taskDescription) throws EmptyDescriptionException, EmptyDateException {
        if (taskDescription.length() <= 9) {
            throw new EmptyDescriptionException("oops! the description of a deadline cannot be empty");
        } else if (!taskDescription.contains("/")) {
            throw new EmptyDateException("oops! the date for the deadline was not specified");
        } else {
            int space = taskDescription.indexOf(" ");
            int slash = taskDescription.indexOf("/");

            this.task = taskDescription.substring(space + 1, slash);
            this.deadline = taskDescription.substring(slash + 4);
            this.done = false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        String deadline;
        try {
            LocalDate localDate = LocalDate.parse(this.deadline);
            deadline = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            deadline = this.deadline;
        }

        sb.append("[D]")
                .append(super.toString())
                .append("(by: ").append(deadline).append(")");
        return sb.toString();
    }

    @Override
    public String encode() {
        StringBuilder encodedTask = new StringBuilder();

        encodedTask.append("D | ")
                .append(this.isDoneInt() + " | ")
                .append(this.task + "| ")
                .append(this.deadline);

        return encodedTask.toString();
    }

    public static Deadline decode(String string) throws EmptyDescriptionException, EmptyDateException {
        String[] split = string.split(" \\| ");

        String taskDescription = "deadline " + split[2] + " /by " + split[3];

        Deadline deadline = new Deadline(taskDescription);

        if (split[1].contains("1")) {
            deadline.markAsDone();
        }

        return deadline;
    }
}