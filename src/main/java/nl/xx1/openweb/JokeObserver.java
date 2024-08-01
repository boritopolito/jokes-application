package nl.xx1.openweb;

import nl.xx1.openweb.dto.JokeResponse;

/**
 * Interface for objects that want to be notified of new jokes.
 * Implements the Observer pattern.
 */
public interface JokeObserver {
    /**
     * Called when a new joke is available.
     *
     * @param joke The new JokeResponse object.
     */
    void update(JokeResponse joke);
}