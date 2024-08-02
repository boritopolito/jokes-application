package nl.xx1.openweb;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.*;
import nl.xx1.openweb.gui.JokesWindow;

/** Main application class for the Chuck Norris Jokes application. */
public class JokesApplication {
    /**
     * The main entry point for the application. Sets up the Look and Feel and initializes the main window.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatIntelliJLaf.setup();
            new JokesWindow();
        });
    }
}
