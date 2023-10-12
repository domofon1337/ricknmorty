package ru.mirea.nizikov.rim;

import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RimClient {
    public static void main(String[] args) throws IOException {
        Retrofit client = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com")
                .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
                .build();

        RimService rimService = client.create(RimService.class);

        Response<DTO> response = rimService.getSeries(1).execute();

        if (response.isSuccessful()) {
            DTO dto = response.body();

            List<Episode> episodes = dto.getEpisodes().stream()
                    .sorted(Comparator.comparingInt(ep -> ep.getCharacters().size()))
                    .collect(Collectors.toList());

            if (!episodes.isEmpty()) {
                Episode episodeWithMostCharacters = episodes.get(episodes.size() - 1);
                System.out.println("Эпизод с наибольшим количеством персонажей:");
                System.out.println("Название: " + episodeWithMostCharacters.getName());
                System.out.println("Количество персонажей: " + episodeWithMostCharacters.getCharacters().size());
            } else {
                System.out.println("Эпизоды не найдены");
            }
        } else {
            System.out.println("Запрос завершился ошибкой: " + response.code());
        }
    }
}
