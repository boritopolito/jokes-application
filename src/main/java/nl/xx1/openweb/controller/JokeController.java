package nl.xx1.openweb.controller;

import java.util.ArrayList;
import java.util.List;
import nl.xx1.openweb.JokeObserver;
import nl.xx1.openweb.dto.JokeResponse;
import nl.xx1.openweb.service.ChuckNorrisJokeGenerator;

/** Controller class that manages joke fetching and notifies observers. Implements the Observer pattern. */
public class JokeController {
    private final ChuckNorrisJokeGenerator chuckNorrisJokeGenerator;
    private final List<JokeObserver> observers = new ArrayList<>();

    /** Constructor for JokeController. Initializes the ChuckNorrisJokeGenerator. */
    public JokeController() {
        this.chuckNorrisJokeGenerator = ChuckNorrisJokeGenerator.getInstance();
    }

    /** Fetches a new joke and notifies all observers. */
    public void fetchJoke() {
        notifyObservers(chuckNorrisJokeGenerator.getRandomJoke());
    }

    /**
     * Adds a new observer to be notified of jokes.
     *
     * @param observer The JokeObserver to be added.
     */
    public void addObserver(JokeObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all observers with a new joke.
     *
     * @param joke The JokeResponse to be sent to all observers.
     */
    private void notifyObservers(JokeResponse joke) {
        observers.forEach(e -> e.update(joke));
    }
}
