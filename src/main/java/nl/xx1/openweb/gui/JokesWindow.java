package nl.xx1.openweb.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import nl.xx1.openweb.JokeObserver;
import nl.xx1.openweb.controller.JokeController;
import nl.xx1.openweb.dto.JokeResponse;

/**
 * JokesWindow is a GUI class that displays jokes and allows users to fetch new ones.
 * It implements the JokeObserver interface to receive updates when new jokes are fetched.
 */
public class JokesWindow extends JFrame implements JokeObserver {
    private final JTextArea jokeArea;
    private final JokeController jokeController;

    /**
     * Constructs a new JokesWindow.
     * Initializes the GUI components and sets up the joke controller.
     */
    public JokesWindow() {
        super("Openweb - Jokes Creator");
        jokeController = new JokeController();
        jokeController.addObserver(this);
        setSize(new Dimension(500, 500));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jokeArea = new JTextArea();
        jokeArea.setEditable(false);
        jokeArea.setWrapStyleWord(true);
        jokeArea.setLineWrap(true);
        add(new JScrollPane(jokeArea), BorderLayout.CENTER);

        final JButton jokeButton = new JButton("Get random joke");
        jokeButton.addActionListener(this::onButtonClicked);
        add(jokeButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Handles the button click event.
     * Triggers the joke controller to fetch a new joke.
     *
     * @param e The ActionEvent object representing the button click event
     */
    private void onButtonClicked(ActionEvent e) {
        jokeController.fetchJoke();
    }

    /**
     * Updates the joke display area with a new joke.
     * This method is called when a new joke is fetched.
     *
     * @param joke The JokeResponse object containing the new joke
     */
    @Override
    public void update(JokeResponse joke) {
        jokeArea.setText(joke.getJoke());
    }
}