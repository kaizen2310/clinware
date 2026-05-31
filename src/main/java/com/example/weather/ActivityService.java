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
     * TASK 2 - Recommends an activity based on weather at the given coordinates.
     * Priority order matters: rain/snow check must come before temperature checks.
     */
    public String getRecommendation(double lat, double lon) {
        WeatherResponse response = weatherClient.getCurrentWeather(lat, lon, true);
        double temp = response.currentWeather().temperature();
        int weatherCode = response.currentWeather().weatherCode();

        String recommendation;

        if (weatherCode >= 51) {
            // Rain, snow, storms — highest priority
            recommendation = "Stay home and read a book";
        } else if (temp > 25 && weatherCode == 0) {
            // Hot + clear sky
            recommendation = "Go to the beach";
        } else if (temp >= 15 && temp <= 25 && (weatherCode == 0 || weatherCode == 1)) {
            // Mild + clear or mainly clear
            recommendation = "Go for a hike";
        } else if (temp < 15) {
            // Cold
            recommendation = "Visit a museum";
        } else {
            recommendation = "Enjoy your day";
        }

        return applyAnomalyLogic(temp, weatherCode, recommendation);
    }

    /**
     * TASK 3 - Anomaly Scoring Logic.
     *
     * Step 1: base score = (temp * 0.8) + (weatherCode * 0.2), truncated to int.
     * Step 2: If weatherCode is ODD, XOR the score with 0x0F (15).
     * Step 3: If the final score is a Twin Prime, reverse the recommendation string.
     *
     * Twin Prime: a number p where p is prime AND either (p-2) or (p+2) is also prime.
     *
     * Example (from test): temp=20, code=1 (odd)
     *   base = (20*0.8) + (1*0.2) = 16.2 → (int) = 16
     *   16 ^ 0x0F = 16 ^ 15 = 31
     *   31 is prime, 29 (31-2) is prime → twin prime → reverse
     */
    public String applyAnomalyLogic(double temp, int weatherCode, String recommendation) {
        // Step 1: Calculate base score and truncate to int (NOT Math.round)
        double base = (temp * 0.8) + (weatherCode * 0.2);
        int score = (int) base;

        // Step 2: XOR with 0x0F only if weatherCode is odd
        if (weatherCode % 2 != 0) {
            score = score ^ 0x0F;
        }

        // Step 3: Reverse the recommendation if score is a twin prime
        if (isTwinPrime(score)) {
            return new StringBuilder(recommendation).reverse().toString();
        }

        return recommendation;
    }

    /**
     * Returns true if n is prime.
     */
    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * Returns true if n is a twin prime.
     * A twin prime is a prime where either (n-2) or (n+2) is also prime.
     * The condition is OR — neighbor on EITHER side counts.
     */
    private boolean isTwinPrime(int n) {
        return isPrime(n) && (isPrime(n - 2) || isPrime(n + 2));
    }
}