package duke.exception;

/**
 * represents an exception that is thrown when the index of a task does not exist
 */
public class DukeInvalidIndexException extends DukeException {
    /**
     * constructs a new invalid index exception with the specified detail message.
     * @param message the detail message.
     *                the message is saved for later retrieval by the <code>Throwable.getMessage()</> method
     */
    public DukeInvalidIndexException(String message) {
        super(message);
    }
}
