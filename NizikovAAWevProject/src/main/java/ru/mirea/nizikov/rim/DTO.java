package ru.mirea.nizikov.rim;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DTO {
    @JsonProperty("results")
    private List<Episode> episodes;

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
