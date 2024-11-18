package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String DEFAULT_FILE_NAME = "output.txt";
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private File currentFile;

    /**
     * Initialize a new Controller with the default path.
     */
    public Controller() {
        this(USER_HOME + FILE_SEPARATOR + DEFAULT_FILE_NAME);
    }

    /**
     * Initialize a new Controller with the passed path.
     * @param path The path to set the current file
     */
    public Controller(final String path) {
        this.setFile(path);
    }

    /**
     * Method to return the current file of the Controller.
     * @return The current file
     */
    public File getFile() {
        return this.currentFile;
    }

    /**
     * Set the current file to the passed path.
     * @param path The path to set the current file
     */
    public final void setFile(final String path) {
        Objects.requireNonNull(path, "Null string passed");
        if (path.isBlank()) {
            throw new IllegalArgumentException("Passed empty path");
        }
        this.currentFile = new File(path);
    }

    /**
     * Method to return the path of the current file of the controller.
     * @return The path of the current file
     */
    public String getFilePath() {
        return this.currentFile.getAbsolutePath();
    }

    /**
     * Save the passed text in the current file.
     * @param inputText The text to write in the current file
     * @throws IOException If an I/O error occurs while operating with files
     */
    public void writeToFile(final String inputText) throws IOException {
        Objects.requireNonNull(inputText, "Null string passed");
        try (PrintStream ps = new PrintStream(currentFile, StandardCharsets.UTF_8)) {
            ps.print(inputText);
        }
    }
}
