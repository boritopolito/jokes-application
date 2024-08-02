import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import nl.xx1.openweb.dto.JokeResponse;
import nl.xx1.openweb.service.ChuckNorrisJokeGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ChuckNorrisJokeGeneratorTest {
    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<String> httpResponse;

    @InjectMocks
    private ChuckNorrisJokeGenerator chuckNorrisJokeGenerator;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        try {
            // We replace the httpClient with our mocked httpClient.
            Field httpClientField = ChuckNorrisJokeGenerator.class.getDeclaredField("httpClient");
            httpClientField.setAccessible(true);
            httpClientField.set(chuckNorrisJokeGenerator, httpClient);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldReturnSameInstance() {
        ChuckNorrisJokeGenerator instance1 = ChuckNorrisJokeGenerator.getInstance();
        ChuckNorrisJokeGenerator instance2 = ChuckNorrisJokeGenerator.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void shouldReturnJoke() throws IOException, InterruptedException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final JokeResponse mockedJoke = new JokeResponse();
        mockedJoke.setJoke("Een hond geboren in een kippenhok is geen kip.");
        String jsonResponse = objectMapper.writeValueAsString(mockedJoke);

        when(httpResponse.body()).thenReturn(jsonResponse);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);

        JokeResponse responseJoke = chuckNorrisJokeGenerator.getRandomJoke();
        assertEquals(responseJoke, mockedJoke);
    }
}
