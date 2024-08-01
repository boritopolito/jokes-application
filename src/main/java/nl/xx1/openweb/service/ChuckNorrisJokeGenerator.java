package nl.xx1.openweb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import nl.xx1.openweb.dto.JokeResponse;

/**
 * This class is responsible for fetching random Chuck Norris jokes from an API. It follows the Singleton pattern to
 * ensure only one instance is created.
 */
public class ChuckNorrisJokeGenerator {
    private static final ChuckNorrisJokeGenerator INSTANCE = new ChuckNorrisJokeGenerator();
    private static final String API_URL = "https://api.chucknorris.io/jokes/random";

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    /** Private constructor to enforce the Singleton pattern. Initializes the ObjectMapper and HttpClient. */
    private ChuckNorrisJokeGenerator() {
        objectMapper = new ObjectMapper();
        httpClient = HttpClient.newHttpClient();
    }

    /**
     * Gets the singleton instance of ChuckNorrisJokeGenerator.
     *
     * @return The singleton instance of ChuckNorrisJokeGenerator.
     */
    public static ChuckNorrisJokeGenerator getInstance() {
        return INSTANCE;
    }

    /**
     * Fetches a random joke from the Chuck Norris API.
     *
     * @return A JokeResponse object containing the fetched joke.
     * @throws RuntimeException if there's an error while fetching the joke.
     */
    public JokeResponse getRandomJoke() {
        final HttpRequest request =
                HttpRequest.newBuilder().uri(URI.create(API_URL)).build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), JokeResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while getting random joke.", e);
        }
    }
}
