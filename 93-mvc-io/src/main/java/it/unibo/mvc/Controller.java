package it.unibo.mvc;

import java.util.List;

/**
 * Models a simple controller responsible of I/O access.
 * It considers only the standard output, and it is able to print on it.
 */
public interface Controller {

    /**
     * A method for setting the next string to print.
     * Null values are not accepted, so an exception is produced.
     * @param next The next string to print
     * @throws NullPointerException When the string passed is null 
     */
    void setNextString(String next);

    /**
     * A method for getting the next string to print.
     * @return The next String to print
     */
    String getNextString();

    /**
     * A method for getting the history of the printed strings.
     * @return A List of the Strings printed
     */
    List<String> getHistory();

    /**
     * A method that prints the current string.
     * @throws IllegalStateException If the current string is unset
     */
    void print();

}
