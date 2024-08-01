package nl.xx1.openweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a joke response from the Chuck Norris API.
 * This class uses Jackson annotations for JSON parsing and Lombok annotations
 * for automatic getter and setter generation.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JokeResponse {

    /**
     * The URL of the icon associated with the joke.
     */
    @JsonProperty("icon_url")
    private String iconUrl;

    /**
     * The unique identifier of the joke.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The text content of the joke.
     */
    @JsonProperty("value")
    private String joke;
}
