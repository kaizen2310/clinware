package com.example.weather.client;

import com.example.weather.model.WeatherResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * REST Client for Open-Meteo API.
 * Documentation: https://open-meteo.com/en/docs
 */
@RegisterRestClient(configKey = "weather-api")
public interface WeatherServiceClient {

    /**
     * TODO: TASK 1 - Implement the REST client method to fetch current weather.
     * The endpoint is "/v1/forecast" and it requires:
     * - latitude (query param)
     * - longitude (query param)
     * - current_weather (query param, should be true)
     * 
     * Hint: Use @GET, @Path, and @QueryParam annotations.
     */
    @GET
    @Path("/v1/forecast")
    WeatherResponse getCurrentWeather(
        @QueryParam("latitude") double lat,
        @QueryParam("longitude") double lon,
        @QueryParam("current_weather") boolean currentWeather
    );
}
