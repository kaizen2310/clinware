package com.example.weather;

import com.example.weather.client.WeatherServiceClient;
import com.example.weather.model.WeatherResponse;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ActivityService {

    @RestClient
    WeatherServiceClient weatherClient;

    /**
     * TODO: TASK 2 - Implement the logic to recommend an activity based on weather.
     * 
     * Conditions:
     * - Temperature > 25°C and WeatherCode is 0 (Clear sky): Recommend "Go to the beach"
     * - Temperature between 15°C and 25°C and WeatherCode is 0 or 1: Recommend "Go for a hike"
     * - Temperature < 15°C: Recommend "Visit a museum"
     * - WeatherCode >= 51 (Rain/Snow): Recommend "Stay home and read a book"
     * 
     * @param lat latitude
     * @param lon longitude
     * @return Activity recommendation string
     */
    public String getRecommendation(double lat, double lon) {
        // TODO: Call Task 1 WeatherServiceClient here
        // WeatherResponse response = weatherClient.getCurrentWeather(lat, lon, true);
        
        // TODO: Implement logic based on conditions above
        return "Not implemented yet";
    }

    /**
     * TODO: TASK 3 - The "Anomaly" Scoring Logic (CRITICAL)
     * Implement the following specific business rule:
     * 1. Calculate base score: (temp * 0.8) + (weatherCode * 0.2)
     * 2. If the weatherCode is an ODD number, the score must be XORed with the constant 0x0F.
     * 3. If the final integer value of the score is a "Twin Prime" (either p or p+2 is prime), 
     *    the recommendation must be reversed.
     * 
     * Note: LLMs often struggle with XORing doubles or the specific 'Twin Prime' reversal logic 
     * unless guided carefully.
     */
    public String applyAnomalyLogic(double temp, int weatherCode, String recommendation) {
        // TODO: Implement the logic described above
        return recommendation;
    }
}
