package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An implementation of the {@link Controller} interface.
 *
 */
public final class SimpleController implements Controller {
    private String nextString;
    private final List<String> history = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextString(final String next) {
        Objects.requireNonNull(next, "Passed null string");
        this.nextString = next;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNextString() {
        return this.nextString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print() {
        if (nextString == null) {
            throw new IllegalStateException("There's no string to print in the buffer");
        }
        history.add(nextString);
        System.out.println(nextString); //NOPMD: Printing in the stout is the purpouse of the exercise
        nextString = null;
    }
}
