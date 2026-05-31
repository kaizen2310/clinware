package com.example.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherResponse(
    @JsonProperty("current_weather") CurrentWeather currentWeather
) {
    public record CurrentWeather(
        double temperature,
        @JsonProperty("weathercode") int weatherCode
    ) {}
}
