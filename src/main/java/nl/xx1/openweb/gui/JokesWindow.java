package nl.xx1.openweb.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import nl.xx1.openweb.JokeObserver;
import nl.xx1.openweb.controller.JokeController;
import nl.xx1.openweb.dto.JokeResponse;

public class JokesWindow extends JFrame implements JokeObserver {
    private final JTextArea jokeArea;
    private final JokeController jokeController;

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

    private void onButtonClicked(ActionEvent e) {
        jokeController.fetchJoke();
    }

    @Override
    public void update(JokeResponse joke) {
        jokeArea.setText(joke.getJoke());
    }
}
