package atlas.exceptions;

/**
 * Represents an exception thrown when the data.txt file is corrupted
 */
public class CorruptedFileException extends Exception {
    public CorruptedFileException() {
        super("The input file is corrupted. Please restart and try again. ");
    }
}
