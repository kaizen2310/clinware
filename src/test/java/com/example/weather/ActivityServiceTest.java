package com.example.weather;

import com.example.weather.client.WeatherServiceClient;
import com.example.weather.model.WeatherResponse;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ActivityServiceTest {

    @Inject
    ActivityService activityService;

    @InjectMock
    @RestClient
    WeatherServiceClient weatherServiceClient;

    @Test
    public void testBeachRecommendation() {
        // Setup mock response
        WeatherResponse.CurrentWeather weather = new WeatherResponse.CurrentWeather(30.0, 0);
        WeatherResponse response = new WeatherResponse(weather);
        
        Mockito.when(weatherServiceClient.getCurrentWeather(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyBoolean()))
               .thenReturn(response);

        String recommendation = activityService.getRecommendation(0, 0);
        assertTrue(recommendation.contains("beach"), "Should recommend beach for hot clear weather");
    }

    @Test
    public void testMuseumRecommendation() {
        WeatherResponse.CurrentWeather weather = new WeatherResponse.CurrentWeather(10.0, 0);
        WeatherResponse response = new WeatherResponse(weather);
        
        Mockito.when(weatherServiceClient.getCurrentWeather(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyBoolean()))
               .thenReturn(response);

        String recommendation = activityService.getRecommendation(0, 0);
        assertTrue(recommendation.contains("museum"), "Should recommend museum for cold weather");
    }

    @Test
    public void testStayHomeRecommendation() {
        WeatherResponse.CurrentWeather weather = new WeatherResponse.CurrentWeather(20.0, 51);
        WeatherResponse response = new WeatherResponse(weather);
        
        Mockito.when(weatherServiceClient.getCurrentWeather(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyBoolean()))
               .thenReturn(response);

        String recommendation = activityService.getRecommendation(0, 0);
        assertTrue(recommendation.contains("Stay home"), "Should recommend staying home for rain/snow");
    }

    @Test
    public void testAnomalyLogicOddWeather() {
        // Temp 20, Code 1 (Odd). Base = 20*0.8 + 1*0.2 = 16.2 -> (int)16
        // 16 XOR 15 (0x0F) = 31.
        // 31 is prime. Twin prime check: 31-2=29 (prime) OR 31+2=33 (not prime).
        // Since 29 is prime, 31 is a twin prime. Recommendation should be reversed.
        String original = "Go for a hike";
        String result = activityService.applyAnomalyLogic(20.0, 1, original);
        assertEquals(new StringBuilder(original).reverse().toString(), result);
    }
}
