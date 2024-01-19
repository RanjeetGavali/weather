package com.test.weather.webClient;

import com.test.weather.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class WeatherClientImpl implements WeatherClient {

    @Autowired
    private WebClient webClient;

    @Value("${weather.api.key}")
    private String weatherRapidApiKey;

    private static WebClientResponseException handleErrorResponse(ClientResponse clientResponse) {
        int statusCode = clientResponse.statusCode().value();
        System.err.println("Error Response - Status Code: " + statusCode);
        return new WebClientResponseException("Error response", statusCode, "Please try after some time ", null, null, null);
    }

    @Override
    public WeatherResponse getWeatherInfoByLocation(String city) throws WebClientResponseException {
        String endpoint = String.format("rapidapi/forecast/%s/summary/", city);
        return getWeatherResponse(endpoint);
    }

    @Override
    public WeatherResponse getHourlyWeatherInfoByLocation(String city) throws WebClientResponseException {
        String endpoint = String.format("rapidapi/forecast/%s/hourly/", city);
        return getWeatherResponse(endpoint);
    }

    private WeatherResponse getWeatherResponse(String endpoint) throws WebClientResponseException {

        return webClient.get()
                .uri(endpoint)
                .header(WeatherClient.RAPID_API_KEY_HEADER, weatherRapidApiKey)
                .header(WeatherClient.RAPID_API_HOST_HEADER, WeatherClient.RAPID_API_HOST_VALUE)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.error(handleErrorResponse(clientResponse)))
                .bodyToMono(WeatherResponse.class)
                .block();

    }
}
