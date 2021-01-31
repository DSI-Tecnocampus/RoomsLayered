package cat.tecnocampus.rooms.application;

import cat.tecnocampus.rooms.application.dtos.WeatherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class WeatherController {
    private final RestTemplate restTemplate;
    private final String REQUEST_URI = "https://api.openweathermap.org/data/2.5/forecast?units=metric&q={city name}&appid={API key}";

    @Value("${weather.apiKey}") //getting the value form application.properties
    private String APIkey;


    public WeatherController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherDTO getWeather(String cityName) {
        String jsonString = restTemplate.getForObject(REQUEST_URI, String.class, cityName, APIkey);
        try {
            return createWeatherDTOfromJsonString(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private WeatherDTO createWeatherDTOfromJsonString(String jsonString) throws JsonProcessingException, JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonString);
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setCityName(node.path("city").path("name").asText());

        Stream<JsonNode> elementStream = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(node.path("list").elements(), Spliterator.ORDERED),
                false);
        var forecasts = elementStream.map(this::element2weatherForecastDTO).collect(Collectors.toList());
        weatherDTO.setForecasts(forecasts);
        return weatherDTO;
    }

    private WeatherDTO.WeatherForecastDTO element2weatherForecastDTO(JsonNode n) {
        var forecast = new WeatherDTO.WeatherForecastDTO();
        forecast.setTemperature(n.path("main").path("temp").asLong());
        forecast.setDescription(n.path("weather").get(0).path("description").asText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        forecast.setDateTime(LocalDateTime.parse(n.path("dt_txt").asText(), formatter));
        return forecast;
    }
}
